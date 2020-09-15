package library.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import library.bean.MemberDTO;
import library.dao.MemberDAO;

public class InformationModify implements ActionListener, Runnable {
	private JFrame frame2;
	private JTextField idT1, nameT, phoneNumberT, phoneNumber2T;
	private JButton inputB, cancelB;
	private JComboBox<String> comboBox;
	private JPasswordField pwT, pwcT;
	private JLabel jlId, jlPass;
	private boolean threadAndVisible;
	
	private MemberDAO memberDAO = new MemberDAO();

	public InformationModify(String id) {
		
		threadAndVisible = true;

		frame2 = new JFrame("회원정보 수정");
		frame2.getContentPane().setBackground(Color.WHITE);
		frame2.setBounds(500, 200, 400, 650);
		frame2.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Information Modify");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 400, 145);
		frame2.getContentPane().add(lblNewLabel);

		JLabel imgL = new JLabel(new ImageIcon("icon/modify.png"));
		imgL.setBounds(40, 150, 30, 30);
		frame2.getContentPane().add(imgL);

		JLabel titleL = new JLabel("회원정보 수정");
		titleL.setBounds(80, 160, 130, 18);
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		frame2.getContentPane().add(titleL);

		JLabel lblNewLabel_1 = new JLabel("I    D");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));

		lblNewLabel_1.setBounds(70, 185, 70, 33);
		frame2.getContentPane().add(lblNewLabel_1);

		idT1 = new JTextField(id);
		idT1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		idT1.setBorder(new LineBorder(new Color(209, 209, 209)));
		idT1.setBounds(85, 213, 230, 30);
		frame2.getContentPane().add(idT1);
		idT1.setColumns(10);
		idT1.setEditable(false);
		idT1.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				// idT1.setText("");
			}
		});

		JLabel label = new JLabel("Pass Word");

		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label.setBounds(70, 240, 130, 33);
		frame2.getContentPane().add(label);

		pwT = new JPasswordField();

		pwT.setColumns(10);
		pwT.setBounds(85, 270, 230, 30);
		pwT.setBorder(new LineBorder(new Color(209, 209, 209)));
		frame2.getContentPane().add(pwT);
		pwT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				pwT.setText("");
			}
		});

		JLabel label_1 = new JLabel("Pass Word 확인");

		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(70, 295, 150, 33);
		frame2.getContentPane().add(label_1);

		pwcT = new JPasswordField();

		pwcT.setColumns(10);
		pwcT.setBorder(new LineBorder(new Color(209, 209, 209)));
		pwcT.setBounds(85, 325, 230, 30);

		frame2.getContentPane().add(pwcT);
		pwcT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				pwcT.setText("");
			}
		});

		JLabel label_2 = new JLabel("이   름");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_2.setBounds(70, 355, 70, 33);
		frame2.getContentPane().add(label_2);

		nameT = new JTextField();
		nameT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		nameT.setBorder(new LineBorder(new Color(209, 209, 209)));
		nameT.setColumns(10);
		nameT.setBounds(85, 385, 230, 30);
		frame2.getContentPane().add(nameT);
		nameT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				nameT.setText("");
			}
		});

		JLabel label_4 = new JLabel("휴대폰 번호");
		// label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_4.setBounds(70, 415, 100, 33);
		frame2.getContentPane().add(label_4);

		String phone[] = { "010", "017", "018", "019" };
		comboBox = new JComboBox<String>(phone);
		comboBox.setBounds(70, 450, 75, 33);
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		frame2.getContentPane().add(comboBox);

		JLabel phoneL1 = new JLabel("-");
		phoneL1.setBounds(168, 45, 20, 30);
		frame2.getContentPane().add(phoneL1);

		phoneNumberT = new JTextField("");
		phoneNumberT.setBorder(new LineBorder(new Color(209, 209, 209)));
		phoneNumberT.setColumns(10);
		phoneNumberT.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneNumberT.setBounds(183, 450, 50, 33);

		frame2.getContentPane().add(phoneNumberT);
		phoneNumberT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				phoneNumberT.setText("");
			}
		});

		JLabel phoneL2 = new JLabel("-");
		phoneL2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneL2.setBounds(243, 452, 20, 30);
		frame2.getContentPane().add(phoneL2);

		phoneNumber2T = new JTextField("");
		phoneNumber2T.setBorder(new LineBorder(new Color(209, 209, 209)));
		phoneNumber2T.setColumns(10);
		phoneNumber2T.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneNumber2T.setBounds(265, 450, 50, 33);
		frame2.getContentPane().add(phoneNumber2T);
		phoneNumber2T.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				phoneNumber2T.setText("");
			}
		});

		inputB = new JButton("확 인");
		inputB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		inputB.setBounds(80, 495, 100, 40);
		inputB.setBorderPainted(false);
		frame2.getContentPane().add(inputB);

		cancelB = new JButton("취 소");
		cancelB.setBorderPainted(false);
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				threadAndVisible = false;
				frame2.setVisible(threadAndVisible);

			}
		});

		cancelB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		cancelB.setBounds(215, 495, 100, 40);
		frame2.getContentPane().add(cancelB);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 140);
		panel.setBackground(new Color(176, 224, 230));
		frame2.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 547, 384, 56);
		frame2.getContentPane().add(panel_1);

		jlId = new JLabel();
		jlId.setHorizontalAlignment(SwingConstants.CENTER);
		jlId.setBounds(170, 185, 145, 30);
		frame2.getContentPane().add(jlId);

		jlPass = new JLabel();
		jlPass.setBounds(220, 295, 150, 30);
		frame2.getContentPane().add(jlPass);
		frame2.setVisible(threadAndVisible);

		inputB.addActionListener(this);

		Thread t = new Thread(this);
		t.start();

	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == inputB) {

			if (idT1.getText() == null || idT1.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .", "회원정보수정", JOptionPane.ERROR_MESSAGE);
				return;

			} else if (!(idT1.getText() == null || idT1.getText().length() == 0) && pwT.getPassword() == null
					|| pwT.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요 .", "회원정보수정", JOptionPane.ERROR_MESSAGE);
				return;
			} else {

				String id = idT1.getText();
				String pass = new String(pwcT.getPassword());
				String name = nameT.getText();
				String tel1 = (String) comboBox.getSelectedItem();
				String tel2 = phoneNumberT.getText();
				String tel3 = phoneNumber2T.getText();

				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(id);
				memberDTO.setPw(pass);
				memberDTO.setName(name);
				memberDTO.setTel1(tel1);
				memberDTO.setTel2(tel2);
				memberDTO.setTel3(tel3);

				memberDAO.updateMember(memberDTO);

				JOptionPane.showMessageDialog(null, "회원정보수정이 완료되었습니다..", "회원정보수정", JOptionPane.INFORMATION_MESSAGE);

				idT1.setText("");
				pwT.setText("");
				pwcT.setText("");
				nameT.setText("");
				comboBox.setSelectedItem("010");
				phoneNumberT.setText("");
				phoneNumber2T.setText("");
				
				threadAndVisible = false;
				frame2.setVisible(threadAndVisible);
				
				Thread t = new Thread(Main.getInstance().member);
				t.start();
			}


		} // if
	}// action

	@Override
	public void run() {
		while (threadAndVisible) {

			int pwSwitch = memberDAO.pwCheck(pwT, pwcT);

			switch (pwSwitch) {
			case 1:
				jlPass.setText("");
				break;
			case 2:
				jlPass.setText("PassWord가 같습니다.");
				jlPass.setForeground(Color.blue);
				break;
			case 3:
				jlPass.setText("PassWord가 다릅니다.");
				jlPass.setForeground(Color.red);
				break;
			}
		}
	}

}// InformationModify