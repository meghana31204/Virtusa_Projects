
---Find top-selling products

select p.name, sum(oi.quantity) as total_sold
from order_items oi
join products p on oi.product_id = p.product_id
group by p.name
order by total_sold desc;

---Identify most valuable customers

select o.customer_id, sum(p.price * oi.quantity) as total_spent
from orders o, order_items oi, products p
where o.order_id = oi.order_id
and oi.product_id = p.product_id
group by o.customer_id;


---Monthly revenue calculation

select extract(month from order_date) as month,
sum(p.price * oi.quantity) as revenue
from orders o, order_items oi, products p
where o.order_id = oi.order_id
and oi.product_id = p.product_id
group by extract(month from order_date);


---Category-wise sales analysis

select 
    p.category,
    sum(p.price * oi.quantity) as total_sales
from products p
join order_items oi on p.product_id = oi.product_id
group by p.category;


---Detect inactive customers

select name
from customers
where customer_id not in (
    select customer_id from orders
);

