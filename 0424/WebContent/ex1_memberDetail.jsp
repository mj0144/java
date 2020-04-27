<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<%
//num 파라미터 값을 받아서 변수에 넣는다.
int num = Integer.parseInt(request.getParameter("num"));

MemberVO vo = MemberDao.getDao().getDetail(num);

%>
<meta charset="EUC-KR">
<title>ex1_memberDetail.jsp</title>
</head>
<body>
<table border="1">
<tr>
	<th colspan="2">회원 Detail information</th>
</tr>
<tr>
	<td>번호</td>
	<td><input type="text" name="num" value="<%=vo.getNum() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>아이디</td>
	<td><input type="text" name="userid" value="<%=vo.getUserid() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="userid" value="<%=vo.getUsername() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>website</td>
	<td><input type="text" name="website" value="<%=vo.getWebsite() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>지역</td>
	<td><input type="text" name="userloc" value="<%=vo.getUserloc() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="email" value="<%=vo.getEmail() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>성별</td>
	<td><input type="text" name="gender" value="<%=vo.getGender() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>가입날짜</td>
	<td><input type="text" name="mdate" value="<%=vo.getMdate() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>과목</td>
	<td><input type="text" name="sub" value="<%=vo.getSub() %>" readonly="readonly"></td>
</tr>
<tr>
	<td>ip</td>
	<td><input type="text" name="reip" value="<%=vo.getReip() %>" readonly="readonly"></td>
</tr>
<tr>
<th colspan="2">
<a href="ex1_memberList.jsp">회원목록</a>
&nbsp; <a href="ex1_memberForm.jsp">가입폼</a>
&nbsp; <a href="ex1_memberupdateForm.jsp?num=<%=num%>">수정</a>
&nbsp; <a href="ex1_memberDeleteForm.jsp?num=<%=num%>">삭제</a>
</tr>
</table>

</body>
</html>