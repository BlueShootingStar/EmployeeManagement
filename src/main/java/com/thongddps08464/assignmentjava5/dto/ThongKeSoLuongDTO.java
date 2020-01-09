package com.thongddps08464.assignmentjava5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThongKeSoLuongDTO {
	private Integer soLuongNhanVien;
	private Integer soLuongPhongBan;
	private Integer soLuongGhiNhan;
	private Integer soLuongGhiNhanThuong;
	private Integer soLuongGhiNhanPhat;
}
