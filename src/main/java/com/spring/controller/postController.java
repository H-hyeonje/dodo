package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@GetMapping("/postcreate")
	public String postcreate(@ModelAttribute Post post) {
		return "postform";
	}
	
	@GetMapping("/postview")
	public String postview(@RequestParam int num, Model model) {
		DateFormatter DateFormatter=new DateFormatter();
		Post result=postService.getpost(num);
		String postdate= DateFormatter.formatPostDate(result.getPublishDate());
		
		model.addAttribute("postdate",postdate);
		model.addAttribute("onepost",result);
		return "postview";
	}
	
	@GetMapping("/postview/delete")
	public String postDelete(@RequestParam int num, Model model) {
	    postService.postdelet(num);
	    return "redirect:/Allboard?page=1";
	}

	@GetMapping("/postview/update")
	public String postupdate(@RequestParam int num,Model model) {
		Post result=postService.getpost(num);
		model.addAttribute("result",result);
		return "updateForm";
	}
	
	@PostMapping("/postview/updatePost")
	public String postupdate(@RequestParam int num, @ModelAttribute Post post,Model model) {
		postService.postupdate(post,num);
		System.out.println(num);
		Post result=postService.getpost(num);
		model.addAttribute("result",result);
		return "redirect:/postview?num="+num;
	}
	
}
