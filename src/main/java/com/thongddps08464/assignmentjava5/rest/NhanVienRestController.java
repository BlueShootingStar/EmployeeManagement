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

import com.thongddps08464.assignmentjava5.model.NhanVien;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.service.NhanVienService;

@RestController
@RequestMapping("/api/v1/nhanviens")
public class NhanVienRestController {

	@Autowired
	private NhanVienService nhanVienService;
	
	@GetMapping("/findAll")
    public ResponseEntity<Response> findAll() {
		Response res = nhanVienService.findAll();
		return ResponseEntity.ok(res);
    }
	
	@GetMapping(value="/findAll", params={"page", "size"})
    public ResponseEntity<Response> findAll(@RequestParam int page, @RequestParam int size) {
		Response res = nhanVienService.findAll(page, size);
		return ResponseEntity.ok(res);
    }
	
	@GetMapping("/findById/{maNhanVien}")
    public ResponseEntity<Response> findById(@PathVariable Integer maNhanVien) {
		Response res = nhanVienService.findById(maNhanVien);
		return ResponseEntity.ok(res);
    }
	
	@GetMapping(value="/findAll", params={"tuKhoaHoTen", "tenPhongBan", "page", "size"})
    public ResponseEntity<Response> findByHoTen(@RequestParam String tuKhoaHoTen, @RequestParam String tenPhongBan, @RequestParam int page, @RequestParam int size) {
		Response res = nhanVienService.findAll(tuKhoaHoTen, tenPhongBan, page, size);
		return ResponseEntity.ok(res);
    }

	@PostMapping("/insert")
	public ResponseEntity<Response> insert(@RequestBody NhanVien nhanVienMoi) {
		Response res = nhanVienService.insert(nhanVienMoi);
		return ResponseEntity.ok(res);
	}

    @PutMapping("/update/{maNhanVien}")
    public ResponseEntity<Response> update(@PathVariable Integer maNhanVien, @RequestBody NhanVien nhanVienMoi) {
    	Response res = nhanVienService.update(maNhanVien, nhanVienMoi);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleteById/{maNhanVien}")
    public ResponseEntity<Response> deleteById(@PathVariable Integer maNhanVien) {
    	Response res = nhanVienService.deleteById(maNhanVien);
        return ResponseEntity.ok(res);
    }
    
}
