create table users (id bigserial primary key, username varchar(255));

insert into users (username) values ('Илья'), ('Наталия');

create table client_products (id bigserial primary key, account_number varchar(255), balance numeric(10,0),
                              product_type varchar(255), userid bigint references users (id));

insert into client_products (account_number, balance, product_type, userid)
values ('1111', 1000, 'CARD', 1), ('2222', 200, 'ACCOUNT', 2);

