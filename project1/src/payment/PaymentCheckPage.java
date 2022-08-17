package payment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import connect.DBconnect;
import movie_login.SignIn;

public class PaymentCheckPage extends JFrame{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql;
	
	JTextField jtf1;
	JTextField jtf2;
	String cardNum;
	int cardPwd;
	
	public PaymentCheckPage() {
		
		setTitle("카드번호 확인");
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		container1.setBackground(Color.WHITE);
		container2.setBackground(Color.WHITE);
		container3.setBackground(Color.WHITE);
		
		JLabel jl1 = new JLabel("카드번호 : ");
		jtf1 = new JTextField(10);
		
		JLabel jl2 = new JLabel("비밀번호 : ");
		jtf2 = new JTextField(10);
		
		JButton button1 = new JButton("결  제");
		
		container1.add(jl1);container1.add(jtf1);
		container2.add(jl2);container2.add(jtf2);
		container3.add(button1);
		
		add(container1,BorderLayout.NORTH);
		add(container2,BorderLayout.CENTER);
		add(container3,BorderLayout.SOUTH);
		
		setBounds(100,100,300,200);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 결제하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
				
				
				if(result == JOptionPane.YES_OPTION) {
					
					select(); //임시
					
					if(jtf1.getText().equals(cardNum) && Integer.parseInt(jtf2.getText()) == cardPwd) {
						
						//예메정보 ticket_table에 저장
						int res = insert();
						if(res>0) {
							JOptionPane.showMessageDialog(null, "결제 완료");
							//new TestMainPage();// 메인 페이지 클래스로 이동
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										SignIn window = new SignIn();
										window.frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							setVisible(false);		
						}else {
							JOptionPane.showMessageDialog(null, "error");
						}
					}else {
						JOptionPane.showMessageDialog(null, "잘못된 정보를 입력했습니다.");
					}
				}
			}
		});
	}
	
	void select() {
		con = DBconnect.getConnection();
		sql = "SELECT * FROM member_info"; // 원래는 WHERE 절로 로그인 한 ID의 데이터만 가져와야 됨.
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				cardNum = rs.getString("cardNum");
				cardPwd = rs.getInt("cardPwd");
					
			}
			rs.close();pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	int insert() {
		sql = "INSERT INTO ticket_info VALUES(?,?)";
		int res = -1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "1111");//임시
			pstmt.setString(2, PaymentPage.seatNum);//수정 필요
			
			res = pstmt.executeUpdate();
			
			con.close();pstmt.close();
			
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		 
	}
	
}
