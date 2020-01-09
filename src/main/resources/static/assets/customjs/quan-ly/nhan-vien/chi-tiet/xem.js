// quan-ly/nhan-vien/chi-tiet/xem.js

function jumpToRecordTab() {
	$(".nav-tabs a[data-target=\'#records\']").tab("show");
};

/**
 * ============================================================================
 * */
$(function() {
	// tải trang khi refresh
	loadThongTinNhanVienAjax();
});

/**
 * ============================================================================
 * */
function loadComboPhongBanAjax() {
	let url = `api/v1/phongbans/findAll`;
	
	$('.cboPhongBan').empty().append($("<option></option>")
			.attr("value", "null")
			.attr("data-ten-phong-ban", "null")
			.text("Chưa có")); 

	let getData = $.ajax({
		type: "GET",
		url: url
	});
	
	getData.done(function(response) {
		if (response.thanhCong) {
			$.each(response.doiTuong, function(index, obj) {
				$('.cboPhongBan')
			         .append($("<option></option>")
			        		 .attr("value", obj.maPhongBan)
			        		 .attr("data-ten-phong-ban", obj.tenPhongBan)
			        		 .text(obj.tenPhongBan)); 
			});
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
function loadThongTinNhanVienAjax() {
	let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');
	let url = `api/v1/nhanviens/findById/${maNhanVien}`;
		
	let getData = $.ajax({
		type: "GET",
		url: url
	});
	
	getData.done(function(response) {
		if (response.thanhCong) {
			loadComboPhongBanAjax().done(function() {
				// reset thông tin và đưa dữ liệu mới vào
				setThongTinCaNhan(response.doiTuong);
			});
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
function setThongTinCaNhan(nhanVien) {
	// cột trái
	$(".nhanVienHoTen").text(nhanVien.hoTen);
	$(".nhanVienEmail").text(nhanVien.email);
	$(".nhanVienGhiChu").text(`${nhanVien.ghiChu.length == 0 ? 'Trống' : nhanVien.ghiChu}`);
	$(".nhanVienHinh").attr("src", `images/avatars/nhan-vien/${nhanVien.hinh}`);
	
	if (nhanVien.phongBan != null) {
		$(".nhanVienTenPhongBan").text(nhanVien.phongBan.tenPhongBan);
	}
	else {
		$(".nhanVienTenPhongBan").text(" chưa thuộc phòng ban nào");
	}
	
	// form
	$(".nhanVienMaNhanVienForm").val(nhanVien.maNhanVien);
	$(".nhanVienHoTenForm").val(nhanVien.hoTen);
	$(".nhanVienGioiTinhForm").bootstrapSwitch('state', nhanVien.gioiTinh);
	$(".nhanVienNgaySinhForm").datepicker('setDate', nhanVien.ngaySinh);
	$(".nhanVienEmailForm").val(nhanVien.email);
	$(".nhanVienSoDienThoaiForm").val(nhanVien.soDienThoai);
	$(".nhanVienTienLuongForm").val(nhanVien.tienLuong);
	$(".nhanVienGhiChuForm").val(nhanVien.ghiChu);
	if (nhanVien.phongBan == null) {
		$(".nhanVienPhongBanForm").val("null");
	}
	else {
		$(".nhanVienPhongBanForm").val(nhanVien.phongBan.maPhongBan);
	}
	$(".nhanVienHinhForm").val('');
	
	$('.nhanVienForm').validate().form();
}