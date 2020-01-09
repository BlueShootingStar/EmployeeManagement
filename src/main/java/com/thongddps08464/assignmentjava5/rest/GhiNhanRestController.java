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

import com.thongddps08464.assignmentjava5.model.GhiNhan;
import com.thongddps08464.assignmentjava5.reponse.Response;
import com.thongddps08464.assignmentjava5.service.GhiNhanService;

@RestController
@RequestMapping("/api/v1/ghinhans")
public class GhiNhanRestController {
	
	@Autowired
	private GhiNhanService ghiNhanService;
	
	@GetMapping("/findAll")
    public ResponseEntity<Response> findAll() {
		Response res = ghiNhanService.findAll();
		return ResponseEntity.ok(res);
    }
	
	@GetMapping("/findAllByMaNhanVien/{maNhanVien}")
	public ResponseEntity<Response> findAllByMaNhanVien(@PathVariable Integer maNhanVien) {
		Response res = ghiNhanService.findAllByMaNhanVien(maNhanVien);
		return ResponseEntity.ok(res);
	}
	
	@GetMapping(value="/findAllByMaNhanVien/{maNhanVien}", params={"orderByNgayGhiNhanDesc", "pageThuong", "pagePhat"})
	public ResponseEntity<Response> findAllByMaNhanVienOrderByNgayGhiNhan(@PathVariable Integer maNhanVien, @RequestParam Boolean orderByNgayGhiNhanDesc, @RequestParam int pageThuong, @RequestParam int pagePhat) {
		Response res = ghiNhanService.findAllByMaNhanVienOrderByNgayGhiNhan(maNhanVien, orderByNgayGhiNhanDesc, pageThuong, pagePhat);
		return ResponseEntity.ok(res);
	}
	
	
	@GetMapping("/findById/{maGhiNhan}")
    public ResponseEntity<Response> findById(@PathVariable Integer maGhiNhan) {
		Response res = ghiNhanService.findById(maGhiNhan);
		return ResponseEntity.ok(res);
    }

	@PostMapping("/insert")
	public ResponseEntity<Response> insert(@RequestBody GhiNhan ghiNhanMoi) {
		Response res = ghiNhanService.insert(ghiNhanMoi);
		return ResponseEntity.ok(res);
	}

    @PutMapping("/update/{maGhiNhan}")
    public ResponseEntity<Response> update(@PathVariable Integer maGhiNhan, @RequestBody GhiNhan ghiNhanMoi) {
    	Response res = ghiNhanService.update(maGhiNhan, ghiNhanMoi);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/deleteById/{maGhiNhan}")
    public ResponseEntity<Response> deleteById(@PathVariable Integer maGhiNhan) {
    	Response res = ghiNhanService.deleteById(maGhiNhan);
        return ResponseEntity.ok(res);
    }
    
}
