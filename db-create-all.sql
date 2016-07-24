create table users (
  id                            bigserial not null,
  name                          varchar(255),
  version                       bigint not null,
  whencreated                   timestamptz not null,
  whenmodified                  timestamptz not null,
  whocreated                    varchar(255) not null,
  whomodified                   varchar(255) not null,
  constraint pk_users primary key (id)
);

