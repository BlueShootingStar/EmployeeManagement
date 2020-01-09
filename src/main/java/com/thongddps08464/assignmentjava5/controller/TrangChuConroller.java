package com.thongddps08464.assignmentjava5.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thongddps08464.assignmentjava5.dto.NhanVienCoDiemCaoNhatTrongThangDTO;
import com.thongddps08464.assignmentjava5.dto.ThongKeSoLuongDTO;
import com.thongddps08464.assignmentjava5.helper.Component;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.service.ThongKeService;

@Controller
@RequestMapping("/trang-chu")
public class TrangChuConroller {
	
	private String route = "/trang-chu";
	
	@Autowired
	ThongKeService thongKeService;
	
	@SuppressWarnings("unchecked")
	@GetMapping("")
	public String getView(ModelMap modelMap) {
		Response res = thongKeService.getThongKeSoLuong();
		Response res2 = thongKeService.getMapListTop5NhanVienCoDiemCaoNhatTrongThangTruoc(3);
		
		if (res.getDoiTuong() != null) {
			modelMap.addAttribute("thongKeSoLuong", (ThongKeSoLuongDTO) res.getDoiTuong());
		}
		
		if (res2.getDoiTuong() != null) {
			modelMap.addAttribute("mapListTop5NhanVienCoDiemCaoNhatTrongThangTruoc", (HashMap<String, List<NhanVienCoDiemCaoNhatTrongThangDTO>>) res2.getDoiTuong());
		}
		
		modelMap.addAttribute("component", Component.build(route));
		return "index";
	}
	
}
