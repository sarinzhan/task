alter table users
add column user_role varchar(50),
add column is_active bool default true;

