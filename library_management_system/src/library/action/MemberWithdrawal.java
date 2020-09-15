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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import library.bean.MemberDTO;
import library.dao.MemberDAO;

public class MemberWithdrawal implements ActionListener {
	private JFrame jf;
	private JTextField idT;
	private JPasswordField passWordT;
	private JButton inputB, cancelB;
	private JPanel panel_1;

	public MemberWithdrawal() {
		jf = new JFrame();
		jf.getContentPane().setBackground(Color.WHITE);

		JLabel lblNewLabel = new JLabel("    I     D");
		lblNewLabel.setFont(new Font("맑은고딕", Font.BOLD, 17));
		lblNewLabel.setBounds(61, 330, 79, 33);
		jf.getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("PassWord");
		label.setFont(new Font("맑은고딕", Font.BOLD, 17));
		label.setBounds(59, 380, 100, 33);
		jf.getContentPane().add(label);

		idT = new JTextField();
		idT.setBounds(169, 330, 155, 33);
		idT.setColumns(10);
		jf.getContentPane().add(idT);

		passWordT = new JPasswordField();
		passWordT.setBounds(169, 380, 155, 33);
		jf.getContentPane().add(passWordT);

		jf.getContentPane().setLayout(null);

		inputB = new JButton("확 인");
		inputB.setBounds(80, 465, 100, 40);
		inputB.setBackground(new Color(176, 224, 230));
		inputB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		inputB.setBorderPainted(false);// -------------------------버튼 테두리 지움
		inputB.setFocusPainted(false);// 클릭했을떄 선 지움
		jf.getContentPane().add(inputB);

		cancelB = new JButton("취 소");
		cancelB.setBounds(210, 465, 100, 40);
		cancelB.setBackground(new Color(176, 224, 230));
		cancelB.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		cancelB.setFocusPainted(false);// 클릭했을떄 선 지
		cancelB.setBorderPainted(false);// -------------------------버튼 테두리 지움
		jf.getContentPane().add(cancelB);
		cancelB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
			}
		});

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 384, 145);
		jf.getContentPane().add(panel);

		JLabel label_1 = new JLabel("Member Withdrawal");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Dialog", Font.BOLD, 37));
		label_1.setBounds(0, 0, 384, 145);
		panel.add(label_1);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(0, 546, 384, 65);
		jf.getContentPane().add(panel_1);

		JLabel label0 = new JLabel(new ImageIcon("icon\\cc.PNG"));
		label0.setBounds(30, 188, 85, 88);
		jf.getContentPane().add(label0);

		JLabel label1 = new JLabel("사용하고 계신 아이디를 탈퇴하시면");
		label1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label1.setBounds(115, 180, 259, 26);
		jf.getContentPane().add(label1);

		JLabel label2 = new JLabel("이용기록은 모두 삭제되며,");
		label2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label2.setForeground(Color.RED);
		label2.setBounds(115, 205, 455, 26);
		jf.getContentPane().add(label2);

		JLabel label3 = new JLabel("삭제된 데이터는 복구되지 않습니다.");
		label3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label3.setForeground(Color.RED);
		label3.setBounds(115, 230, 455, 26);
		jf.getContentPane().add(label3);

		JLabel label4 = new JLabel("신중하게 선택하시기 바랍니다.");
		label4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		label4.setBounds(115, 255, 321, 26);
		jf.getContentPane().add(label4);

		jf.setTitle("회원탈퇴");
		jf.setBounds(500, 200, 400, 650);
		jf.setVisible(true);

		inputB.addActionListener(this);
	}

	public void delete() {
		String id = idT.getText();
		String pass = new String(passWordT.getPassword());

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId(id);
		memberDTO.setPw(pass);

		new MemberDAO().deleteMember(memberDTO);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == inputB) {
			int sw = JOptionPane.showConfirmDialog(null, "회원을 탈퇴하시겠습니까?", "회원탈퇴", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (sw == JOptionPane.YES_OPTION) {

				delete();

				idT.setText("");
				passWordT.setText("");
				
				JOptionPane.showMessageDialog(jf, "성공적으로 탈퇴되었습니다", "회원탈퇴", JOptionPane.INFORMATION_MESSAGE);
				
				jf.setVisible(false);
				Main.getInstance().member.getJf().setVisible(false);
				Main.getInstance().logout();

			} else if (sw == JOptionPane.NO_OPTION) {
				
			}
		}
	}
}