package com.thongddps08464.assignmentjava5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thongddps08464.assignmentjava5.model.PhongBan;
import com.thongddps08464.assignmentjava5.repository.custom.PhongBanRepositoryCustom;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Integer>, PhongBanRepositoryCustom {
	
}
