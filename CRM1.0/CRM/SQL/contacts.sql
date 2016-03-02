CREATE TABLE Contacts(
ID int NOT NULL,
first_name varchar2(50),
last_name varchar2(50),
Address1 varchar2(255),
Address2 varchar2(255),
City varchar(40),
zipcode NUMBER(10),
Mobile NUMBER(10),
Email varchar2(100)
PRIMARY KEY (ID)
);

CREATE SEQUENCE seq_contacts
START WITH 11000
INCREMENT BY 1;
commit;