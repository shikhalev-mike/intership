create table INTERSHIP_SHOP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255) not null,
    NAME varchar(255) not null,
    COMMERCIAL_NETWORK_ID uuid not null,
    --
    primary key (ID)
);