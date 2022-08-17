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
import javax.swing.JSeparator;
import java.awt.Color;

public class Find_pwd {

	// �������
	Connection con = null;  		// �����ͺ��̽� ����
	PreparedStatement pstmt = null; // �����ͺ��̽��� sql ����
	ResultSet rs = null; 			// �� ����
	String sql = null;				// sql��
	
	private JFrame frame;
	private JTextField txtf_id;

	private JTextField txtf_name;
	private JTextField txtf_phone;


	// ���θ޼���
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find_pwd window = new Find_pwd();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// �⺻������
	public Find_pwd() {
		initialize();
	}

	// ������Ʈ ��� �޼���
	private void initialize() {
		// ȭ�� ����
		frame = new JFrame();
		frame.setTitle("��й�ȣ ã��");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 700, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// ������Ʈ
		// ��� ----------------------------------------------------------------------------------
		ImageIcon icon_back = new ImageIcon("img/back3.png"); 
		JButton btn_back = new JButton();
		btn_back.setBounds(22, 30, 70, 70);
		btn_back.setIcon(icon_back);
		btn_back.setBorder(null);
		frame.getContentPane().add(btn_back);
		
		// �߾� ----------------------------------------------------------------------------------
		JSeparator separator = new JSeparator();
		separator.setBounds(63, 295, 582, 12);
		frame.getContentPane().add(separator);
		
		JLabel logo = new JLabel(" ");
		logo.setIcon(new ImageIcon(SignIn.class.getResource("/img/tmplogo_small.png")));
		logo.setBounds(295, 180, 244, 115);
		frame.getContentPane().add(logo);
		
		JLabel label_id = new JLabel("���̵�");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_id.setBounds(63, 340, 202, 44);
		frame.getContentPane().add(label_id);
		
		txtf_id = new JTextField();
		txtf_id.setBounds(277, 340, 316, 44);
		frame.getContentPane().add(txtf_id);
		txtf_id.setColumns(10);
		
		JLabel label_name = new JLabel("�̸�");
		label_name.setHorizontalAlignment(SwingConstants.CENTER);
		label_name.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_name.setBounds(63, 405, 202, 44);
		frame.getContentPane().add(label_name);
		
		txtf_name = new JTextField();
		txtf_name.setColumns(10);
		txtf_name.setBounds(277, 405, 316, 44);
		frame.getContentPane().add(txtf_name);
		
		JLabel label_phone = new JLabel("�ڵ�����ȣ");
		label_phone.setHorizontalAlignment(SwingConstants.CENTER);
		label_phone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_phone.setBounds(63, 477, 202, 44);
		frame.getContentPane().add(label_phone);
		
		txtf_phone = new JTextField();
		//txtf_phone.setText("000-0000-0000");
		txtf_phone.setColumns(10);
		txtf_phone.setBounds(277, 475, 316, 44);
		frame.getContentPane().add(txtf_phone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(63, 542, 582, 12);
		frame.getContentPane().add(separator_1);
		
		// �ϴ� ----------------------------------------------------------------------------------
		JButton btn_find_pwd = new JButton("��й�ȣ ã��");
		btn_find_pwd.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btn_find_pwd.setBounds(239, 640, 215, 65);
		frame.getContentPane().add(btn_find_pwd);
		
		// * �̺�Ʈ -------------------------------------------------------------------------------
			// �ڷΰ��� ��ư Ŭ���� �α��� ȭ��â���� �̵�
				btn_back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btn_back.setForeground(Color.blue);
						SignIn.main(null);
						frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���

					}
				});
				
			// ��й�ȣ ã�� ��ư Ŭ��
				btn_find_pwd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// ����Ŭ ����̹� �ε� �� ������ ���̽� ���� 
						con = Movie_System.getConnection();
						
						// ���̵� ã�� �޼���
						findPwd();
					}
				});
		
	}
	
	// �޼���
	// ��й�ȣ ã��
	void findPwd() {
		try {
			
			// ��������
			String id = txtf_id.getText();
			String name = txtf_name.getText();
			String phone = txtf_phone.getText();
			String input_pwdInfo = id + name + phone;	// ���̵�� �ڵ�����ȣ ������ ���� ã�� ���� �ӽ� ���ĳ��� ��(�Ϸù�ȣ)
			
			boolean memTrue = false;		// ȸ���� ��� true
			String mem_name = null;			// ȸ���� ��� �� 
			String mem_pwd = null;			// ȸ���� ��� �� 
			
			int choice = 1;					// �α��� ������ Ŭ���� 1
			String[] choose = {"�α���������" , "�ݱ�"}; 
			
			// sql
			sql = "select id"
				+ "     , pwd"
				+ "     , MEMBERNAME "
				+ "     , id || MEMBERNAME || phone as data_pwdInfo"
				+ "  from member_info"
				+ " where id || MEMBERNAME || phone = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input_pwdInfo);
			
			rs = pstmt.executeQuery();
			
			// �����ͺ��̽��� Ȯ���Ͽ� �Է°��� �ִ��� Ȯ�� 
			while(rs.next()) {
				String data_pwdInfo = rs.getString("data_pwdInfo");
				
				if(input_pwdInfo.equals(data_pwdInfo)) {
					 mem_name = rs.getString("MEMBERNAME");
					 mem_pwd = rs.getString("pwd");
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
				choice = JOptionPane.showOptionDialog(null, mem_name + "���� ��й�ȣ�� " + mem_pwd + "�Դϴ�.", "��й�ȣ ã��", 0, JOptionPane.PLAIN_MESSAGE, null,choose,choose[1]);
			}else {
				JOptionPane.showMessageDialog(null, "��ġ�ϴ� ȸ�������� �����ϴ�.", "��й�ȣ ã��", JOptionPane.PLAIN_MESSAGE);
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
