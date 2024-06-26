create table token_handler(
    id bigserial primary key ,
    user_id bigint not null references users(id),
    expire_at timestamp not null,
    token varchar
)
