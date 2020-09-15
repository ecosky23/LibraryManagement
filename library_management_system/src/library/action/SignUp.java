package library.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import library.bean.MemberDTO;
import library.dao.MemberDAO;

public class SignUp extends JFrame implements ActionListener, Runnable {
	
	// 회원가입 창 싱글턴화
	private static SignUp signUp;
	
	private JTextField idT, nameT, birthT, phoneNumberT, phoneNumber2T;
	private JButton inputB, cancelB;
	private JRadioButton maleBtn, femaleBtn;
	private JComboBox<String> comboBox;
	private JPasswordField pwT, pwcT;
	private JLabel jlId, jlPass;

	private boolean threadAndVisible;

	private MemberDAO memberDAO = new MemberDAO();
	
	public static SignUp getInstance() {
		if (signUp == null) {
			signUp = new SignUp();
		}
		return signUp;
	}
	
	public void setThreadAndVisible(boolean trueOrFalse) {
		threadAndVisible = trueOrFalse;
		setVisible(threadAndVisible);
		Thread t = new Thread(this);
		t.start();
	}

	public SignUp() {

		threadAndVisible = true;

		getContentPane().setBackground(Color.WHITE);
		setBounds(500, 200, 400, 650);
		getContentPane().setLayout(null);
		setTitle("회원가입");

		JLabel lblNewLabel = new JLabel("Member Sign Up");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 400, 145);
		getContentPane().add(lblNewLabel);

		JLabel imgL = new JLabel(new ImageIcon("icon/pw.png"));
		imgL.setBounds(40, 150, 30, 30);
		getContentPane().add(imgL);

		JLabel titleL = new JLabel("회원가입");
		titleL.setBounds(80, 160, 130, 18);
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		getContentPane().add(titleL);

