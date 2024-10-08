alter table INTERSHIP_PRODUCT add constraint FK_INTERSHIP_PRODUCT_ON_MANUFACTURER foreign key (MANUFACTURER_ID) references INTERSHIP_MANUFACTURER_OF_GOODS(ID);
create unique index IDX_INTERSHIP_PRODUCT_UK_NAME on INTERSHIP_PRODUCT (NAME) where DELETE_TS is null ;
create index IDX_INTERSHIP_PRODUCT_ON_MANUFACTURER on INTERSHIP_PRODUCT (MANUFACTURER_ID);
