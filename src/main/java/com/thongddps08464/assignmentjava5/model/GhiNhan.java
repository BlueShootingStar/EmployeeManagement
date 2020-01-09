package com.thongddps08464.assignmentjava5.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "GhiNhan")
public class GhiNhan implements Serializable {
	private static final long serialVersionUID = 4073783670101611931L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maGhiNhan;
	
	@Column
	@NotNull
	private Boolean loaiGhiNhan;
	
	@Column
	@NotNull
	@NotBlank
	private String lyDo;
	
	@Column(nullable = false, updatable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate ngayGhiNhan;

	@ManyToOne
	@JoinColumn(name = "maNhanVien", referencedColumnName = "maNhanVien")
	private NhanVien nhanVien;

}
