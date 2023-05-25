Create database PRO1041_UT
go
USE PRO1041_UT
GO




create table customer (
	id int identity(1,1) primary key,
	full_name nvarchar(45) not null,
	phone nvarchar(11) not null unique,
	email nvarchar(45),
	CMND nvarchar(15) not null unique,
	address nvarchar(255)
)

create table staff (
	id nchar(6) primary key, 
	full_name nvarchar(45) not null,
	role nvarchar(45) not null,
	phone nvarchar(11),
	email nvarchar(45),
	address nvarchar(255),
	username nvarchar(30) not null unique,
	pass nvarchar(30) not null,
	img nvarchar(50)
)

create table booking (
	id int identity(1,1) primary key,
	bk_date date not null,
	checkin_date datetime not null,
	checkout_date datetime not null,
	total_room int not null,
	customer_id int FOREIGN KEY REFERENCES customer(id),
	staff_id nchar(6) FOREIGN KEY REFERENCES staff(id),
	room_id nchar(6)
)

create table rating (
	id int identity(1,1) primary key,
	type nvarchar(255) default(N'Không đánh giá')
)


create table payment (
	id int identity(1,1) primary key,
	payment_type nvarchar(255) not null,
	total_price decimal(10,2) not null,
	rating_id int foreign key references rating(id),
	booking_id int foreign key references booking(id) unique
)

create table room (
	id nchar(6) PRIMARY KEY,
	number int not null unique,
	price decimal(15,2) not null,
	type nvarchar(45) not null,
	description nvarchar(255),
	booking_id int foreign key references booking(id) default(null) 
)

create table service (
	id int identity(1,1) primary key,
	name nvarchar(200) not null,
	price decimal(10,2) not null,
	description nvarchar(255)
)

create table room_service (
	id int identity(1,1) primary key,
	service_id int foreign key references service(id),
	room_id nchar(6) foreign key references room(id),
	bk_date datetime
)


create table convenient (
	id int identity(1,1) primary key,
	name nvarchar(200) not null,
	price decimal(15,2) not null,
	description nvarchar(255),
	room_id nchar(6) foreign key references room(id) on delete cascade on update cascade
)


create table discount (
	id int identity(1,1) primary key,
	rate decimal(5,2) not null,
	start_time date not null,
	end_time date not null,
	room_id nchar(6) foreign key references room(id) on delete cascade on update cascade
)

--newwwww
create table status_room (
	room_id nchar(6) not null foreign key references room(id) on delete cascade on update cascade,
	is_clean bit default(1), --1 đã dọn dẹp 0 chưa dọn
	is_repair bit default(0), -- 1 đang sửa chữa 0 không sửa chữa
	
	primary key(room_id)
)
GO

alter table booking add CONSTRAINT fk_book_room FOREIGN KEY (room_id) REFERENCES room(id)
GO



-------- Trigger Phòng
--thêm update với xoá thì dùng on delete/update cascade
create trigger trg_themphong
on room
for insert as
begin
	declare @id nchar(6) 
	SELECT @id = (select id from inserted)
	insert into status_room values (@id, 1, 0)
end
go


-- hàm tự động tăng id nhân viên
CREATE FUNCTION auto_idnv()
RETURNS VARCHAR(6)
AS
BEGIN
	DECLARE @ID VARCHAR(6)
	IF (SELECT COUNT(id) FROM staff) = 0
		SET @ID = '0'
	ELSE
		SELECT @ID = MAX(RIGHT(id, 3)) FROM staff
		SELECT @ID = CASE
			WHEN @ID >= 0 and @ID < 9 THEN 'NV00' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
			WHEN @ID >= 9 THEN 'NV0' + CONVERT(CHAR, CONVERT(INT, @ID) + 1)
		END
	RETURN @ID
END
GO

-- PROC loi
CREATE PROC sp_service_used_id(@dvid INT)
AS BEGIN
	SELECT
		pdv.room_id,
		dv.id,
		dv.name,
		dv.price,
		pdv.bk_date
	FROM room_service pdv
		INNER JOIN service dv ON pdv.service_id = dv.id
	WHERE dv.id=@dvid
END
GO

CREATE PROC sp_service_used_roomid(@roomid CHAR(6))
AS BEGIN
	SELECT
		pdv.room_id,
		dv.id,
		dv.name,
		dv.price,
		dv.description,
		pdv.bk_date
	FROM room_service pdv
		INNER JOIN service dv ON pdv.service_id = dv.id
	WHERE pdv.room_id=@roomid
END
GO

Drop proc sp_service_used_roomid
Go



CREATE PROC sp_service_used
AS BEGIN
	SELECT
		pdv.room_id,
		dv.id,
		dv.name,
		dv.price,
		pdv.bk_date
	FROM room_service pdv
		INNER JOIN service dv ON pdv.service_id = dv.id
