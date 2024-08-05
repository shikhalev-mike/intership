alter table INTERSHIP_BUYER add constraint FK_INTERSHIP_BUYER_ON_USER foreign key (USER_ID) references SEC_USER(ID);
create index IDX_INTERSHIP_BUYER_ON_USER on INTERSHIP_BUYER (USER_ID);
