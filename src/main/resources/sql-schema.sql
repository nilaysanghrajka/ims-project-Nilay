create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table ims.items (id int primary key auto_increment, item_name varchar(40),item_value int);
create table ims.orders (id int primary key auto_increment, customer_id int NOT NULL, itemm_id int NOT NULL, units int, total_talue double, FOREIGN KEY (customer_id) REFERENCES customers(id), FOREIGN key (item_id) REFERENCES items(id));
