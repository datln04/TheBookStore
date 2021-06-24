create database BookStore
go

use BookStore
go

create table tblRoles(
	RoleID varchar(50) primary key,
	RoleName varchar(50)
)

create table tblUsers(
	UserID		int IDENTITY(1,1) primary key,
	UserName	varchar(50) not null,
	PassWord	varchar(50) not null,
	Address		varchar(50),
	Phone		varchar(50),
	RoleID		varchar(50) foreign key references tblRoles(RoleID)
)

create table tblOrder(
	OrderID		int IDENTITY(1,1) primary key,
	DateOrder	date,
	total		float,
	UserID		int foreign key references tblUsers(UserID)
)

create table tblCategory(
	CategoryID		int IDENTITY(1,1) primary key,
	CategoryName	varchar(50)
)

create table tblBook(
	BookID		int IDENTITY(1,1) primary key,
	BookName		varchar(50),
	image			varchar(50),
	Price			float,
	quantity		int,
	CurrentDate		date,
	Status			bit,
	CategoryID		int foreign key references tblCategory(CategoryID)
)

create table tblDetail(
	DetailID	int IDENTITY(1,1) primary key,
	Price		float,
	quantity	int,
	OrderID		int foreign key references tblOrder(OrderID),
	BookID	int foreign key references tblBook(BookID)
)

create table tblDiscount(
	DiscountID int IDENTITY(1,1) primary key,
	DName varchar(20),
	keyword varchar(30),
	DPercent int,
	Begindate	date,
	Enddate date
)

select UserID,UserName,PassWord,Address,Phone,RoleID
from tblUsers

insert into tblCategory
values ('Learning'),
	   ('Disney'),
	   ('legend')


insert into tblBook(BookName,image,Price,quantity,CurrentDate,Status,CategoryID)
values ('C# Toturial','C#Book','50',50,'2021-01-01',1),
	   ('Clean Code','CleanCode','98',50,'2021-01-01',1),
	   ('Head First Java','JavaBook','126',50,'2021-01-01',1),
	   ('Far From Agrabah','Aladin_disney','80',50,'2021-01-01',2),
	   ('Anna and Elsa ss2','Frozen_disney','196',50,'2021-01-01',2),
	   ('Tom & Jerry','TomJerry_disney','196',50,'2021-01-01',2),
	   ('The Blood (The Olympus seri 5)','blood_Greek','320',50,'2021-01-01',3),
	   ('God and Devil','God_Greek','130',50,'2021-01-01',3),
	   ('Hades House','Hades_Greek','196',50,'2021-01-01',3),
	   ('Percy Jackson and the Olympians','PercyJackson_Greek','196',50,'2021-01-01',3),


	   

insert into tblDetail
values (100,2,1,1),
	   (130,1,1,8)

insert into tblOrder
values ('2021-05-18',84,2)


------------------------------------------------------------------------


select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID
from tblBook
where status = 1 and quantity > 0

select CategoryID,CategoryName
from tblCategory


delete from tblBook
where BookID = 13


select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID
from tblBook

select *
from tblDiscount





ALTER TABLE tblDetail
DROP COLUMN Name;


select *
from tblUsers


select *
from tblBook


select o.DateOrder, d.detailPrice, d.detailQuantity, b.image , b.BookName
from tblOrder o inner join tblDetail d on o.OrderID = d.OrderID, tblBook b
where b.BookID = d.BookID and UserID = 2

select *
from tblOrder

select *
from tblDetail

select *
from tblDiscount


go
EXEC sp_RENAME 'tblDetail.quantity', 'detailQuantity', 'COLUMN'
go

insert into tblOrder
values ('','','')


insert tblDetail (detailPrice,detailQuantity,OrderID,BookID)
values ('','','','')

update tblDiscount
set status = 0
where DiscountID = ''

delete from tblOrder
where OrderID = 2


SELECT MAX(OrderID) as OrderID
FROM tblOrder

update tblBook
set quantity = quantity - 1
where BookID = 4




select BookID,BookName,image,Price,quantity,CurrentDate,Status,CategoryID
from tblBook
where Status = 1 and BookName like ''

delete from tblDiscount
where DiscountID = 6







