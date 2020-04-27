<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="vo.MemberVO"%>

<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
request.setCharacterEncoding("EUC-KR");
List<MemberVO> list = null;
	//List<MemberVO>�� MemberDao�κ��� ��ȯ�޴´�.
	if (request.getParameter("stype") != null) { //���࿡ key:stype(name)���� ������
		Map<String, String> map = new HashMap<>();// map ��ü �����Ͽ� 
		map.put(request.getParameter("stype"), request.getParameter("svalue"));//(key, values)��  put �ֱ� --> key�� values��  ��û�� ���� ���·� 
		list = MemberDao.getDao().getMemberSearch(map);// MemeberDao�� getMemberSearch �޼��忡 map���� �־� list������ ���� <�� list�� ���̺� 2�࿡ for������� �ѷ���>
	}else{
		list = MemberDao.getDao().getMemberList();
	}


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
			<th colspan="7">ȸ������Ʈ</th>
		</tr>

		<tr>
			<td>��ȣ</td>
			<td>���̵�</td>
			<td>�̸�</td>
			<td>�̸���</td>
			<td>����</td>
			<td>���Գ�¥</td>
			<td>ȸ������</td>
		</tr>
		<%
			for (MemberVO e : list) {
		%>
		<tr style="background: <%=e.getCol()%>">
			<td><%=e.getNum()%></td>
			<td><%=e.getUserid()%></td>
			<td><%=e.getUsername()%></td>
			<td><%=e.getEmail()%></td>
			<td><%=e.getGender()%></td>
			<td><%=e.getMdate()%></td>
			<td><a href="ex1_memberDetail.jsp?num=<%=e.getNum()%>">ȸ������Detail</a></td>
		</tr>
		<%
			}
		%>
		<form method="post" action="ex1_memberList.jsp">
			<tr>
				<th colspan="7"><select name="stype"> <!-- option stype : 1:��ü, 2:���̵�,3:�̸�,4:�ڽ��ִ� ���� -->
						<option value="1">��ü</option>  <!--key�� = stype(name)  -->
						<option value="2">���̵�</option>
						<option value="3">�̸�</option>
						<option value="4">�ڽ��ִ� ����</option> 
				</select> <input type="text" name="svalue"> 
				<!--input text : �Է�â�� ���� ��  -->
				<!-- values��= svalue(name) -->  
				<!-- �� ���� Map ��ü ���� Map<key, values> -->
				<input type="submit" value="�˻�"></th>
			</tr>


		</form>








		<tr>
			<th colspan="7"><a href="ex1_memberForm.jsp">������</a></th>
		</tr>
	</table>
	<br>
	<input type="button" value="�޴��� ���ư���"
		onclick="location.href='index.jsp'">
</body>
</html>