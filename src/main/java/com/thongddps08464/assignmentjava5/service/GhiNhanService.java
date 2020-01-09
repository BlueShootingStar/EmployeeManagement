package com.thongddps08464.assignmentjava5.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.thongddps08464.assignmentjava5.dto.NhanVienTongDiemDTO;
import com.thongddps08464.assignmentjava5.helper.PhoneCallHelper;
import com.thongddps08464.assignmentjava5.model.GhiNhan;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.repository.GhiNhanRepository;
import com.thongddps08464.assignmentjava5.repository.ThongKeRepository;

@Service
public class GhiNhanService {

	@Autowired
	private GhiNhanRepository ghiNhanRepository;
	
	@Autowired
	private ThongKeRepository thongKeRepository;
	
	/**
	 * 
	 * */
    public Response findAll() {
		try {
			List<GhiNhan> list = ghiNhanRepository.findAll();
			return new Response(true, "Truy vấn thành công", list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn: " + ex.getMessage(), null);
		}
    }
	
    /**
	 * 
	 * */
    public Response findById(Integer maGhiNhan) {
    	try {
    		GhiNhan ghiNhan = ghiNhanRepository.findById(maGhiNhan).orElse(null);
    		
    		if (ghiNhan == null)
    			return new Response(false, "Không tồn tại ghi nhận có mã: " + maGhiNhan, null);

    		return new Response(true, "Truy vấn thành công", ghiNhan);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn: " + ex.getMessage(), null);
		}
    }
    
    /**
	 * 
	 * */
    public Response findAllByMaNhanVienOrderByNgayGhiNhan(Integer maNhanVien, Boolean desc, int pageThuong, int pagePhat) {
    	List<GhiNhan> listGhiNhanGanNhat;
    	Page<GhiNhan> listGhiNhanThuong;
    	Page<GhiNhan> listGhiNhanPhat;

    	try {
    		if (desc) {
    			listGhiNhanGanNhat = ghiNhanRepository.findTop5ByNhanVienMaNhanVienOrderByNgayGhiNhanDesc(maNhanVien);
    			listGhiNhanThuong = ghiNhanRepository.findByNhanVienMaNhanVienAndLoaiGhiNhanOrderByNgayGhiNhanDesc(maNhanVien, true, PageRequest.of(pageThuong - 1, 5));
    			listGhiNhanPhat = ghiNhanRepository.findByNhanVienMaNhanVienAndLoaiGhiNhanOrderByNgayGhiNhanDesc(maNhanVien, false, PageRequest.of(pagePhat - 1, 5));
    		}
    		else {
    			listGhiNhanGanNhat = ghiNhanRepository.findTop5ByNhanVienMaNhanVienOrderByNgayGhiNhanAsc(maNhanVien);
    			listGhiNhanThuong = ghiNhanRepository.findByNhanVienMaNhanVienAndLoaiGhiNhanOrderByNgayGhiNhanAsc(maNhanVien, true, PageRequest.of(pageThuong - 1, 5));
    			listGhiNhanPhat = ghiNhanRepository.findByNhanVienMaNhanVienAndLoaiGhiNhanOrderByNgayGhiNhanAsc(maNhanVien, false, PageRequest.of(pagePhat - 1, 5));
    		}
    		
    		HashMap<String, Object> mapGhiNhan = new HashMap<String, Object>();
    		mapGhiNhan.put("listGhiNhanGanNhat", listGhiNhanGanNhat);
    		mapGhiNhan.put("listGhiNhanThuong", listGhiNhanThuong);
    		mapGhiNhan.put("listGhiNhanPhat", listGhiNhanPhat);
    		
    		return new Response(true, "Truy vấn thành công", mapGhiNhan);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn: " + ex.getMessage(), null);
		}
    }
	
    /**
	 * 
	 * */
	public Response findAllByMaNhanVien(Integer maNhanVien) {
		try {
			List<GhiNhan> list = ghiNhanRepository.findByNhanVienMaNhanVien(maNhanVien);
			return new Response(true, "Truy vấn thành công" , list);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi truy vấn:  " + ex.getMessage(), null);
		}
	}

	/**
	 * 
	 * */
	public Response insert(GhiNhan ghiNhanMoi) {
		try {
			GhiNhan ghiNhan = ghiNhanRepository.save(ghiNhanMoi);
			
			// gửi tin nhắn điện thoại nếu nhân viên có tổng điểm dưới -5
			NhanVienTongDiemDTO nhanVienTongDiemDTO = thongKeRepository.getTongDiemCuaNhanVienThangNay(ghiNhanMoi.getNhanVien().getMaNhanVien());
			if (nhanVienTongDiemDTO.getTongSoDiem() <= -5) {

				boolean thanhCong = PhoneCallHelper.sendSMS(
						nhanVienTongDiemDTO.getSoDienThoai(),
						"Tong so diem cua ban hien tai la " + nhanVienTongDiemDTO.getTongSoDiem() + ". Hay chu y den noi quy cong ty!"
						);
				
				if (thanhCong) {
					return new Response(true, "Thêm mới thành công và đã gửi tin nhắn nhắc nhở", ghiNhan);
				}
				else {
					return new Response(false, " Thêm mới thành công nhưng gửi tin nhắn thất bại", ghiNhan);
				}
			}
			
			return new Response(true, "Thêm mới thành công", ghiNhan);
			
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi thêm ghi nhận mới: " + ex.getMessage(), null);
		}
	}

	/**
	 * 
	 * */
    public Response update(Integer maGhiNhan, GhiNhan ghiNhanMoi) {
    	try {
	    	GhiNhan ghiNhanCu = ghiNhanRepository.findById(maGhiNhan).orElse(null);
	    	
	    	if (ghiNhanCu == null)
				return new Response(false, "Không tồn tại ghi nhận có mã " + maGhiNhan, null);
	
	        GhiNhan ghiNhanDaCapNhat = ghiNhanRepository.save(ghiNhanMoi);
	        return new Response(true, "Cập nhật thành công", ghiNhanDaCapNhat);
    	}
    	catch (Exception ex) {
    		return new Response(false, "Có lỗi xảy ra khi cập nhật ghi nhận: " + ex.getMessage(), null);
    	}
    }

    /**
	 * 
	 * */
	public Response deleteById(Integer maGhiNhan) {
		try {
			GhiNhan ghiNhan = ghiNhanRepository.findById(maGhiNhan).orElse(null);
	    	
	    	if (ghiNhan == null)
				return new Response(false, "Không tồn tại ghi nhận có mã " + maGhiNhan, null);
	    	
	    	ghiNhanRepository.deleteById(maGhiNhan);
	    	return new Response(true, "Xóa thành công", ghiNhan);
		}
		catch (Exception ex) {
			return new Response(false, "Có lỗi xảy ra khi xóa ghi nhận: " + ex.getMessage(), null);
		}
	}
	
}
