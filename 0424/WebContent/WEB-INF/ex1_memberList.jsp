<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>

<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    //List<MemberVO>�� MemberDao�κ��� ��ȯ�޴´�.
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
	<th colspan ="6"> ȸ������Ʈ </th>
</tr>

<tr>
	<td>��ȣ</td>
	<td>���̵�</td>
	<td>�̸�</td>
	<td>�̸���</td>
	<td>����</td>
	<td>���Գ�¥</td>
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
		<a href="ex1_memberForm.jsp">������</a>
	</th>
</tr>
</table>
<br>
<input type="button"  value="�޴��� ���ư���" onclick="location.href='index.jsp'">
</body>
</html>