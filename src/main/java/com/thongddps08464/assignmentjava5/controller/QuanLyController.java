package com.thongddps08464.assignmentjava5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quan-ly")
public class QuanLyController {
	
	@GetMapping("")
	public String getView() {
		return "redirect:/quan-ly/nhan-vien";
	}
}
