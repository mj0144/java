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
	//List<MemberVO>를 MemberDao로부터 반환받는다.
	if (request.getParameter("stype") != null) { //만약에 key:stype(name)값이 들어오면
		Map<String, String> map = new HashMap<>();// map 객체 생성하여 
		map.put(request.getParameter("stype"), request.getParameter("svalue"));//(key, values)로  put 넣기 --> key와 values가  요청을 받은 상태로 
		list = MemberDao.getDao().getMemberSearch(map);// MemeberDao의 getMemberSearch 메서드에 map값을 넣어 list변수에 넣음 <이 list는 테이블 2행에 for향상문으로 뿌려짐>
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
			<th colspan="7">회원리스트</th>
		</tr>

		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>성별</td>
			<td>가입날짜</td>
			<td>회원정보</td>
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
			<td><a href="ex1_memberDetail.jsp?num=<%=e.getNum()%>">회원정보Detail</a></td>
		</tr>
		<%
			}
		%>
		<form method="post" action="ex1_memberList.jsp">
			<tr>
				<th colspan="7"><select name="stype"> <!-- option stype : 1:전체, 2:아이디,3:이름,4:자신있는 과목 -->
						<option value="1">전체</option>  <!--key값 = stype(name)  -->
						<option value="2">아이디</option>
						<option value="3">이름</option>
						<option value="4">자신있는 과목</option> 
				</select> <input type="text" name="svalue"> 
				<!--input text : 입력창에 적힌 것  -->
				<!-- values값= svalue(name) -->  
				<!-- 맨 위에 Map 객체 설계 Map<key, values> -->
				<input type="submit" value="검색"></th>
			</tr>


		</form>








		<tr>
			<th colspan="7"><a href="ex1_memberForm.jsp">가입폼</a></th>
		</tr>
	</table>
	<br>
	<input type="button" value="메뉴로 돌아가기"
		onclick="location.href='index.jsp'">
</body>
</html>