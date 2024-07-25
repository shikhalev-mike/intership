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
);