create table if not exists user_info -- Юзер
(
    id          bigserial,                          -- Id для идентификации сущности (PK)
    uuid        uuid unique not null,               -- UUID для связей
    create_date timestamptz          default now(), -- Дата создания записи в бд
    update_date timestamptz          default now(), -- Дата последнего обновления в бд
    is_deleted boolean not null default false, -- Флаг для мягкого удаления
    data        jsonb       not null,               -- Jsonb данные
    primary key (id)
);

