update INTERSHIP_PRODUCT_IN_STORE set PRICE = 0 where PRICE is null ;
alter table INTERSHIP_PRODUCT_IN_STORE alter column PRICE set not null ;
