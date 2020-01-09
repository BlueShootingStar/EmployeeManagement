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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thongddps08464.assignmentjava5.model.PhongBan;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.service.PhongBanService;

@RestController
@RequestMapping("/api/v1/phongbans")
public class PhongBanRestController {
	
	@Autowired
	private PhongBanService phongBanService;
	
	@GetMapping("/findAll")
    public ResponseEntity<Response> findAll() {
		Response res = phongBanService.findAll();
		return ResponseEntity.ok(res);
    }
	
	@GetMapping(value="/findAll", params={"page", "size"})
    public ResponseEntity<Response> findAll(@RequestParam int page, @RequestParam int size) {
		Response res = phongBanService.findAll(page, size);
		return ResponseEntity.ok(res);
    }
	
	@GetMapping(value="/findAllWithDetailData")
    public ResponseEntity<Response> findAllWithDetailData() {
		Response res = phongBanService.findAllWithDetailData();
		return ResponseEntity.ok(res);
    }
	
	@GetMapping("/findById/{maPhongBan}")
    public ResponseEntity<Response> findById(@PathVariable Integer maPhongBan) {
		Response res = phongBanService.findById(maPhongBan);
		return ResponseEntity.ok(res);
    }

	@PostMapping("/insert")
	public ResponseEntity<Response> insert(@RequestBody PhongBan phongBanMoi) {
		Response res = phongBanService.insert(phongBanMoi);
		return ResponseEntity.ok(res);
	}

    @PutMapping("/update/{maPhongBan}")
    public ResponseEntity<Response> update(@PathVariable Integer maPhongBan, @RequestBody PhongBan phongBanMoi) {
    	Response res = phongBanService.update(maPhongBan, phongBanMoi);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleteById/{maPhongBan}")
    public ResponseEntity<Response> deleteById(@PathVariable Integer maPhongBan) {
    	Response res = phongBanService.deleteById(maPhongBan);
        return ResponseEntity.ok(res);
    }
    
}
