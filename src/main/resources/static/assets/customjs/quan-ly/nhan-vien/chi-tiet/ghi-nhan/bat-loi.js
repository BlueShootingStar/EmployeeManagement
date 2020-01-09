// quan-ly/nhan-vien/chi-tiet/ghi-nhan/bat-loi.js

/**
 * ============================================================================
 * */

$(document).ready(function() {
	
	$(".ghiNhanForm").each(function() {
		$(this).validate(
			{
				rules : {
					ngayGhiNhan : "required",
					lyDo : {
						required : true,
						minlength : 3,
						maxlength: 200
					}
				},
				messages : {
					ngayGhiNhan : "Vui lòng chọn ngày ghi nhận",
					lyDo : {
						required : "Vui lòng viết lý do",
						minlength : "Lý do phải nhiều hơn 3 ký tự",
						maxlength : "Lý do chỉ ít hơn 200 ký tự"
					}
				}
			}
		);
    });
	
});