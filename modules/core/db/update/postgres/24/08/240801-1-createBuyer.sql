create table INTERSHIP_BUYER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    ADDRESS_CITY varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_HOUSE varchar(255),
    --
    FULL_NAME varchar(255),
    USER_ID uuid,
    EMAIL varchar(255),
    --
    primary key (ID)
);