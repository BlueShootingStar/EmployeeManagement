package com.thongddps08464.assignmentjava5.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "NguoiDung")
public class NguoiDung implements Serializable {
	private static final long serialVersionUID = 6143179235004660323L;

	@Id
	@NotBlank
	private String tenDangNhap;
	
	@Column
	@NotNull
	@NotBlank
	private String matKhau;

	@Column
	@NotNull
	@NotBlank
	private String hoTen;
	
	@Column(unique = true)
	@NotNull
	@NotBlank
	private String email;
	
	@Column
	@NotNull
	@NotBlank
	private String hinh;

}
