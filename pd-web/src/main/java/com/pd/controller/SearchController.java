package com.pd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pd.pojo.Item;
import com.pd.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	//浏览器路径http://www.pd.com/search/toSearch.html?key=%E7%94%B5%E8%84%91
	@GetMapping("/search/toSearch.html")
	public String search(String key, Model model) throws Exception {
		List<Item> itemList = searchService.findItemByKey(key);
		model.addAttribute("list", itemList);
		return "/search.jsp";
	}
}