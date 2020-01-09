package com.thongddps08464.assignmentjava5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thongddps08464.assignmentjava5.dto.NguoiDungDTO;
import com.thongddps08464.assignmentjava5.model.NguoiDung;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.repository.NguoiDungRepository;

@Service
public class NguoiDungService implements UserDetailsService {
	
	@Autowired
	private NguoiDungRepository nguoiDungRepository;
	
	/**
	 * 
	 * */
    public Response findAll() {
		try {
			List<NguoiDung> list = nguoiDungRepository.findAll();
			return new Response(true, "Truy vấn thành công", list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
	
    /**
	 * 
	 * */
    public Response findById(String tenDangNhap) {
    	try {
    		NguoiDung nguoiDung = nguoiDungRepository.findById(tenDangNhap).orElse(null);
    		
    		if (nguoiDung == null)
    			return new Response(false, "Không tồn tại người dùng " + tenDangNhap, null);

    		return new Response(true, "Truy vấn thành công", nguoiDung);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn", null);
		}
    }
	
	/**
	 * 
	 * */
	public Response insert(NguoiDung nguoiDungMoi) {
		try {
			NguoiDung nguoiDungCu = nguoiDungRepository.findById(nguoiDungMoi.getTenDangNhap()).orElse(null);
	    	
	    	if (nguoiDungCu != null)
				return new Response(false, "Đã tồn tại người dùng " + nguoiDungMoi.getTenDangNhap(), null);
			
			NguoiDung nguoiDung = nguoiDungRepository.save(nguoiDungMoi);
			return new Response(true, "Thêm mới thành công", nguoiDung);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi thêm người dùng mới", null);
		}
	}

	/**
	 * 
	 * */
    public Response update(String tenDangNhap, NguoiDung nguoiDungMoi) {
    	try {
	    	NguoiDung nguoiDungCu = nguoiDungRepository.findById(tenDangNhap).orElse(null);
	    	
	    	if (nguoiDungCu == null)
				return new Response(false, "Không tồn tại người dùng " + tenDangNhap, null);
	    	
	    	nguoiDungCu.setMatKhau(nguoiDungMoi.getMatKhau());
	    	nguoiDungCu.setHoTen(nguoiDungMoi.getHoTen());
	    	nguoiDungCu.setEmail(nguoiDungMoi.getEmail());
	    	nguoiDungCu.setHinh(nguoiDungMoi.getHinh());
	    	
	        NguoiDung nguoiDungDaCapNhat = nguoiDungRepository.save(nguoiDungCu);
	        return new Response(true, "Cập nhật thành công", nguoiDungDaCapNhat);
    	}
    	catch (Exception ex) {
    		return new Response(false, "Có lỗi xảy ra khi cập nhật người dùng", null);
    	}
    }

    /**
	 * 
	 * */
	public Response deleteById(String tenDangNhap) {
		try {
			NguoiDung nguoiDung = nguoiDungRepository.findById(tenDangNhap).orElse(null);
	    	
	    	if (nguoiDung == null)
				return new Response(false, "Không tồn tại người dùng " + tenDangNhap, null);
	    	
	    	nguoiDungRepository.deleteById(tenDangNhap);
	    	return new Response(true, "Xóa thành công", nguoiDung);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi xóa người dùng", null);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String tenDangNhap) throws UsernameNotFoundException {
        NguoiDung user = nguoiDungRepository.findById(tenDangNhap).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(tenDangNhap);
        }
        return new NguoiDungDTO(user);
	}
}
