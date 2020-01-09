package com.thongddps08464.assignmentjava5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.service.PhoneCallService;

@RestController
@RequestMapping("/api/v1/phonecall")
public class PhoneCallRestController {
	
	@Autowired
	private PhoneCallService phoneCallService;
	
	@PostMapping(value="/sendVerificationToken", params="soDienThoai")
    public ResponseEntity<Response> sendVerificationToken(@RequestParam String soDienThoai) {
		Response res = phoneCallService.sendVerificationToken(soDienThoai);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping(value="/checkVerificationToken", params={"soDienThoai", "maXacNhan"})
    public ResponseEntity<Response> checkVerificationToken(@RequestParam String soDienThoai, @RequestParam String maXacNhan) {
		Response res = phoneCallService.checkVerificationToken(soDienThoai, maXacNhan);
		return ResponseEntity.ok(res);
	}

}
