<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%
int num = Integer.parseInt(request.getParameter("num"));
// Dao�� ���� �� ȸ���� ������ ��ȯ �ޱ�
MemberVO vo = MemberDao.getDao().getDetail(num);

%>

<meta charset="EUC-KR">
<title>ex1_memberupdateForm.jsp</title>
</head>
<body>
	<div>
		<h1>�̵����� �ڵ� ����</h1>
		<h2>�� �ʵ� �Ҽ��־�!</h2>
<!-- https://coding-factory.tistory.com/188 div ���� -->
		<div>
			<form method="post" action="memberUpdate.jsp">
			<input type="hidden" name="num" value="<%=vo.getNum() %>">
			<!-- �������  ip -->
			<!-- hidden: �������������� �Ⱥ������� ���� �����͸� �����ϱ� ���� �Ķ������ ������ �Ѵ�.-->
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			
				���̵�: <input type="text" required="required" maxlength="10" readonly="readonly"
				value="<%=vo.getUserid()%>"><br> 
				��й�ȣ: <input type="password" name="pwd" required="required" maxlength="10" value="<%=vo.getPwd() %>"><br>
				�̸�: <input type="text"	name="username" value="<%=vo.getUsername() %>"><br>
				�����ϴ� ��: <input type="color" name="col" value="<%=vo.getCol() %>"><br>
				��ȣ �˻�����Ʈ: <input type="url" name="website" list="s_data" required="required"
				pattern="http://.*" placeholder="http://www.ikosmo.co.kr" value="<%=vo.getWebsite()%>"><br>
				<%-- userloc�� ���� 02,032,031�̳Ŀ� ���� selected="selected" --%>
				�������: <%=vo.getUserloc() %><select name="userloc">
				<option value="">�����˻�</option>
				<%
				String locv= vo.getUserloc();
				if(locv.equals("02")){ %>
					<option value="02" selected="selected">����</option>
					<option value="032">��õ</option>
					<option value="031">���</option>
				<%}else if(locv.equals("032")){ %>
				<option value="02" >����</option>
					<option value="032" selected="selected">��õ</option>
					<option value="031">���</option>
				<%}else if(locv.equals("031")){ %>
				<option value="02">����</option>
					<option value="032" selected="selected">��õ</option>
					<option value="031">���</option>
				<%} %>
				
				
				</select> <br>
				�̸���: <input type="email" name="email" required="required" value="<%=vo.getEmail()%>">
				����: 				     
   				<input	type="radio" name="gender" value="f" <%if(vo.getGender() == 'f') {%> checked="checked"<%} %>>����
   				<input	type="radio" name="gender" value="m" <%if(vo.getGender() == 'm') {%> checked="checked"<%} %>>����
   				<br>
	
				�ڽ��ִ� ����: 
				<%
				//check box ó��
				String[] subArr = vo.getSub().split("/");
				Map<String,String> checked=new HashMap<>();
				for(String e : subArr){
					checked.put(e, "checked");
				}
				%>
				<input type="checkbox" name="sub" value="java" checked="<%=checked.get("java") %>"> JAVA
				<input type="checkbox" name="sub" value="python" checked="<%=checked.get("python") %>"> Python
				<input type="checkbox" name="sub" value="<%=checked.get("web") %>"> Web
				<input type="checkbox" name="sub" value="<%=checked.get("oracle") %>"> Oracle
				<input type="checkbox" name="sub" value="<%=checked.get("javascript") %>"> Javascript <br>
			
				 <span style="margin-left: 300px"><input type="submit" value="�����ϱ�"></span>
				 <!-- html5���� �����͸� ����� �� �ִ� ����̴�. -->
				 <datalist id="s_data">
				 <option label="���̹�" value="http://www.naver.com">
				 <option label="����" value="http://www.daum.net">
				 <option label="�ڽ���" value="http://www.ikosmo.co.kr">
				 <option label="����" value="http://www.google.com">
				 <option label="����" value="http://www.shinhan.com">
				 </datalist>
			</form>
		</div>
	</div>
</body>
</html>