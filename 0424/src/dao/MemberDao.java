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
	// �̱��� ���� ����: �ڿ��� �ֲ�����

	private static MemberDao dao;// static�� ���� ���� ��, ���� �ϳ��� ����.

	private MemberDao() { // �⺻������ private�� ������ �ܺο��� new�� �������� �����
	}

	// syschronized ����ȭ ��� ���� - Thread Safe - Lock pool
	public synchronized static MemberDao getDao() {
		// ������ MemberDao�� ��ȯ�ϴ� getMember() ȣ���ߴٸ� �����ϴ� ���ǹ�
		// ó�� dao�� ���� �� ���� ���� �־ ���. �̺κ� ���� ���� �Ƴ��°���. �� �ڷδ� �� retrun��.
		if (dao == null)
			dao = new MemberDao();
		return dao;
	}

	// 1. tomcat�� ���̺귯�� ������ ojdbc6.jar�� �־� �д�.
	// C:\ikosmo64\oracle1\product\11.2.0\dbhome_1\jdbc\lib

	// 2. Connection Pool���� �����Ǵ� Connection�� ��ȯ �޾Ƽ� �����.
	// JDBC���� ����ϴ� Connection pool�� ��������� ���� ������ ���뼺�� ���̱� ����
	// Tomcat�� ���� WAS���� �����ǰ� �������ִ� Connection pool�� ������ִ� ���� ����.

	// 2tier jdbc�� ����Ŭ ���̿� ���ع� ���� ����Ǵ� �� ����.(���� ����)
	// 3tier�� tomcat�� �ִ� java�� ���ؼ� ����Ǳ� ������ 3tier.
	// �߰��� �������� �޶�� �ϴ°���. ������ Connection pool�� ���� ������ �����ϴ� ��.
	// ��Ʈī ��ü�� key�� ���� �ִ� tomcat�� ���� �ִ°���
	// 2-1. ���� ����(Context.xml)

	public void addMember(MemberVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member values");
		sql.append("(member_seq.nextVal,?,?,?,?,?,?,?,?,?,sysdate,?)");
		// try-with-resources �� ���� : jdk7
		// autoClosed�� ����Ǳ� ������ finally���� close()�� �� �ʿ䰡 ����.
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
		// try() -> ��ȣ �ȿ� ������ close�� �����൵ ��.
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

	// �󼼺���.
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
				vo.setPwd(rs.getString("pwd")); // detail������ ��� ���Ѵܴ�!
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
			// return vo; //�ٱ����ٰ� �ϴ°���? if �� ��ȯ���� �� ���ֳ�? �ƴ��� try catch�ϱ� try �϶�, catch�� �� ��
			// ���� ��� return��
			// ��� �Ǵ��� ���� ���� �־����
		} catch (Exception e) {
			// TODO: handle exception
		}
		// return null;

		return vo; // catch�� null �ֱ� ���� �� vo�ѹ��� �ְ� ���� finally ����. �� vo�� try�� �ٱ��� �־�� ����

	}

	public void updateMember(MemberVO vo) {
		// �̹� �����ϴ� vo���� ������Ʈ �ϴµ� vo�� ���� ����� �ȵ���. �ű⼭ vo�� ���� �� �״�� ��� �;� �� �� �ƴѰ�!!!!!!
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

	public List<MemberVO> getMemberSearch(Map<String, String> map) { // key�� �˻��� ����, value�� �˻���.
		StringBuffer sql = new StringBuffer();
		List<MemberVO> list = new ArrayList<MemberVO>(); //MemberVO���� �������� ������ ������ ����Ʈ ��ü ���� 
		
		if (map.containsKey("1")) { // key���� 1�� ��� 
			sql.append("select * from member where userid like '%");
			sql.append(map.get("1"));//value���� ������
			sql.append("%' or username like '%");
			sql.append(map.get("1"));
			sql.append("%' or email like '%");
			sql.append(map.get("1"));
			sql.append("%' or gender like '%");
			sql.append(map.get("1"));
			sql.append("%'");
					// "select * from member where username like '%��%'"
		}
		//���� ���� 
		else if (map.containsKey("2")) {
			sql.append("select * from member where ");
			sql.append("userid like '%");
			sql.append(map.get("2"));
			sql.append("%'");
			// "select * from member where username like '%��%'"
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
		
		//select ������ ���� 
		try (Connection con = MyConn.getConn();
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();)
		// try() -> ��ȣ �ȿ� ������ close�� �����൵ ��.
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

			// "select * from member where username like '&��&'"

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
