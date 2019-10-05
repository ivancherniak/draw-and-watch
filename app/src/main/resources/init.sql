declare res number := 0;
begin

  select count(*) into res from ALL_SEQUENCES where lower(sequence_name) = 'id_generator';
  if res = 0 then
    execute immediate('create sequence id_generator');
  end if;

  select count(*) into res from all_tables where lower(table_name) = 'users';
  if res = 0 then
    execute immediate('create table users (
	  login varchar2(128) primary key,
	  name varchar2(128) not null,
	  password varchar2(256) not null
	)');
    execute immediate 'insert into users values(''user1'', ''Buda'', ''$2a$10$HpfgkwWum1iPv8xMDSy0xOOj9p2mKLEv2kcPAjd.fM36df/LXmnBG'')';
    execute immediate 'insert into users values(''user2'', ''Pesht'', ''$2a$10$fkcVbtueTQUACbz/qA3q0.FrvEVqmV.TiuLsQjuF2WOpNmCd2YJSy'')';
  end if;

  select count(*) into res from all_tables where lower(table_name) = 'favourite_profiles';
  if res = 0 then
    execute immediate('create table favourite_profiles (
	  login varchar2(128) not null references users (login),
	  likes varchar2(128) not null references users (login),
	  constraint u_login_likes unique(login, likes)
	)');
    execute immediate 'insert into favourite_profiles values(''user1'', ''user2'')';
    execute immediate 'insert into favourite_profiles values(''user2'', ''user1'')';
  end if;

  select count(*) into res from all_tables where lower(table_name) = 'pictures';
  if res = 0 then
    execute immediate('create table pictures (
	  picture_id number primary key,
	  painted_by varchar2(128) not null references users (login),
	  painted_when date not null,
	  content clob not null
	)');
  end if;

  select count(*) into res from all_tables where lower(table_name) = 'picture_likes';
  if res = 0 then
    execute immediate('create table picture_likes (
	    picture_id number not null references pictures (picture_id),
	    login varchar2(128) not null references users (login),
	    constraint u_pic_login unique(picture_id, login)
	)');
  end if;

  select count(*) into res from all_tables where lower(table_name) = 'comments';
  if res = 0 then
    execute immediate('create table comments (
	    comment_id number primary key,
	    picture_id number not null references pictures (picture_id),
	    parent_id number references comments (comment_id),
	    login varchar2(128) not null references users (login),
	    comment_data varchar2(1024) not null
	)');
  end if;

  commit;
end;