// quan-ly/phong-ban/xoa.js

function xoaPhongBan(maPhongBan, tenPhongBan) {
	SweetAlertHelper.hoi(`Xóa phòng ban <b>${tenPhongBan}</b> với mã <b>${maPhongBan}</b><br><b style="color:red">Tất cả nhân viên trong phòng này sẽ không thuộc phòng ban nào</b>`, function() {
		SweetAlertHelper.choXuLy();
		
		let url = `api/v1/phongbans/deleteById/${maPhongBan}`;
		
		let getData = $.ajax({
			type: "DELETE",
			url: url
		});
		
		getData.done(function(response) {
			if (response.thanhCong) {
				SweetAlertHelper.thanhCong(response.thongBao);
				loadPageAjax();
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