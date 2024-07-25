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
);