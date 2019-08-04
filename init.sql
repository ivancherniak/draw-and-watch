drop sequence id_generator;

drop table comments cascade constraints;
drop table favourite_profiles cascade constraints;
drop table picture_likes cascade constraints;
drop table pictures cascade constraints;
drop table users cascade constraints;

create sequence id_generator;

create table users (
  login varchar2(128) primary key,
  name varchar2(128) not null,
  password varchar2(256) not null
);

create table favourite_profiles (
  login varchar2(128) not null references users (login),
  likes varchar2(128) not null references users (login),
  constraint u_login_likes unique(login, likes)
);

create table pictures (
  picture_id number primary key,
  painted_by varchar2(128) not null references users (login),
  painted_when date not null,
  content clob not null
);

create table picture_likes (
    picture_id number not null references pictures (picture_id),
    login varchar2(128) not null references users (login),
    constraint u_pic_login unique(picture_id, login)
);

create table comments (
    comment_id number primary key,
    picture_id number not null references pictures (picture_id),
    parent_id number references comments (comment_id),
    login varchar2(128) not null references users (login),
    comment_data varchar2(1024) not null
);