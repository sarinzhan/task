alter table users
add column user_role varchar(50),
add column is_active boolean default true;

