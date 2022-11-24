 ------ Spring test ----
 drop database SpringDB;
create database SpringDB;
use SpringDB;

-- table categories
 create table categories(
	id int(11) unsigned not null,
    name varchar(50) not null,
    description varchar(100) default null,
    primary key (id)
 )Engine=InnoDB Default charset=utf8;

-- table products
 create table products(
	id int(11) unsigned not null,
    name varchar(50) not null,
    price double not null,
    unitsInStock int ,
    discontinued boolean,
    primary key (id)
 )Engine=InnoDB Default charset=utf8;
 
drop table products;
drop table categories;

select * from products;
select * from categories;

INSERT INTO categories 
   (id,name,description)
VALUES 
   (1,'Car','The best on the market'),
   (2,'Laptop','The best on the market');

INSERT INTO products 
   (id,name,price,unitsInStock,discontinued)
VALUES 
   (1,'Sumo',500000,400,true),
   (2,'Nexa ',1000000,200,false),
   (3,'Lenevo ',35000,100,false),
   (4,'Hp',40000,100,true);
   
select * from categories where name like 'To%';
select * from products where discontinued = true;