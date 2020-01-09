// quan-ly/nhan-vien

/**
 * ============================================================================
 * */
$(function() {
	// tải trang khi refresh
	loadComboPhongBanAjax();
	loadPageAjax(1);
	
	$("#tuKhoaHoTen").on("keyup", function() {
		
		if ($(this).val().trim().length == 0)
			return;
		
		$("#tuKhoaHoTen").addClass('loading');
		doSearch();
	});
	
	$("#soLuongMuc, #timKiemPhongBan").change(function() {
		loadPageAjax(1);
	});
});

/**
 * ============================================================================
 * */
function loadComboPhongBanAjax() {
	let getData = $.ajax({
		type: "GET",
		url: `api/v1/phongbans/findAll`
	});
	
	getData.done(function(response) {
		if (response.thanhCong) {
			$.each(response.doiTuong, function(index, obj) {
				$('#timKiemPhongBan')
			         .append($("<option></option>").attr("value", obj.tenPhongBan).text(obj.tenPhongBan)); 
				$('.cboPhongBan')
					.append($("<option></option>").attr("value", obj.maPhongBan).text(obj.tenPhongBan)); 
			});
		}
		else {
			SweetAlertHelper.thatBai(response.thongBao);
		}
	});
	
	getData.fail(function(request, status, error) {
		SweetAlertHelper.thatBai(request.responseText);
	});
}

/**
 * ============================================================================
 * */
function loadPageAjax(trangHienTai) {
	let tuKhoaHoTen = $("#tuKhoaHoTen").val();
	let tenPhongBan = $("#timKiemPhongBan").children("option:selected").val();
	let soLuongMuc = $("#soLuongMuc").children("option:selected").val();
	
	let url = `api/v1/nhanviens/findAll?` +
	`tuKhoaHoTen=${tuKhoaHoTen}` +
	`&` + `tenPhongBan=${tenPhongBan}` +
	`&` + `page=${trangHienTai}` + 
	`&` + `size=${soLuongMuc}`;
	
	let getData = $.ajax({
		type: "GET",
		url: url
	});
	
	getData.done(function(response) {
		if (response.thanhCong) {
			
			$("#soLuongNhanVien").text(`Tổng số ${response.doiTuong.totalElements} nhân viên`);
			
        	// reset bảng và đưa dữ liệu mới vào
        	updateList(response.doiTuong.content);
        	// update các ô số trang
        	updatePagePart(response.doiTuong.totalPages, trangHienTai);
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
	let $listNhanVien = "#listNhanVien";
	
	$($listNhanVien).fadeOut("fast").promise()
	.then(function() {
		$($listNhanVien).empty();
		$.each(listObj, function(index, obj) {
	        $($listNhanVien).append(createRow(index, obj));
	    });
	})
	.then(function() {
		$($listNhanVien).fadeIn("fast");
	})
	;
}

/**
 * ============================================================================
 * */
function createRow(index, obj) {
	let row =
	`<div class="card bg-light card-primary mt-3 h-100">` +
		`<div class="row no-gutters">` +
			`<div class="col-md-3">` +
				`<img onerror="this.src='assets/images/no-image.jpg'" src="images/avatars/nhan-vien/${obj.hinh}" class="card-img shadow-primary" alt="">` +
			`</div>` +
			`<div class="col-md-7 py-2">` +
				`<div class="card-body">` +
					`<h5 class="card-title">` +
						`<i class="icon-user icons"></i>` +
						`${obj.hoTen} ` +
						`<i class='icons ${obj.gioiTinh ? 'text-primary icon-symbol-male' : 'text-danger icon-symbol-female'}'></i>` +
					`</h5>` +
					`<p class="card-text">` +
						`<small class="text-muted">${obj.email}</small>` +
					`</p>` +
					`<p class="card-text">` +
						`<i class="icon-wallet icons"></i>` +
						`<strong>Lương</strong>: ${obj.tienLuong} triệu VNĐ` +
					`</p>` +
					`<p class="card-text">` +
						`<i class="icon-layers icons"></i>` +
						`<strong>Phòng ban</strong>: ${obj.phongBan == null ? '<strong class="text-danger">Chưa có</strong>' : obj.phongBan.tenPhongBan}` +
					`</p>` +
					`<p class="card-text">` +
						`<i class="icon-pin icons"></i>` +
						`<strong>Ghi chú</strong>: ${obj.ghiChu.length == 0 ? 'Trống' : obj.ghiChu}` +
					`</p>` +
				`</div>` +
			`</div>` +
			`<div class="col-md-2 py-3">` +
				`<button onclick="window.open('quan-ly/nhan-vien/chi-tiet/${obj.maNhanVien}', '_blank')" type='button' class='btn shadow-success btn-success waves-effect waves-light m-1'>` +
	   				`<i class='fa fa-pencil'></i>` +
	   			`</button>` +
	   			`<button onclick='xoaNhanVien(${obj.maNhanVien}, "${obj.hoTen}")' type='button' class='btn shadow-danger btn-danger waves-effect waves-light m-1'>` +
					`<i class='fa fa-trash-o'></i>` +
				`</button>` +
			`</div>` +
		`</div>` +
	`</div>`;
	return row;
}

/**
 * ============================================================================
 * */
function updatePagePart(tongSoTrang, trangHienTai) {
	let element = ".page-part";
	
	$(element).empty();
	
	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(1)'><<</a></li>`);
	$(element).append(`<li class='page-item ${trangHienTai > 1 ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(${trangHienTai - 1})'><</a></li>`);
	
	let tongSoO = 5; // nên là số lẻ cho đẹp
	
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
				`<a class='page-link' onclick='loadPageAjax(${i})'>${i}</a>` +
			`</li>`
		);
		count++;
		i++;
	}
	
	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(${trangHienTai + 1})'>></a></li>`);
	$(element).append(`<li class='page-item ${trangHienTai < tongSoTrang ? '' : 'disabled'}'><a class='page-link' onclick='loadPageAjax(${tongSoTrang})'>>></a></li>`);
}

/**
 * ============================================================================
 * */
var delayTimer;
function doSearch() {
    clearTimeout(delayTimer);
    
    delayTimer = setTimeout(function() {
    	loadPageAjax(1).always(function(response) {
    		$("#tuKhoaHoTen").removeClass('loading');
    	});
    	
    }, 700);
}