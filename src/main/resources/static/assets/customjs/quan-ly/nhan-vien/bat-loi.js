// quan-ly/nhan-vien/bat-loi.js

/**
 * ============================================================================
 * */

$(document).ready(function() {
	
	$(".nhanVienForm input[name=soDienThoai]").on("keyup", function() {
		if (this.value.match(/[^0-9]/g)) {
			this.value = this.value.replace(/[^0-9]/g, '');
		}
	});
	
	$(".nhanVienForm").each(function() {
		$(this).validate(
			{
				rules : {
					hoTen : {
						required : true,
						minlength : 3,
						maxlength: 75
					},
					ngaySinh : {
						required : true
					},
					hinh : {
				        accept : "image/jpg"
					},
					email : {
						required : true,
						email : true
					},
					soDienThoai : {
						required : true,
				        minlength : 10,
				        maxlength : 12
					},
					tienLuong : {
						required : true
					}
				},
				messages : {
					hoTen : {
						required : "Vui lòng điền họ tên",
						minlength : "Lý do phải nhiều hơn 3 ký tự",
						maxlength : "Lý do chỉ ít hơn 75 ký tự"
					},
					ngaySinh : {
						required : "Vui lòng điền ngày sinh"
					},
					hinh : {
				        accept : "Chỉ dùng hình có đuôi .jpg"
					},
					email : {
						required : "Vui lòng điền email",
						email : "Email không hợp lệ"
					},
					soDienThoai : {
						required : "Vui lòng điền số điện thoại",
				        minlength : "Số điện thoại gồm 10 - 12 số",
				        maxlength : "Số điện thoại gồm 10 - 12 số"
					},
					tienLuong : {
						required : "Vui lòng nhập lương"
					}
				}
			}
		);
    });
	
});