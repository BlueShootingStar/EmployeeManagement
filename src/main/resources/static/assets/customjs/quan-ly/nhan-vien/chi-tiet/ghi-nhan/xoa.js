// quan-ly/nhan-vien/chi-tiet/ghi-nhan/xoa.js

function xoaGhiNhan(maGhiNhan, loaiGhiNhan, ngayGhiNhan, lyDo) {
	SweetAlertHelper.hoi(`Xóa ghi nhận <span class="text-${loaiGhiNhan ? 'success' : 'danger'}">${loaiGhiNhan ? 'thưởng' : 'phạt'}</span> ngày <b>${ngayGhiNhan}</b> với lý do <br> <b>${lyDo}</b>`, function() {
		SweetAlertHelper.choXuLy();
		
		let url = `api/v1/ghinhans/deleteById/${maGhiNhan}`;
		
		let getData = $.ajax({
			type: "DELETE",
			url: url
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
}