create table Users(
	username nvarchar(20) not null primary key,
	pass nvarchar(20) not null
);
create table Files(
	fId int not null identity(1,1) primary key,
	fName nvarchar(100) not null,
	fSize nvarchar(30) not null,
	fOwner nvarchar(20) not null foreign key references Users(username),
	Privacy nvarchar(20)
);
create table Permit(
	Id int not null,
	username nvarchar(20) foreign key references Users(username)
);