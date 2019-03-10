-- tabel Product --
create table product (
    id varchar(36) primary key,
    code varchar(10) not null unique,
    name varchar(255) not null,
    price decimal(19,2) not null
);

insert into product (id, code, name, price)
values ('p001', 'P-001', 'Product 001', 101001.01),
values ('p002', 'P-002', 'Product 002', 101002.02),
values ('p003', 'P-003', 'Product 003', 101003.03),
values ('p004', 'P-004', 'Product 004', 101004.04),
values ('p005', 'P-005', 'Product 005', 101005.05),
values ('p006', 'P-006', 'Product 006', 101006.06),
values ('p007', 'P-007', 'Product 007', 101007.07),
values ('p008', 'P-008', 'Product 008', 101008.08),
values ('p009', 'P-009', 'Product 009', 101009.09),
values ('p0010', 'P-0010', 'Product 0010', 1010010.010);