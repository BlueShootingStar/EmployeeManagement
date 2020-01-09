package com.thongddps08464.assignmentjava5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thongddps08464.assignmentjava5.model.GhiNhan;

@Repository
public interface GhiNhanRepository extends JpaRepository<GhiNhan, Integer> {
	List<GhiNhan> findByNhanVienMaNhanVien(Integer maNhanVien);
	
	Page<GhiNhan> findByNhanVienMaNhanVienAndLoaiGhiNhanOrderByNgayGhiNhanDesc(Integer maNhanVien, Boolean loaiGhiNhan, Pageable pageable);

	Page<GhiNhan> findByNhanVienMaNhanVienAndLoaiGhiNhanOrderByNgayGhiNhanAsc(Integer maNhanVien, Boolean loaiGhiNhan, Pageable pageable);

	List<GhiNhan> findTop5ByNhanVienMaNhanVienOrderByNgayGhiNhanDesc(Integer maNhanVien);

	List<GhiNhan> findTop5ByNhanVienMaNhanVienOrderByNgayGhiNhanAsc(Integer maNhanVien);
	
}
