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
		
		setTitle("ī���ȣ Ȯ��");
		
		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		container1.setBackground(Color.WHITE);
		container2.setBackground(Color.WHITE);
		container3.setBackground(Color.WHITE);
		
		JLabel jl1 = new JLabel("ī���ȣ : ");
		jtf1 = new JTextField(10);
		
		JLabel jl2 = new JLabel("��й�ȣ : ");
		jtf2 = new JTextField(10);
		
		JButton button1 = new JButton("��  ��");
		
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
				int result = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.YES_NO_OPTION);
				
				
				if(result == JOptionPane.YES_OPTION) {
					
					select(); //�ӽ�
					
					if(jtf1.getText().equals(cardNum) && Integer.parseInt(jtf2.getText()) == cardPwd) {
						
						//�������� ticket_table�� ����
						int res = insert();
						if(res>0) {
							JOptionPane.showMessageDialog(null, "���� �Ϸ�");
							//new TestMainPage();// ���� ������ Ŭ������ �̵�
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
						JOptionPane.showMessageDialog(null, "�߸��� ������ �Է��߽��ϴ�.");
					}
				}
			}
		});
	}
	
	void select() {
		con = DBconnect.getConnection();
		sql = "SELECT * FROM member_info"; // ������ WHERE ���� �α��� �� ID�� �����͸� �����;� ��.
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
			pstmt.setString(1, "1111");//�ӽ�
			pstmt.setString(2, PaymentPage.seatNum);//���� �ʿ�
			
			res = pstmt.executeUpdate();
			
			con.close();pstmt.close();
			
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		 
	}
	
}
