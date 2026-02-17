create table if not exists category (
  id bigserial primary key,
  name varchar(100) not null unique
);

create table if not exists book (
  id bigserial primary key,
  title varchar(255) not null,
  author varchar(255) not null,
  isbn varchar(64) not null unique,
  category_id bigint references category(id),
  copies_total int not null,
  copies_available int not null
);

create table if not exists member (
  id bigserial primary key,
  name varchar(255) not null,
  email varchar(255) not null unique,
  subscription_expiry date,
  status varchar(40) not null
);

create table if not exists loan (
  id bigserial primary key,
  member_id bigint not null references member(id),
  book_id bigint not null references book(id),
  request_id varchar(32) not null unique,
  borrow_date date,
  due_date date,
  return_date date,
  status varchar(40) not null
);

create table if not exists fine (
  id bigserial primary key,
  loan_id bigint not null references loan(id),
  amount numeric(12,2) not null,
  reason varchar(255) not null,
  paid_flag boolean not null,
  created_at timestamptz not null
);

create table if not exists request_sequence (
  year int primary key,
  version bigint,
  next_running_number int not null
);

create table if not exists user_account (
  id bigserial primary key,
  username varchar(120) not null unique,
  password varchar(255) not null,
  role varchar(40) not null
);
