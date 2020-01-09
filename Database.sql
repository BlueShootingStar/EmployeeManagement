/*
	MSSV: PS08464 - Đinh Đạt Thông
*/

/* 1 tạo bảng */

use master
if db_id('AssignmentJava5') is not null
	begin
		drop database AssignmentJava5
	end
go

create database AssignmentJava5
go

use AssignmentJava5
go

-- 1. Bảng NguoiDung
create table NguoiDung (
	tenDangNhap varchar(50),
	matKhau varchar(255) not null,
	hoTen nvarchar(75) not null,
	email varchar(100) not null,
	hinh varchar(54) not null,

	primary key (tenDangNhap)
)
go

-- 2. Bảng PhongBan
create table PhongBan (
	maPhongBan int identity(1,1),
	tenPhongBan nvarchar(50) not null,
	
	primary key (maPhongBan)
)
go

-- 3. Bảng NhanVien
create table NhanVien (
	maNhanVien int identity(1,1),
	hoTen nvarchar(75) not null,
	gioiTinh bit not null,
	ngaySinh date not null,
	hinh varchar(20) not null,
	email varchar(75) not null,
	soDienThoai varchar(12) not null,
	tienLuong money not null,
	ghiChu nvarchar(max) not null,
	maPhongBan int,

	primary key (maNhanVien),
	foreign key (maPhongBan) references PhongBan(maPhongBan) on delete set null
)
go

-- 4. Bảng GhiNhan
create table GhiNhan (
	maGhiNhan int identity(1,1),
	loaiGhiNhan bit not null,
	lyDo nvarchar(200) not null,
	ngayGhiNhan date not null,
	maNhanVien int,

	primary key (maGhiNhan),
	foreign key (maNhanVien) references NhanVien(maNhanVien) on delete cascade,
	
	constraint CHK_ngayGhiNhan check (ngayGhiNhan <= CAST(GETDATE() AS DATE))
)
go

/* hết phần 1 tạo bảng */

/* 2 nhập dữ liệu */

use master
go

use AssignmentJava5
go

-- 1. Bảng NguoiDung
insert into NguoiDung
values 
--	tenDangNhap		matKhau																	hoTen				email					hinh --
	('hao',			'$2a$10$X6cJgcyflgYgeAnlC0W26.3G18hOWmTGeaQNrV9igiyWeHy8uzHT2',			N'Nguyễn Đại Hào',	'haond@gmail.com',		'hao.jpg'),
	('thanh',		'$2a$10$X6cJgcyflgYgeAnlC0W26.3G18hOWmTGeaQNrV9igiyWeHy8uzHT2',			N'Lê Long Thành',	'thanhll@gmail.com',	'thanh.jpg'),
	('tien',		'$2a$10$X6cJgcyflgYgeAnlC0W26.3G18hOWmTGeaQNrV9igiyWeHy8uzHT2',			N'Đào Quang Tiến',	'tiendq@gmail.com',		'tien.jpg'),
	('thong',		'$2a$10$X6cJgcyflgYgeAnlC0W26.3G18hOWmTGeaQNrV9igiyWeHy8uzHT2',			N'Đinh Đạt Thông',	'thongdd@gmail.com',	'thong.jpg')
go

-- 2. Bảng PhongBan
insert into PhongBan
values 
--	tenPhongBan	--		
	(N'Kỹ thuật'),
	(N'Nhân sự'),
	(N'Kế toán'),
	(N'Kinh Doanh')
go

