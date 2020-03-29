create table if not exists accounts
(
    id            bigint auto_increment
        primary key,
    creation_date date         null,
    email         varchar(255) null unique,
    name          varchar(255) null,
    password      varchar(255) null,
    update_date   date         null
);

create table if not exists change_password_tokens
(
    token           varchar(255) not null
        primary key,
    expiration_date datetime     null,
    account_id         bigint       null,
    constraint change_password_token_to_account foreign key (account_id) references accounts(id) on delete cascade
);

create table if not exists authentication_tokens
(
    token           varchar(255) not null
        primary key,
    expiration_date datetime     null,
    issuer          varchar(255) null,
    account_id         bigint       null,
    constraint authentication_token_to_account foreign key (account_id) references accounts(id) on delete cascade
);
