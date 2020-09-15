package library.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library.bean.MemberDTO;
import library.dao.MemberDAO;

public class Member implements Runnable {
	
	private JFrame jf;
	private JPanel panel1, panel2;
	private JLabel titleL, imgL, updateImg, updateL, btnImg, btnL;
	private JLabel idL, nameL, lblNewLabel, label;
	private JTextField nameT, idT, tel1, tel2, tel3;
	private JButton updateBtn, btn2;
	
	private MemberDAO memberDAO = new MemberDAO();

	private String id;
	
	public JFrame getJf() {
		return jf;
	}
	
	public Member() {
		jf = new JFrame();
		
		id = JOptionPane.showInputDialog(jf, "아이디를 입력해 주세요");
		if (id == null) {
			return;
		}
		
		MemberDTO memberDTO = memberDAO.findMemberById(id);
		
		jf.setBounds(500, 200, 400, 650);
		jf.getContentPane().setLayout(null);
		
		panel1 = new JPanel();
		panel1.setBackground(new Color(176, 224, 230));
		panel1.setBounds(0, 0, 400, 145);
		jf.getContentPane().add(panel1);
		panel1.setLayout(null);

		panel2 = new JPanel();
		panel2.setBackground(new Color(176, 224, 230));
		panel2.setBounds(0, 547, 400, 56);
		jf.getContentPane().add(panel2);

		titleL = new JLabel("Membership");
		titleL.setBounds(0, 0, 384, 145);
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setForeground(Color.WHITE);
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		panel1.add(titleL);
		// -------------------------------------------------------------------------
		imgL = new JLabel(new ImageIcon("icon/member.jpg"));
		imgL.setBounds(40, 190, 129, 121);
		jf.getContentPane().add(imgL);

		idT = new JTextField();
		idT.setForeground(Color.GRAY);
		idT.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		idT.setColumns(10);
		idT.setBorder(new EmptyBorder(0, 0, 0, 0));
		idT.setBounds(240, 190, 95, 30);
		idT.setEnabled(false);
		// idT.setText(memberDTO.getId());
		jf.getContentPane().add(idT);

		nameT = new JTextField();
		nameT.setForeground(Color.GRAY);
		nameT.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		nameT.setColumns(10);
		nameT.setBorder(new EmptyBorder(0, 0, 0, 0));
		nameT.setBounds(240, 230, 95, 30);
		nameT.setEnabled(false);
		jf.getContentPane().add(nameT);

		tel1 = new JTextField();
		tel1.setForeground(Color.GRAY);
		tel1.setText((String) null);
		tel1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tel1.setColumns(10);
		tel1.setBorder(new EmptyBorder(0, 0, 0, 0));
		tel1.setBounds(178, 270, 50, 30);
		tel1.setEnabled(false);
		jf.getContentPane().add(tel1);

		label = new JLabel("-");
		label.setForeground(Color.GRAY);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		label.setBounds(230, 270, 17, 30);
		jf.getContentPane().add(label);

		tel2 = new JTextField();
		tel2.setForeground(Color.GRAY);
		tel2.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tel2.setColumns(10);
		tel2.setBorder(new EmptyBorder(0, 0, 0, 0));
		tel2.setBounds(250, 270, 50, 30);
		tel2.setEnabled(false);
		jf.getContentPane().add(tel2);

		lblNewLabel = new JLabel("-");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lblNewLabel.setBounds(303, 270, 17, 30);
		jf.getContentPane().add(lblNewLabel);

		tel3 = new JTextField();
		tel3.setForeground(Color.GRAY);
		tel3.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tel3.setColumns(10);
		tel3.setBorder(new EmptyBorder(0, 0, 0, 0));
		tel3.setBounds(320, 270, 50, 30);
		tel3.setEnabled(false);
		jf.getContentPane().add(tel3);

		// -------------------------------------------------------------------------

		updateImg = new JLabel(new ImageIcon("icon/update.jpg"));
		updateImg.setBounds(55, 387, 30, 30);
		jf.getContentPane().add(updateImg);

		updateL = new JLabel("회원정보 수정");
		updateL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		updateL.setBounds(94, 375, 117, 30);
		jf.getContentPane().add(updateL);

		updateBtn = new JButton("    비밀번호 등 내정보를 변경하세요.");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InformationModify(idT.getText());
			}
		});
		updateBtn.setBackground(Color.GRAY);
		updateBtn.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		updateBtn.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
		updateBtn.setBorderPainted(false);// -------------------------버튼 테두리 지움
		updateBtn.setFocusPainted(false);// 클릭했을떄 선 지움
		updateBtn.setBounds(0, 365, 382, 90);
		jf.getContentPane().add(updateBtn);

		btnImg = new JLabel(new ImageIcon("icon/bbb.jpg"));
		btnImg.setBounds(55, 482, 30, 30);
		jf.getContentPane().add(btnImg);

		btnL = new JLabel("회원탈퇴");
		btnL.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btnL.setBounds(93, 470, 117, 30);
		jf.getContentPane().add(btnL);

		idL = new JLabel(" I  D : ");
		idL.setForeground(Color.GRAY);
		idL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		idL.setBounds(181, 190, 55, 30);
		jf.getContentPane().add(idL);

		nameL = new JLabel("이 름 : ");
		nameL.setForeground(Color.GRAY);
		nameL.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		nameL.setBounds(181, 230, 55, 30);
		jf.getContentPane().add(nameL);

		btn2 = new JButton("          더 이상 이용하지 않을 경우 진행해주세요.");
		btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		btn2.setBorder(new EmptyBorder(0, 0, 0, 0));
		btn2.setContentAreaFilled(false);// 배경지우는거 ( 버튼 눌렀을떄, 주변에 색들어가는거 지움)
		btn2.setBorderPainted(false);// -------------------------버튼 테두리 지움
		btn2.setFocusPainted(false);// 클릭했을떄 선 지움
		btn2.setBounds(0, 470, 382, 70);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MemberWithdrawal();
			}
		});
		jf.getContentPane().add(btn2);
		jf.getContentPane().setLayout(null);
		jf.getContentPane().setBackground(Color.WHITE);
		// ------------------------------------------------------------------------- 회원
		// 정보 불러오기

		idT.setText(memberDTO.getId());
		nameT.setText(memberDTO.getName());
		tel1.setText(memberDTO.getTel1());
		tel2.setText(memberDTO.getTel2());
		tel3.setText(memberDTO.getTel3());
		
		jf.setTitle("회원정보");
		if (memberDTO.getId() == null) {
			JOptionPane.showMessageDialog(jf, "일치하는 회원이 없습니다", "회원정보", JOptionPane.ERROR_MESSAGE);
			jf.setVisible(false);
		} else {
			jf.setVisible(true);
		}

	}// Member()

	@Override
	public void run() {
		MemberDTO memberDTO = memberDAO.findMemberById(id);
		idT.setText(memberDTO.getId());
		nameT.setText(memberDTO.getName());
		tel1.setText(memberDTO.getTel1());
		tel2.setText(memberDTO.getTel2());
		tel3.setText(memberDTO.getTel3());
	}

}