END
GO


-----------------------------------------------------------------------done--------------------
CREATE PROC sp_thongke(@nam CHAR(6))
AS BEGIN
		SELECT t1.[thang], t1.[phòng],COALESCE(t2.[dịch vụ],0) AS 'dịch vụ', t1.[thanh toán] FROM 
		(
		SELECT MONTH(bk_date) AS 'thang', COUNT(total_room) AS 'phòng',SUM(payment.total_price) AS 'thanh toán' FROM booking
		LEFT JOIN payment ON booking.id = payment.booking_id
		WHERE bk_date LIKE @nam
		GROUP BY MONTH(booking.bk_date)
		) t1
		LEFT JOIN
		(
		SELECT MONTH(a.bk_date) as 'thang', SUM(b.price) AS 'dịch vụ' FROM room_service AS a 
		INNER JOIN service AS b ON a.service_id = b.id 
		WHERE a.bk_date LIKE @nam
		GROUP BY MONTH(a.bk_date)
		) t2
		ON (t1.[thang] = t2.[thang])
END
GO
----------------------------------------------------------------------------------------




-- sửa bảng nhân viên default là chạy hàm tự động tăng
ALTER TABLE staff ADD CONSTRAINT df_auto_id DEFAULT dbo.auto_idnv() FOR id

--set null sf_id bảng bôking khi xoá staff
ALTER TABLE [dbo].[booking] ADD CONSTRAINT [FK__booking__staff_id_setnull] FOREIGN KEY ([staff_id]) REFERENCES [dbo].[staff] ([id]) ON DELETE SET NULL

ALTER TABLE discount add unique (room_id)


-- Table NhanVien
-- insert into staff values ('NV001', N'Trần Phước Khánh Huy', N'Quản lý', '0905112552', 'huytpkpd06244@fpt.edu.vn', N'37 Nguyễn Lý', 'blackundo', '123456', null)
-- insert into staff values ('NV002', N'Đặng Văn Võ', N'Nhân viên', '0899477525', 'vodvpd06348@fpt.edu.vn', N'14 Cẩm Chánh 1', 'voxvuive', '123456', null)
-- insert into staff values ('NV003', N'Phạm Hoàng Phúc', N'Lễ tân', '0905123321', 'phucph012343@fpt.edu.vn', N'Cẩm Lệ', 'hoangphuc', '123456', null)
-- insert into staff values ('NV004', N'Trần Văn Hiếu', N'Nhân sự', '0918828282', 'hieutvpd06268@fpt.edu.vn', N'Trần Văn Cẩn', 'vanhieu', '123456', null)
-- go

--nhanvienmoi
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV001 ', N'Trần Phước Khánh Huy', N'Quản lý', N'0905112552', N'huytpkpd06244@fpt.edu.vn', N'37 Nguyễn Lý', N'blackundo', N'123456', N'huy.jpg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV002 ', N'Đặng Văn Võ', N'Kế toán', N'0899477525', N'vodvpd06348@fpt.edu.vn', N'14 Cẩm Chánh 1', N'voxvuive', N'123456', N'vox.jpg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV003 ', N'Phạm Hoàng Phúc', N'Lễ tân', N'0905123321', N'phucph012343@fpt.edu.vn', N'Cẩm Lệ', N'hoangphuc', N'123456', N'meo1.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV005 ', N'Black Undo', N'Lễ tân', N'123', N'123', N'123', N'khanh', N'123', N'meo3.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV006 ', N'Undo Tech', N'Kế toán', N'123', N'33', N'123', N'123', N'123', N'meo5.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV007 ', N'Bảo Nam', N'Kế toán', N'123', N'123', N'123', N'zxc', N'zxc', N'meo2.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV008 ', N'Khánh Đoan', N'Quản lý', N'123123', N'123123', N'123123', N'ads', N'123', N'meo5.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV009 ', N'Mỹ Tâm', N'Quản lý', N'123123', N'123', N'123', N'khanhuy', N'23', N'meo5.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV010 ', N'Đinh Ngọc Trung', N'Nhân sự', N'123', N'123', N'123', N'1', N'1', N'meo3.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV011 ', N'Trần A Đức', N'Lễ tân', N'23423423', N'zxc', N'zxc', N'khanhuy11', N'123', N'meo5.jpeg');
INSERT INTO [dbo].[staff] ([id], [full_name], [role], [phone], [email], [address], [username], [pass], [img]) VALUES (N'NV012 ', N'Black Undoo', N'Quản lý', N'0905161884', N'blackundovn@gmail.com', N'37nguyenly', N'blackund', N'12345', N'meo5.jpeg');

