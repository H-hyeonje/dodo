package com.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.Post;
import com.spring.service.*;

@Controller
public class boradController {
	PaginationHelper paginationHelper=new PaginationHelper();
	
	@Autowired
	boradService boradService;
	
	@GetMapping("/Allboard")
	public String Allboard(@RequestParam int page, Model model) {
		DateFormatter DateFormatter=new DateFormatter();
		ArrayList<String> date=new ArrayList<String>();
		Map<String, Object> result=boradService.AllboardRead(page);
		List<Post> Allpost=(List<Post>) result.get("Allpost");
		int Allpostgetnum=(int)result.get("Allpostgetnum");
		ArrayList<Integer> getTotalPages=paginationHelper.getTotalPages(Allpostgetnum,10);
		ArrayList<Integer> getpostnumber=paginationHelper.getpostnumber(Allpostgetnum,page,10);
		for(int i=0;i<Allpost.size();i++) {
			String fommet=DateFormatter.formatBoardDate(Allpost.get(i).getPublishDate());
			date.add(fommet);
		}
		
		model.addAttribute("date",date);
		model.addAttribute("getTotalPages",getTotalPages);
		model.addAttribute("getpostnumber",getpostnumber);
		model.addAttribute("Allpost",Allpost);
		return "Allboard";
	}
	

	@GetMapping("/board/search")
	public String boardSearch() {
		
		return "borad";
	}
	
}
