package library.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library.dao.AdminDAO;
import library.dao.BookDAO;
import library.dao.MemberDAO;

public class Login implements ActionListener {
	private static Login login;

	private JTextField id;
	private JPasswordField pass;
	private JButton b1;
	private JCheckBox adminCheckBox;
	private JFrame frame;

	private AdminDAO adminDAO = new AdminDAO();
	private MemberDAO memberDAO = new MemberDAO();
	private BookDAO bookDAO = new BookDAO();
	
	public static Login getInstance() {
		if (login == null) {
			login = new Login();
		}
		return login;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public Login() {

		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(500, 200, 400, 650);
		frame.getContentPane().setLayout(null);

		JLabel lbtitle_1 = new JLabel("I  D");
		lbtitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitle_1.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lbtitle_1.setBounds(55, 190, 50, 33);
		frame.getContentPane().add(lbtitle_1);

		id = new JTextField("  아이디를 입력해주세요.");// ------------------------마우스 클릭시 글 사라지는거);
		id.setFont(new Font("함초롬돋움", Font.PLAIN, 14));

		id.setColumns(10);
		id.setOpaque(false);// 텍스트필드 컬러 삭제
		id.setBorder(new EmptyBorder(0, 0, 0, 0));

		id.setBounds(130, 190, 200, 33);
		frame.getContentPane().add(id);
		id.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				id.setText("");
			}
		});
		JLabel lbtitle_2 = new JLabel("P W");
		lbtitle_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbtitle_2.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		lbtitle_2.setBounds(55, 230, 50, 33);
		frame.getContentPane().add(lbtitle_2);

		pass = new JPasswordField();// ------------------------마우스 클릭시 글 사라지는거)
		pass.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		pass.setColumns(10);
		pass.setOpaque(false);// 텍스트필드 컬러 삭제
		pass.setBorder(new EmptyBorder(0, 0, 0, 0));
		pass.setBounds(130, 230, 200, 33);
		pass.setEchoChar('*');

		adminCheckBox = new JCheckBox("관리자 모드");
		adminCheckBox.setBackground(Color.WHITE);
		adminCheckBox.setBounds(50, 279, 140, 25);
		frame.getContentPane().add(adminCheckBox);
		// --------------------------------------------------------------------
		b1 = new JButton("Login");
		b1.setFont(new Font("함초롬5움", Font.BOLD, 17));
		b1.setBounds(55, 320, 275, 40);
		b1.setBorderPainted(false);
		frame.getContentPane().add(b1);

		JButton button = new JButton("회원 가입");
		button.setFont(new Font("함초롬돋움", Font.BOLD, 17));
		button.setBorderPainted(false);
		button.setBounds(55, 370, 275, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp.getInstance().setThreadAndVisible(true);
			}
		});
		frame.getContentPane().add(button);
		// --------------------------------------------------------------------
		JLabel lblNewLabel = new JLabel("              로그인 정보를 잊으셨나요?");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 14));
		lblNewLabel.setBounds(55, 440, 275, 20);
		frame.getContentPane().add(lblNewLabel);

		JButton button_1 = new JButton("ID 찾기");
		button_1.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		button_1.setBorderPainted(false);
		button_1.setBounds(90, 475, 100, 40);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IdFind();
			}
		});
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("PW 찾기");
		button_2.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		button_2.setBorderPainted(false);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PwFind.getInstance().setVisible(true);
			}
		});
		button_2.setBounds(200, 475, 110, 40);
		frame.getContentPane().add(button_2);
		// --------------------------------------------------------------------
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 400, 145);
		frame.getContentPane().add(panel);

		JLabel label = new JLabel("Login");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 37));
		label.setBounds(0, 0, 400, 145);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 547, 400, 65);
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(pass);

		frame.setVisible(true);
		b1.addActionListener(this);

	}// Login

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (adminCheckBox.isSelected()) {

				if (id.getText() == null || id.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .", "로그인", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int adminSwitch = adminDAO.adminLogin(id, pass);

				switch (adminSwitch) {
				case 1:
					frame.setVisible(false);
					new Admin();
					JOptionPane.showMessageDialog(null, "관리자로 로그인 되었습니다.", "관리자", JOptionPane.INFORMATION_MESSAGE);
					id.setText("  아이디를 입력해주세요.");
					pass.setText("");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "관리자의 아이디 혹은 비밀번호가 틀렸습니다.", "관리자", JOptionPane.ERROR_MESSAGE);
					id.setText("");
					pass.setText("");
					break;
				}

			} else {
				if (id.getText() == null || id.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요 .", "로그인", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(bookDAO.black(id) == 1) {
					
				}else {
				
				
				
				int memberSwitch = memberDAO.memberLogin(id, pass);

				switch (memberSwitch) {
				case 1:
					JOptionPane.showMessageDialog(null, "회원 로그인 되었습니다.", "로그인", JOptionPane.INFORMATION_MESSAGE);
					id.setText("  아이디를 입력해주세요.");
					pass.setText("");
					frame.setVisible(false);
					Main.getInstance().loginSucces();
					Main.getInstance().getJf().setVisible(true);
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 확인해 주세요", "로그인", JOptionPane.ERROR_MESSAGE);
					id.setText("");
					pass.setText("");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다", "로그인", JOptionPane.ERROR_MESSAGE);
					id.setText("");
					pass.setText("");
					break;
				}
			}//
			}
		} // if

	}// action

}