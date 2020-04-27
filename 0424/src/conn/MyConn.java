package conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
//JNDI(Java Naming and Directory Interface) �ڹ� �÷��� ����� ���ø����̼��� ��Ī�� �ο��Ѵٴ���
//���丮 ���񽺸� ó���� �� ����ϴ� �������α׷� �������̽� API
//Ư�� ������Ʈ�� naming���� ã�Ƴ��� ���
//=> context.xml �� (�ɺθ����� DataSource�� Tomcat�� ���ؼ� �����Ǵ� Connection Pool����
// �ϳ��� Connection�� ȹ���ϱ� ���� ��������)�� ���� ���� InitialContext�� ����ؼ� Connection�� 
//ã�ƿ��� ���(��, JDNI�� ����ؼ� ã�ƿͶ�.) 

public class MyConn {
	// Connection�� ȹ���ϴ� ��ü�� ����
	private static DataSource ds;
	static {
		try {
			//context.xml�� �о� ���̴� API�̴�.
			InitialContext ctx = new InitialContext();
			// lookup naming�� �������� Connection�� ȹ���� DataSource ��ü�� ��ȯ
			//java:comp/env/ �����
			//jdbc/myoracle�̶� �̸��� ���� �� ���� �ͼ� DataSource�� ���� �ٲ㼭 ���� ����
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// �ʱ�ȭ�� DatSource ��ü�κ��� CP�κ��� �ϳ��� Connection�� ��ȯ���ִ� �޼���
	public static Connection getConn() throws SQLException {
		return ds.getConnection();
	}
	

}
