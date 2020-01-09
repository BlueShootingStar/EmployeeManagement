/**
 * nhấn nút trên hình để chọn hình mới
 */

$(document).ready(function() {
	$('#hinh').attr("onchange", "readURL(this, '#nhanVienHinh');");
	
	$("#btnTaiHinh").click(function() {
	    $("#hinh").focus().click();
	});
});

function readURL(input, imgId) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $(imgId).attr('src', e.target.result);
        };

        reader.readAsDataURL(input.files[0]);
    }
}