		JLabel lblNewLabel_1 = new JLabel("I    D");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));

		lblNewLabel_1.setBounds(70, 185, 70, 33);
		getContentPane().add(lblNewLabel_1);

		idT = new JTextField("");
		idT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		idT.setBounds(85, 213, 230, 30);
		idT.setBorder(new LineBorder(new Color(209, 209, 209)));
		getContentPane().add(idT);
		idT.setColumns(10);
		idT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				idT.setText("");
			}
		});

		JLabel label = new JLabel("Pass Word");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label.setBounds(70, 240, 130, 33);
		getContentPane().add(label);

		pwT = new JPasswordField();
		pwT.setColumns(10);
		pwT.setBounds(85, 270, 230, 30);
		pwT.setBorder(new LineBorder(new Color(209, 209, 209)));
		getContentPane().add(pwT);

		JLabel label_1 = new JLabel("Pass Word 확인");
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_1.setBounds(70, 295, 150, 33);
		getContentPane().add(label_1);

		pwcT = new JPasswordField();
		pwcT.setColumns(10);
		pwcT.setBorder(new LineBorder(new Color(209, 209, 209)));
		pwcT.setBounds(85, 325, 230, 30);
		getContentPane().add(pwcT);

		JLabel label_2 = new JLabel("이   름");
		label_2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_2.setBounds(70, 355, 70, 33);
		getContentPane().add(label_2);

		nameT = new JTextField();
		nameT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		nameT.setBorder(new LineBorder(new Color(209, 209, 209)));
		nameT.setColumns(10);
		nameT.setBounds(85, 385, 90, 30);
		getContentPane().add(nameT);
		nameT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				nameT.setText("");
			}
		});

		JLabel birth = new JLabel("생년월일");
		birth.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		birth.setBounds(185, 355, 70, 33);
		getContentPane().add(birth);

		birthT = new JTextField("6자리 숫자");
		birthT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
		birthT.setBorder(new LineBorder(new Color(209, 209, 209)));
		birthT.setColumns(10);
		birthT.setBounds(185, 385, 100, 30);
		getContentPane().add(birthT);
		birthT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				birthT.setText("");
			}
		});

		maleBtn = new JRadioButton("남자", true);
		maleBtn.setBounds(70, 415, 60, 33);
		getContentPane().add(maleBtn);

		femaleBtn = new JRadioButton("여자");
		femaleBtn.setBounds(140, 415, 60, 33);
		getContentPane().add(femaleBtn);

		ButtonGroup bg = new ButtonGroup();

		bg.add(maleBtn);
		bg.add(femaleBtn);

		JLabel label_4 = new JLabel("휴대폰 번호");
		// label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		label_4.setBounds(70, 440, 100, 33);
		getContentPane().add(label_4);

		String phone[] = { "010", "017", "018", "019" };
		comboBox = new JComboBox<String>(phone);
		comboBox.setBounds(70, 465, 85, 40);
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		getContentPane().add(comboBox);

		JLabel phoneL1 = new JLabel("-");
		phoneL1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneL1.setBounds(170, 470, 20, 30);
		getContentPane().add(phoneL1);

		phoneNumberT = new JTextField();
		phoneNumberT.setBorder(new LineBorder(new Color(209, 209, 209)));
		phoneNumberT.setColumns(10);
		phoneNumberT.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneNumberT.setBounds(183, 465, 50, 33);
		getContentPane().add(phoneNumberT);

		JLabel phoneL2 = new JLabel("-");
		phoneL2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneL2.setBounds(245, 470, 20, 30);
		getContentPane().add(phoneL2);

		phoneNumber2T = new JTextField();
		phoneNumber2T.setBorder(new LineBorder(new Color(209, 209, 209)));
		phoneNumber2T.setColumns(10);
		phoneNumber2T.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneNumber2T.setBounds(265, 465, 50, 33);
		getContentPane().add(phoneNumber2T);

		inputB = new JButton("확 인");
		inputB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		inputB.setBounds(80, 515, 100, 40);
		inputB.setBorderPainted(false);
		getContentPane().add(inputB);

		cancelB = new JButton("취 소");
		cancelB.setBorderPainted(false);
		cancelB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		cancelB.setBounds(215, 515, 100, 40);
		getContentPane().add(cancelB);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 140);
		panel.setBackground(new Color(176, 224, 230));
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 580, 400, 70);
		getContentPane().add(panel_1);

		jlId = new JLabel();
		jlId.setHorizontalAlignment(SwingConstants.CENTER);
		jlId.setBounds(210, 185, 150, 30);
		getContentPane().add(jlId);

		jlPass = new JLabel();
		jlPass.setBounds(210, 300, 150, 30);
		getContentPane().add(jlPass);

		setVisible(threadAndVisible);
		setResizable(false);

		// Event
		inputB.addActionListener(this);
		cancelB.addActionListener(this);

	}// constructor

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == inputB) {

			if (idT.getText() == null || idT.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "회원가입", JOptionPane.ERROR_MESSAGE);
				return;

			} else if (!(idT.getText() == null || idT.getText().length() == 0) && pwT.getPassword() == null
					|| pwT.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "회원가입", JOptionPane.ERROR_MESSAGE);
				return;
			} else {

				String id = idT.getText();
				String pw = new String(pwcT.getPassword());
				String name = nameT.getText();
				int gender = maleBtn.isSelected() ? 0 : 1;

				if (birthT.getText() == null || birthT.getText().length() != 6) {
					JOptionPane.showMessageDialog(null, "생년월일 6자리를 입력해 주세요", "회원가입", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int birth = Integer.parseInt(birthT.getText());
				String tel1 = (String) comboBox.getSelectedItem();
				String tel2 = phoneNumberT.getText();
				String tel3 = phoneNumber2T.getText();

				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setSeq(memberDAO.getSeq());
				memberDTO.setId(id);
				memberDTO.setPw(pw);
				memberDTO.setName(name);
				memberDTO.setGender(gender);
				memberDTO.setBirth(birth);
				memberDTO.setTel1(tel1);
				memberDTO.setTel2(tel2);
				memberDTO.setTel3(tel3);

				int su = memberDAO.insertMember(memberDTO);

				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "회원가입", JOptionPane.INFORMATION_MESSAGE);
				threadAndVisible = false;
				SignUp.this.setVisible(threadAndVisible);
			}

			idT.setText("");
			pwT.setText("");
			pwcT.setText("");
			nameT.setText("");
			birthT.setText("6자리 숫자");
			maleBtn.setSelected(true);
			comboBox.setSelectedItem("010");
			phoneNumberT.setText("");
			phoneNumber2T.setText("");

		} else if (e.getSource() == cancelB) {
			threadAndVisible = false;
			SignUp.this.setVisible(threadAndVisible);
		}
	}// action

	@Override
	public void run() {
		while (threadAndVisible) {

			int idSwitch = memberDAO.idCheck(idT);

			switch (idSwitch) {
			case 1:
				jlId.setText("");
				break;
			case 2:
				jlId.setForeground(Color.red);
				jlId.setText("id가 중복됩니다.");
				break;
			case 3:
				jlId.setForeground(Color.blue);
				jlId.setText("사용 가능한 id입니다.");
				break;
			}

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
}// SignUp