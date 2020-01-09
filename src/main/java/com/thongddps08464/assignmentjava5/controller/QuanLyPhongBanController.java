package com.thongddps08464.assignmentjava5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thongddps08464.assignmentjava5.helper.Component;

@Controller
@RequestMapping("/quan-ly/phong-ban")
public class QuanLyPhongBanController {

	private String route = "/quan-ly/phong-ban";
	
	@GetMapping("")
	public String getView(ModelMap modelMap) {
		modelMap.addAttribute("component", Component.build(route));
		return "index";
	}
}
