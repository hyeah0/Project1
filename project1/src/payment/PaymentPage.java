package payment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import connect.DBconnect;
import movie_login.SignIn;

public class PaymentPage extends JFrame{
	
	ImageIcon icon_back;
	ImageIcon icon_cgv;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql;
	
	//DB���� �����;��� ������
	//��ȭ �̸�, �� ��¥, �¼� ��ȣ, ǥ ����, ��ȭ�� �̸� ������ 
	String movieName;
	String seatDate;
	static String seatNum;//�ӽ�
	String seatPrice;
	String cinemaName;

	
	public PaymentPage() {
		

		setTitle("���� ������");

		JPanel container1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		container1.setBackground(Color.WHITE);
		container2.setBackground(Color.WHITE);
		container3.setBackground(Color.WHITE);
		
		//���
		icon_back = new ImageIcon("src/img/back3.png");
		JButton button1 = new JButton();
		button1.setBounds(100, 100, 100, 100);
		button1.setIcon(icon_back);
		button1.setBorder(null);
		//button1.setBackground(new Color(0, 0, 0, 0));
		button1.setContentAreaFilled(false);
		
		
		//�ߴ�
		select();//�ӽ�
		//�����ϸ� �¼��������������� ����� ������ �������°� ���� �� ����.

//		icon_cgv = new ImageIcon("src/img/tmplogo.png");
//		JLabel jimg = new JLabel();
//		jimg.setIcon(icon_cgv);
		
		JLabel jl1 = new JLabel("<html><body><center>" + "��ȭ������<br>" + "<p style=\"font-size:30px\">"+movieName
				+"</p><br>"+seatDate+"<br>"+seatNum+"<br>"+seatPrice+"<br>"+cinemaName
						+ "</center></body></html>");
		jl1.setFont(new Font("Serif",Font.BOLD,20));
		
		//�ϴ�
		JButton button2 = new JButton("��  ��");
		
		container1.add(button1);
		
		container2.add(jl1);
		
		container3.add(button2);

		add(container1,BorderLayout.NORTH);
		add(container2,BorderLayout.CENTER);
		add(container3,BorderLayout.SOUTH);
		
		
		
		setBounds(100,100,700,900);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new TestMainPage();// �¼����������� Ŭ������ �̵�
				setVisible(false);
			
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PaymentCheckPage();// ī���ȣ Ȯ�� �������� �̵�
				setVisible(false);
			
			
			}
		});
	}
	
	void select() {
		con = DBconnect.getConnection();
		sql = "SELECT mv.movieName, cn.cinemaDate, s.seatNum,s.seatPrice,cn.cinemaName "
				+ "FROM seat_info s JOIN cinema_info cn ON s.cinemaNum = cn.cinemaNum "
				+ "JOIN movie_info mv ON cn.movieNum = mv.movieNum ";
				//+ "WHERE s.seatNum = ? AND cn.cinemaNum = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				movieName = rs.getString("movieName");
				seatDate = rs.getString("seatDate");
				seatNum = rs.getString("seatNum");
				seatPrice = rs.getString("seatPrice");
				cinemaName = rs.getString("cinemaName");
			}
			
			rs.close();pstmt.close();con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	void insert() {
//		try {
//			sql = "INSERT INTO ticket_info VALUES(?,?)";
//			pstmt = con.prepareStatement(sql);
//			
//			int res = pstmt.executeUpdate();
//			
//			if(res > 0) {
//				JOptionPane.showMessageDialog(null, "�μ� �߰� ����");
//			}else {
//				JOptionPane.showMessageDialog(null, "�μ� �߰� ����");	
//			}
//			pstmt.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	

}
