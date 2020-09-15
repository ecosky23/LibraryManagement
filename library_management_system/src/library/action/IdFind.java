package library.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library.dao.MemberDAO;

public class IdFind extends JFrame {
	private JLabel nameL, phoneL, phoneL1, phoneL2, lblNewLabel_1;
	private JButton okBtn, cancelBtn;
	private JPanel panel;
	private JTextField idT, nameT, phoneT1, phoneT2;
	private JComboBox<String> combo;

	public IdFind() {

		getContentPane().setBackground(Color.WHITE);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 400, 145);
		getContentPane().add(panel);

		JLabel label = new JLabel("Id Search");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		label.setBounds(0, 0, 400, 145);
		panel.add(label);
		// --------------------------------------------------
		JLabel imgL = new JLabel(new ImageIcon("icon/id.png"));
		imgL.setBounds(40, 180, 30, 30);
		getContentPane().add(imgL);

		lblNewLabel_1 = new JLabel("아이디 찾기");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(80, 190, 118, 18);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		getContentPane().add(lblNewLabel_1);
		
		idT = new JTextField();

		nameL = new JLabel("이    름");
		nameL.setBounds(70, 240, 70, 33);
		nameL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		nameL.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(nameL);

		nameT = new JTextField(" 이름을 입력해주세요.");
		nameT.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		nameT.setColumns(10);
		nameT.setOpaque(false);// 텍스트필드 컬러 삭제
		nameT.setBorder(new EmptyBorder(0, 0, 0, 0));
		// nameT.setBounds(new Rectangle(3, 3, 3, 3));
		// nameT.setBorder(new LineBorder(new Color(190,190,190)));
		nameT.setBounds(100, 280, 200, 33);
		getContentPane().add(nameT);
		nameT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				nameT.setText("");
			}
		});

		phoneL = new JLabel("핸드폰 번호");
		phoneL.setBounds(70, 330, 100, 33);
		phoneL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		phoneL.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(phoneL);

		String[] phone = { "010", "017", "018", "019" };
		combo = new JComboBox<String>(phone);
		combo.setBounds(75, 380, 80, 30);
		getContentPane().add(combo);

		phoneL1 = new JLabel("-");
		phoneL1.setBounds(170, 380, 20, 30);
		getContentPane().add(phoneL1);

		phoneT1 = new JTextField("번호");
		phoneT1.setColumns(5);
		phoneT1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneT1.setOpaque(false);// 텍스트필드 컬러 삭제
		phoneT1.setBorder(new EmptyBorder(0, 0, 0, 0));
		// phoneT1.setBounds(new Rectangle(3, 3, 3, 3));
		// phoneT1.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		phoneT1.setBounds(190, 380, 53, 33);
		getContentPane().add(phoneT1);
		phoneT1.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				phoneT1.setText("");
			}
		});

		phoneL2 = new JLabel("-");
		phoneL2.setBounds(240, 380, 20, 30);
		getContentPane().add(phoneL2);

		phoneT2 = new JTextField("입력");
		phoneT2.setColumns(5);
		phoneT2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		phoneT2.setOpaque(false);// 텍스트필드 컬러 삭제
		phoneT2.setBorder(new EmptyBorder(0, 0, 0, 0));
		// phoneT2.setBounds(new Rectangle(3, 3, 3, 3));
		// phoneT2.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		phoneT2.setBounds(260, 380, 53, 33);
		getContentPane().add(phoneT2);
		phoneT2.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
			@Override
			public void mouseClicked(MouseEvent e) {
				phoneT2.setText("");
			}
		});

		okBtn = new JButton("확인");
		okBtn.setBounds(80, 465, 100, 40);
		okBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		okBtn.setBorderPainted(false);// -------------------------버튼 테두리 지움
		getContentPane().add(okBtn);
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MemberDAO().selectMember(idT, nameT, (String) combo.getSelectedItem(), phoneT1.getText(), phoneT2.getText());

			}
		});

		cancelBtn = new JButton("취소");
		cancelBtn.setBounds(210, 465, 100, 40);
		cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		cancelBtn.setBorderPainted(false);// -------------------------버튼 테두리 지움
		getContentPane().add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?", "아이디 찾기", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					setVisible(false);
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 547, 400, 56);
		getContentPane().add(panel_1);

		getContentPane().setLayout(null);

		setTitle("아이디 찾기");
		setBounds(500, 200, 400, 650);
		setVisible(true);

	}// IdFind

}