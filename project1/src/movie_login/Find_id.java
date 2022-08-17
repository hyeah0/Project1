package movie_login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import netscape.javascript.JSObject;

import javax.swing.JSeparator;
import java.awt.Color;

public class Find_id {

	// �������
	Connection con = null;  		// �����ͺ��̽� ����
	PreparedStatement pstmt = null; // �����ͺ��̽��� sql ����
	ResultSet rs = null; 			// �� ����
	String sql = null;				// sql��
	
	private JFrame frame;
	private JTextField txtf_name;
	private JTextField txtf_phone;

	// ���θ޼���
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find_id window = new Find_id();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// �⺻������
	public Find_id() {
		initialize();
	}

	// ������Ʈ ��� �޼���
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("���̵� ã��");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 700, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImageIcon icon_back = new ImageIcon("img/back3.png"); 
		JButton btn_back = new JButton();
		btn_back.setBounds(22, 30, 70, 70);
		btn_back.setIcon(icon_back);
		btn_back.setBorder(null);
		frame.getContentPane().add(btn_back);
		
		JLabel logo = new JLabel(" ");
		logo.setIcon(new ImageIcon(SignIn.class.getResource("/img/tmplogo_small.png")));
		logo.setBounds(295, 220, 244, 115);
		frame.getContentPane().add(logo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(63, 368, 582, 12);
		frame.getContentPane().add(separator);
		
		JLabel label_name = new JLabel("�̸�");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_name.setBounds(63, 405, 202, 38);
		frame.getContentPane().add(label_name);
		
		txtf_name = new JTextField();
		txtf_name.setColumns(10);
		txtf_name.setBounds(277, 403, 316, 44);
		frame.getContentPane().add(txtf_name);
		
		JLabel label_phone = new JLabel("�ڵ�����ȣ");
		label_phone.setHorizontalAlignment(SwingConstants.CENTER);
		label_phone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_phone.setBounds(63, 477, 202, 38);
		frame.getContentPane().add(label_phone);
		
		txtf_phone = new JTextField();
		//txtf_phone.setText("000-0000-0000");
		txtf_phone.setColumns(10);
		txtf_phone.setBounds(277, 475, 316, 44);
		frame.getContentPane().add(txtf_phone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(63, 542, 582, 12);
		frame.getContentPane().add(separator_1);
		
		JButton btn_find_id = new JButton("���̵� ã��");
		btn_find_id.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btn_find_id.setBounds(239, 640, 215, 65);
		frame.getContentPane().add(btn_find_id);
		
		// * �̺�Ʈ -------------------------------------------------------------------------------
		// �ڷΰ��� ��ư Ŭ���� �α��� ȭ��â���� �̵�
			btn_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_back.setForeground(Color.blue);
					SignIn.main(null);
					frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���

				}
			});
		
		
		// ���̵� ã�� ��ư Ŭ��
			btn_find_id.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ����Ŭ ����̹� �ε� �� ������ ���̽� ���� 
					con = Movie_System.getConnection();
					
					// ���̵� ã�� �޼���
					findId();
					
					
				}
			});

	}
	
	// �޼���
	// ���̵� ã��
		void findId() {
			try {
				
				// ��������
				String name = txtf_name.getText();
				String phone = txtf_phone.getText();
				String input_idInfo = name + phone;	// ���̵�� �ڵ�����ȣ ������ ���� ã�� ���� �ӽ� ���ĳ��� ��(�Ϸù�ȣ)
				
				boolean memTrue = false;		// ȸ���� ��� true
				String mem_id = null;			// ȸ���� ��� �� 
				String mem_name = null;			// ȸ���� ��� �� 
				
				int choice = 1;					// �α��� ������ Ŭ���� 1
				String[] choose = {"�α���������" , "�ݱ�"}; 
				
				// sql
				sql = "select id "
					+ "     , MEMBERNAME "
					+ "     , MEMBERNAME || phone as data_idInfo"
					+ "  from member_info"
					+ " where MEMBERNAME || phone = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, input_idInfo);
				
				rs = pstmt.executeQuery();
				
				// �����ͺ��̽��� Ȯ���Ͽ� �Է°��� �ִ��� Ȯ�� 
				while(rs.next()) {
					String data_idInfo = rs.getString("data_idInfo");
					
					if(input_idInfo.equals(data_idInfo)) {
						 mem_id = rs.getString("id");
						 mem_name = rs.getString("MEMBERNAME");
						 memTrue = true;
						 
					}else {
						memTrue = false;
					}
				}
				
				// �����ڿ� ����
				rs.close();
				pstmt.close();
				
				// ��ġ�ϴ� ���̵� ������ ���� ��ȯ
				if(memTrue == true) {
					choice = JOptionPane.showOptionDialog(null, mem_name + "���� ID�� " + mem_id + "�Դϴ�.", "���̵� ã��", 0, JOptionPane.PLAIN_MESSAGE, null,choose,choose[1]);
				}else {
					JOptionPane.showMessageDialog(null, "��ġ�ϴ� ȸ�������� �����ϴ�.", "���̵� ã��", JOptionPane.PLAIN_MESSAGE);
				}

				// �α��� �������� �̵� Ŭ���� �̵�
				if(choice == 0) {
					SignIn signIn = new SignIn();
					SignIn.main(null);
					frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		};
}
