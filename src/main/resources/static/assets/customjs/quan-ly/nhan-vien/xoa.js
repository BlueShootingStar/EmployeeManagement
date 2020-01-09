// quan-ly/nhan-vien/xoa.js

function xoaNhanVien(maNhanVien, hoTen) {
	SweetAlertHelper.hoi(`Xóa nhân viên <b>${hoTen}</b> với mã <b>${maNhanVien}</b>`, function() {
		SweetAlertHelper.choXuLy();
		
		let url = `api/v1/nhanviens/deleteById/${maNhanVien}`;
		
		let getData = $.ajax({
			type: "DELETE",
			url: url
		});
		
		getData.done(function(response) {
			if (response.thanhCong) {
				SweetAlertHelper.thanhCong(response.thongBao);
				loadPageAjax(1);
			}
			else {
				SweetAlertHelper.thatBai(response.thongBao);
			}
		});
		
		getData.fail(function(request, status, error) {
			SweetAlertHelper.thatBai(request.responseText);
		});
	});
}