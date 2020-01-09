package com.thongddps08464.assignmentjava5.repository.custom;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thongddps08464.assignmentjava5.dto.PhongBanDTO;

@Repository
@Transactional
public class PhongBanRepositoryImpl implements PhongBanRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PhongBanDTO> getThongTinChiTietPhongBan() {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_ThongTinChiTietPhongBan");

		@SuppressWarnings("unchecked")
		List<Object[]> storedProcedureResults = storedProcedure.getResultList();
		
		List<PhongBanDTO> list = storedProcedureResults.stream().map(result -> new PhongBanDTO(
	         (Integer) result[0],
	         (String) result[1],
	         (Integer) result[2],
	         (Integer) result[3],
	         (Integer) result[4],
	         (Integer) result[5]
	        		 )).collect(Collectors.toList());
		
		return list;
	}
	
}
