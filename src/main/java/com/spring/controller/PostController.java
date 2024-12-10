package com.spring.controller;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.domain.*;

import com.spring.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PostController {
	SimpleDateFormat daysFormat=new SimpleDateFormat("yy.MM.dd.HH:mm");
	SimpleDateFormat timesFormat =new SimpleDateFormat("HH:mm");
	zerotime zero =new zerotime();
	page Page=new page();

	
	@Autowired
	PostService postService;
	
	@GetMapping("/")
	public String indax() {
		
		return "index";
	}
	
	@GetMapping("/Post")
	public String postForm() {
		
		return "PostForm";
	}
	
	@PostMapping("/Postadd")
	public String addPost(@ModelAttribute Post post) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		post.setPublishDate(timestamp);
		postService.savePost(post);
		return "redirect:/PostAllBoard/1";
	}
	
	@GetMapping("/Postview/{p_unique}")
	public String Postviews(@PathVariable int p_unique,Model model) {
		Map<String, Object> onePost =postService.getPost(p_unique);//공개 여부 추가 boolean
		Post post=(Post) onePost.get("onePost");
		List<Comment> comments=(List<Comment>)onePost.get("comments");
		int commentnum=(int) onePost.get("commentnum");
		ArrayList<Integer> totalPage=Page.calculateTotalPages(commentnum);
		List<String> formattedDates = new ArrayList<String>();
	    long midnightCalendar =zero.zerotime();
	    for(int i=0;i<comments.size();i++) {
			 if (comments.get(i).getCommentDate().getTime()>=midnightCalendar ) {
				 String times=timesFormat .format(comments.get(i).getCommentDate());
				 formattedDates.add(times);   
			 }
			 else {
				 String days=daysFormat .format(comments.get(i).getCommentDate());
				 formattedDates.add(days); 
			 }
	    	
	    }
	    int tol = 0;
	    if (totalPage != null && !totalPage.isEmpty()) {  // totalPage가 null이 아니고 비어있지 않은지 확인
	        tol = totalPage.getLast();  // 정상적으로 getLast() 호출
	    } else {
	        // totalPage가 null이거나 비어있을 경우 기본값 설정
	        tol = 0;
	    }
		String publishDate=daysFormat .format(post.getPublishDate());
		model.addAttribute("tol",tol);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("formattedDates",formattedDates);
		model.addAttribute("comments",comments);
		model.addAttribute("publishDate",publishDate);
		model.addAttribute("Post",post);
		return "Postview";
	}

	
	
	@GetMapping("/PostAllBoard/{ps}")
	public String viewAllPosts(@PathVariable int ps, Model model) {
		Map<String, Object> result = postService.getAllPosts(ps);
		List<String> formattedDates = new ArrayList<String>();
		List<Post> allPosts=(List<Post>)result.get("posts");
		int totalPages =(Integer)result.get("totalPages");
		long midnightCalendar =zero.zerotime();
		for(Post post:allPosts) {

			 if (post.getPublishDate().getTime()>=midnightCalendar ) {
				 String times=timesFormat .format(post.getPublishDate());
				 formattedDates.add(times);   
			 }
			 else {
				 String days=daysFormat .format(post.getPublishDate());
				 formattedDates.add(days); 
			 }
			 }
		
		ArrayList<Integer> pageNumbers=Page.calculateTotalPages(totalPages);
		ArrayList<Integer> totalPagesList=Page.calculatePageNumbers(totalPages);
		
		model.addAttribute("pageNumbers",pageNumbers);
		model.addAttribute("totalPagesList", totalPagesList);
		model.addAttribute("formattedDates",formattedDates);
		model.addAttribute("allPosts",allPosts);
		return "PostAllBoard";
	}
	
	@GetMapping("/PostBoard/{ps}")
	public String postBoard(@RequestParam String id,@PathVariable int ps, Model model) {
		List<String> formattedBoardTimes= new ArrayList<String>();
		Map<String, Object> result=postService.getUserPosts(id,ps);
		List<Post> userPosts =(List<Post>) result.get("Board");
		int currentPageNumber =(int) result.get("pagenum");
		for(Post post : userPosts) {
			String timeDate=daysFormat .format(post.getPublishDate());
			formattedBoardTimes.add(timeDate);}
		
		
		ArrayList<Integer> totalPageNumbers =Page.calculateTotalPages(currentPageNumber);
		ArrayList<Integer> postPageNumbers=Page.calculatePageNumbers(currentPageNumber);
		Collections.reverse(totalPageNumbers);
		model.addAttribute("postPageNumbers",postPageNumbers);
		model.addAttribute("totalPageNumbers",totalPageNumbers);
		model.addAttribute("formattedBoardTimes",formattedBoardTimes);
		model.addAttribute("userId",userPosts);
		return "PostBoard";
	}
	
	@GetMapping("/PostupdatePage/{id}/{p_unique}")
	public String updatePage(@PathVariable String id,@PathVariable int p_unique, Model model) {
		//id 세션이랑 맞는지 확인하는거 추가
		Map<String,Object> Post=postService.getPost(p_unique);
		Post postToEdit=(Post) Post.get("onePost");
		String privacyStatus=null;
		if(postToEdit.getIsPrivate()==true) {
			privacyStatus="공개";
		}else {
			privacyStatus="비공개";

		}
		
		model.addAttribute("postToEdit", postToEdit);
		model.addAttribute("privacyStatus", privacyStatus);
		return "PostupdatePage";
	}
	
	@PostMapping("/PostUpdate")
	public String PostUpdate(@ModelAttribute Post updatedPost) {
		postService.updatePost(updatedPost);
		System.out.println(updatedPost.getP_unique());
		return "redirect:/Postview/"+updatedPost.getP_unique();
	}
	
	@GetMapping("/PostDelete/{id}/{p_unique}")
	private String PostDelete(@PathVariable String id,@PathVariable int p_unique) {
		postService.deletePost(p_unique);
		
		return "redirect:/PostAllBoard/1";
	}
	
	
	@GetMapping("/editor")
	public String getMethodName() {
		return "editor";
	}
	
	
}
