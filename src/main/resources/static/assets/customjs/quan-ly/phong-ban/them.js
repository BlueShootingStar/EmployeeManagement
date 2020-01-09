// quan-ly/phong-ban/them.js

/**
 * ============================================================================
 * */
$(function() {
	let $phongBanFormThem = "#phongBanFormThem";
	$($phongBanFormThem).on('submit', function(e) {
		e.preventDefault();
	    
	    if (!$($phongBanFormThem).valid())
	    	return;
	    
	    SweetAlertHelper.choXuLy();
	    
	    var url = `api/v1/phongbans/insert`;

	    let data = {
	    	tenPhongBan : $($phongBanFormThem + " input[name=tenPhongBan]").val()
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
				clearForm();
				loadPageAjax();
				return;
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
function clearForm() {
	$('.phongBanForm').validate().resetForm();

	// form
	$(".phongBanMaPhongBanForm").val("");
	$(".phongBanTenPhongBanForm").val("");
	
}