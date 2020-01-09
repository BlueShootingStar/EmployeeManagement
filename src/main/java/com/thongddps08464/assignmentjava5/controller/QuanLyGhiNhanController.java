package com.thongddps08464.assignmentjava5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thongddps08464.assignmentjava5.helper.Component;

@Controller
@RequestMapping("/quan-ly/ghi-nhan")
public class QuanLyGhiNhanController {
	
	private String route = "/quan-ly/ghi-nhan";

	@GetMapping("")
	public String getView(ModelMap modelMap) {
		modelMap.addAttribute("component", Component.build(route));
		return "index";
	}
}
