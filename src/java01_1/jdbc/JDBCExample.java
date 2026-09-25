package java1_1.jdbc;

/**
 * Java 1.1 JDBC (Java Database Connectivity) Example Demonstrates basic JDBC operations
 * NOTE: This is a conceptual example. Actual database connection requires database driver.
 */
public class JDBCExample
{
	public static void main(String[] args)
	{
		System.out.println("=== Java 1.1 JDBC (Java Database Connectivity) ===\n");

		System.out.println("JDBC provides a standard API for database connectivity.\n");

		System.out.println("Example code structure:");
		System.out.println("""
				import java.sql.*;
				
				public class JDBCExample {
				    public static void main(String[] args) {
				        try {
				            // 1. Load database driver
				            Class.forName("com.mysql.jdbc.Driver");
				            
				            // 2. Create connection
				            Connection conn = DriverManager.getConnection(
				                "jdbc:mysql://localhost:3306/mydb", 
				                "username", 
				                "password");
				            
				            // 3. Create statement
				            Statement stmt = conn.createStatement();
				            
				            // 4. Execute query
				            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
				            
				            // 5. Process results
				            while (rs.next()) {
				                String name = rs.getString("name");
				                int age = rs.getInt("age");
				                System.out.println(name + " - " + age);
				            }
				            
				            // 6. Close resources
				            rs.close();
				            stmt.close();
				            conn.close();
				            
				        } catch (ClassNotFoundException e) {
				            System.err.println("Driver not found: " + e.getMessage());
				        } catch (SQLException e) {
				            System.err.println("SQL Error: " + e.getMessage());
				        }
				    }
				}
				""");

		System.out.println("\nKey Features:");
		System.out.println("- Database connectivity");
		System.out.println("- SQL query execution");
		System.out.println("- Result set processing");
		System.out.println("- Transaction management");
		System.out.println("- Prepared statements");

		System.out.println("\nJDBC Components:");
		System.out.println("- DriverManager: Manages database drivers");
		System.out.println("- Connection: Represents database connection");
		System.out.println("- Statement: Executes SQL queries");
		System.out.println("- ResultSet: Contains query results");
		System.out.println("- PreparedStatement: Pre-compiled SQL statements");

		System.out.println("\nBenefits:");
		System.out.println("- Standard API for all databases");
		System.out.println("- Database independence");
		System.out.println("- Easy to use");
		System.out.println("- Wide driver support");

		System.out.println("\nNote: This example is conceptual.");
		System.out.println("Actual implementation requires database driver and database setup.");
	}
}

