$(document).ready(function() {
    var currentPageNum = 1;

    // 페이지 로딩 시 마지막 페이지 로딩 시도
    if (typeof totalPages !== 'undefined' && totalPages > 0) {
        loadComments(postId, totalPages);
    } else {
        loadComments(postId, 1);
    }

    $("#submitCommentBtn").on("click", function() {
        if (!sessionId) {
            alert("로그인이 필요합니다.");
            return;
        }

        var commentContent = $("#commentContent").val().trim();
        if (commentContent === "") {
            alert("댓글 내용을 입력해주세요.");
            return;
        }

        $.ajax({
            url: "/TripPlanner/api/comments",
            method: "POST",
            dataType: "json",
            data: {
                postId: postId,
                id: sessionId,
                comments: commentContent
            },
            success: function(response) {
                if (response.message === "success") {
                    $("#commentContent").val("");
                    // 댓글 작성 후 마지막 페이지를 알 수 없으니 더블 로딩
                    doubleLoadLastPage();
                } else {
                    alert("댓글 작성에 실패했습니다. 다시 시도해주세요.");
                }
            },
            error: function() {
                alert("댓글 작성 중 오류가 발생했습니다.");
            }
        });
    });

    $(document).on("click", ".page-link", function(e) {
        e.preventDefault();
        var page = $(this).data("page");
        loadComments(postId, page);
    });

    // 댓글 삭제
    $(document).on("click", ".deleteBtn", function() {
        var c_unique = $(this).closest(".comment").data("id");
        if (!confirm("해당 댓글을 삭제하시겠습니까?")) {
            return;
        }

        $.ajax({
            url: "/TripPlanner/api/comments/" + c_unique,
            method: "DELETE",
            dataType: "json",
            success: function(res) {
                if (res.message === "deleted") {
                    // 삭제 후 페이지 수 변동 가능성 → 더블 로딩
                    doubleLoadLastPage();
                } else {
                    alert("댓글 삭제에 실패했습니다.");
                }
            },
            error: function() {
                alert("댓글 삭제 중 오류가 발생했습니다.");
            }
        });
    });

    // 댓글 좋아요
    $(document).on("click", ".likeBtn", function() {
        var c_unique = $(this).closest(".comment").data("id");
        $.ajax({
            url: "/TripPlanner/api/comments/" + c_unique + "/like",
            method: "POST",
            dataType: "json",
            success: function(res) {
                if (res.message === "liked") {
                    // 좋아요 후 다시 현재 페이지 로딩
                    loadComments(postId, currentPageNum);
                } else {
                    alert("좋아요 처리에 실패했습니다.");
                }
            },
            error: function() {
                alert("좋아요 처리 중 오류가 발생했습니다.");
            }
        });
    });

	$("#postLikeBtn").on("click", function() {
	        $.ajax({
	            url: contextPath + "/" + postId + "/like", // contextPath + postId 조합
	            method: "POST",
	            dataType: "json",
	            success: function(res) {
	                if (res.message === "liked") {
	                    $("#postLikes").text(res.likes);
	                } else {
	                    alert("포스트 좋아요 실패");
	                }
	            },
	            error: function() {
	                alert("포스트 좋아요 처리 중 오류 발생");
	            }
	        });
	    });
    function loadComments(postId, page, callback) {
        $.ajax({
            url: "/TripPlanner/api/comments",
            method: "GET",
            dataType: "json",
            data: { postId: postId, page: page },
            success: function(res) {
                renderComments(res.comments);
                renderPagination(res.currentPage, res.totalPage);
                totalPages = res.totalPage;
                currentPageNum = res.currentPage;

                if (typeof callback === 'function') {
                    callback(res);
                }
            },
            error: function() {
                alert("댓글 목록을 가져오는 중 오류가 발생했습니다.");
            }
        });
    }

    function renderComments(comments) {
        var section = $("#commentSection");
        section.empty();

        if (!comments || comments.length === 0) {
            section.append("<p>댓글이 없습니다.</p>");
            return;
        }

        $.each(comments, function(i, comment) {
            var html = "<div class='comment' data-id='" + comment.c_unique + "'>";
            html += "<p>" + comment.id + ": " + comment.comments + " (" + comment.commentDate + ")<br>Likes: " + comment.commentLikes + "</p>";
            html += "<button class='likeBtn'>좋아요</button> ";
            html += "<button class='deleteBtn'>삭제</button>";
            html += "</div>";
            section.append(html);
        });
    }

    function renderPagination(currentPage, totalPage) {
        var pagination = $("#pagination");
        pagination.empty();

        for (var i = 1; i <= totalPage; i++) {
            if (i === currentPage) {
                pagination.append("<span>" + i + "</span> ");
            } else {
                pagination.append("<a href='#' class='page-link' data-page='" + i + "'>" + i + "</a> ");
            }
        }
    }

    // 더블 로딩: 999999 페이지를 불러서 totalPage 최신화 후 다시 마지막 페이지 로딩
    function doubleLoadLastPage() {
        loadComments(postId, 999999, function(res) {
            // 응답에서 최신 totalPage 획득 후 다시 로딩
            loadComments(postId, res.totalPage);
        });
    }
});

