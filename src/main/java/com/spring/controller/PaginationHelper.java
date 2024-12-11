package com.spring.controller;

import java.util.ArrayList;
import java.util.Collections;



public class PaginationHelper {
	
	public ArrayList<Integer> getTotalPages(int totalPosts, int pageSize) {
	  
	    int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
	    
	    ArrayList<Integer> pageList = new ArrayList<>();
	    
	    for (int i = 1; i <= totalPages; i++) {
	        pageList.add(i);
	    }
	    
	    return pageList;  
	}
	    
	   public ArrayList<Integer> getpostnumber(int totalPosts,int page,int pageSize){
		   ArrayList<Integer> postNumbers = new ArrayList<>();
		  
		   int startPost = totalPosts - (page - 1) * pageSize; 
		   int endPost = startPost - pageSize + 1;

		    for (int i = startPost; i >= endPost && i > 0; i--) {
		        postNumbers.add(i);
		    }
		   return postNumbers;
	   }
	
	
}
