alter table INTERSHIP_JURIDICAL_PERSON add constraint FK_INTERSHIP_JURIDICAL_PERSON_ON_ID foreign key (ID) references INTERSHIP_BUYER(ID) on delete CASCADE;
