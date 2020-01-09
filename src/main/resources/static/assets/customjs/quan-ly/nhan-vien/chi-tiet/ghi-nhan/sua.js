// quan-ly/nhan-vien/chi-tiet/ghi-nhan/sua.js

/**
 * ============================================================================
 * */
$(function() {
	
	let $ghiNhanThuongFormSua = "#ghiNhanThuongFormChiTiet";
	$($ghiNhanThuongFormSua).on('submit', function(e) {
	    e.preventDefault();
	    
	    if (!$($ghiNhanThuongFormSua).valid())
	    	return;
	    
	    let maGhiNhan = $($ghiNhanThuongFormSua + " input[name=maGhiNhan]").val();
	    let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');

	    var url = `api/v1/ghinhans/update/${maGhiNhan}`;
	    let data = {
	    	maGhiNhan : maGhiNhan,
			loaiGhiNhan : true,
			lyDo : $($ghiNhanThuongFormSua + " textarea[name=lyDo]").val(),
			ngayGhiNhan : $($ghiNhanThuongFormSua + "input[name=ngayGhiNhan]").val(),
            nhanVien : {
            	maNhanVien: maNhanVien,
            }
	    };
	    
	    let getData = $.ajax({
	    	contentType: 'application/json; charset=utf-8',
			type : "PUT",
			url : url,
			data : JSON.stringify(data)
		});
	   
	    getData.done(function(response) {
			if (response.thanhCong) {
				SweetAlertHelper.thanhCong(response.thongBao);
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
	
	//---------------------------------------------------------------------------------------//
	let $ghiNhanPhatFormSua = "#ghiNhanPhatFormChiTiet";
	$($ghiNhanPhatFormSua).on('submit', function(e) {
	    e.preventDefault();
	    
	    if (!$($ghiNhanPhatFormSua).valid())
	    	return;
	    
	    let maGhiNhan = $($ghiNhanPhatFormSua + " input[name=maGhiNhan]").val();
	    let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');

	    var url = `api/v1/ghinhans/update/${maGhiNhan}`;
	    let data = {
	    	maGhiNhan : maGhiNhan,
			loaiGhiNhan : false,
			lyDo : $($ghiNhanPhatFormSua + " textarea[name=lyDo]").val(),
			ngayGhiNhan : $($ghiNhanPhatFormSua + " input[name=ngayGhiNhan]").val(),
            nhanVien : {
            	maNhanVien: maNhanVien,
            }
	    };
	    
	    let getData = $.ajax({
	    	contentType: 'application/json; charset=utf-8',
			type : "PUT",
			url : url,
			data : JSON.stringify(data)
		});
	   
	    getData.done(function(response) {
			if (response.thanhCong) {
				SweetAlertHelper.thanhCong(response.thongBao);
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
});

/**
 * ============================================================================
 * */
function setGhiNhanChiTiet(formId, sourceId) {
	$(formId).validate().resetForm();
	
	let maGhiNhan = $(sourceId).data('ma-ghi-nhan');
	let ngayGhiNhan = $(sourceId).data('ngay-ghi-nhan');
	let tenNhanVien = $(sourceId).data('ten-nhan-vien');
	let lyDo = $(sourceId).data('ly-do');
	
	$(formId + " input[name=maGhiNhan]").val(maGhiNhan);
	$(formId + " input[name=ngayGhiNhan]").removeClass('error').datepicker('setDate', ngayGhiNhan);
	$(formId + " input[name=tenNhanVien]").val(tenNhanVien);
	$(formId + " textarea[name=lyDo]").removeClass('error').val(lyDo);
}