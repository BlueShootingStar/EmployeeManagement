package com.thongddps08464.assignmentjava5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhongBanDTO {
	
	private Integer maPhongBan;
	private String tenPhongBan;
	private Integer soLuongNhanVienNam;
	private Integer soLuongNhanVienNu;
	private Integer tongDiemGhiNhanThuong;
	private Integer tongDiemGhiNhanPhat;
	
}
