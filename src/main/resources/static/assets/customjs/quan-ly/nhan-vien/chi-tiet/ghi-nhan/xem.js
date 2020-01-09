// quan-ly/nhan-vien/chi-tiet/ghi-nhan/xem.js

/**
 * ============================================================================
 * */
$(function() {
	// tải trang khi refresh
	loadGhiNhanAjax(1, 1);
});

/**
 * ============================================================================
 * */
function loadGhiNhanAjax(trangHienTaiThuong, trangHienTaiPhat) {
	let maNhanVien = $('#maNhanVien').data('ma-nhan-vien');
	let url =
		`api/v1/ghinhans/findAllByMaNhanVien/${maNhanVien}?orderByNgayGhiNhanDesc=true` +
		`&pageThuong=${trangHienTaiThuong}` +
		`&pagePhat=${trangHienTaiPhat}`
		;
	
	let getData = $.ajax({
		type: "GET",
		url: url
	});
	
	getData.done(function(response) {
		if (response.thanhCong) {
			setThongTinSoLuongGhiNhan(response.doiTuong);
			updateListGhiNhanGanNhat(response.doiTuong.listGhiNhanGanNhat);
			updateListGhiNhanThuong(response.doiTuong.listGhiNhanThuong.content);
			updateListGhiNhanPhat(response.doiTuong.listGhiNhanPhat.content);
			
			updatePagePartGhiNhanThuong(response.doiTuong.listGhiNhanThuong.totalPages, trangHienTaiThuong);
			updatePagePartGhiNhanPhat(response.doiTuong.listGhiNhanPhat.totalPages, trangHienTaiPhat);
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
function setThongTinSoLuongGhiNhan(mapGhiNhan) {
	$(".nhanVienSoLuongGhiNhan").text(mapGhiNhan.listGhiNhanThuong.totalElements + mapGhiNhan.listGhiNhanPhat.totalElements);
	$(".nhanVienSoLuongGhiNhanThuong").text(mapGhiNhan.listGhiNhanThuong.totalElements);
	$(".nhanVienSoLuongGhiNhanPhat").text(mapGhiNhan.listGhiNhanPhat.totalElements);
}

/**
 * ============================================================================
 * */
function updateListGhiNhanGanNhat(listObj) {
	
	let $listGhiNhan = ".listGhiNhanGanNhat tbody";
	
	$($listGhiNhan).fadeOut("fast").promise()
	.then(function() {
		$($listGhiNhan).empty();
		$.each(listObj, function(index, obj) {
			let row =
				`<tr>` +
					`<td>` +
						`${obj.loaiGhiNhan ? '<i class="text-success icon-like icons"></i>' : '<i class="text-danger icon-dislike icons"></i>'}` +
						`${obj.lyDo}<strong class="float-right">${obj.ngayGhiNhan}</strong>` +
					`</td>` +
				`</tr>`;
	        $($listGhiNhan).append(row);
	    });
	})
	.then(function() {
		$($listGhiNhan).fadeIn("fast");
	})
	;
}

/**
 * ============================================================================
 * */
function updateListGhiNhanThuong(listObj) {
	
	let $listGhiNhan = ".listGhiNhanThuong";
	
	$($listGhiNhan).fadeOut("fast").promise()
	.then(function() {
		$($listGhiNhan).empty();
		$.each(listObj, function(index, obj) {
			let row =
				`<div class="mb-2 list-group-item list-group-item-success flex-column align-items-start">` +
					`<div ` +
						`style="display:none;" ` +
						`id="ghiNhan${obj.maGhiNhan}" ` +
						`data-ma-ghi-nhan="${obj.maGhiNhan}" ` +
						`data-loai-ghi-nhan="${obj.loaiGhiNhan}" ` +
						`data-ly-do="${obj.lyDo}" ` +
						`data-ngay-ghi-nhan="${obj.ngayGhiNhan}" ` +
						`data-ten-nhan-vien="${obj.nhanVien.hoTen}" ` +
						`>` +
					`</div>` +
					`<div class="d-flex w-100 justify-content-between">` +
						`<small>${obj.ngayGhiNhan}</small>` +
					`</div>` +
					`<p class="mb-1">${obj.lyDo}</p>` +
					`<button onclick="xoaGhiNhan(${obj.maGhiNhan}, ${obj.loaiGhiNhan}, '${obj.ngayGhiNhan}', '${obj.lyDo}')" type='button' class='float-right btn btn-outline-danger waves-effect waves-light m-1'>` +
						`<i class='fa fa-trash-o'></i>` +
					`</button>` +
					`<button onclick="setGhiNhanChiTiet('#modal-chi-tiet-ghi-nhan-thuong', '#ghiNhan${obj.maGhiNhan}')" type='button' class='float-right btn btn-outline-success waves-effect waves-light m-1' data-toggle="modal" data-target="#modal-chi-tiet-ghi-nhan-thuong">` +
		   				`<i class='fa fa-pencil'></i>` +
		   			`</button>` +
				`</div>`;
	        $($listGhiNhan).append(row);
	    });
	})
	.then(function() {
		$($listGhiNhan).fadeIn("fast");
	})
	;
}

/**
 * ============================================================================
 * */
function updateListGhiNhanPhat(listObj) {
	
	let $listGhiNhan = ".listGhiNhanPhat";
	
	$($listGhiNhan).fadeOut("fast").promise()
	.then(function() {
		$($listGhiNhan).empty();
		$.each(listObj, function(index, obj) {
			let row =
				`<div class="mb-2 list-group-item list-group-item-danger flex-column align-items-start">` +
					`<div ` +
						`id="ghiNhan${obj.maGhiNhan}" ` +
						`style="display:none;" ` +
						`data-ma-ghi-nhan="${obj.maGhiNhan}" ` +
						`data-loai-ghi-nhan="${obj.loaiGhiNhan}" ` +
						`data-ly-do="${obj.lyDo}" ` +
						`data-ngay-ghi-nhan="${obj.ngayGhiNhan}" ` +
						`data-ten-nhan-vien="${obj.nhanVien.hoTen}" ` +
						`>` +
					`</div>` +
					`<div class="d-flex w-100 justify-content-between">` +
						`<small>${obj.ngayGhiNhan}</small>` +
					`</div>` +
					`<p class="mb-1">${obj.lyDo}</p>` +
					`<button onclick="xoaGhiNhan(${obj.maGhiNhan}, ${obj.loaiGhiNhan}, '${obj.ngayGhiNhan}', '${obj.lyDo}')" type='button' class='float-right btn btn-outline-danger waves-effect waves-light m-1'>` +
						`<i class='fa fa-trash-o'></i>` +
					`</button>` +
					`<button onclick="setGhiNhanChiTiet('#modal-chi-tiet-ghi-nhan-phat', '#ghiNhan${obj.maGhiNhan}')" type='button' class='float-right btn btn-outline-success waves-effect waves-light m-1' data-toggle="modal" data-target="#modal-chi-tiet-ghi-nhan-phat">` +
	   					`<i class='fa fa-pencil'></i>` +
		   			`</button>` +
				`</div>`;
	        $($listGhiNhan).append(row);
	    });
	})
	.then(function() {
		$($listGhiNhan).fadeIn("fast");
	})
	;
}

/**
 * ============================================================================
 * */
function updatePagePartGhiNhanThuong(tongSoTrang, trangHienTai) {
	let element = ".page-part-ghi-nhan-thuong";
	
	$(element).empty();
	
	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(1, 1)'><<</a></li>`);
	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(${trangHienTai - 1}, 1)'><</a></li>`);
	
	let tongSoO = 3; // nên là số lẻ cho đẹp
	
	let count = 1;
	let i;
	
	// nếu đang ở trang mà cách trang cuối (tổng số trang - (tổng số ô / 2)) ô
	if (trangHienTai >= (tongSoTrang - Math.floor(tongSoO / 2))) {
		i = tongSoTrang - tongSoO + 1; // bắt đầu in từ vị trí (trang cuối - tổng số ô)
	}
	else {
		i = trangHienTai - Math.floor(tongSoO / 2); // bắt đầu in từ vị trí (trang hiện tại - (tổng số ô / 2))
	}
	
	while (count <= tongSoO && count <= tongSoTrang) {
		if (i < 1) { // nếu là trang âm thì không in
			i++;
			continue;
		}
		
		// in
		$(element).append(
			`<li class='page-item ${(i == trangHienTai) ? 'active' : ''}'>` +
				`<a class='page-link' onclick='loadGhiNhanAjax(${i}, 1)'>${i}</a>` +
			`</li>`
		);
		count++;
		i++;
	}
	
	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(${trangHienTai + 1}, 1)'>></a></li>`);
	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(${tongSoTrang}, 1)'>>></a></li>`);
}

/**
 * ============================================================================
 * */
function updatePagePartGhiNhanPhat(tongSoTrang, trangHienTai) {
	let element = ".page-part-ghi-nhan-phat";
	
	$(element).empty();
	
	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(1, 1)'><<</a></li>`);
	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(1, ${trangHienTai - 1})'><</a></li>`);
	
	let tongSoO = 3; // nên là số lẻ cho đẹp
	
	let count = 1;
	let i;
	
	// nếu đang ở trang mà cách trang cuối (tổng số trang - (tổng số ô / 2)) ô
	if (trangHienTai >= (tongSoTrang - Math.floor(tongSoO / 2))) {
		i = tongSoTrang - tongSoO + 1; // bắt đầu in từ vị trí (trang cuối - tổng số ô)
	}
	else {
		i = trangHienTai - Math.floor(tongSoO / 2); // bắt đầu in từ vị trí (trang hiện tại - (tổng số ô / 2))
	}
	
	while (count <= tongSoO && count <= tongSoTrang) {
		if (i < 1) { // nếu là trang âm thì không in
			i++;
			continue;
		}
		
		// in
		$(element).append(
			`<li class='page-item ${(i == trangHienTai) ? 'active' : ''}'>` +
				`<a class='page-link' onclick='loadGhiNhanAjax(1, ${i})'>${i}</a>` +
			`</li>`
		);
		count++;
		i++;
	}
	
	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(1, ${trangHienTai + 1})'>></a></li>`);
	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadGhiNhanAjax(1, ${tongSoTrang})'>>></a></li>`);
}