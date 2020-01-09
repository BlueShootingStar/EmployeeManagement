package com.thongddps08464.assignmentjava5.service;

import org.springframework.stereotype.Service;

import com.thongddps08464.assignmentjava5.helper.PhoneCallHelper;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@Service
public class PhoneCallService {
	
	/**
	 * 
	 * */
	public Response sendVerificationToken(String soDienThoai) {
		if (PhoneCallHelper.sendVerificationToken(soDienThoai)) {
			return new Response(true, "Đã gửi mã xác nhận" , null);
		}
		else {
			return new Response(false, "Có lỗi xảy ra khi gửi mã xác nhận", null);
		}
	}
	
	/**
	 * 
	 * */
	public Response checkVerificationToken(String soDienThoai, String maXacNhan) {
		VerificationCheck verificationCheck = PhoneCallHelper.checkVerificationToken(soDienThoai, maXacNhan);
		
		if (verificationCheck == null)
			return new Response(false, "Có lỗi xảy ra khi gửi mã xác nhận", null);
			
		if (verificationCheck.getStatus().equals("pending"))
			return new Response(false, "Mã xác nhận sai! Vui lòng thử lại", null);
		
//		ValidationRequest validationRequest = PhoneCallHelper.addVerifiedNumber(soDienThoai, "test");
//		if (validationRequest == null) {
//			return new Response(false, "Thêm số vào danh bạ thất bại", null);
//		}
		
		return new Response(true, "Xác nhận thành công", null);
	}
	
}
