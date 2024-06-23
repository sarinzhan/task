create table users
(
    id         bigserial primary key,
    first_name varchar(100),
    last_name  varchar(100),
    login      varchar(100) UNIQUE NOT NULL,
    password   varchar(100)        NOT NULL,
    email      varchar(50)         not null,
    created_at timestamp,
    last_login timestamp
);


create table application
(
    id          bigserial PRIMARY KEY,
    title       varchar(255) NOT NULL,
    description text,
    status      varchar(50)  not null,
    priority    varchar(50),
    due_date    timestamp,
    created_by  bigint       not null references users (id),
    created_at  timestamp    not null,
    assigned_to bigint references users (id),
    assigned_at timestamp
);

create table application_audit
(
    id             bigserial PRIMARY KEY,
    title          varchar(255),
    description    text,
    status         varchar(50),
    priority       varchar(50),
    due_date       timestamp,
    created_by     bigint,
    created_at     timestamp,
    assigned_to    bigint,
    assigned_at    timestamp,

    application_id bigint    not null references application (id),
    action_at      timestamp not null,
    action_by      bigint    not null references users (id)
);

-- CREATE TABLE comment
-- (
--     id         bigserial PRIMARY KEY,
--     task_id    bigint    NOT NULL REFERENCES task (id),
--     comment    text      NOT NULL,
--     created_by bigint    NOT NULL REFERENCES user (id),
--     created_at timestamp not null
-- );
--
-- create table comment_audit
-- (
--     id         bigserial PRIMARY KEY,
--     task_id    bigint    NOT NULL,
--     comment    text      NOT NULL,
--     created_by bigint    NOT NULL,
--     created_at timestamp not null,
--
--     comment_id bigint references comment(id)
--     action_time timestamp   not null,
--     action_user bigint references user (id)
-- )


create table attachments
(
    id             bigserial primary key,
    application_id bigint       not null references application (id),
    file_path      varchar(255) not null,
    uploaded_by    bigint       not null references users (id),
    uploaded_at    timestamp    not null
);



