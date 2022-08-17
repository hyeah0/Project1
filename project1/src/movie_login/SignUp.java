package movie_login;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Arrays;

public class SignUp {

	// * ���� -----------------------------------------------------
	Connection con = null;  		// �����ͺ��̽� ����
	PreparedStatement pstmt = null; // �����ͺ��̽��� sql ����
	ResultSet rs = null; 			// �� ����
	String sql = null;				// sql��
	
	Member_DTO mem_dto = new Member_DTO();
	
	private JFrame frame;
	private JTextField txtf_id;
	private JTextField txtf_pwd;
	private JTextField txtf_pwd_ck;
	private JTextField txtf_name;
	private JTextField txtf_phone;
	private JTextField txtf_card_num;
	private JTextField txtf_card_pwd;

	JLabel label_id_txt;
	JLabel label_pwd_txt;
	JLabel label_card_pwd_txt;
	
	String id;
	String pwd;
	String memberName;
	String phone;
	String cardNum;
	int cardPwd;
	
	JButton btn_id_ck;
	int id_ck = 0;	// ���̵� ��� ������ ��� : 1 
	
	// ���θ޼���
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// �⺻������
	public SignUp() {
		initialize();
	}

	// ������Ʈ ��� �޼���
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 700, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// ������Ʈ
		// ��� ----------------------------------------------------------------------------------
		ImageIcon icon_back = new ImageIcon("src/img/back3.png"); 
		JButton btn_back = new JButton();
		btn_back.setBounds(22, 30, 70, 70);
		btn_back.setIcon(icon_back);
		btn_back.setBorder(null);
		frame.getContentPane().add(btn_back);
		
		JLabel logo = new JLabel(" ");
		logo.setIcon(new ImageIcon(SignIn.class.getResource("/img/tmplogo_small.png")));
		logo.setBounds(295, 0, 244, 115);
		frame.getContentPane().add(logo);
		
		// �߰� -----------------------------------------------------------------------
		JLabel label_id = new JLabel("���̵�");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_id.setBounds(63, 127, 202, 44);
		frame.getContentPane().add(label_id);
		
		txtf_id = new JTextField();
		txtf_id.setBounds(277, 127, 316, 44);
		frame.getContentPane().add(txtf_id);
		txtf_id.setColumns(10);
		
		label_id_txt = new JLabel("�� ���� �Ǵ� ���� �ִ� 20�� �����մϴ�.");
		label_id_txt.setForeground(Color.GRAY);
		label_id_txt.setBounds(277, 166, 316, 40);
		frame.getContentPane().add(label_id_txt);
		
		btn_id_ck = new JButton("���̵� Ȯ��");
		btn_id_ck.setBounds(602, 127, 74, 44);
		frame.getContentPane().add(btn_id_ck);
		
		JLabel label_pwd = new JLabel("��й�ȣ");
		label_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_pwd.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_pwd.setBounds(63, 209, 202, 44);
		frame.getContentPane().add(label_pwd);
		
		txtf_pwd = new JTextField();
		txtf_pwd.setColumns(10);
		txtf_pwd.setBounds(277, 209, 316, 44);
		frame.getContentPane().add(txtf_pwd);
		
		JLabel label_pwd_ck = new JLabel("��й�ȣȮ��");
		label_pwd_ck.setHorizontalAlignment(SwingConstants.CENTER);
		label_pwd_ck.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_pwd_ck.setBounds(63, 276, 202, 44);
		frame.getContentPane().add(label_pwd_ck);
		
		txtf_pwd_ck = new JTextField();
		txtf_pwd_ck.setColumns(10);
		txtf_pwd_ck.setBounds(277, 276, 316, 44);
		frame.getContentPane().add(txtf_pwd_ck);
		
		label_pwd_txt = new JLabel("�� ���� �Ǵ� ���� �ִ� 20�� �����մϴ�.");
		label_pwd_txt.setForeground(Color.GRAY);
		label_pwd_txt.setBounds(277, 316, 316, 40);
		frame.getContentPane().add(label_pwd_txt);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(63, 368, 582, 12);
		frame.getContentPane().add(separator);
	
