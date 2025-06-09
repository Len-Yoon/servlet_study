package sec04.ex03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	
	// DB 연결정보
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:testdb";
	private static final String user = "scott";
	private static final String pwd = "tiger";
	
	private void connDB() {
		try {
	        Class.forName(driver);
	        System.out.println("Oracle 드라이버 로딩 성공");
	        con = DriverManager.getConnection(url, user, pwd);
	        System.out.println("Connection 생성 성공");
	        stmt = con.createStatement();
	        System.out.println("Statement 생성 성공");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }		
	}
	
	public List listMembers() {
        List list = new ArrayList();
        try {
            connDB();
            String query = "select * from t_member ";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                String id = rs.getString("id");
                String pwd = rs.getString("pwd");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date joinDate = rs.getDate("joinDate");
                MemberVO vo = new MemberVO();
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                vo.setEmail(email);
                vo.setJoinDate(joinDate);
                list.add(vo);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public void addMember(MemberVO vo) {
	    Connection localCon = null;
	    PreparedStatement localPstmt = null;
	    try {
	        connDB();  // 연결 초기화
	        localCon = this.con;  // 지역 변수에 할당

	        String query = "INSERT INTO t_member (id, pwd, name, email) VALUES (?, ?, ?, ?)";
	        localPstmt = localCon.prepareStatement(query);
	        localPstmt.setString(1, vo.getId());
	        localPstmt.setString(2, vo.getPwd());
	        localPstmt.setString(3, vo.getName());
	        localPstmt.setString(4, vo.getEmail());
	        	       
	        int result = localPstmt.executeUpdate();
	        localCon.commit();  // 트랜잭션 커밋
	        System.out.println(result + "행이 삽입됨");
	    } catch (SQLException e) {
	        try {
	            if (localCon != null) localCon.rollback();  // 롤백 처리
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        try {
	            if (localPstmt != null) localPstmt.close();
	            if (localCon != null) localCon.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void delMember(String id) {
		Connection localCon = null;
	    PreparedStatement localPstmt = null;
	    try {
	    	connDB();  // 연결 초기화
	        localCon = this.con;  // 지역 변수에 할당
	        
	        String query = "DELETE FROM t_member WHERE id=?";
	        PreparedStatement pstmt = localCon.prepareStatement(query);
	        pstmt.setString(1, id);
	        pstmt.executeUpdate();
	        pstmt.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

