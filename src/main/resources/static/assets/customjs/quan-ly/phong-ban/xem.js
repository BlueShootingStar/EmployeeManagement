// quan-ly/phong-ban/xem.js

/**
 * ============================================================================
 * */
$(function() {
	// tải trang khi refresh
	loadPageAjax();
});

/**
 * ============================================================================
 * */
function loadPageAjax() {
	
	let url = `api/v1/phongbans/findAllWithDetailData`;
	
	let getData = $.ajax({
		type: "GET",
		url: url
	});
	
	getData.done(function(response) {
		if (response.thanhCong) {
			let soLuongPhongBan = Object.keys(response.doiTuong).length;
			$("#soLuongPhongBan").text(`Tổng số ${soLuongPhongBan} phòng ban`);
			
        	// reset bảng và đưa dữ liệu mới vào
        	updateList(response.doiTuong);
        	// update các ô số trang
//        	updatePagePart(response.doiTuong.totalPages, trangHienTai);
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
function updateList(listObj) {
	let $listPhongBan = "#listPhongBan";
	
	$($listPhongBan).fadeOut("fast").promise()
	.then(function() {
		$($listPhongBan).empty();
		$.each(listObj, function(index, obj) {
	        $($listPhongBan).append(createRow(index, obj));
	    });
	})
	.then(function() {
		$($listPhongBan).fadeIn("fast");
	})
	;
}

/**
 * ============================================================================
 * */
function createRow(index, obj) {
	let row =
	`<div class="col-lg-6">`+
		`<div ` +
			`style="display:none;" ` +
			`id="phongBan${obj.maPhongBan}" ` +
			`data-ma-phong-ban="${obj.maPhongBan}" ` +
			`data-ten-phong-ban="${obj.tenPhongBan}" ` +
		`>` +
		`</div>` +
	
		`<div class="card">`+
			`<div class="card-body">`+
				`<div style="display:none;"></div>`+
				`<h5 class="card-title text-warning"><i class="fa fa-cube"></i> ${obj.tenPhongBan.toUpperCase()}</h5>`+
				`<div class="row">`+
					`<div class="col-6">`+
						`<p class="card-text"><i class="icon-people icons"></i>Số lượng nhân viên: ${obj.soLuongNhanVienNam + obj.soLuongNhanVienNu}</p>`+
						`<p class="card-text"><i class='icons text-primary icon-symbol-male'></i>Nhân viên nam: ${obj.soLuongNhanVienNam}</p>`+
						`<p class="card-text"><i class='icons text-danger icon-symbol-female'></i>Nhân viên nữ: ${obj.soLuongNhanVienNu}</p>`+
					`</div>`+
					`<div class="col-6">`+
						`<div class="float-left">`+
							`<p class="card-text"><span class="text-primary">Ghi nhận</span><span class="badge badge-primary shadow-primary m-1">${obj.tongDiemGhiNhanThuong + obj.tongDiemGhiNhanPhat}</span></p>`+
							`<p class="card-text"><span class="text-success">Điểm thưởng</span><span class="badge badge-success shadow-success m-1">${obj.tongDiemGhiNhanThuong}</span></p>`+
							`<p class="card-text"><span class="text-danger">Điểm phạt</span><span class="badge badge-danger shadow-danger m-1">${obj.tongDiemGhiNhanPhat}</span></p>`+
							`<p class="card-text"><span class="text-warning">Tổng điểm</span><span class="badge badge-warning shadow-warning m-1">${obj.tongDiemGhiNhanThuong - obj.tongDiemGhiNhanPhat}</span></p>`+
						`</div>`+
					`</div>`+
				`</div>`+
				`<hr>`+
				`<button onclick="setPhongBanChiTiet('#modal-chi-tiet-phong-ban', '#phongBan${obj.maPhongBan}')" class="mr-2 btn btn-success shadow-success waves-effect waves-light" data-toggle="modal" data-target="#modal-chi-tiet-phong-ban"><i class="fa fa-pencil"></i></button>`+
				`<button onclick="xoaPhongBan(${obj.maPhongBan}, '${obj.tenPhongBan}')" class="btn btn-danger shadow-danger waves-effect waves-light"><i class="fa fa-trash-o"></i></button>`+
			`</div>`+
		`</div>`+
	`</div>`;
	return row;
}

///**
// * ============================================================================
// * */
//function updatePagePart(tongSoTrang, trangHienTai) {
//	let element = ".page-part";
//	
//	$(element).empty();
//	
//	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(1)'><<</a></li>`);
//	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(${trangHienTai - 1})'><</a></li>`);
//	
//	let tongSoO = 5; // nên là số lẻ cho đẹp
//	
//	let count = 1;
//	let i;
//	
//	// nếu đang ở trang mà cách trang cuối (tổng số trang - (tổng số ô / 2)) ô
//	if (trangHienTai >= (tongSoTrang - Math.floor(tongSoO / 2))) {
//		i = tongSoTrang - tongSoO + 1; // bắt đầu in từ vị trí (trang cuối - tổng số ô)
//	}
//	else {
//		i = trangHienTai - Math.floor(tongSoO / 2); // bắt đầu in từ vị trí (trang hiện tại - (tổng số ô / 2))
//	}
//	
//	while (count <= tongSoO && count <= tongSoTrang) {
//		if (i < 1) { // nếu là trang âm thì không in
//			i++;
//			continue;
//		}
//		
//		// in
//		$(element).append(
//			`<li class='page-item ${(i == trangHienTai) ? 'active' : ''}'>` +
//				`<a class='page-link' onclick='loadPageAjax(${i})'>${i}</a>` +
//			`</li>`
//		);
//		count++;
//		i++;
//	}
//	
//	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(${trangHienTai + 1})'>></a></li>`);
//	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(${tongSoTrang})'>>></a></li>`);
//}