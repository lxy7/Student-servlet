package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Util {
	static 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public Connection getConnection(){
		
		Connection cn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String userName="data";
		String passWord = "data";
		try {
			cn=DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cn;
}
public void close(Connection cn,PreparedStatement ps, ResultSet rs)
{
	try {
		cn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		ps.close();
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
