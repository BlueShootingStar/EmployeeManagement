package com.thongddps08464.assignmentjava5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NhanVienCoDiemCaoNhatTrongThangDTO {
	private Integer maNhanVien;
	private String hoTen;
	private Boolean gioiTinh;
	private String hinh;
	private String tenPhongBan;
	private Integer tongSoGhiNhanThuong;
	private Integer tongSoGhiNhanPhat;
	private Integer tongSoDiem;
}
