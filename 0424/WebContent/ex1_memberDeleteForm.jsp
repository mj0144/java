<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% int num = Integer.parseInt(request.getParameter("num"));

MemberVO vo = new MemberVO();
vo.setNum(num);
MemberDao.getDao().deleteMember(vo);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

삭제 되었습니다.

</body>
</html>