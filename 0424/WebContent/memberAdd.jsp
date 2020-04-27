<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- 폼에서의 회원의 데이터를 모두 받아서 MemberVO를 저장하고 Dao로 저장된 vo의 주소를 전달 목적
수행 후 index.jsp로 자동으로 이동시키는 기능
-->

<% //자바의 메서드 영역
// 한글 인코딩 처리
request.setCharacterEncoding("euc-kr");

//1. 파라미터를 모두 받아서 MemberVO setter를 호출해서 저장해놓는다.
MemberVO vo = new MemberVO();
//request.getParameter("userid") => 폼에서 받은 userid 파라미터를 받고 난 후에
//vo객체의 주소로부터 setter호출해서 값을 전달.
vo.setReip(request.getParameter("reip"));
vo.setUserid(request.getParameter("userid"));
vo.setPwd(request.getParameter("pwd"));
vo.setUsername(request.getParameter("username"));
vo.setCol(request.getParameter("col"));
vo.setWebsite(request.getParameter("website"));
vo.setUserloc(request.getParameter("userloc"));
vo.setEmail(request.getParameter("email"));
//---------------------------------------------------------
// charAt(0) : 문자열에서 첫번째 한 문자를 반환
char gender = request.getParameter("gender").charAt(0);
vo.setGender(gender);
// checkbox일 경우 name="sub" 즉, 배열로 반환되기 때문에 배열로 처리
StringBuffer sb = new StringBuffer();
String[] sub = request.getParameterValues("sub");
for(String e : sub){
	//각 선택값을 구분자로 입력시킨다.
	sb.append(e).append("/");
}
vo.setSub(sb.toString());

//아이피가 0:0:0:0:0:0:0:1 이라는건 ipv6의 주소를 가져온것으로 ipv4로 봤을때 127.0.0.1 이 맞다.
//localhost로 서버에 접속했을 때 생기는 현상이며, 10.10.10.1 과 같은 아이피로 접근했을때는 정상적으로 가져온다.
//윈도우7 에선 기본적으로 IPv6를 리턴하므로 WAS 세팅으로 바꾸어 주면된다.


System.out.println("reip: "+vo.getReip());
System.out.println("userid: "+vo.getUserid());
System.out.println("pwd: "+vo.getPwd());
System.out.println("username: "+vo.getUsername());
System.out.println("col: "+vo.getCol());
System.out.println("website: "+vo.getWebsite());
System.out.println("userloc: "+vo.getUserloc());
System.out.println("email: "+vo.getEmail());
System.out.println("gender: "+vo.getGender());
System.out.println("sub: "+vo.getSub());
//System.out.println(gender);
//System.out.println(sb);

// Dao로 값을 전달한다.
MemberDao.getDao().addMember(vo);
// MemberDao dao = new MemberDao

// 페이지 이동 index.jsp
response.sendRedirect("index.jsp");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>