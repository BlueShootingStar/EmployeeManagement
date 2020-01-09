package com.thongddps08464.assignmentjava5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thongddps08464.assignmentjava5.helper.Component;
import com.thongddps08464.assignmentjava5.model.NhanVien;
import com.thongddps08464.assignmentjava5.service.NhanVienService;

@Controller
@RequestMapping("/quan-ly/nhan-vien")
public class QuanLyNhanVienController {
	
	private String route = "/quan-ly/nhan-vien";
	
	@Autowired
	private NhanVienService nhanVienService;
	
	@GetMapping("")
	public String getView(ModelMap modelMap) {
		modelMap.addAttribute("component", Component.build(route));
		return "index";
	}
	
	@GetMapping("chi-tiet/{maNhanVien}")
	public String getViewDetail(ModelMap modelMap, @PathVariable Integer maNhanVien) {
		NhanVien nhanVien = (NhanVien) (nhanVienService.findById(maNhanVien).getDoiTuong());
		
		if (nhanVien == null) {
			return "error-404";
		}
		
		modelMap.addAttribute("maNhanVien", nhanVien.getMaNhanVien());
		modelMap.addAttribute("component", Component.build(route + "/chi-tiet"));
		return "index";
	}
}
