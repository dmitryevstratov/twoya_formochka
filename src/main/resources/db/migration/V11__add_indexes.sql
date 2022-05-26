create index if not exists discount_type_value_idx on discounts using btree (discount_type_id, value);
create index if not exists discount_type_name_idx on discount_type using btree (name);
create index if not exists order_date_create_idx on orders using btree (date_create);
create index if not exists report_year_quarter_idx on reports using btree (year, quarter);
create index if not exists report_year_idx on reports using btree (year);