go


-- Table DichVu 
insert into service values (N'Center fitness', 200.00, '...')
insert into service values (N'Hồ bơi', 200.00, '...')
insert into service values (N'Spa', 200.00, '...')
insert into service values (N'Nhà hàng', 200.00, '...')
insert into service values (N'Giặt ủi', 200.00, '...')
insert into service values (N'Golf & Tennis', 200.00, '...')
insert into service values (N'Quầy bar', 200.00, '...')
insert into service values (N'Thuê xe tự lái', 200.00, '...')
go


-- Phong edited
insert into room values ('P001', 01, 450.00, N'Phòng Presidential Suite', null, null)
insert into room values ('P002', 02, 450.00, N'Phòng Grand Suite', null, null)
insert into room values ('P003', 03, 450.00, N'Phòng Royal Suite', null, null)
insert into room values ('P004', 04, 450.00, N'Phòng Executive Suite', null, null)
insert into room values ('P005', 05, 450.00, N'Phòng Deluxe Family Triple', null, null)
insert into room values ('P006', 06, 450.00, N'Phòng Premium Deluxe Twin', null, null)
insert into room values ('P007', 07, 450.00, N'Phòng Deluxe King hướng biển', null, null)
insert into room values ('P008', 08, 450.00, N'Phòng Deluxe Twin hướng biển', null, null)
insert into room values ('P009', 09, 450.00, N'Phòng Deluxe King',  null, null)
insert into room values ('P010', 10, 450.00, N'Phòng Deluxe Twin', null, null)
go


-- ChietKhau
insert into discount values (10, N'2022-11-01', N'2022-11-02', 'P001')
insert into discount values (15, N'2022-11-01', N'2022-11-02', 'P002')
insert into discount values (20, N'2022-11-05', N'2022-11-07', 'P005')
insert into discount values (5, N'2022-11-02', N'2022-11-04', 'P008')
insert into discount values (15, N'2022-11-01', N'2022-11-02', 'P005')
insert into discount values (17, N'2022-11-10', N'2022-11-12', 'P002')
insert into discount values (18, N'2022-11-09', N'2022-11-12', 'P006')
insert into discount values (10, N'2022-11-02', N'2022-11-04', 'P008')
insert into discount values (13, N'2022-11-01', N'2022-11-02', 'P009')
insert into discount values (25, N'2022-11-03', N'2022-11-05', 'P007')
insert into discount values (14, N'2022-11-02', N'2022-11-02', 'P007')
insert into discount values (8, N'2022-11-02', N'2022-11-03', 'P001')
insert into discount values (12, N'2022-11-08', N'2022-11-11', 'P005')
go


--phieu dịch Vụ
insert into room_service values(1, 'P001','')
go



-- Table KhachHang
insert into customer values (N'LỮ HUY CƯỜNG', '0967006218', 'huycuong@gmail.com', '419029997', N'65 Lê Duẩn, Thanh Khê, TP. Đà Nẵng')
insert into customer values (N'TRẦM MINH MẪN', '0920197355', 'minhman@gmail.com', '338223474', N'120 Điện Biên Phủ, TP. Đà Nẵng')
insert into customer values (N'NGUYỄN MINH SANG', '0930649274', 'minhsang@gmail.com', '224106534', N'102 Thái Phiên, TP. Đà Nẵng')
insert into customer values (N'ĐẶNG LÊ QUANG VINH', '0920020472', 'quangvinh@gmail.com', '716749004', N'200 Lý Thái Tổ, TP. Đà Nẵng')
insert into customer values (N'PHAN QUỐC QUI', '0926714368', 'quocqui@gmail.com', '434855982', N'101 Ông Ích Đường, Cẩm Lệ, TP. Đà Nẵng')
insert into customer values (N'NGUYỄN ANH TUẤN', '0917749344', 'anhtuan@gmail.com', '787055612', N'199 CMT8, TP. Đà Nẵng')
insert into customer values (N'LÊ DUY BẢO', '0994296169', 'duybao@gmail.com', '318844784', N'K7/2 Trưng Nữ Vương, TP. Đà Nẵng')
insert into customer values (N'ĐẶNG BẢO VIỆT', '0922948096', 'baoviet@gmail.com', '499962289', N'290 Hoàng Diệu, TP. Đà Nẵng')
insert into customer values (N'PHẠM NGỌC NHẬT TRƯỜNG', '0945196719', 'nhattruong@gmail.com', '661040662', N'304 Phạm Hùng, Hoà Xuân, TP. Đà Nẵng')
insert into customer values (N'LÊ THÀNH PHƯƠNG', '0945196719', 'thanhpuong@gmail.com', '317962779', N'54 Phan Thao, TP. Đà Nẵng')
insert into customer values (N'ĐOÀN HỮU KHANG', '0938101529', 'huukhang@gmail.com', '704744124', N'164 Phan Đăng Lưu, TP. Đà Nẵng')
insert into customer values (N'NGUYỄN HOÀNG TRUNG', '0916436052', 'hoangtrung@gmail.com', '427107540', N'189 Trần Cao Vân, TP. Đà Nẵng')
insert into customer values (N'HUỲNH THANH HUY', '0977117727', 'thanhhuy@gmail.com', '471802594', N'430 Hoàng Thị Loan, TP. Đà Nẵng')
insert into customer values (N'NGUYỄN CAO PHƯỚC', '0924832716', 'caophuoc@gmail.com', '718662864', N'182 Trường Chinh, TP. Đà Nẵng')
insert into customer values (N'PHAN TẤN VIỆT', '0924984876', 'tanviet@gmail.com', '912603721', N'230 Nguyễn Tất Thành, TP. Đà Nẵng')
insert into customer values (N'HỒ HỮU HẬU', '0912499836', 'huuhau@gmail.com', '749100976', N'293 Võ Nguyên Giáp, TP. Đà Nẵng')
insert into customer values (N'ĐOÀN TRẦN NHẬT VŨ', '0924774498', 'nhatvu@gmail.com', '479278055', N'283 Lê Thanh Nghị, TP. Đà Nẵng')
insert into customer values (N'TRẦN VĂN NAM', '0946984711', 'vannam@gmail.com', '646553888', N'102 Đinh Gia Trinh, TP. Đà Nẵng')
go





