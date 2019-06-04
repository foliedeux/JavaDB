//Manipulating Employee Database
import java.sql.*;
public class EmployeeExample	{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	static final String USER = "root";
	static final String PASS = "BangaloreMobisy";

	public static void main(String args[])	{
		Connection conn = null;
		Statement stmt = null;
		try{
			//Registering JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//Opening a connection
			System.out.println("Connecting to Database....");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			//Executing a query
			System.out.println("Creating statement....");
			stmt = conn.createStatement();
			String sql;
			sql = "select id, age, name from employees";
			ResultSet rs = stmt.executeQuery(sql);

			//Extracting data from result set
			while(rs.next()){
				//Data needs to retrieved column-wise
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");

				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.println(", Name: " + name);
			}
			//Cleaning up environment
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			//JDBC errors
			se.printStackTrace();
		}
		catch(Exception e){
			//Class.forName errors
			e.printStackTrace();
		}
		finally{
			//closing resources
			try{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se2){
				//we can do nothing
			}
			try{
				if(conn != null)
					conn.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Byebye.");
	}
}