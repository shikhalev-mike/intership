-- begin INTERSHIP_PRODUCT
create table INTERSHIP_PRODUCT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    MANUFACTURER_PRICE decimal(19, 2) not null,
    MANUFACTURER_ID uuid not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT
-- begin INTERSHIP_COMMERCIAL_NETWORK
create table INTERSHIP_COMMERCIAL_NETWORK (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    --
    primary key (ID)
)^
-- end INTERSHIP_COMMERCIAL_NETWORK
-- begin INTERSHIP_SHOP
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
)^
-- end INTERSHIP_SHOP
-- begin INTERSHIP_MANUFACTURER_OF_GOODS
create table INTERSHIP_MANUFACTURER_OF_GOODS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end INTERSHIP_MANUFACTURER_OF_GOODS
