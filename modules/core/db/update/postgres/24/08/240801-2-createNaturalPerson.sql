alter table INTERSHIP_NATURAL_PERSON add constraint FK_INTERSHIP_NATURAL_PERSON_ON_ID foreign key (ID) references INTERSHIP_BUYER(ID) on delete CASCADE;
