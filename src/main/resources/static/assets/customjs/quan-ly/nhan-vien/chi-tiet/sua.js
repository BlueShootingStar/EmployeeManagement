// quan-ly/nhan-vien/chi-tiet/sua.js

/**
 * ============================================================================
 * */
$(function() {
	let $nhanVienFormChiTiet = "#nhanVienFormChiTiet";
	$($nhanVienFormChiTiet).on('submit', function(e) {
	    e.preventDefault();
	    
	    if (!$($nhanVienFormChiTiet).valid())
	    	return;
	    
	    SweetAlertHelper.choXuLy();
	    
	    let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');
	    var url = `api/v1/nhanviens/update/${maNhanVien}`;

	    let phongBan = {
            	maPhongBan : $($nhanVienFormChiTiet + " select[name=maPhongBan]").val()
        };
	    
	    if ($($nhanVienFormChiTiet + " select[name=maPhongBan]").val() == "null") {
	    	phongBan = null;
	    }
	    
	    let data = {
	    	maNhanVien : maNhanVien,
	    	hoTen : $($nhanVienFormChiTiet + " input[name=hoTen]").val(),
	    	gioiTinh : $($nhanVienFormChiTiet + " input[name=gioiTinh]").bootstrapSwitch('state'),
	    	ngaySinh : $($nhanVienFormChiTiet + " input[name=ngaySinh]").val(),
	    	hinh : maNhanVien + ".jpg",
	    	email : $($nhanVienFormChiTiet + " input[name=email]").val(),
	    	soDienThoai : $($nhanVienFormChiTiet + " input[name=soDienThoai]").val(),
	    	tienLuong : $($nhanVienFormChiTiet + " input[name=tienLuong]").val(),
	    	ghiChu : $($nhanVienFormChiTiet + " textarea[name=ghiChu]").val(),
            phongBan : phongBan
	    };
	    
	    let getData = $.ajax({
	    	contentType: 'application/json; charset=utf-8',
	    	type : "PUT",
	    	url : url,
			data : JSON.stringify(data)
		});
	   
	    getData.done(function(response) {
			if (response.thanhCong) {
				if ($('#hinh').val() == "") {
					SweetAlertHelper.thanhCong(response.thongBao);
					loadThongTinNhanVienAjax();
					loadGhiNhanAjax(1, 1);
					return;
				}
				uploadHinhAjax();
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

function uploadHinhAjax() {
	
    let file = $('#hinh').get(0).files[0];
    let newFileName = $('#maNhanVien').data('ma-nhan-vien') + ".jpg";
    
	// Get form
    var data = new FormData();
    data.append('hinh', file, newFileName);
    
    let getData = $.ajax({
    	type: "Post",
    	url: "api/v1/upload/uploadHinhNhanVien",
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 1000000
    });
    
    getData.done(function(response) {
		if (response.thanhCong) {
			SweetAlertHelper.thanhCong(response.thongBao);
			loadThongTinNhanVienAjax();
			loadGhiNhanAjax(1, 1);
		}
		else {
			SweetAlertHelper.thatBai(response.thongBao);
		}
	});
	
	getData.fail(function(request, status, error) {
		SweetAlertHelper.thatBai(request.responseText);
	});
}