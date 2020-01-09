package com.thongddps08464.assignmentjava5.helper;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.ValidationRequest;
import com.twilio.rest.verify.v2.Service;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

public class PhoneCallHelper {
	private static final String ACCOUNT_SID = "AC40557e610c3f7af9d55db481c792d1b6";
	private static final String AUTH_TOKEN = "ee7a315ea6f8b837e349cbddc4ae4250";
	private static Service service;
	
	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		service = Service.creator("My First Verify Service").create();
	}
	

	public static boolean sendVerificationToken(String soDienThoai) {
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			Verification.creator(
					service.getSid(),
					changeFormPhoneNumber(soDienThoai), // to
					"sms")
			.create();
			
			return true;
		}
		catch (com.twilio.exception.ApiException exApi) {
			return false;
		}
	}
	
	public static VerificationCheck checkVerificationToken(String soDienThoai, String code) {
		try {
	        VerificationCheck verificationCheck = VerificationCheck.creator(
	        		service.getSid(),
	        		code)
	            .setTo(changeFormPhoneNumber(soDienThoai)).create();
			return verificationCheck;
		}
		catch (com.twilio.exception.ApiException exApi) {
			return null;
		}
	}
	
	public static boolean sendSMS(String soDienThoai, String noiDung) {
		try {
			// Chức năng nhắn tin điện thoại
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			
			// gửi tin nhắn điện thoại thông báo
			Message.creator(
					new com.twilio.type.PhoneNumber(changeFormPhoneNumber(soDienThoai)), // to
					new com.twilio.type.PhoneNumber("+18583485813"), // from
					noiDung)
			.create();
			return true;
		}
		catch(com.twilio.exception.ApiException exApi) {
			return false;
		}
	}
	
	public static ValidationRequest addVerifiedNumber(String soDienThoai, String tenDanhBa) {
		try {
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			ValidationRequest validationRequest = ValidationRequest.creator(
					new com.twilio.type.PhoneNumber(changeFormPhoneNumber(soDienThoai)))
					.setFriendlyName(tenDanhBa)
					.create();
			return validationRequest;
		}
		catch(com.twilio.exception.ApiException exApi) {
			exApi.printStackTrace();
			return null;
		}
	}
	
	private static String changeFormPhoneNumber(String str) {
		String strAppend = str.substring(1, str.length());
		str = "+84" + strAppend;
		return str;
	}
}
