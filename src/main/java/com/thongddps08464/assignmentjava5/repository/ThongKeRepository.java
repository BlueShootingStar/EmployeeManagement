package com.thongddps08464.assignmentjava5.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thongddps08464.assignmentjava5.dto.NhanVienCoDiemCaoNhatTrongThangDTO;
import com.thongddps08464.assignmentjava5.dto.NhanVienTongDiemDTO;
import com.thongddps08464.assignmentjava5.dto.ThongKeSoLuongDTO;

@Repository
@Transactional
public class ThongKeRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public ThongKeSoLuongDTO getThongKeSoLuong() {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_ThongKeSoLuong");

		@SuppressWarnings("unchecked")
		List<Object[]> storedProcedureResults = storedProcedure.getResultList();
		
		List<ThongKeSoLuongDTO> list = storedProcedureResults.stream().map(result -> new ThongKeSoLuongDTO(
	         (Integer) result[0],
	         (Integer) result[1],
	         (Integer) result[2],
	         (Integer) result[3],
	         (Integer) result[4]
	        		 )).collect(Collectors.toList());
		
		return list.get(0);
	}
	
	public List<NhanVienCoDiemCaoNhatTrongThangDTO> getTop5NhanVienCoDiemCaoNhatTrongThangTruoc(Integer soLuongThangCachHomNay) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_Top5NhanVienCoDiemCaoNhatTrongThang")
				.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN).setParameter(1, soLuongThangCachHomNay);

		@SuppressWarnings("unchecked")
		List<Object[]> storedProcedureResults = storedProcedure.getResultList();
		
		List<NhanVienCoDiemCaoNhatTrongThangDTO> list = storedProcedureResults.stream().map(result -> new NhanVienCoDiemCaoNhatTrongThangDTO(
	         (Integer) result[0],
	         (String) result[1],
	         (Boolean) result[2],
	         (String) result[3],
	         (String) result[4],
	         (Integer) result[5],
	         (Integer) result[6],
    		 (Integer) result[7]
	        		 )).collect(Collectors.toList());
		
		return list;
	}
	
	public NhanVienTongDiemDTO getTongDiemCuaNhanVienThangNay(Integer maNhanVien) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sp_TongDiemCuaNhanVienThangNay")
			.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN).setParameter(1, maNhanVien);

		@SuppressWarnings("unchecked")
		List<Object[]> storedProcedureResults = storedProcedure.getResultList();
		
		List<NhanVienTongDiemDTO> list = storedProcedureResults.stream().map(result -> new NhanVienTongDiemDTO(
	         (Integer) result[0],
	         (String) result[1],
	         (Integer) result[2]
	        		 )).collect(Collectors.toList());
		
		return list.get(0);
	}
}
