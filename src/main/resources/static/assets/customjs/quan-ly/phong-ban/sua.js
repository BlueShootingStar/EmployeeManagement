// quan-ly/phong-ban/sua.js

/**
 * ============================================================================
 * */
$(function() {
	
	let $phongBanFormSua = "#phongBanFormChiTiet";
	$($phongBanFormSua).on('submit', function(e) {
	    e.preventDefault();
	    
	    if (!$($phongBanFormSua).valid())
	    	return;
	    
	    let maPhongBan = $($phongBanFormSua + " input[name=maPhongBan]").val();

	    var url = `api/v1/phongbans/update/${maPhongBan}`;
	    let data = {
	    	maPhongBan : maPhongBan,
			tenPhongBan : $($phongBanFormSua + " input[name=tenPhongBan]").val(),
	    };
	    
	    let getData = $.ajax({
	    	contentType: 'application/json; charset=utf-8',
			type : "PUT",
			url : url,
			data : JSON.stringify(data)
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
});

/**
 * ============================================================================
 * */
function setPhongBanChiTiet(formId, sourceId) {
	$(formId).validate().resetForm();
//	clearForm();
	
	let maPhongBan = $(sourceId).data('ma-phong-ban');
	let tenPhongBan = $(sourceId).data('ten-phong-ban');
	
	$(formId + " input").removeClass('error');
	
	$(formId + " input[name=maPhongBan]").val(maPhongBan);
	$(formId + " input[name=tenPhongBan]").val(tenPhongBan);
}