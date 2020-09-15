package library.action;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import library.bean.RentDTO;
import library.dao.BookDAO;

public class BookRent extends JFrame { // 도서목록
	private JLabel titleL, titleL2;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	private JPanel panel;

	private BookDAO bookDAO = new BookDAO();

	public BookRent() {
		getContentPane().setLayout(null); // -------------------------------------질문ㅇㅇ
		getContentPane().setBackground(Color.WHITE);// -------------------------------------질문
		getContentPane().setForeground(Color.WHITE);

		panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 400, 132);
		getContentPane().add(panel);
		panel.setLayout(null);

		titleL = new JLabel("Book Rent");
		titleL.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		titleL.setForeground(Color.WHITE);
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setBounds(0, 0, 400, 132);
		panel.add(titleL);

		titleL2 = new JLabel("도서 목록");
		titleL2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		titleL2.setForeground(new Color(119, 136, 153));
		titleL2.setBounds(73, 150, 108, 30);

		getContentPane().add(titleL2);

		Vector<String> vector = new Vector<String>();
		vector.add("회원ID");
		vector.add("책제목");
		vector.add("대여일");

		model = new DefaultTableModel(vector, 0) {
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};

		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(10, 200, 380, 210);

		getContentPane().add(scroll);

		JLabel lblNewLabel = new JLabel(new ImageIcon("icon/zzz.jpg"));
		lblNewLabel.setBounds(10, 150, 56, 30);
		getContentPane().add(lblNewLabel);

		List<RentDTO> list = bookDAO.rentList();
		for (RentDTO dto : list) {
			Vector<Object> v = new Vector<Object>();
			v.add(dto.getMember_id());
			v.add(dto.getBook_name());
			v.add(dto.getRentDate());

			model.addRow(v);
		}
		model.removeRow(0);
		model.removeRow(0);
		model.removeRow(0);
		model.removeRow(0);
		model.removeRow(0);
		model.removeRow(0);

		setTitle("도서 관리");
		setBounds(500, 200, 400, 650);
		setVisible(true);

	}
}
