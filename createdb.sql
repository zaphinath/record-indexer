drop table users
drop table projects


create table users 
(
  id integer not null primary key autoincrement,
  username varchar(255) not null,
  password varchar(255) not null,
  name_first varchar(255) not null,
  name_last varchar(255) not null,
  email varchar(255) not null,
  indexed_records integer
);

create table projects
(
  id integer not null primary key autoincrement,
  title varchar(255) not null,
  records_per_image integer default 0,
  first_y_coord integer default 0,
  record_height integer default 0,
);