		// ---------------------------------------------------------------------------- //
		
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
		txtf_phone.setBounds(277, 477, 316, 44);
		frame.getContentPane().add(txtf_phone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(63, 542, 582, 12);
		frame.getContentPane().add(separator_1);
		
		// ---------------------------------------------------------------------------- //
		
		JLabel label_card_num = new JLabel("���� ī���ȣ");
		label_card_num.setHorizontalAlignment(SwingConstants.CENTER);
		label_card_num.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_card_num.setBounds(63, 578, 202, 44);
		frame.getContentPane().add(label_card_num);
		
		txtf_card_num = new JTextField();
		txtf_card_num.setColumns(10);
		txtf_card_num.setBounds(277, 578, 316, 44);
		frame.getContentPane().add(txtf_card_num);
		
		
		JLabel label_card_pwd = new JLabel("���� ī�� ��й�ȣ");
		label_card_pwd.setHorizontalAlignment(SwingConstants.CENTER);
		label_card_pwd.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		label_card_pwd.setBounds(63, 649, 202, 44);
		frame.getContentPane().add(label_card_pwd);
		
		txtf_card_pwd = new JTextField(4);
		txtf_card_pwd.setColumns(10);
		txtf_card_pwd.setBounds(277, 649, 316, 44);
		frame.getContentPane().add(txtf_card_pwd);
		
		label_card_pwd_txt = new JLabel("�� ���� 4�ڸ� �Է����ּ���.");
		label_card_pwd_txt.setForeground(Color.GRAY);
		label_card_pwd_txt.setBounds(277, 689, 316, 40);
		frame.getContentPane().add(label_card_pwd_txt);
		
		// �ϴ� -----------------------------------------------------------------------
		JButton btn_signUp = new JButton("ȸ������ �ϱ�");
		btn_signUp.setForeground(Color.RED);
		btn_signUp.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btn_signUp.setBounds(239, 770, 215, 65);
		frame.getContentPane().add(btn_signUp);
		
		// * �̺�Ʈ -------------------------------------------------------------------------------
		// �ڷΰ��� ��ư Ŭ���� �α��� ȭ��â���� �̵�
			btn_back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btn_back.setForeground(Color.blue);
					SignIn.main(null);
					frame.dispose(); // ������ �ִ� â�� �����ִ� �޼���

				}
			});
			
		// ���̵� Ȯ�� ��ư Ŭ��
			btn_id_ck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(txtf_id.getText().equals("")) {
						label_id_txt.setText("���̵� �Է����ּ���. ");
						label_id_txt.setForeground(Color.red);
						id_ck = 0;
					}else if(txtf_id.getText().length()>20) {
						label_id_txt.setText("���̵�� 20�� �̳��� ���������մϴ�.");
						label_id_txt.setForeground(Color.red);
						id_ck = 0;
					}else {
						//����Ŭ ����̹� �ε� �� ������ ���̽� ����
						con = Movie_System.getConnection();
						checkId();
					}
				}
			});
			
		// ȸ������ ��ư Ŭ��
			btn_signUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					int pwd_ck = 0; // ��й�ȣ ��밡�� �� ��� : 1
					int cardPwd_ck =0; // ī���й�ȣ ��밡�� �� ��� :1 
					
					// ��й�ȣ ��ȿ�� �˻�
					if(txtf_pwd.getText().equals(txtf_pwd_ck.getText()) && txtf_pwd.getText().length()>0) {
						label_pwd_txt.setText("��� �����մϴ�.");
						label_pwd_txt.setForeground(Color.green);
						pwd_ck =1;
						
					}else {
						label_pwd_txt.setText("��й�ȣ�� �ٸ��ϴ�.");
						label_pwd_txt.setForeground(Color.red);
						pwd_ck = 0;
					}
					
					// ī�� ��й�ȣ ��ȿ�� �˻�
					if(txtf_card_pwd.getText().length()==4) {
						label_card_pwd_txt.setText("��� �����մϴ�.");
						label_card_pwd_txt.setForeground(Color.green);
						cardPwd_ck =1;
						
					}else if(txtf_card_pwd.getText().length() > 4 || txtf_card_pwd.getText().length() < 4){
						label_card_pwd_txt.setText("��й�ȣ�� 4�ڸ� �ۼ����ּ���"); 
						label_card_pwd_txt.setForeground(Color.red);
						cardPwd_ck = 0;
					}
					
					if(txtf_name.getText().length()==0 || txtf_phone.getText().length()==0 || txtf_card_num.getText().length()==0){
						JOptionPane.showMessageDialog(null, "��ü ������ �Է� ��Ź�帳�ϴ�.");
						reset_txt();
						
					}else if(id_ck == 1 && pwd_ck != 1 &&  cardPwd_ck != 1) {
						JOptionPane.showMessageDialog(null, "��й�ȣ, ī�� ��й�ȣ Ȯ�� ��Ź�帳�ϴ�.");
						
					}else if(id_ck == 1 && pwd_ck != 1 &&  cardPwd_ck == 1) {
						JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ�� ��Ź�帳�ϴ�.");
						
					}else if(id_ck == 1 && pwd_ck == 1 &&  cardPwd_ck != 1) {
						JOptionPane.showMessageDialog(null, "ī���й�ȣ Ȯ�� ��Ź�帳�ϴ�.");
	
					}else if(id_ck != 1 && pwd_ck == 1 &&  cardPwd_ck == 1) {
						JOptionPane.showMessageDialog(null, "���̵� Ȯ�� ��Ź�帳�ϴ�.");
						reset_txt();
	
					}else {
						// ����Ŭ ����̹� �ε� �� ������ ���̽� ���� 
						con = Movie_System.getConnection();
						
						// mem_dto ��ü�� �� ����
						mem_dto.setId(txtf_id.getText());
						mem_dto.setPwd(txtf_pwd.getText());
						mem_dto.setMemberName(txtf_name.getText());
						mem_dto.setPhone(txtf_phone.getText());
						mem_dto.setCardNum(txtf_card_num.getText());
						mem_dto.setCardPwd(Integer.parseInt(txtf_card_pwd.getText()));
					
						// ������ �� ȣ�� �޼��� 
						insert(mem_dto);
						
						// �Է°� �ʱ�ȭ 
						reset_txt();
						reset();
						
					}
					
				}
			});
		
	}// initialize() �޼��� ��
	

	// �޼��� - ���̵� üũ -----------------------------------------------------------------
		void checkId() {
			try {
				
				String input_id = txtf_id.getText();
				
				sql = "select id"
					+ "  from member_info"
					+ " where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, input_id);
				
				rs = pstmt.executeQuery();
				boolean dataTrue = rs.next(); 	// true �� ��� ���� ���̵� ����
				System.out.println(dataTrue);
				
				if(dataTrue == false) {
					id_ck =1;
					label_id_txt.setText("��밡���մϴ�.");
					label_id_txt.setForeground(Color.green);
					id_ck = 1;  // 1�ϰ�� ���̵� ��� ����
				}else {
						String tmp = rs.getString("id");
						if(txtf_id.getText().equals(tmp)) {
							label_id_txt.setText("���� ���̵� �ֽ��ϴ�. �ٸ� ���̵� �Է��� �ּ���.");
							label_id_txt.setForeground(Color.red);
							id_ck = 0;
						}
				}
	
				// �����ڿ� ����
				pstmt.close();
				rs.close();
	
	
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	// �޼��� - ȸ�� ��� -------------------------------------------------------------------
		void insert(Member_DTO mem_dto) {
			try {
				
				// 1. sql
				sql = "insert into member_info"
					+ "     values(?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mem_dto.getId());
				pstmt.setString(2, mem_dto.getPwd());
				pstmt.setString(3, mem_dto.getMemberName());
				pstmt.setString(4, mem_dto.getPhone());
				pstmt.setString(5, mem_dto.getCardNum());
				pstmt.setInt(6, mem_dto.getCardPwd());
				
				// 2. sql -> �����ͺ��̽�
				int res = pstmt.executeUpdate();
				if(res>0) {
					JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
				}else {
					JOptionPane.showMessageDialog(null, "�Է��� �ٽ� ���ּ���.");
				}
				
				// 3. ���� �ڿ� ����
				pstmt.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	// ���� ����ϴ� �޼��� ---------------------------------------------------------------
		// �޼��� - ���� ---------------------------------------
			void reset() {
				txtf_id.setText("");
				txtf_pwd.setText("");
				txtf_pwd_ck.setText("");
				txtf_name.setText("");
				txtf_phone.setText("");
				txtf_card_num.setText("");
				txtf_card_pwd.setText("");
				
				txtf_id.requestFocus();
			}
	
		// �޼��� - �ؽ�Ʈ ���� ---------------------------------------
			void reset_txt() {
				label_id_txt.setText("�� ���� �Ǵ� ���� �ִ� 20�� �����մϴ�.");
				label_pwd_txt.setText("�� ���� �Ǵ� ���� �ִ� 20�� �����մϴ�.");
				label_card_pwd_txt.setText("�� ���� 4�ڸ� �Է����ּ���.");
				label_id_txt.setForeground(Color.gray);
				label_pwd_txt.setForeground(Color.gray);
				label_card_pwd_txt.setForeground(Color.gray);
			}
		
		// �޼��� - ���̵� �ؽ�Ʈ ����
			void reset_txt_id() {
				label_id_txt.setText("�� ���� �Ǵ� ���� �ִ� 20�� �����մϴ�.");
				label_id_txt.setForeground(Color.gray);
			}
}
