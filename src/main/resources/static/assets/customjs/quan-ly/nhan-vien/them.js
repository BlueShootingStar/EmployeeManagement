// quan-ly/nhan-vien/them.js

/**
 * ============================================================================
 * */
$(function() {
	let $nhanVienFormThem = "#nhanVienFormThem";
	$($nhanVienFormThem).on('submit', function(e) {
		e.preventDefault();
	    
	    if (!$($nhanVienFormThem).valid())
	    	return;
	    
	    SweetAlertHelper.choXuLy();
	    
	    guiMaXacNhanAjax($($nhanVienFormThem + " input[name=soDienThoai]").val());
	});
	
	$("#xacNhanSoDienThoaiForm").on('submit', function(e) {
		e.preventDefault();
	    
	    if (!$($nhanVienFormThem).valid())
	    	return;
	    
	    SweetAlertHelper.choXuLy();
	    
	    kiemTraMaXacNhanAjax($($nhanVienFormThem + " input[name=soDienThoai]").val(), $("#xacNhanSoDienThoaiForm input[name=maXacNhan]").val());
	});
});

function themNhanVienAjax($nhanVienFormThem) {
	var url = `api/v1/nhanviens/insert`;

    let data = {
    	hoTen : $($nhanVienFormThem + " input[name=hoTen]").val(),
    	gioiTinh : $($nhanVienFormThem + " input[name=gioiTinh]").bootstrapSwitch('state'),
    	ngaySinh : $($nhanVienFormThem + " input[name=ngaySinh]").val(),
    	hinh : "none.jpg",
    	email : $($nhanVienFormThem + " input[name=email]").val(),
    	soDienThoai : $($nhanVienFormThem + " input[name=soDienThoai]").val(),
    	tienLuong : $($nhanVienFormThem + " input[name=tienLuong]").val(),
    	ghiChu : $($nhanVienFormThem + " textarea[name=ghiChu]").val(),
        phongBan : {
        	maPhongBan : $($nhanVienFormThem + " select[name=maPhongBan]").val()
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
			if ($('#hinh').val() == "") {
				SweetAlertHelper.thanhCong(response.thongBao);
				clearForm();
				loadPageAjax(1);
				return;
			}
			uploadHinhAjax(response.doiTuong.maNhanVien);
		}
		else {
			SweetAlertHelper.thatBai(response.thongBao);
		}
	});
	
	getData.fail(function(request, status, error) {
		SweetAlertHelper.thatBai(request.responseText);
	});
}

/**
 * ============================================================================
 * */
function guiMaXacNhanAjax(soDienThoai) {
	var url = `api/v1/phonecall/sendVerificationToken?soDienThoai=${soDienThoai}`;

    let data = {
    		soDienThoai: soDienThoai
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
			$('#modal-xac-nhan-so-dien-thoai input[name=maXacNhan]').val('');
			$('#modal-xac-nhan-so-dien-thoai').modal('show');
		}
		else {
			SweetAlertHelper.thatBai(response.thongBao);
		}
	});
	
	getData.fail(function(request, status, error) {
		SweetAlertHelper.thatBai(request.responseText);
	});
	
	return getData;
}

/**
 * ============================================================================
 * */
function kiemTraMaXacNhanAjax(soDienThoai, maXacNhan) {
	var url = `api/v1/phonecall/checkVerificationToken?soDienThoai=${soDienThoai}&maXacNhan=${maXacNhan}`;

    let data = {
    		soDienThoai: soDienThoai,
    		maXacNhan: maXacNhan
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
			$('#modal-xac-nhan-so-dien-thoai').modal('hide');
			themNhanVienAjax("#nhanVienFormThem");
		}
		else {
			SweetAlertHelper.thatBai(response.thongBao);
		}
	});
	
	getData.fail(function(request, status, error) {
		SweetAlertHelper.thatBai(request.responseText);
	});
	
	return getData;
}

/**
 * ============================================================================
 * */
function clearForm() {
	$('.nhanVienForm').validate().form();
	
	// cột trái
	$(".nhanVienHinh").attr("src", "");
	
	// form
	$(".nhanVienMaNhanVienForm").val("");
	$(".nhanVienHoTenForm").val("");
	$(".nhanVienGioiTinhForm").bootstrapSwitch('state', true);
	$(".nhanVienNgaySinhForm").datepicker('setDate', null);
	$(".nhanVienEmailForm").val("");
	$(".nhanVienSoDienThoaiForm").val("");
	$(".nhanVienTienLuongForm").val("");
	$(".nhanVienGhiChuForm").val("");
	$(".nhanVienPhongBanForm").val(1);
	$(".nhanVienHinhForm").val('');
}

/**
 * ============================================================================
 * */
function uploadHinhAjax(maNhanVien) {
	
    let file = $('#hinh').get(0).files[0];
    let newFileName = maNhanVien + ".jpg";
    
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
			clearForm();
			loadPageAjax(1);
		}
		else {
			SweetAlertHelper.thatBai(response.thongBao);
		}
	});
	
	getData.fail(function(request, status, error) {
		SweetAlertHelper.thatBai(request.responseText);
	});
}