-- 3. Bảng NhanVien
insert into NhanVien
values 
--	hoTen						gioiTinh	ngaySinh		hinh		email						soDienThoai		tienLuong	ghiChu		maPhongBan --		
	(N'Nguyễn Thị Minh Ngọc',	'false',	'12/13/1995',	'1.jpg',	'ngocntm01@gmail.com',		'0376555796',	5,			'',		1),
	(N'Nguyễn Bùi Thanh Sang',	'true',		'10/25/1997',	'2.jpg',	'sangnbt02@gmail.com',		'0367716718',	8,			'',		3),			
	(N'Lê Thành Tiến',			'true',		'02/21/1992',	'3.jpg',	'tienlt03@gmail.com',		'0248795623',	6,			'',		3),
	(N'Nguyễn Hữu Minh',		'true',		'07/21/1993',	'4.jpg',	'minhnh04@gmail.com',		'0457898965',	9,			'',		1),
	(N'Trần Công Mạnh',			'true',		'02/14/1994',	'5.jpg',	'manhtc05@gmail.com',		'0121479895',	7,			'',		2),
	(N'Lê Thị Ái',				'false',	'11/04/1998',	'6.jpg',	'ailt06@gmail.com',			'0654689896',	5,			'',		2),
	(N'Hoàng Ngọc Minh Châu',	'false',	'12/24/1997',	'7.jpg',	'chauhnm07@gmail.com',		'0146789984',	5,			'',		3),
	(N'Trần Công Phước',		'true',		'04/03/1995',	'8.jpg',	'phuoctc08@gmail.com',		'0424775754',	4,			'',		3),
	(N'Lê Văn Dũng',			'true',		'05/05/1995',	'9.jpg',	'dunglv09@gmail.com',		'0147887542',	2,			'',		2),
	(N'Trần Anh Khoa',			'true',		'07/19/1991',	'10.jpg',	'khoata10@gmail.com',		'0165987977',	5,			'',		1),
	(N'Nguyễn Ngọc Đông Nhi',	'false',	'10/01/1995',	'11.jpg',	'nhinnd11@gmail.com',		'0312346778',	6,			'',		3),
	(N'Trần Minh Thư',			'false',	'11/02/1997',	'12.jpg',	'thutm12@gmail.com',		'0187845235',	10,			'',		2),
	(N'Nguyễn Thiện Nhân',		'true',		'04/07/1994',	'13.jpg',	'nhannt13@gmail.com',		'0198786565',	5,			'',		1),
	(N'Trần Lê Yến Nhi',		'false',	'11/12/1996',	'14.jpg',	'nhitly14@gmail.com',		'0332468798',	5,			'',		1),
	(N'Nguyễn Quốc Tuấn',		'true',		'01/18/1997',	'15.jpg',	'tuannq15@gmail.com',		'0987987653',	4,			'',		1),
	(N'Lê Quỳnh Anh',			'false',	'08/13/1994',	'16.jpg',	'anhlq16@gmail.com',		'0124545498',	4,			'',		2),
	(N'Nguyễn Thu Mai',			'false',	'01/19/1993',	'17.jpg',	'maint17@gmail.com',		'0332168988',	9,			'',		2),
	(N'Lý Thị Hồng Vân',		'false',	'05/17/1995',	'18.jpg',	'vanlth18@gmail.com',		'0145454945',	7,			'',		3),
	(N'Nguyễn Thị Duyên',		'false',	'06/24/1996',	'19.jpg',	'duyennt19@gmail.com',		'0235468989',	6,			'',		3),
	(N'Đinh Tiến Luật',			'true',		'05/28/1999',	'20.jpg',	'luatdt20@gmail.com',		'0468798462',	8,			'',		1),
	(N'Trần Lê Anh Tú',			'false',	'07/26/1992',	'21.jpg',	'tutla21@gmail.com',		'0989565983',	7,			'',		1),
	(N'Lê Đức Việt',			'true',		'07/23/1995',	'22.jpg',	'vietld22@gmail.com',		'0365468987',	8,			'',		3),
	(N'Lê Minh Hoàng',			'true',		'04/15/1994',	'23.jpg',	'hoanglm23@gmail.com',		'0212654987',	8,			'',		2),
	(N'Trần Quốc Đạt',			'true',		'09/07/1991',	'24.jpg',	'dattq24@gmail.com',		'0845162168',	8,			'',		3),
	(N'Hoàng Ngọc Thư',			'false',	'10/26/1997',	'25.jpg',	'thuhn25@gmail.com',		'0894561232',	5,			'',		2),
	(N'Lê Thị Bảo Ngọc',		'false',	'07/30/1995',	'26.jpg',	'ngocltb26@gmail.com',		'0126589891',	6,			'',		1),
	(N'Tiêu Kim Hường',			'false',	'12/03/1992',	'27.jpg',	'huongtk27@gmail.com',		'0321254489',	6,			'',		1),
	(N'Hà Tán Thành',			'true',		'08/30/1992',	'28.jpg',	'thanhht28@gmail.com',		'0121545789',	9,			'',		3),
	(N'Nguyễn Ngọc Quốc Thịnh',	'true',		'04/16/1996',	'29.jpg',	'thinhnnq29@gmail.com',		'0132165498',	7,			'',		3),
	(N'Lê Công Vương',			'true',		'03/04/1995',	'30.jpg',	'vuonglc30@gmail.com',		'0321546895',	6,			'',		2),
	(N'Trần Nhật Tân',			'true',		'03/15/1996',	'31.jpg',	'tantn31@gmail.com',		'0121654988',	15,			'',		1),
	(N'Nguyễn Minh Nhật',		'true',		'03/30/1994',	'32.jpg',	'nhatnm32@gmail.com',		'0212154895',	11,			'',		2),
	(N'Nguyễn Đức Tài',			'true',		'08/25/1997',	'33.jpg',	'taind33@gmail.com',		'0116489553',	10,			'',		1),
	(N'Nguyễn Minh Đức',		'true',		'02/18/1998',	'34.jpg',	'ducnm34@gmail.com',		'0356579854',	9,			'',		3),
	(N'Trần Thị Ngọc Mai',		'false',	'07/28/1998',	'35.jpg',	'maittn35@gmail.com',		'0121245478',	8,			'',		1),
	(N'Trần Ngọc Trang',		'false',	'11/07/1995',	'36.jpg',	'trangtn36@gmail.com',		'0233546898',	6,			'',		1),
	(N'Lê Anh Tú',				'true',		'12/14/1992',	'37.jpg',	'tula37@gmail.com',			'0213548896',	2,			'',		1),
	(N'Lê Văn Phụng',			'true',		'12/23/1992',	'38.jpg',	'phunglv38@gmail.com',		'0124154578',	8,			'',		2),
	(N'Tôn Thất Nhật Khánh',	'true',		'10/15/1993',	'39.jpg',	'khanhttn39@gmail.com',		'0133546549',	4,			'',		3),
	(N'Nguyễn Xuân Duyên',		'false',	'07/19/1994',	'40.jpg',	'duyenxn40@gmail.com',		'0132456488',	6,			'',		3),
	(N'Lê Chánh Anh Khoa',		'true',		'06/02/1993',	'41.jpg',	'khoalca41@gmail.com',		'0115455687',	5,			'',		3),
	(N'Huỳnh Ngọc Phương Mai',	'false',	'03/06/1992',	'42.jpg',	'maihnp42@gmail.com',		'0132465488',	4,			'',		1),
	(N'Đỗ Hoàng Minh',			'true',		'04/03/1995',	'43.jpg',	'minhdh43@gmail.com',		'0124678982',	3,			'',		1),
	(N'Đinh Quốc Mạnh',			'true',		'05/15/1992',	'44.jpg',	'manhdq44@gmail.com',		'0154687982',	4,			'',		1),
	(N'Lê Đình Tiến',			'true',		'08/28/1996',	'45.jpg',	'tienld45@gmail.com',		'0124354545',	8,			'',		1),
	(N'Nguyễn Hoàng Thịnh',		'true',		'12/12/1995',	'46.jpg',	'thinhnh46@gmail.com',		'0146579895',	7,			'',		1),
	(N'Trần Lê Thiên Ngân',		'false',	'04/23/1996',	'47.jpg',	'ngantlt47@gmail.com',		'0145798352',	9,			'',		1),
	(N'Phạm Ngọc Như',			'false',	'08/15/1997',	'48.jpg',	'nhupn48@gmail.com',		'0144578668',	6,			'',		2)
