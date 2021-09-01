import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTest {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/coffeeshop"; // 3306 is default port
		String user = "root";
		String password = ""; // you set password when MySQL is installed
		
		Connection con = null; // JDBC connection
 		Statement stmt = null; // SQL statement object
		String query; // SQL query string
 		ResultSet result = null; // results after SQL execution
		
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password); // connect to MySQL
			stmt = con.createStatement();
			
			query = "SELECT * FROM Coffees;";
			result = stmt.executeQuery(query); // execute the SQL query
			
			System.out.printf("%-10s%-35s%-12s  %-9s%-7s%-7s\n",
					"CoffeeID", "CoffeeName", "SupplierID", "Price", "Sales", "Total");
			
			while (result.next()) { // loop until the end of the results
				int coffeeID = result.getInt("CoffeeID");
				String coffeeName = result.getString("CoffeeName");
				int supplierID = result.getInt("SupplierID");
				double price = result.getDouble("Price");
				int sales = result.getInt("Sales");
				int total = result.getInt("Total");
				
				System.out.printf("%8d  %-35s%10d  %7.2f  %7d%7d\n",
						coffeeID, coffeeName, supplierID, price, sales, total);
			}
		} catch (Exception ex) {
			System.out.println("SQLException caught: " + ex.getMessage());
		} finally {
			// Close all database objects nicely
			try {
				if (result != null) {
					result.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
				
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println("SQLException caught: " + ex.getMessage());
			}
		}
	}
}
