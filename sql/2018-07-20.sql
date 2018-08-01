--security remember-me
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key,token varchar(64) not null, last_used timestamp not null);
--oracle分布式锁
create table Oracle_lock(id varchar2(1) not null primary key);