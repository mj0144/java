<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h1>�̵����� �ڵ� ����</h1>
		<h2>�� �ʵ� �Ҽ��־�!</h2>
<!-- https://coding-factory.tistory.com/188 div ���� -->
		<div>
			<form method="post" action="memberAdd.jsp">
			<!-- �������  ip -->
			<!-- hidden: �������������� �Ⱥ������� ���� �����͸� �����ϱ� ���� �Ķ������ ������ �Ѵ�.-->
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			
				���̵�: <input type="text" name="userid" required="required" maxlength="10"><br> 
				��й�ȣ: <input type="password" name="pwd" required="required" maxlength="10"><br>
				�̸�: <input type="text"	name="username"><br>
				�����ϴ� ��: <input type="color" name="col"><br>
				��ȣ �˻�����Ʈ: <input type="url" name="website" list="s_data" required="required"
				pattern="http://.*" placeholder="http://www.ikosmo.co.kr"><br>
				�������: <select name="userloc">
					<option value="">�����˻�</option>
					<option value="02">����</option>
					<option value="032">��õ</option>
					<option value="031">���</option>
				</select> <br>
				�̸���: <input type="email" name="email" required="required">
				����: <input	type="radio" name="gender" value="f" checked="checked">����
				     <input type="radio" name="gender" value="m">���� <br>
				�ڽ��ִ� ����: <input type="checkbox" name="sub" value="java" checked="checked"> JAVA
				           <input type="checkbox" name="sub" value="python" checked="checked"> Python
				           <input type="checkbox" name="sub" value="web"> Web
				           <input type="checkbox" name="sub" value="oracle"> Oracle
				           <input type="checkbox" name="sub" value="javascript"> Javascript <br>
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