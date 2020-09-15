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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import library.dao.MemberDAO;

public class PwFind extends JFrame {
	private static PwFind pf;
	
	private JLabel nameL, idL, phoneL, phoneL1, phoneL2, label;
	private JButton okBtn, cancelBtn;
	private JPanel panel, panel_1;
	private JTextField nameT, idT, phoneT1, phoneT2;
	private JComboBox<String> combo;

	private MemberDAO memberDAO = new MemberDAO();
	
	public static PwFind getInstance() {
		if (pf == null) {
			pf = new PwFind();
		}
		return pf;
	}

	public PwFind() {
	   
      getContentPane().setBackground(Color.WHITE);

      JLabel imgL = new JLabel(new ImageIcon("icon/pw.png"));
      imgL.setBounds(40, 175, 30, 30);
      getContentPane().add(imgL);

      JLabel lblNewLabel_1 = new JLabel("패스워드 찾기");
      lblNewLabel_1.setBounds(80, 185, 130, 18);
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
      getContentPane().add(lblNewLabel_1);

      nameL = new JLabel("이    름");
      nameL.setBounds(70, 220, 70, 33);
      nameL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      nameL.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(nameL);

      nameT = new JTextField(" 이름을 입력해주세요.");
      nameT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
      nameT.setColumns(10);
      nameT.setOpaque(false);// 텍스트필드 컬러 삭제
      nameT.setBorder(new EmptyBorder(0, 0, 0, 0));
      // nameT.setBounds(new Rectangle(3, 3, 3, 3));
      // nameT.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      nameT.setBounds(100, 250, 200, 33);
      getContentPane().add(nameT);
      nameT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
         @Override
         public void mouseClicked(MouseEvent e) {
            nameT.setText("");
         }
      });

      idL = new JLabel("아 이 디");
      idL.setBounds(70, 285, 70, 33);
      idL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      idL.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(idL);

      idT = new JTextField(" 아이디를 입력해주세요.");
      idT.setFont(new Font("함초롬돋움", Font.PLAIN, 16));
      idT.setColumns(10);
      idT.setOpaque(false);// 텍스트필드 컬러 삭제
      idT.setBorder(new EmptyBorder(0, 0, 0, 0));
      // nameT.setBounds(new Rectangle(3, 3, 3, 3));
      // nameT.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      idT.setBounds(100, 320, 200, 33);
      getContentPane().add(idT);
      idT.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
         @Override
         public void mouseClicked(MouseEvent e) {
            idT.setText("");
         }
      });

      phoneL = new JLabel("핸드폰 번호");
      phoneL.setBounds(70, 360, 100, 33);
      phoneL.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      phoneL.setHorizontalAlignment(SwingConstants.CENTER);
      getContentPane().add(phoneL);

      String[] phone = { "010", "017", "018", "019" };
      combo = new JComboBox<String>(phone);
      combo.setBounds(75, 405, 80, 30);
      getContentPane().add(combo);

      phoneL1 = new JLabel("-");
      phoneL1.setBounds(170, 405, 20, 30);
      getContentPane().add(phoneL1);

      phoneT1 = new JTextField("번호");
      phoneT1.setColumns(5);
      phoneT1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      phoneT1.setOpaque(false);// 텍스트필드 컬러 삭제
      phoneT1.setBorder(new EmptyBorder(0, 0, 0, 0));
      // phoneT1.setBounds(new Rectangle(3, 3, 3, 3));
      // phoneT1.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      phoneT1.setBounds(190, 405, 53, 33);
      getContentPane().add(phoneT1);
      phoneT1.addMouseListener(new MouseAdapter() {// ㅇ-------------여기부터 추가
         @Override
         public void mouseClicked(MouseEvent e) {
            phoneT1.setText("");
         }
      });

      phoneL2 = new JLabel("-");
      phoneL2.setBounds(240, 405, 20, 30);
      getContentPane().add(phoneL2);

      phoneT2 = new JTextField("입력");
      phoneT2.setColumns(5);
      phoneT2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      phoneT2.setOpaque(false);// 텍스트필드 컬러 삭제
      phoneT2.setBorder(new EmptyBorder(0, 0, 0, 0));
      // phoneT2.setBounds(new Rectangle(3, 3, 3, 3));
      // phoneT2.setBorder(new LineBorder(new Color(176, 224, 230), 3));
      phoneT2.setBounds(260, 405, 53, 33);
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
      setVisible(false);
      okBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 memberDAO.selectMember(idT, nameT, (String) combo.getSelectedItem(), phoneT1.getText(), phoneT2.getText());
         }

      });

      cancelBtn = new JButton("취소");
      cancelBtn.setBounds(210, 465, 100, 40);
      cancelBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
      cancelBtn.setBorderPainted(false);
      getContentPane().add(cancelBtn);
      cancelBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(null, "취소하시겠습니까?", "비밀번호 찾기", JOptionPane.YES_NO_OPTION);
            if (result == 0) {
            	PwFind.this.setVisible(false);
            	Login.getInstance().getFrame().setVisible(false);
                Main.getInstance().getJf().setVisible(true);
            }
         }
      });
      panel_1 = new JPanel();
      panel_1.setLayout(null);
      panel_1.setBackground(new Color(176, 224, 230));
      panel_1.setBounds(0, 547, 400, 56);
      getContentPane().add(panel_1);

      getContentPane().setLayout(null);

      panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(new Color(176, 224, 230));
      panel.setBounds(0, 0, 400, 145);
      getContentPane().add(panel);

      label = new JLabel("Password Search");
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label.setForeground(Color.WHITE);
      label.setFont(new Font("맑은 고딕", Font.BOLD, 36));
      label.setBounds(0, 0, 384, 145);
      panel.add(label);

      setTitle("비밀번호 찾기");
      setBounds(500, 200, 400, 650);
      setVisible(true);

   }// constructor
}
