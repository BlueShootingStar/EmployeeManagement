package com.thongddps08464.assignmentjava5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thongddps08464.assignmentjava5.model.NhanVien;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
	
	Page<NhanVien> findByHoTenIgnoreCaseContainsAndPhongBanTenPhongBanContains(String tuKhoaHoTen, String tenPhongBan, Pageable pageable);

	Page<NhanVien> findByHoTenIgnoreCaseContainsAndPhongBanMaPhongBanIsNull(String tuKhoaHoTen, Pageable pageable);

	Page<NhanVien> findByHoTenIgnoreCaseContainsAndPhongBanTenPhongBanContainsOrPhongBanMaPhongBanIsNull(String tuKhoaHoTen, String tenPhongBan, Pageable pageable);
	
}
