//understanding statement, preparedStatements, callableStatements
import java.sql.*;
public class EmployeeExample2	{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	static final String USER = "root";
	static final String PASS = "BangaloreMobisy";
	public static void main(String args[])	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			String sql = "update employees set age = 24 where id = 10";

			boolean state = stmt.execute(sql);
			System.out.println("boolean execute(SQL) with update " + state);

			int rows = stmt.executeUpdate(sql);
			System.out.println("Number of rows affected with the update. " + rows);

			ResultSet rs = stmt.executeQuery("Select * from employees");

			while(rs.next()){
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");

				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.println(", Name: " + name);
			}
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException se){
			}
			try{
				if(conn != null)
					conn.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Byes");
	}
}
