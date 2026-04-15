create table customers (
    customer_id int primary key,
    name varchar(50),
    city varchar(50)
);

create table products (
    product_id int primary key,
    name varchar(50),
    category varchar(50),
    price decimal(10,2)
);

create table orders (
    order_id int primary key,
    customer_id int,
    order_date date,
    foreign key (customer_id) references customers(customer_id)
);

create table order_items (
    order_id int,
    product_id int,
    quantity int,
    foreign key (order_id) references orders(order_id),
    foreign key (product_id) references products(product_id)
);



