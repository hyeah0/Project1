package payment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import connect.DBconnect;

public class PaymentPage extends JFrame{
	
	//DB에서 가져와야할 변수들
	//영화 이름, 상영 날짜, 좌석 번호, 표 가격, 영화관 이름 순으로 
	String movieName;
	String seatDate;
	String seatNum;
	String seatPrice;
	String cinemaName;
	
	public PaymentPage() {
		
		setTitle("결제 페이지");

		JPanel container1 = new JPanel();
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		//상단
		JLabel jl1 = new JLabel("영화입장권");
		JButton button1 = new JButton("뒤로가기");
		
		//중단
		JLabel jl2 = new JLabel(movieName);
		JLabel jl3 = new JLabel(seatDate);
		JLabel jl4 = new JLabel(seatNum);
		JLabel jl5 = new JLabel(seatPrice);
		JLabel jl6 = new JLabel(cinemaName);
		
		//하단
		JButton button2 = new JButton("결  제");
		
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
				int result = JOptionPane.showConfirmDialog(null, "정말로 결제하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "결제 완료");
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
