<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%
//num �Ķ���� ���� �޾Ƽ� ������ �ִ´�.
int num = Integer.parseInt(request.getParameter("num"));

MemberVO vo = MemberDao.getDao().getDetail(num);

%>
<meta charset="EUC-KR">
<title>ex1_memberDetail.jsp</title>
</head>
<body>
<table border="1">
<tr>
	<th colspan="2">ȸ�� Detail information</th>
</tr>
<tr>
	<td>��ȣ</td>
	<td><input type="text" name="num" value="<%=vo.getNum() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>���̵�</td>
	<td><input type="text" name="userid" value="<%=vo.getUserid() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>�̸�</td>
	<td><input type="text" name="userid" value="<%=vo.getUsername() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>website</td>
	<td><input type="text" name="website" value="<%=vo.getWebsite() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>����</td>
	<td><input type="text" name="userloc" value="<%=vo.getUserloc() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>�̸���</td>
	<td><input type="text" name="email" value="<%=vo.getEmail() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>����</td>
	<td><input type="text" name="gender" value="<%=vo.getGender() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>���Գ�¥</td>
	<td><input type="text" name="mdate" value="<%=vo.getMdate() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>����</td>
	<td><input type="text" name="sub" value="<%=vo.getSub() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>ip</td>
	<td><input type="text" name="reip" value="<%=vo.getReip() %>" readonly="readonly"></td>
</tr>
<tr>
<th colspan="2">
<a href="ex1_memberList.jsp">ȸ�����</a>
&nbsp; <a href="ex1_memberForm.jsp">������</a>
&nbsp; <a href="ex1_memberupdateForm.jsp?num=<%=num%>">����</a>
&nbsp; <a href="ex1_memberDeleteForm.jsp?num=<%=num%>">����</a>
</tr>
</table>

</body>
</html>