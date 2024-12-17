package com.spring.controller;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import com.spring.domain.Post;
import com.spring.service.*;

@Controller
public class postController {
	@Autowired
	postService postService;
	
	@GetMapping("/postform")
	public String postform() {
		return "postform";
		
		
	}
	@PostMapping("/postcreate")
	public String postcreate(@ModelAttribute Post post,HttpSession session) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		post.setId((String)session.getAttribute("userId"));
		post.setPublishDate(timestamp);
		postService.createPost(post);
		return "postview";
	}
	
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/postview")
    public String postview(@RequestParam int num,
                           Model model, HttpSession session) {
        Post onepost = postService.getPostById(num);
        
        // 총 댓글 수
        int commentCount = commentService.countCommentsByPostId(num);
        int pageSize = 10;
        int totalPages = (int) Math.ceil((double) commentCount / pageSize);

        String postdate = new DateFormatter().formatBoardDate(onepost.getPublishDate());
        model.addAttribute("onepost", onepost);
        model.addAttribute("postdate", postdate);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("postId", num);

        String userId = (String) session.getAttribute("userId");
        model.addAttribute("sessionUserId", userId);

        return "postview";
    }
    
    
    @PostMapping("/{postId}/like")
    public Map<String, Object> likePost(@PathVariable int postId) {
        postService.incrementPostLike(postId);
        Post updated = postService.getPostById(postId);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "liked");
        response.put("likes", updated.getLikes());
        return response;
    }
	
	@GetMapping("/postview/delete")
	public String postDelete(@RequestParam int num, Model model) {
	    postService.deletePost(num);
	    return "redirect:/Allboard?page=1";
	}

	@GetMapping("/postview/update")
	public String postupdate(@RequestParam int num,Model model) {
		Post result=(Post) postService.getPostById(num);
		model.addAttribute("result",result);
		return "updateForm";
	}
	
	@PostMapping("/postview/updatePost")
	public String postupdate(@RequestParam int num, @ModelAttribute Post post,Model model) {
		postService.updatePost(post,num);
		System.out.println(num);
		Post result=(Post) postService.getPostById(num);
		model.addAttribute("result",result);
		return "redirect:/postview?num="+num;
	}
	
}
