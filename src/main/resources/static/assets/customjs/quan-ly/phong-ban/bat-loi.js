// quan-ly/phong-ban/bat-loi.js

/**
 * ============================================================================
 * */
$(document).ready(function() {
	
	$(".phongBanForm").each(function() {
		$(this).validate(
			{
				rules : {
					tenPhongBan : {
						required : true,
						minlength : 3,
						maxlength: 50
					}
				},
				messages : {
					tenPhongBan : {
						required : "Vui lòng điền tên phòng ban",
						minlength : "Lý do phải nhiều hơn 3 ký tự",
						maxlength : "Lý do chỉ ít hơn 50 ký tự"
					}
				}
			}
		);
    });
	
});