go

-- 4. Bảng GhiNhan
insert into GhiNhan
values 
--	loaiGhiNhan			lyDo					ngayGhiNhan		maNhanVien --		
	('true',			N'Công tác tốt',		'08/01/2019',	1),
	('false',			N'Đi trễ',				'09/01/2019',	2),
	('true',			N'Công tác tốt',		'03/01/2019',	1),
	('false',			N'Đi trễ',				'05/24/2018',	44),
	('false',			N'Thái độ không tốt',	'04/01/2019',	2),
	('true',			N'Công tác tốt',		'10/06/2019',	23),
	('true',			N'Công tác tốt',		'08/24/2019',	2),
	('true',			N'Công tác tốt',		'10/12/2019',	2),
	('false',			N'Vi phạm nội quy',		'10/01/2019',	3),
	('false',			N'Đi trễ',				'10/20/2019',	1),
	('true',			N'Công tác tốt',		'10/01/2019',	1),
	('false',			N'Nghỉ việc không báo',	'09/14/2019',	1),
	('true',			N'Công tác tốt',		'08/09/2019',	1),
	('false',			N'Thành tích loại kém',	'09/01/2019',	5),
	('true',			N'Công tác tốt',		'10/01/2019',	3),
	('false',			N'Thành tích loại kém',	'07/28/2019',	1),
	('true',			N'Công tác tốt',		'06/30/2019',	1),
	('true',			N'Công tác tốt',		'08/24/2019',	1)
go

/* hết phần 2 nhập dữ liệu */

/* 3 tạo thủ tục */
-- 1. 
if (object_id('sp_ThongTinChiTietPhongBan') is not null)
	drop procedure sp_ThongTinChiTietPhongBan
go
create procedure sp_ThongTinChiTietPhongBan
as
	select
		PhongBan.maPhongBan,
		tenPhongBan,
		sum(case When gioiTinh='true' then 1 else 0 end) as [soLuongNhanVienNam],
		sum(case When gioiTinh='false' then 1 else 0 end) as [soLuongNhanVienNu],
		sum(case When loaiGhiNhan='true' then 1 else 0 end) as [tongDiemGhiNhanThuong],
		sum(case When loaiGhiNhan='false' then 1 else 0 end) as [tongDiemGhiNhanPhat]
	from PhongBan
		left join NhanVien on PhongBan.maPhongBan = NhanVien.maPhongBan
		left join GhiNhan on NhanVien.maNhanVien = GhiNhan.maGhiNhan
	group by PhongBan.maPhongBan, tenPhongBan
