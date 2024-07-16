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
    ADDRESS_CITY varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_HOUSE varchar(255),
    --
    NUMBER_ varchar(255) not null,
    SHOP_TYPE varchar(50),
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
    ADDRESS_CITY varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_HOUSE varchar(255),
    --
    NAME varchar(255) not null,
    FULL_NAME varchar(255),
    USER_ID uuid,
    --
    primary key (ID)
)^
-- end INTERSHIP_MANUFACTURER_OF_GOODS
-- begin INTERSHIP_PRICE_HISTORY
create table INTERSHIP_PRICE_HISTORY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    SHOP_ID uuid not null,
    PRICE_CHANGE_DATE date not null,
    OLD_PRICE decimal(19, 2),
    NEW_PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRICE_HISTORY
-- begin INTERSHIP_PRODUCT_IN_PURCHASE
create table INTERSHIP_PRODUCT_IN_PURCHASE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PURCHASE_ID uuid not null,
    PRODUCT_IN_STORE_ID uuid not null,
    PRICE decimal(19, 2) not null,
    QUANTITY integer not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT_IN_PURCHASE
-- begin INTERSHIP_PRODUCT_IN_STORE
create table INTERSHIP_PRODUCT_IN_STORE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PRODUCT_ID uuid not null,
    SHOP_ID uuid not null,
    PRICE decimal(19, 2),
    QUANTITY integer not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PRODUCT_IN_STORE
-- begin INTERSHIP_PURCHASE
create table INTERSHIP_PURCHASE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SHOP_ID uuid not null,
    --
    primary key (ID)
)^
-- end INTERSHIP_PURCHASE
