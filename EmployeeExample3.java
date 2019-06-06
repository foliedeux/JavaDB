import java.sql.*;
import java.util.*;
public class EmployeeExample3	{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/EMP";

	static final String USER = "root";
	static final String PASS = "BangaloreMobisy";

	public static void display_all(Statement stm)throws SQLException{
		ResultSet rs = stm.executeQuery("Select * from employees");
		while(rs.next()){
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");

				System.out.print("ID: " + id);
				System.out.print(", Age: " + age);
				System.out.println(", Name: " + name);
			}
			rs.close();
	}
	public static void display_meta(ResultSet rs)throws SQLException{
		System.out.println("Table Name: " + rs.getMetaData().getTableName(1));
		System.out.println("Columns:-");
		for (int i = 0, col = rs.getMetaData().getColumnCount(); i < col; i++)
			System.out.println(rs.getMetaData().getColumnName(i + 1));
	}
	public static void main(String args[]){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.prepareStatement("Select * from employees");
			display_meta(stmt.executeQuery());

			String sql = "Insert into employees values(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 15);
			stmt.setInt(2, 30);
			stmt.setString(3, "XYZ");
			int row = stmt.executeUpdate();
			System.out.println("Number of rows affected: "+ row);
			display_all(stmt);

			stmt = conn.prepareStatement("Update employees set name = ? where id = ?");
			stmt.setString(1, "LMN");
			stmt.setInt(2, 10);
			row = stmt.executeUpdate();
			System.out.println("Number of rows affected: " + row);
			display_all(stmt);

			stmt = conn.prepareStatement("Delete from employees where id = ?");
			stmt.setInt(1, 20);
			row = stmt.executeUpdate();
			System.out.println("Number of rows affected: " + row);
			display_all(stmt);

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
		System.out.println("Bye World.");
	}
}