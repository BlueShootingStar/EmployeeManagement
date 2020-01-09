package com.thongddps08464.assignmentjava5.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {
	private static final long serialVersionUID = -2702406631384505388L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maNhanVien;
	
	@Column
	@NotBlank
	@NotNull
	private String hoTen;
	
	@Column
	@NotNull
	private Boolean gioiTinh;
	
	@Column
	@JsonFormat(pattern = "dd-MM-yyyy") 
	private LocalDate ngaySinh;
	
	@Column
	private String hinh;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String soDienThoai;
	
	@Column
	@NotNull
	@Range(min = 0, max = 100)
	private BigDecimal tienLuong;
	
	@Column
	@NotNull
	private String ghiChu;

	@ManyToOne
	@JoinColumn(name = "maPhongBan", referencedColumnName = "maPhongBan", nullable = true)
	private PhongBan phongBan;

}
