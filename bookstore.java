import java.sql.*;
import java.util.Scanner;
public class bookstore {

	public static void main(String[] args) {
		try {
			
		
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebookstore?useSSL=false",
				"root",
				"@Theo1996"
				);
		
		Statement statement = connection.createStatement();
		ResultSet results;
		int rowsAffected;
		results = statement.executeQuery("SELECT title, qty FROM books");
		
		// Loop over the results, printing them all.
        while (results.next()) {
            System.out.println(results.getString("title") + ", " +results.getInt("qty"));
            
        }

	
		
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println(scan);
		
		System.out.println("===========");
		System.out.println("Welcome to eBOOKSTORE!!!");
		System.out.println("===========");
		
		System.out.println("MENU");
		System.out.println("============");
		
		System.out.println("1. Enter book");
		System.out.println("2. Update book");
		System.out.println("3. Delete book");
		System.out.println("4. Search book");
		System.out.println("0. Exit book");
		
		System.out.println("============");
		
		System.out.print("Enter your choice:\n");   
		int choice = scan.nextInt();
		
		// Add a new book
		
		if(choice == 1) {
			
			System.out.print("Enter Book ID:\n");
			int Book_ID = scan.nextInt();
			System.out.print("Enter Book Title:\n");
			scan = new Scanner(System.in);
			String Book_Title = scan.nextLine();
			System.out.print("Enter Book Author:\n");
			String Book_author = scan.nextLine();
			System.out.print("Enter Book qty:\n");
			int Book_qty = scan.nextInt();
			
			
			// Inserts the new book to the database.
			rowsAffected = statement.executeUpdate("INSERT INTO books VALUES (" + Book_ID + "," + "'"+Book_Title+"'" + "," + "'"+Book_author+"'" + "," + Book_qty + ")");
			System.out.println("Query complete, " + rowsAffected + " rows added.");
			printAllFromTable(statement);
			
			
		}else if (choice == 2 ) {
			
			System.out.print("Enter ID where to Edit:\n");
			int Book_ID = scan.nextInt();
			
			System.out.print("Enter your qty Update:\n");
			int Book_qty = scan.nextInt();
			
			// Update books on the database.
            rowsAffected = statement.executeUpdate("UPDATE books SET qty=" + Book_qty + " WHERE id="+ Book_ID );
            System.out.println("Query complete, " + rowsAffected + " rows updated.");
            printAllFromTable(statement);
			
		}else if (choice == 3) {
			
			System.out.print("Enter the row id you want to delete\n");
			int Book_ID = scan.nextInt();
			
			// Clear a book:
            rowsAffected = statement.executeUpdate("DELETE FROM books WHERE id=" + Book_ID);
            System.out.println("Query complete, " + rowsAffected + " rows removed.");
            printAllFromTable(statement);
		}else if (choice == 4) {
			scan = new Scanner(System.in);
			System.out.print("Enter your search:\n");
			String search = scan.nextLine();
			rowsAffected = statement.executeUpdate("SELECT * FROM books WHERE title LIKE" + "'%"+search+"%'");
            System.out.println("Query complete, " + rowsAffected + " rows removed.");
            printAllFromTable(statement);
		
		}else if(choice == 0) {
			System.out.print("Thank you see you next time");
		
		}
		
		// Close up our connections
        results.close();
        statement.close();
        connection.close();
		}catch(Exception e) {
			
			System.out.println("Something went wrong");
			
		}
	}
	public static void printAllFromTable(Statement statement) throws SQLException{
        
        ResultSet results = statement.executeQuery("SELECT id, title, author, qty FROM books");
        while (results.next()) {
            System.out.println(
                    results.getInt("id") + ", "
                    + results.getString("title") + ", "
                    + results.getString("author") + ", "        
                    + results.getInt("qty")
                );
        }
    }
}
