package com.thongddps08464.assignmentjava5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NhanVienTongDiemDTO {
	private Integer maNhanVien;
	private String soDienThoai;
	private Integer tongSoDiem;
}
