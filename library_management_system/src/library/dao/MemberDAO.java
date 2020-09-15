package library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import library.action.Login;
import library.action.NewPw;
import library.action.PwFind;
import library.bean.MemberDTO;

public class MemberDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@14.38.228.214:2000:xe";
	private String username = "library_manager";
	private String password = "asdf";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// constructor
	public MemberDAO() {
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

	// mebmerLogin
	public int memberLogin(JTextField id, JPasswordField pass) {
		int sw = 0;

		getConnection();

		String sql = "select member_id, member_pw from member_table where member_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id.getText());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (id.getText().equals(rs.getString("member_id"))
						&& new String(pass.getPassword()).equals(rs.getString("member_pw"))) {
					sw = 1;
				} else {
					sw = 2;
				}
			} else {
				sw = 3;
			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close(); // rs도 닫아줘야함
				if (pstmt != null)
					pstmt.close(); // 닫아주기 거꾸로
				if (conn != null)
					conn.close();
			} catch (SQLException e2) {

				e2.printStackTrace();
			}

		} // try catch finally
		return sw;
	}// memberLogin

	// getSeq
	public int getSeq() {
		int seq = 0;

		getConnection();

		String sql = "select seq_member.nextval from dual";

		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				seq = rs.getInt("nextval");
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
		return seq;
	}

	// insertMember
	public int insertMember(MemberDTO memberDTO) {
		int su = 0;
		getConnection();
		String sql = "insert into member_table values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberDTO.getSeq());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getPw());
			pstmt.setString(4, memberDTO.getName());
			pstmt.setInt(5, memberDTO.getGender());
			pstmt.setInt(6, memberDTO.getBirth());
			pstmt.setString(7, memberDTO.getTel1());
			pstmt.setString(8, memberDTO.getTel2());
			pstmt.setString(9, memberDTO.getTel3());

			su = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return su;
	}// insertMember

	// deleteMember
	public void deleteMember(MemberDTO memberDTO) {
		getConnection();

		String sql = "delete member_table where member_id = ? and member_pw = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// updateMember
	public void updateMember(MemberDTO memberDTO) {
		int su = 0;
		
		getConnection();

		String sql = "update member_table set member_pw = ?, member_name = ?, member_tel1 = ?, member_tel2 = ?, member_tel3 = ? where member_id = ?";
		
		try {
			getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getName());
			pstmt.setString(3, memberDTO.getTel1());
			pstmt.setString(4, memberDTO.getTel2());
			pstmt.setString(5, memberDTO.getTel3());
			pstmt.setString(6, memberDTO.getId());

			su = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close(); // 닫아주기 거꾸로
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// selectMember
	public void selectMember(JTextField idT, JTextField nameT, String phone1, String phone2, String phone3) {
		
		getConnection();
		
        String sql = "select * from member_table";

        try {
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();

           int sw = 0;
           while (rs.next()) {
              if (nameT.getText().equals("") || nameT.getText().length() == 0) {
                 JOptionPane.showMessageDialog(null, " 정보를 입력해주세요.", "비밀번호 찾기", JOptionPane.ERROR_MESSAGE);
                 return;// 돌아가
                 
              }  else if(rs.getString("member_id").equals(idT.getText())) {
                 sw = 1;
                 new NewPw(PwFind.getInstance()).setId(idT.getText());
                 break;
              } else if (rs.getString("member_name").equals(nameT.getText())
                      && rs.getString("member_tel1").equals(phone1)
                      && rs.getString("member_tel2").equals(phone2)
                      && rs.getString("member_tel3").equals(phone3)) {
                   JOptionPane.showMessageDialog(null,
                         "        " + rs.getString("member_name") + "님의 아이디는 " + rs.getString("member_id") + " 입니다.",
                         "아이디 찾기", JOptionPane.PLAIN_MESSAGE);
                   sw = 2;
                   break;
                }
        
           }
        
           if (sw == 0)
              JOptionPane.showMessageDialog(null, "잘못입력하셨습니다.", "아이디/비밀번호 찾기", JOptionPane.ERROR_MESSAGE);
           
           
        }catch (SQLException e1) {
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
        }
        //-----------------------------
        //-----------------------------
	}

	// findMemberById
	public MemberDTO findMemberById(String id) {
		MemberDTO memberDTO = new MemberDTO();

		getConnection();

		String sql = "select * from member_table where member_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberDTO.setSeq(rs.getInt("member_seq"));
				memberDTO.setId(rs.getString("member_id"));
				memberDTO.setPw(rs.getString("member_pw"));
				memberDTO.setName(rs.getString("member_name"));
				memberDTO.setGender(rs.getInt("member_gender"));
				memberDTO.setBirth(rs.getInt("member_birth"));
				memberDTO.setTel1(rs.getString("member_tel1"));
				memberDTO.setTel2(rs.getString("member_tel2"));
				memberDTO.setTel3(rs.getString("member_tel3"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberDTO;
	}

	// idCheck
	public int idCheck(JTextField idT) {// id 중복체크
		int sw = 0;

		getConnection();

		String sql = "select member_id from member_table";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				if (idT.getText() == null || idT.getText().length() == 0) {
					return sw = 1;
				}

				if (idT.getText().equals(rs.getString("member_id"))) {
					sw = 2;
					break;
				} else if (!idT.getText().equals(rs.getString("member_id"))) {
					sw = 3;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} // try catch finally
		return sw;
	}// idCheck

	// pwCheck
	public int pwCheck(JPasswordField pwT, JPasswordField pwcT) {// 비밀번호 중복체크

		int sw = 0;

		if (pwT.getPassword() == null || pwT.getPassword().length == 0 || pwcT.getPassword() == null
				|| pwcT.getPassword().length == 0) {
			return sw = 1;
		} else {

			if (new String(pwT.getPassword()).equals(new String(pwcT.getPassword()))) {
				sw = 2;

			} else if (!new String(pwT.getPassword()).equals(new String(pwcT.getPassword()))) {
				sw = 3;

			}
		}
		return sw;
	}

	public void pwUpdate(MemberDTO memberDTO) {
		int su = 0;
		
		getConnection();
		
		String sql = "update member_table set member_pw = ? where member_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getId());
			
			su = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close(); // 닫아주기 거꾸로
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
