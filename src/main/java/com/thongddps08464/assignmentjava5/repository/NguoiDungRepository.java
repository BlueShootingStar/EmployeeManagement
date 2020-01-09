package com.thongddps08464.assignmentjava5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thongddps08464.assignmentjava5.model.NguoiDung;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {

}