-- DatPhong
insert into booking values ('2022-11-01', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-11-02', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-11-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-11-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-11-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-11-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-11-07', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-11-08', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')


insert into booking values ('2022-01-01', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-01-02', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-01-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-01-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-01-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-01-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-01-07', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-01-08', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')

insert into booking values ('2022-02-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-02-07', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-02-08', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')

insert into booking values ('2022-03-01', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-03-02', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-03-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-03-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-03-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-03-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-03-07', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-03-08', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-03-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-03-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-03-07', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-03-08', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')

insert into booking values ('2022-04-01', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-04-02', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')
insert into booking values ('2022-04-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-04-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-04-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-04-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')

insert into booking values ('2022-05-01', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-05-02', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P001')

insert into booking values ('2022-06-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-06-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-06-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-06-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-06-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-06-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-06-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-06-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-06-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-06-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-06-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-06-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
insert into booking values ('2022-06-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-06-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-06-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-06-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')

insert into booking values ('2022-07-03', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P002')
insert into booking values ('2022-07-04', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV002', 'P002')
insert into booking values ('2022-07-05', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV003', 'P003')
insert into booking values ('2022-07-06', '2022-11-01 09:15:00', '2022-11-01 11:15:00', 96, 1, 'NV001', 'P001')
go


-- Table DanhGia
insert into rating values (N'Rất tốt')
insert into rating values (N'Tốt')
insert into rating values (N'Bình thường')
insert into rating values (N'Tệ')
insert into rating values (N'Rất tệ')
go


-- Table ThanhToan
insert into payment values (N'Tiền mặt', 500.00, 1002,4021)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4023)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4024)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4025)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4026)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4027)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4028)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4029)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4030)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4031)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4032)
insert into payment values (N'Tiền mặt', 1000.00, 1002,4033)
go




-- Table TienNghi
insert into convenient values (N'Tủ lạnh mini', 70.00, N'', 'P001')
insert into convenient values (N'Nước ngọt', 70.00, N'', 'P002')
insert into convenient values (N'Rượu', 70.00, N'', 'P003')
insert into convenient values (N'Bánh', 70.00, N'', 'P004')
insert into convenient values (N'Mấy xấy', 70.00, N'', 'P005')
insert into convenient values (N'Khăn', 70.00, N'', 'P006')
insert into convenient values (N'Kem đánh răng', 70.00, N'', 'P007')
insert into convenient values (N'Bàn chải đánh răng', 70.00, N'', 'P008')
insert into convenient values (N'Tivi', 70.00, N'', 'P009')
insert into convenient values (N'Két an toàn', 70.00, N'', 'P010')
insert into convenient values (N'Ấm siêu tốc', 70.00, N'', 'P005')
insert into convenient values (N'Dầu gội', 70.00, N'', 'P002')
insert into convenient values (N'Sữa tắm', 70.00, N'', 'P005')
insert into convenient values (N'Lược', 70.00, N'', 'P006')
insert into convenient values (N'Điện thoại bàn', 70.00, N'', 'P003')
go




SELECT * FROM booking

SELECT * FROM room