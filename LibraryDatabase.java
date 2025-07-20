package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class LibraryDatabase {

	public static void main(String[] args) {


		try {

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Library", "root",
					"PasswordHere");
			Statement statement = connection.createStatement();

			
			
			 int choice;
		        Scanner scanner = new Scanner(System.in);
		        // Creating infinite while loop
		        
		        
		        System.out.println("Library Database");
	        	System.out.println();
	        	System.out.println("Welcome to the library database.");
	        	System.out.println("You can use this application to see what books have been checked out as well as the names of the book publishers/n and many other things as well.");
	        	System.out.println();
	        	System.out.println("My database is for a library, and the database will have many different books, different authors, and different genres of books. \nA database is needed so the library member can search up if the library has the item they want or if it is checked out. \nIt can keep track of all the different books the library has access to and who checks out what.");
	        	System.out.println();
		        
		        while (true) {

		            // Creating menu
		        	System.out.println();
		        	System.out.println();
		        	
		        	System.out.println("1: view author names, author ids, book ids, titles, and ISBN numbers for each author");
		        	System.out.println(
							"2: view the genre of the book and authors of the books for the book, and the title of book 8.");
		        	System.out.println(
							"3: view the author first and last name of the author with the last name starting with the letter M as well as the book IDs and titles of their books and genre of each book. ");
		        	System.out.println(
							"4: view the book ISBN and the book genre, as well as the name of the book publisher and the first name and last name of a member of the library who checked out book 3. ");
		    		System.out.println("5: view the member ID and a book they checked out for book number 9.");
		    		System.out.println(
							"6: view the publisher names that start with S, the publisher IDs and the title of the books");
		    		System.out.println("7: quit\n\n");

		            // Asking user to make choice
		            System.out.println("Enter a number\n");
		            choice = scanner.nextInt();

		            // Creating switch case branch
		            switch (choice) {

		            // option 1
		                case 1:
		                	ResultSet resultSet = statement.executeQuery(
									"SELECT Author_FN, Author_LN, a.AuthorID, b.BookID, b.Title, b.ISBN FROM authors AS a JOIN books AS b ON a.AuthorID = b.AuthorID Group By AuthorID; ");
							
		                		
		                	while (resultSet.next()) {
								
								
								String Author_FN = resultSet.getString("Author_FN");
								String Author_LN = resultSet.getString("Author_LN");
								int AuthorID = resultSet.getInt("AuthorID");
								int BookID = resultSet.getInt("BookID");
								String Title = resultSet.getString("Title");
								int ISBN = resultSet.getInt("ISBN");
								
						
								
								System.out.println(Author_FN + " " + Author_LN + "  " + AuthorID + "  " + BookID + "  " + Title + "  " + ISBN);
							}
						
		                    break;

		           
		                    // option 2
		                case 2:
		                	resultSet = statement.executeQuery(
									"SELECT Author_FN, Author_LN, Title, genre_type, books.BookID From books left join authors on authors.AuthorID = books.AuthorID left join genre on books.BookID = genre.BookID Where books.BookID = 8; ");
							if (resultSet.next()) {
								 System.out.println(  "author first name: "+ resultSet.getString(1) + "  author last name: " + (resultSet.getString(2)+ "  title: " + resultSet.getString(3) + "  genre: " + resultSet.getString(4) + "  book id: " + resultSet.getString(5)));
								 
								 
							}

		                    break;


		                    // option 3 has multiple rows and can only access first one
		                case 3:
		                	resultSet = statement.executeQuery(
									"SELECT authors.Author_FN, authors.Author_LN, authors.AuthorID, books.Title, genre.Genre_type FROM authors left join books on authors.AuthorID = books.AuthorID left join genre on books.BookID = genre.BookID WHERE Author_LN LIKE 'M%' GROUP BY Author_FN; ");
							while (resultSet.next()) {
								
								
							String Author_FN = resultSet.getString("Author_FN");
							String	 Author_LN = resultSet.getString("Author_LN");
							int	 AuthorID = resultSet.getInt("AuthorID");
							String	Title = resultSet.getString("Title");
							String Genre_type = resultSet.getString("Genre_type");
								
								System.out.println(Author_FN + " " + Author_LN + "  " + AuthorID + "  " + Title + "  " + Genre_type);
					
							}

		                    break;

		          
		                    // option 4
		                case 4:
		                	resultSet = statement.executeQuery(
									"SELECT books.ISBN, member.memberFirstName, member.memberLastName,  publisher.PublisherName, genre.genre_type From books left join member on member.bookID = books.BookID left join genre on books.BookID = genre.BookID left join publisher on books.publisherID = publisher.publisherID Where genre.BookID = 3; ");
							if (resultSet.next()) {
								System.out.println("ISBN number: "+ resultSet.getString(1) + "  member first name: " + (resultSet.getString(2) + "  member last name: " + resultSet.getString(3) + "  publisher name: " + resultSet.getString(4) + "  genre: " + resultSet.getString(5)));
							}

		                    break;

		          
		                    // option 5
		                case 5:
		                	resultSet = statement.executeQuery(
									"SELECT member.memberID, books.bookID, books.Title FROM member left join books on member.BookID = books.BookID Where books.BookID = 9; ");
							if (resultSet.next()) {
								System.out.println("member id number: " + resultSet.getString(1) + "  book id number: " + (resultSet.getString(2)+ "  title: " + resultSet.getString(3)));
							}
		                	
		                	break;

		                	
		                	// option 6 
		                case 6:
		            		resultSet = statement.executeQuery(
									"SELECT publisher.PublisherName, publisher.PublisherID, Title FROM library.publisher join library.books on publisher.PublisherID = books.PublisherID WHERE publisherName LIKE 'S%'; ");
							while (resultSet.next()) {
					
								String PublisherName = resultSet.getString("PublisherName");
								String PublisherID = resultSet.getString("PublisherID");
								String	Title = resultSet.getString("Title");
								
									
									System.out.println(PublisherName + " " + PublisherID + "  " +  "  " + Title);

							}
		                	
		                	break;
		                	
		                	// exit
		                case 7: 
		                	System.out.println("quit program");
		                	System.exit(0);
		         
		                    // default case to display the message invalid choice made by the user
		                default:
		                    System.out.println("Invalid choice! Please make a valid choice. \\n\\n");
		            }
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		    }
}


