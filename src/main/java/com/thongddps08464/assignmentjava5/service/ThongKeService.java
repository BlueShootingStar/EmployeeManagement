package com.thongddps08464.assignmentjava5.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thongddps08464.assignmentjava5.dto.NhanVienCoDiemCaoNhatTrongThangDTO;
import com.thongddps08464.assignmentjava5.dto.NhanVienTongDiemDTO;
import com.thongddps08464.assignmentjava5.dto.ThongKeSoLuongDTO;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.repository.ThongKeRepository;

@Service
public class ThongKeService {
	
	@Autowired
	ThongKeRepository thongKeRepository;
	
	public Response getThongKeSoLuong() {
		try {
			ThongKeSoLuongDTO data = thongKeRepository.getThongKeSoLuong();
			return new Response(true, "Truy vấn thành công", data);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
	}
	
	public Response getMapListTop5NhanVienCoDiemCaoNhatTrongThangTruoc(Integer soLuongThangCachHomNay) {
		HashMap<String, List<NhanVienCoDiemCaoNhatTrongThangDTO>> map = new HashMap<String, List<NhanVienCoDiemCaoNhatTrongThangDTO>>();
		try {
			for (int i = 0; i < soLuongThangCachHomNay; i++) {
				List<NhanVienCoDiemCaoNhatTrongThangDTO> list = thongKeRepository.getTop5NhanVienCoDiemCaoNhatTrongThangTruoc(i);
				map.put("cach" + i + "ThangTruoc", list);
			}
			
			return new Response(true, "Truy vấn thành công", map);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
	}
	
	public Response getTongDiemCuaNhanVienThangNay(Integer maNhanVien) {
		try {
			NhanVienTongDiemDTO data = thongKeRepository.getTongDiemCuaNhanVienThangNay(maNhanVien);
			return new Response(true, "Truy vấn thành công", data);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
	}
}
