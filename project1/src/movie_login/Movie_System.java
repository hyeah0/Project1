package movie_login;

import java.sql.Connection;
import java.sql.DriverManager;


/*
 * 
 * Ŭ������ : Movie_System 
 * Movie_System.getConnection();
 *
 */

public class Movie_System {


	public static Connection getConnection() {
	
			Connection conn = null;
		
		// ������� 
			String driver = "oracle.jdbc.driver.OracleDriver";
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			
			String user = "system1";
			
			String password = "1234";
		
		// ����Ŭ ���� �ּҰ�(��)
//			String driver = "oracle.jdbc.driver.OracleDriver";
//			String url = "jdbc:oracle:thin:@SYSTEM_high?TNS_ADMIN=/Users/hye/oraclewallet/Wallet_system";
//			String userid = "admin";
//			String pwd = "Asdfghjklzxc0";
//	
		// ����Ŭ ����̹� �ε� �� ������ ���̽� ���� 
		try {
			/* 1�ܰ� ����Ŭ ����̹� �ε� */
			Class.forName(driver);
			
			/* 2�ܰ� : �����ͺ��̽� ���� �� �غ� */
 	        conn = DriverManager.getConnection(url, user, password); 
 	        // System.out.println("����Ŭ �ε�, �����ͺ��̽� ���� �Ϸ�!");
 	        
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
			
			
		
	}
	

}
