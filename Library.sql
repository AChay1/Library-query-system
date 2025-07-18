Create Database Library;
Use Library;

CREATE TABLE Authors (
   Author_FN VARCHAR(70) NOT NULL,
   Author_LN VARCHAR(70) NOT NULL,
   AuthorID INT NOT NULL);


CREATE TABLE Books (
   BookID int NOT NULL,
    Title varchar(50) NOT NULL,
    ISBN int NOT NULL,
    AuthorID int,
    PRIMARY KEY (BookID),
    CONSTRAINT AuthorID FOREIGN KEY (AuthorID)
    REFERENCES Authors(AuthorID));

  
CREATE TABLE Publisher (
    PublisherID INT,
    PublisherName varchar (50),
    PRIMARY KEY (PublisherID));

ALTER TABLE library.books
  ADD COLUMN PublisherID INT NOT NULL;
  
ALTER TABLE library.books ADD FOREIGN KEY PublisherID(PublisherID) REFERENCES books(PublisherID);



CREATE TABLE Member (
MemberID INT NOT NULL,
MemberFirstName varchar(50) NOT NULL,
MemberLastName varchar(50) NOT NULL,
Address varchar(50) NOT NULL,
MemberExpirationDate varchar(50) NOT NULL,
PRIMARY KEY (MemberID));

ALTER TABLE library.Member
ADD COLUMN BookID INT,
ADD FOREIGN KEY (BookID) REFERENCES books(BookID);



CREATE TABLE Genre (
Genre_type varchar(50),
BookID INT NULL,
Primary key (BookID),
 CONSTRAINT BookID FOREIGN KEY (BookID)
    REFERENCES Books(BookID)
);





