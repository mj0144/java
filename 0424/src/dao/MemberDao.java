package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import conn.MyConn;
import vo.MemberVO;

public class MemberDao {
	// 싱글톤 패턴 적용: 자원을 애껴쓰자

	private static MemberDao dao;// static인 것은 공유 놉, 오직 하나만 생성.

	private MemberDao() { // 기본생성자 private인 이유는 외부에서 new로 생성하지 말라고
	}

	// syschronized 동기화 블록 설정 - Thread Safe - Lock pool
	public synchronized static MemberDao getDao() {
		// 최초의 MemberDao를 반환하는 getMember() 호출했다면 동작하는 조건문
		// 처음 dao에 값이 들어갈 때만 값을 넣어서 출력. 이부분 또한 값을 아끼는거임. 그 뒤로는 걍 retrun임.
		if (dao == null)
			dao = new MemberDao();
		return dao;
	}

	// 1. tomcat에 라이브러리 폴더에 ojdbc6.jar를 넣어 둔다.
	// C:\ikosmo64\oracle1\product\11.2.0\dbhome_1\jdbc\lib

	// 2. Connection Pool에서 관리되는 Connection을 반환 받아서 사용함.
	// JDBC에서 사용하는 Connection pool은 생성비용이 높기 때문에 재사용성을 높이기 위해
	// Tomcat과 같은 WAS에서 관리되고 제공해주는 Connection pool을 사용해주는 것이 좋다.

	// 2tier jdbc와 오라클 사이에 방해물 없이 연결되는 걸 말함.(직접 연결)
	// 3tier는 tomcat에 있는 java를 통해서 연결되기 때문에 3tier.
	// 중간에 서버에게 달라고 하는거임. 서버가 Connection pool을 갖고 연결을 관리하는 것.
	// 렌트카 업체가 key를 갖고 있는 tomcat이 갖고 있는거임
	// 2-1. 관리 대장(Context.xml)

	public void addMember(MemberVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member values");
		sql.append("(member_seq.nextVal,?,?,?,?,?,?,?,?,?,sysdate,?)");
		// try-with-resources 문 적용 : jdk7
		// autoClosed가 적용되기 때문에 finally에서 close()를 할 필요가 없음.
		try (Connection con = MyConn.getConn(); PreparedStatement pstmt = con.prepareStatement(sql.toString());) {
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getCol());
			pstmt.setString(5, vo.getWebsite());
			pstmt.setString(6, vo.getUserloc());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, String.valueOf(vo.getGender()));
			pstmt.setString(9, vo.getSub());
			pstmt.setString(10, vo.getReip());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<MemberVO> getMemberList() {

		List<MemberVO> list = new ArrayList<MemberVO>();
		String sql = "select num, userid, username, col, email, gender, mdate from member";

		try (Connection con = MyConn.getConn();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();)
		// try() -> 괄호 안에 넣으면 close를 안해줘도 됨.
		{
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setNum(rs.getInt("num"));
				vo.setUserid(rs.getString("userid"));
				vo.setUsername(rs.getString("username"));
				vo.setCol(rs.getString("col"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender").charAt(0));
				vo.setMdate(rs.getString("mdate"));

				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	// 상세보기.
	public MemberVO getDetail(int num) {

		StringBuffer sql = new StringBuffer();
		sql.append("select num, userid, pwd, username, col, website, ");
		sql.append("userloc, email, gender, sub, mdate, reip ");
		sql.append("from member where num=?");

		MemberVO vo = new MemberVO();

		try (Connection con = MyConn.getConn(); PreparedStatement pstmt = con.prepareStatement(sql.toString());) {
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				vo.setNum(rs.getInt("num"));
				vo.setUserid(rs.getString("userid"));
				vo.setPwd(rs.getString("pwd")); // detail에서는 사용 안한단다!
				vo.setUsername(rs.getString("username"));
				vo.setCol(rs.getString("col"));
				vo.setWebsite(rs.getString("website"));
				vo.setUserloc(rs.getString("userloc"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender").charAt(0));
				vo.setSub(rs.getString("sub"));
				vo.setMdate(rs.getString("mdate"));
				vo.setReip(rs.getString("reip"));

			}
			// return vo; //바깥에다가 하는거임? if 도 반환으로 쓸 수있나? 아니지 try catch니까 try 일때, catch일 때 두
			// 가지 경우 return이
			// 어떻게 되는지 말할 수는 있어야지
		} catch (Exception e) {
			// TODO: handle exception
		}
		// return null;

		return vo; // catch때 null 주기 싫음 걍 vo한번만 주고 끝내 finally 마냥. 단 vo가 try문 바깥에 있어야 겠죵

	}

	public void updateMember(MemberVO vo) {
		// 이미 존재하는 vo값을 업데이트 하는데 vo를 새로 만들면 안되죠. 거기서 vo에 넣은 값 그대로 들고 와야 할 것 아닌가!!!!!!
		String sql = "update member set pwd=?, username=?, col=?, website=?, userloc=?, email=?, gender=?, sub=? where num=?";

		try (Connection con = MyConn.getConn(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getCol());
			pstmt.setString(4, vo.getWebsite());
			pstmt.setString(5, vo.getUserloc());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, String.valueOf(vo.getGender()));
			pstmt.setString(8, vo.getSub());
			pstmt.setInt(9, vo.getNum());

			pstmt.executeUpdate();
			System.out.println("-------------------------------------*" + vo.getNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteMember(MemberVO vo) {
		String sql = "delete from member where num = ?";

		try (Connection con = MyConn.getConn(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, vo.getNum());

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<MemberVO> getMemberSearch(Map<String, String> map) { // key는 검색할 조건, value는 검색값.
		StringBuffer sql = new StringBuffer();
		List<MemberVO> list = new ArrayList<MemberVO>(); //MemberVO에서 가져오는 값들을 저장할 리스트 객체 생성 
		
		if (map.containsKey("1")) { // key값이 1일 경우 
			sql.append("select * from member where userid like '%");
			sql.append(map.get("1"));//value값을 가져옴
			sql.append("%' or username like '%");
			sql.append(map.get("1"));
			sql.append("%' or email like '%");
			sql.append(map.get("1"));
			sql.append("%' or gender like '%");
			sql.append(map.get("1"));
			sql.append("%'");
					// "select * from member where username like '%니%'"
		}
		//위와 같음 
		else if (map.containsKey("2")) {
			sql.append("select * from member where ");
			sql.append("userid like '%");
			sql.append(map.get("2"));
			sql.append("%'");
			// "select * from member where username like '%니%'"
		} else if (map.containsKey("3")) {
			sql.append("select * from member where ");
			sql.append("username like '%");
			sql.append(map.get("3"));
			sql.append("%'");
		} else if (map.containsKey("4")){
			sql.append("select * from member where ");
			sql.append("sub like '%");
			sql.append(map.get("4"));
			sql.append("%'");
		}
		
		//select 구문과 같음 
		try (Connection con = MyConn.getConn();
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();)
		// try() -> 괄호 안에 넣으면 close를 안해줘도 됨.
		{
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setNum(rs.getInt("num"));
				vo.setUserid(rs.getString("userid"));
				vo.setUsername(rs.getString("username"));
				vo.setCol(rs.getString("col"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender").charAt(0));
				vo.setMdate(rs.getString("mdate"));

				list.add(vo);
			}

			// "select * from member where username like '&니&'"

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
