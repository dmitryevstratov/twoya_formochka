create table if not exists addresses
(
    id              int4 not null primary key,
    additional_info varchar(255),
    country         varchar(255),
    index           int4,
    locality        varchar(255),
    region          varchar(255),
    room            int4,
    street          varchar(255)
);

create table if not exists clients
(
    id          int4 not null primary key,
    birthday    varchar(255),
    email       varchar(255),
    first_name  varchar(255),
    last_name   varchar(255),
    second_name varchar(255),
    telephone   varchar(255),
    address_id  int4,
    foreign key (address_id) references addresses (id)
);

create table if not exists discount_type
(
    id   int4 not null primary key,
    name varchar(255)
);

create table if not exists discounts
(
    id               int4 not null primary key,
    value            int4,
    discount_type_id int4,
    foreign key (discount_type_id) references discount_type (id)
);

create table if not exists item_categories
(
    id   int4 not null primary key,
    name varchar(255)
);

create table if not exists item_types
(
    id   int4 not null primary key,
    name varchar(255)
);

create table if not exists items
(
    id               int4 not null primary key,
    name             varchar(255),
    price            float8,
    size             float8,
    item_category_id int4,
    item_type_id     int4,
    foreign key (item_category_id) references item_categories (id),
    foreign key (item_type_id) references item_types (id)
);

create table if not exists orders
(
    id          int4 not null primary key,
    count       int4,
    date_closed timestamp,
    date_create timestamp,
    status      varchar(255),
    total_price float8,
    client_id   int4,
    discount_id int4,
    foreign key (client_id) references clients (id),
    foreign key (discount_id) references discounts (id)
);

create table if not exists orders_items
(
    order_id int4 not null,
    item_id  int4 not null,
    foreign key (item_id) references items (id),
    foreign key (order_id) references orders (id)
);

create table if not exists clients_discounts
(
    client_id   int4 not null,
    discount_id int4 not null
);

create table if not exists reports
(
    id           int4 not null primary key,
    year         int4,
    quarter      int4,
    income       float8,
    tax          int4,
    sum_for_tax  float8,
    clear_income float8
);

create sequence hibernate_sequence start with 1 increment by 1;
create sequence address_id_seq start with 11 increment by 1;
create sequence clients_id_seq start with 11 increment by 1;
create sequence discount_types_id_seq start with 4 increment by 1;
create sequence discounts_id_seq start with 6 increment by 1;
create sequence discounts_to_clients_id_seq start with 6 increment by 1;
create sequence item_types_id_seq start with 3 increment by 1;
create sequence item_categories_id_seq start with 5 increment by 1;
create sequence items_id_seq start with 11 increment by 1;
create sequence orders_id_seq start with 11 increment by 1;
create sequence orders_items_id_seq start with 11 increment by 1;
create sequence reports_id_seq start with 1 increment by 1;