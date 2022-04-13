create table checks(
                       id bigserial primary key,
                       card varchar(30),
                       date_time timestamp,
                       discount_qty decimal,
                       discount_card decimal,
                       total_price decimal
);
create table product_list(
                             check_id int8 references checks(id),
                             title varchar(100),
                             price decimal,
                             quantity int8
);