go

-- 2.
if (object_id('sp_TongDiemCuaNhanVienThangNay') is not null)
	drop procedure sp_TongDiemCuaNhanVienThangNay
go
create procedure sp_TongDiemCuaNhanVienThangNay
	@maNhanVien int
as
	select
		NhanVien.maNhanVien,
		soDienThoai,
		(sum(case when loaiGhiNhan='true' then 1 else 0 end) - sum(case when loaiGhiNhan='false' then 1 else 0 end)) as [tongSoDiem]
	from NhanVien
		left join GhiNhan on NhanVien.maNhanVien = GhiNhan.maNhanVien
	where
		NhanVien.maNhanVien = @maNhanVien
		and year(ngayGhiNhan) = year(getdate())
		and month(ngayGhiNhan) = month(getdate())
	group by NhanVien.maNhanVien, soDienThoai
go

-- 3.

-- 4.

-- Thống kê --
-- 1. 
if (object_id('sp_ThongKeSoLuong') is not null)
	drop procedure sp_ThongKeSoLuong
go
create procedure sp_ThongKeSoLuong
as
	select soLuongNhanVien, soLuongPhongBan, soLuongGhiNhan, soLuongGhiNhanThuong, soLuongGhiNhanPhat
	from
		(select count(*) as [soLuongNhanVien] from NhanVien) t1
	,
		(select count(*) as [soLuongPhongBan] from PhongBan) t2
	,
		(select count(*) as [soLuongGhiNhan] from GhiNhan) t3
	,
		(select count(*) as [soLuongGhiNhanThuong] from GhiNhan where loaiGhiNhan = 'true') t4
	,
		(select count(*) as [soLuongGhiNhanPhat] from GhiNhan where loaiGhiNhan = 'false') t5
go

-- 2.
if (object_id('sp_Top5NhanVienCoDiemCaoNhatTrongThang') is not null)
	drop procedure sp_Top5NhanVienCoDiemCaoNhatTrongThang
go
create procedure sp_Top5NhanVienCoDiemCaoNhatTrongThang
	@soLuongThangCachHomNay int
as
	select top 5
		NhanVien.maNhanVien,
		hoTen,
		gioiTinh,
		hinh,
		tenPhongBan,
		sum(case when loaiGhiNhan='true' then 1 else 0 end) as [tongSoGhiNhanThuong],
		sum(case when loaiGhiNhan='false' then 1 else 0 end) as [tongSoGhiNhanPhat],
		(sum(case when loaiGhiNhan='true' then 1 else 0 end) - sum(case when loaiGhiNhan='false' then 1 else 0 end)) as [tongSoDiem]
	from NhanVien
		left join PhongBan on NhanVien.maPhongBan = PhongBan.maPhongBan
		left join GhiNhan on NhanVien.maNhanVien = GhiNhan.maNhanVien
	where 
		year(ngayGhiNhan) = year(dateadd(month, -@soLuongThangCachHomNay, getdate()))
		and month(ngayGhiNhan) = month(dateadd(month, -@soLuongThangCachHomNay, getdate()))

	group by NhanVien.maNhanVien, hoTen, gioiTinh, hinh, tenPhongBan
	having (sum(case when loaiGhiNhan='true' then 1 else 0 end) - sum(case when loaiGhiNhan='false' then 1 else 0 end)) > 0
	order by tongSoDiem desc
go

-- 3.

-- 4.

/* hết phần 3 tạo thủ tục */

/* 4 tạo trigger */

/* hết phần 4 tạo Trigger */

/* 5 tạo người dùng */

-- 1. quản trị viên của cơ sở dữ liệu AssignmentJava5

	-- 1.1 tạo tên đăng nhập
	use master
	if exists (select name from master.sys.server_principals where name = 'AssignmentJava5_QTV')
		begin
			drop login AssignmentJava5_QTV
		end
	go
	create login AssignmentJava5_QTV with password = '123', default_database = AssignmentJava5

	-- 1.2 tạo người dùng cho tên đăng nhập trên
	use AssignmentJava5
	if exists (select name from sys.database_principals where name = 'AssignmentJava5_QTV')
		begin
			drop user AssignmentJava5_QTV
		end
	go
	create user AssignmentJava5_QTV for login AssignmentJava5_QTV

	-- 1.3 thêm vai trò cho người dùng trên
	use AssignmentJava5
	alter role [db_owner] add member AssignmentJava5_QTV;

/* hết phần 5 tạo người dùng */