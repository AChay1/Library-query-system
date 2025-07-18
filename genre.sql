SELECT * FROM library.genre;

-- query 1
SELECT Author_FN, Author_LN, a.AuthorID, b.BookID, b.Title, b.ISBN FROM authors AS a JOIN books AS b ON a.AuthorID = b.AuthorID Group By AuthorID;


-- query 2
SELECT Author_FN, Author_LN, Title, genre_type, books.BookID From books left join authors on authors.AuthorID = books.AuthorID left join genre on books.BookID = genre.BookID Where books.BookID = 8;


-- query 3
SELECT authors.Author_FN, authors.Author_LN, authors.AuthorID, books.Title, genre.Genre_type FROM authors left join books on authors.AuthorID = books.AuthorID left join genre on books.BookID = genre.BookID WHERE Author_LN LIKE 'M%' GROUP BY Author_FN;

-- query 4
SELECT books.ISBN, member.memberFirstName, member.memberLastName,  publisher.PublisherName, genre.genre_type From books left join member on member.bookID = books.BookID left join genre on books.BookID = genre.BookID left join publisher on books.publisherID = publisher.publisherID Where genre.BookID = 3;


-- query 5
SELECT member.memberID, books.bookID, books.Title FROM member left join books on member.BookID = books.BookID Where books.BookID = 9;

-- query 6

SELECT publisher.PublisherName, publisher.PublisherID, Title FROM library.publisher join library.books on publisher.PublisherID = books.PublisherID WHERE publisherName LIKE 'S%';
