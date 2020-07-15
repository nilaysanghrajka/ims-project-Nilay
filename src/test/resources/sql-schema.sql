create database if not exists ims;
drop table if exists ims.customers;

create table ims.customers
  ( customer_id int primary key auto_increment,
    first_name varchar(40) not NULL,
    surname varchar(40) NOT NULL
  );

create table ims.items
  ( item_id int primary key auto_increment,
    item_name varchar(40),
    itemvalue varchar(40)
  );

create table ims.orders
  ( order_id int primary key auto_increment,
    customer_id int,
    item_id int,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN key (item_id) REFERENCES items(item_id)
  );
