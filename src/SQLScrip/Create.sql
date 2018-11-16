create database appdb;

create user 'owner'@'%' identified by '!QAZ2wsx';

grant all on appdb to 'owner'@'%';

use appdb;

drop table if exists app_user cascade ;
create table app_user(
  u_id varchar(40),
  u_name varchar(256),
  u_sex varchar(4),
  u_birthday date,
  u_phone varchar(20) not null unique,
  u_mail varchar(256) not null ,
  u_photo varchar(256),
  constraint 'PK_UID' primary key (u_id)
);

drop table if exists user_auths cascade ;
create table user_auths(
  ua_id integer auto_increment,
  u_id varchar(40),
  indentity_type varchar(40),
  identify varchar(256),
  credential varchar(256),
  constraint 'PK_UAID'primary key (ua_id),
  constraint 'FK_AUTHS_USER' foreign key (u_id) references app_user(u_id) on delete cascade
);

drop table if exists bill;
create table bill(
  b_id varchar(40),
  u_id varchar(40),
  b_name varchar(256),
  b_desc varchar(256),
  constraint 'PK_BID' primary key (b_id),
  constraint 'FK_BILL_USER' foreign key (u_id) references app_user(u_id) on delete  cascade
);

drop table if exists record ;
create table record(
  r_id varchar(40),
  b_id varchar(40),
  r_type varchar(20),
  r_kind varchar(20),
  r_money decimal,
  r_time timestamp,
  r_desc varchar(256),
  constraint 'PK_RID' primary key (r_id),
  constraint 'FK_RECORD_BILL' foreign key (b_id) references bill(b_id) on delete cascade
);






