package com.thongddps08464.assignmentjava5.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thongddps08464.assignmentjava5.model.NhanVien;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.repository.NhanVienRepository;

@Service
public class NhanVienService {
	
	@Autowired
	private NhanVienRepository nhanVienRepository;
	
	/**
	 * 
	 * */
    public Response findAll() {
		try {
			List<NhanVien> list = nhanVienRepository.findAll();
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
			Page<NhanVien> list = nhanVienRepository.findAll(PageRequest.of(page - 1, size));
			
			return new Response(true, "Truy vấn thành công", list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
    
    /**
   	 * 
   	 * */
	public Response findAll(String tuKhoaHoTen, String tenPhongBan, int page, int size) {
		try {
			Page<NhanVien> list = null;
			
			if (tenPhongBan.equals("")) {
				list = nhanVienRepository.findByHoTenIgnoreCaseContainsAndPhongBanTenPhongBanContainsOrPhongBanMaPhongBanIsNull(tuKhoaHoTen, tenPhongBan, PageRequest.of(page - 1, size));
			}
			else if (tenPhongBan.equals("null")) {
				list = nhanVienRepository.findByHoTenIgnoreCaseContainsAndPhongBanMaPhongBanIsNull(tuKhoaHoTen, PageRequest.of(page - 1, size));
			}
			else {
				list = nhanVienRepository.findByHoTenIgnoreCaseContainsAndPhongBanTenPhongBanContains(tuKhoaHoTen, tenPhongBan, PageRequest.of(page - 1, size));
			}
			
			return new Response(true, "Truy vấn thành công", list);
		} catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
	}
    
    /**
	 * 
	 * */
    public Response findById(Integer maNhanVien) {
    	try {
    		NhanVien nhanVien = nhanVienRepository.findById(maNhanVien).orElse(null);
    		
    		if (nhanVien == null)
    			return new Response(false, "Không tồn tại nhân viên có mã " + maNhanVien, null);

    		return new Response(true, "Truy vấn thành công", nhanVien);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
    
	/**
	 * 
	 * */
	public Response insert(NhanVien nhanVienNew) {
		try {
			NhanVien nhanVien = nhanVienRepository.save(nhanVienNew);
			nhanVien.setHinh(nhanVien.getMaNhanVien() + ".jpg");	// thay tên hình bằng mã nhân viên + .jpg
			nhanVien = nhanVienRepository.save(nhanVien);
			return new Response(true, "Thêm mới thành công", nhanVien);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return new Response(false, "Có lỗi xảy ra khi thêm nhân viên mới", null);
		}
	}

	/**
	 * 
	 * */
    public Response update(Integer maNhanVien, NhanVien nhanVienMoi) {
    	try {
	    	NhanVien nhanVienCu = nhanVienRepository.findById(maNhanVien).orElse(null);
	    	
	    	if (nhanVienCu == null)
				return new Response(false, "Không tồn tại nhân viên có mã " + maNhanVien, null);
	
	        NhanVien nhanVienDaCapNhat = nhanVienRepository.save(nhanVienMoi);
	        return new Response(true, "Cập nhật thành công", nhanVienDaCapNhat);
    	}
    	catch (Exception ex) {
    		return new Response(false, "Có lỗi xảy ra khi cập nhật nhân viên", null);
    	}
    }

    private String thuMucHinh = "./images/avatars/nhan-vien/";
    
    /**
	 * 
	 * */
	public Response deleteById(Integer maNhanVien) {
		try {
			NhanVien nhanVien = nhanVienRepository.findById(maNhanVien).orElse(null);
			
	    	if (nhanVien == null)
				return new Response(false, "Không tồn tại nhân viên có mã " + maNhanVien, null);
	    	
	    	Files.deleteIfExists(Paths.get(thuMucHinh + nhanVien.getHinh()));
	    	nhanVienRepository.deleteById(maNhanVien);
	    	return new Response(true, "Xóa thành công", nhanVien);
		}
		catch (IOException ioEx) {
			return new Response(false, "Có lỗi khi xóa hình nhân viên", null);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi xóa nhân viên", null);
		}
	}
}
