package conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
//JNDI(Java Naming and Directory Interface) 자바 플랫폼 기반의 애플리케이션이 명칭을 부여한다던지
//디렉토리 서비스를 처리할 때 사용하는 응용프로그램 인터페이스 API
//특정 컴포넌트를 naming으로 찾아내는 기법
//=> context.xml 에 (심부름꾼인 DataSource를 Tomcat에 의해서 관리되는 Connection Pool에서
// 하나의 Connection을 획득하기 위한 설정파일)서 부터 실제 InitialContext를 사용해서 Connection을 
//찾아오는 방법(즉, JDNI를 사용해서 찾아와라.) 

public class MyConn {
	// Connection을 획득하는 객체를 선언
	private static DataSource ds;
	static {
		try {
			//context.xml을 읽어 들이는 API이다.
			InitialContext ctx = new InitialContext();
			// lookup naming을 기준으로 Connection을 획득할 DataSource 객체를 반환
			//java:comp/env/ 예약어
			//jdbc/myoracle이란 이름을 가진 걸 갖고 와서 DataSource로 형태 바꿔서 변수 지정
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 초기화된 DatSource 객체로부터 CP로부터 하나의 Connection을 반환해주는 메서드
	public static Connection getConn() throws SQLException {
		return ds.getConnection();
	}
	

}
