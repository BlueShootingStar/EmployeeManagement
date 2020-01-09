package com.thongddps08464.assignmentjava5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thongddps08464.assignmentjava5.model.NguoiDung;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.service.NguoiDungService;

@RestController
@RequestMapping("/api/v1/nguoidungs")
public class NguoiDungRestController {

	@Autowired
	private NguoiDungService nguoiDungService;
	
	@GetMapping("/findAll")
    public ResponseEntity<Response> findAll() {
		Response res = nguoiDungService.findAll();
		return ResponseEntity.ok(res);
    }
	
	@GetMapping("/findById/{tenDangNhap}")
    public ResponseEntity<Response> findById(@PathVariable String tenDangNhap) {
		Response res = nguoiDungService.findById(tenDangNhap);
		return ResponseEntity.ok(res);
    }

	@PostMapping("/insert")
	public ResponseEntity<Response> insert(@RequestBody NguoiDung nguoiDungMoi) {
		Response res = nguoiDungService.insert(nguoiDungMoi);
		return ResponseEntity.ok(res);
	}

    @PutMapping("/update/{tenDangNhap}")
    public ResponseEntity<Response> update(@PathVariable String tenDangNhap, @RequestBody NguoiDung nguoiDungMoi) {
    	Response res = nguoiDungService.update(tenDangNhap, nguoiDungMoi);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleteById/{tenDangNhap}")
    public ResponseEntity<Response> deleteById(@PathVariable String tenDangNhap) {
    	Response res = nguoiDungService.deleteById(tenDangNhap);
        return ResponseEntity.ok(res);
    }
    
}
