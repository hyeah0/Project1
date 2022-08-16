package payment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import connect.DBconnect;

public class PaymentPage extends JFrame{
	
	//DB���� �����;��� ������
	//��ȭ �̸�, �� ��¥, �¼� ��ȣ, ǥ ����, ��ȭ�� �̸� ������ 
	String movieName;
	String seatDate;
	String seatNum;
	String seatPrice;
	String cinemaName;
	
	public PaymentPage() {
		
		setTitle("���� ������");

		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		//���
		JLabel jl1 = new JLabel("��ȭ�����");
		JButton button1 = new JButton("�ڷΰ���");
		
		//�ߴ�
		JLabel jl2 = new JLabel(movieName);
		JLabel jl3 = new JLabel(seatDate);
		JLabel jl4 = new JLabel(seatNum);
		JLabel jl5 = new JLabel(seatPrice);
		JLabel jl6 = new JLabel(cinemaName);
		
		//�ϴ�
		JButton button2 = new JButton("��  ��");
		
		container1.add(button1);container1.add(jl1);
		
		container2.add(jl2);container2.add(jl3);container2.add(jl4);container2.add(jl5);container2.add(jl6);
		
		container3.add(button2);
		
		add(container1,BorderLayout.NORTH);
		add(container2,BorderLayout.CENTER);
		add(container3,BorderLayout.SOUTH);
		
		setBounds(100,100,700,900);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "������ �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "���� �Ϸ�");
					new TestMainPage();
					setVisible(false);
				}
			
			}
		});
	}
	
	void ticket_info() {
		
	}
	
	public static void main(String[] args) {
		new PaymentPage();
		//new TestMainPage();
		
	}

}
