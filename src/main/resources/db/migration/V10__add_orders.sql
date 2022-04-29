insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (1, 3, null, '10-02-2021', 2, 'CREATED', 10.4, 1);
insert into orders_items (order_id, item_id) values (1, 2);
insert into orders_items (order_id, item_id) values (1, 2);
insert into orders_items (order_id, item_id) values (1, 3);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (5, 2, null, '29-09-2021', 2, 'MODELED', 6.4, 2);
insert into orders_items (order_id, item_id) values (2, 2);
insert into orders_items (order_id, item_id) values (2, 1);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (2, 5, null, '11-04-2021', null, 'MODELED', 12.4, 3);
insert into orders_items (order_id, item_id) values (3, 2);
insert into orders_items (order_id, item_id) values (3, 6);
insert into orders_items (order_id, item_id) values (3, 7);
insert into orders_items (order_id, item_id) values (3, 7);
insert into orders_items (order_id, item_id) values (3, 9);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (7, 1, '10-08-2021', '01-08-2021', 5, 'SENT', 11.4, 4);
insert into orders_items (order_id, item_id) values (4, 6);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (10, 3, '21-02-2022', '16-02-2022', null, 'SENT', 23.4, 5);
insert into orders_items (order_id, item_id) values (5, 6);
insert into orders_items (order_id, item_id) values (5, 1);
insert into orders_items (order_id, item_id) values (5, 2);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (9, 3, null, '21-04-2022', null, 'PRINTED', 44.4, 6);
insert into orders_items (order_id, item_id) values (6, 1);
insert into orders_items (order_id, item_id) values (6, 8);
insert into orders_items (order_id, item_id) values (6, 9);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (1, 3, null, '27-04-2022', null, 'PAID', 17.0, 7);
insert into orders_items (order_id, item_id) values (7, 1);
insert into orders_items (order_id, item_id) values (7, 2);
insert into orders_items (order_id, item_id) values (7, 3);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (3, 3, null, '01-05-2022', null, 'PAID', 221.0, 8);
insert into orders_items (order_id, item_id) values (8, 9);
insert into orders_items (order_id, item_id) values (8, 9);
insert into orders_items (order_id, item_id) values (8, 8);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (5, 2, null, '15-05-2022', null, 'CREATED', 12.2, 9);
insert into orders_items (order_id, item_id) values (9, 3);
insert into orders_items (order_id, item_id) values (9, 6);

insert into orders (client_id, count, date_closed, date_create, discount_id, status, total_price, id) values (2, 2, null, '20-05-2022', null, 'CANCELED', 23.2, 10);
insert into orders_items (order_id, item_id) values (10, 3);
insert into orders_items (order_id, item_id) values (10, 1);