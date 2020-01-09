// quan-ly/nhan-vien/chi-tiet/ghi-nhan/them.js

/**
 * ============================================================================
 * */
$(function() {
	
	let $ghiNhanThuongFormThem = "#ghiNhanThuongFormThem";
	$($ghiNhanThuongFormThem).on('submit', function(e) {
	    e.preventDefault();
	    
	    if (!$($ghiNhanThuongFormThem).valid())
	    	return;
	    
	    SweetAlertHelper.choXuLy();
	    let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');

	    var url = `api/v1/ghinhans/insert`;
	    let data = {
			loaiGhiNhan : true,
			lyDo : $($ghiNhanThuongFormThem + " textarea[name=lyDo]").val(),
			ngayGhiNhan : $($ghiNhanThuongFormThem + " input[name=ngayGhiNhan]").val(),
            nhanVien : {
            	maNhanVien: maNhanVien,
            }
	    };
	    
	    let getData = $.ajax({
	    	contentType: 'application/json; charset=utf-8',
			type : "POST",
			url : url,
			data : JSON.stringify(data)
		});
	   
	    getData.done(function(response) {
			if (response.thanhCong) {
				SweetAlertHelper.thanhCong(response.thongBao);
				clearForm($ghiNhanThuongFormThem);
				loadGhiNhanAjax(1, 1);
			}
			else {
				SweetAlertHelper.thatBai(response.thongBao);
			}
		});
		
		getData.fail(function(request, status, error) {
			SweetAlertHelper.thatBai(request.responseText);
		});
	});
	
	let $ghiNhanPhatFormThem = "#ghiNhanPhatFormThem";
	$($ghiNhanPhatFormThem).on('submit', function(e) {
	    e.preventDefault();
	    
	    if (!$($ghiNhanPhatFormThem).valid())
	    	return;
	    
	    SweetAlertHelper.choXuLy();
	    let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');

	    var url = `api/v1/ghinhans/insert`;
	    let data = {
			loaiGhiNhan : false,
			lyDo : $($ghiNhanPhatFormThem + " textarea[name=lyDo]").val(),
			ngayGhiNhan : $($ghiNhanPhatFormThem + " input[name=ngayGhiNhan]").val(),
            nhanVien : {
            	maNhanVien: maNhanVien,
            }
	    };
	    
	    let getData = $.ajax({
	    	contentType: 'application/json; charset=utf-8',
			type : "POST",
			url : url,
			data : JSON.stringify(data)
		});
	   
	    getData.done(function(response) {
			if (response.thanhCong) {
				SweetAlertHelper.thanhCong(response.thongBao);
				clearForm($ghiNhanPhatFormThem);
				loadGhiNhanAjax(1, 1);
			}
			else {
				SweetAlertHelper.thatBai(response.thongBao);
			}
		});
		
		getData.fail(function(request, status, error) {
			SweetAlertHelper.thatBai(request.responseText);
		});
	});
	
	let clearForm = function(formId) {
		$(formId + " textarea[name=lyDo]").val("");
	}
});

