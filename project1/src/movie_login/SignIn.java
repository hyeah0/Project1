package movie_login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class SignIn {

	// �������
	Connection con = null;  		// �����ͺ��̽� ����
	PreparedStatement pstmt = null; // �����ͺ��̽��� sql ����
	ResultSet rs = null; 			// �� ����
	String sql = null;				// sql��
	
	public JFrame frame;
	public JTextField txtf_id;
	public JPasswordField txtf_pwd;

	// ���θ޼���
	public static void main(String[] args) {
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
	}

	// �⺻������
	public SignIn() {
		initialize();
	}

	// ������Ʈ ��� �޼���
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("�α���");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_id = new JLabel("���̵�");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_id.setBounds(98, 371, 105, 34);
		frame.getContentPane().add(label_id);
		
		txtf_id = new JTextField();
		txtf_id.setBounds(226, 371, 280, 34);
		frame.getContentPane().add(txtf_id);
		txtf_id.setColumns(10);
		txtf_id.requestFocus();
		
		JButton btn_login = new JButton("�α���");
		btn_login.setBounds(526, 367, 89, 76);
		frame.getContentPane().add(btn_login);
		
		JLabel label_pwd = new JLabel("��й�ȣ");
		label_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_pwd.setFont(new Font("Dialog", Font.PLAIN, 16));
		label_pwd.setBounds(98, 413, 105, 34);
		frame.getContentPane().add(label_pwd);
		
		txtf_pwd = new JPasswordField();
		txtf_pwd.setColumns(10);
		txtf_pwd.setBounds(226, 413, 280, 34);
		frame.getContentPane().add(txtf_pwd);
		
		JButton btn_signup = new JButton("ȸ�������ϱ�");
		
		btn_signup.setForeground(Color.GRAY);
		btn_signup.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btn_signup.setBounds(129, 478, 130, 44);
		btn_signup.setBorder(null); 	// ��ư �׵θ� ����
		frame.getContentPane().add(btn_signup);
		
		JButton btn_find_id = new JButton("���̵�ã��");
		btn_find_id.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btn_find_id.setForeground(Color.GRAY);
		btn_find_id.setBounds(285, 479, 130, 44);
		btn_find_id.setBorder(null); 	// ��ư �׵θ� ����
		frame.getContentPane().add(btn_find_id);
		
		JButton btn_find_pwd = new JButton("��й�ȣã��");
		btn_find_pwd.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btn_find_pwd.setForeground(Color.GRAY);
		btn_find_pwd.setBounds(438, 479, 130, 44);
		btn_find_pwd.setBorder(null); 	// ��ư �׵θ� ����
		frame.getContentPane().add(btn_find_pwd);
		
		JLabel logo = new JLabel(" ");
		logo.setIcon(new ImageIcon(SignIn.class.getResource("/img/tmplogo.png")));
		logo.setBounds(262, 222, 244, 115);
		frame.getContentPane().add(logo);
		frame.setBounds(100, 100, 700, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// * �̺�Ʈ -------------------------------------------------------------------------------
			// �α��� ��ư Ŭ��
				btn_login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// ����Ŭ ����̹� �ε� �� ������ ���̽� ���� 
						con = Movie_System.getConnection();
						
						// ���Ȯ�� �޼���
						check();
					}
				});
	
			// ȸ������ ��ư Ŭ��
					btn_signup.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_signup.setForeground(Color.blue);
							SignUp.main(null);
							frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���
						}
					});
			
			// ���̵� ã�� ��ư Ŭ��
					btn_find_id.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_find_id.setForeground(Color.blue);
							Find_id.main(null);
							frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���
						}
					});
			
			// ��й�ȣ ã�� ��ư Ŭ��
					btn_find_pwd.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							btn_find_id.setForeground(Color.blue);
							Find_pwd.main(null);
							frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���
						}
					});
	}

	
	// �޼���
	void check() {
		try {
			
			String id = txtf_id.getText();
			String pwd = txtf_pwd.getText();
			String input_loginInfo = id+pwd;
			
			boolean memTrue = false;		// ȸ���� ��� true
			
			// sql
			sql = "select id||pwd as data_loginInfo"
				+ "  from member_info"
				+ " where id||pwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input_loginInfo);
			
			rs = pstmt.executeQuery();
			
			// �����ͺ��̽��� Ȯ���Ͽ� �Է°��� �ִ��� Ȯ�� 
			while(rs.next()) {
				String data_loginInfo = rs.getString("data_loginInfo");
				
				if(input_loginInfo.equals(data_loginInfo)) {
					 memTrue = true;
		 
				}else {
					memTrue = false;
				}
			}
			
			// ��ġ�ϴ� ���̵� ������ ���� ��ȯ
			if(memTrue == true) {
				//MainPage.main(null);
				frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���
			}else {
				JOptionPane.showMessageDialog(null, "��ġ�ϴ� ȸ�������� �����ϴ�. \n ��õ� ��Ź�帳�ϴ�.", "�α���", JOptionPane.PLAIN_MESSAGE);
			}

			
			// �����ڿ� ����
			rs.close();
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
