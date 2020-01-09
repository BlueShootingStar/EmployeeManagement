package com.thongddps08464.assignmentjava5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thongddps08464.assignmentjava5.dto.PhongBanDTO;
import com.thongddps08464.assignmentjava5.model.PhongBan;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.repository.PhongBanRepository;

@Service
public class PhongBanService {
	
	@Autowired
	private PhongBanRepository phongBanRepository;
	
	/**
	 * 
	 * */
    public Response findAll() {
		try {
			List<PhongBan> list = phongBanRepository.findAll();
			return new Response(true, "Truy vấn thành công", list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
    
    /**
	 * 
	 * */
	public Response findAllWithDetailData() {
		try {
			List<PhongBanDTO> list = phongBanRepository.getThongTinChiTietPhongBan();
			return new Response(true, "Truy vấn thành công", list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
    
    /**
	 * 
	 * */
    public Response findAll(int page, int size) {
		try {
			Page<PhongBan> list = phongBanRepository.findAll(PageRequest.of(page - 1, size));
			return new Response(true, "Truy vấn thành công", list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
    
    /**
	 * 
	 * */
    public Response findById(Integer maPhongBan) {
    	try {
    		PhongBan phongBan = phongBanRepository.findById(maPhongBan).orElse(null);
    		
    		if (phongBan == null)
    			return new Response(false, "Không tồn tại ghi nhận có mã " + maPhongBan, null);

    		return new Response(true, "Truy vấn thành công", phongBan);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
	
	/**
	 * 
	 * */
	public Response insert(PhongBan phongBanNew) {
		try {
			PhongBan phongBan = phongBanRepository.save(phongBanNew);
			return new Response(true, "Thêm mới thành công", phongBan);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi thêm ghi nhận mới", null);
		}
	}

	/**
	 * 
	 * */
    public Response update(Integer maPhongBan, PhongBan phongBanMoi) {
    	try {
	    	PhongBan phongBanCu = phongBanRepository.findById(maPhongBan).orElse(null);
	    	
	    	if (phongBanCu == null)
				return new Response(false, "Không tồn tại ghi nhận có mã " + maPhongBan, null);
	
	        PhongBan phongBanDaCapNhat = phongBanRepository.save(phongBanMoi);
	        return new Response(true, "Cập nhật thành công", phongBanDaCapNhat);
    	}
    	catch (Exception ex) {
    		return new Response(false, "Có lỗi xảy ra khi cập nhật ghi nhận", null);
    	}
    }

    /**
	 * 
	 * */
	public Response deleteById(Integer maPhongBan) {
		try {
			PhongBan phongBan = phongBanRepository.findById(maPhongBan).orElse(null);
	    	
	    	if (phongBan == null)
				return new Response(false, "Không tồn tại ghi nhận có mã " + maPhongBan, null);
	    	
	    	phongBanRepository.deleteById(maPhongBan);
	    	return new Response(true, "Xóa thành công", phongBan);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi xóa ghi nhận", null);
		}
	}
	
}
