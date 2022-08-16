package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
	static void getConnection() {
		Connection con = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String user = "system1";
		
		String password = "1234";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		System.out.println("Success");
	}
	
}
