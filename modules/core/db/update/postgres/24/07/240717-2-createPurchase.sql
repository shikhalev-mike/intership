alter table INTERSHIP_PURCHASE add constraint FK_INTERSHIP_PURCHASE_ON_SHOP foreign key (SHOP_ID) references INTERSHIP_SHOP(ID);
create index IDX_INTERSHIP_PURCHASE_ON_SHOP on INTERSHIP_PURCHASE (SHOP_ID);
