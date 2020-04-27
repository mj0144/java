<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>

<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    //List<MemberVO>를 MemberDao로부터 반환받는다.
    	List<MemberVO> list = MemberDao.getDao().getMemberList();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ex1_memberList.jsp</title>
</head>
<body>
<table border="1">
<tr>
	<th colspan ="6"> 회원리스트 </th>
</tr>

<tr>
	<td>번호</td>
	<td>아이디</td>
	<td>이름</td>
	<td>이메일</td>
	<td>성별</td>
	<td>가입날짜</td>
</tr>
<%
for(MemberVO e : list){ %>
	<tr style="background: <%=e.getCol()%>">
	<td><%=e.getNum() %></td>
	<td><%=e.getUserid() %></td>
	<td><%=e.getUsername() %></td>
	<td><%=e.getEmail() %></td>
	<td><%=e.getGender() %></td>
	<td><%=e.getMdate() %></td>
</tr>
<% } %>

<tr>
	<th colspan ="6">
		<a href="ex1_memberForm.jsp">가입폼</a>
	</th>
</tr>
</table>
<br>
<input type="button"  value="메뉴로 돌아가기" onclick="location.href='index.jsp'">
</body>
</html>