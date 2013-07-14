
drop table if exists users;
drop table if exists projects;
drop table if exists fields;
drop table if exists images;
drop table if exists records;
drop table if exists record_values;

create table users 
(
  id integer not null primary key autoincrement,
  username varchar(255) not null,
  password varchar(255) not null,
  name_first varchar(255) not null,
  name_last varchar(255) not null,
  email varchar(255) not null,
  indexed_records integer,
  current_image_id integer default 0
);

create table projects
(
  id integer not null primary key autoincrement,
  title varchar(255) not null,
  records_per_image integer default 0,
  first_y_coord integer default 0,
  record_height integer default 0
);

create table fields
(
  id integer not null primary key autoincrement,
  project_id integer not null,
  title varchar(255) not null,
  xcoord integer default 0,
  width integer default 0,
  help_html varchar(255),
  known_data varchar(255)
);

create table batches
(
  id integer not null primary key autoincrement,
  project_id integer not null,
  image_file varchar(255)
);

create table records
(
  id integer not null primary key autoincrement,
  image_id integer not null
);

create table record_values
(
  id integer not null primary key autoincrement,
  record_id integer not null,
  field_id integer not null,
  value varchar(255)
);
