package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@14.38.228.214:2000:xe";
	private String username = "library_manager";
	private String password = "asdf";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public AdminDAO() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// getConnection
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int adminLogin(JTextField id, JPasswordField pass) {

		int sw = 0;

		getConnection();

		String sql = "select admin_id, admin_pw from admin_table where admin_id=? and admin_pw=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id.getText());
			pstmt.setString(2, new String(pass.getPassword()));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (id.getText().equals(rs.getString("admin_id"))
						&& new String(pass.getPassword()).equals(rs.getString("admin_pw"))) {
					sw = 1;
				}
			} else {
				sw = 2;
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {

				e2.printStackTrace();
			}

		} // try catch finally
		return sw;
	}

}
