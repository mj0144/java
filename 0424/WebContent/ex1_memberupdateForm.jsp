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
// Dao로 전달 후 회원의 데이터 반환 받기
MemberVO vo = MemberDao.getDao().getDetail(num);

%>

<meta charset="EUC-KR">
<title>ex1_memberupdateForm.jsp</title>
</head>
<body>
	<div>
		<h1>이동현의 코딩 강좌</h1>
		<h2>야 너두 할수있어!</h2>
<!-- https://coding-factory.tistory.com/188 div 설명 -->
		<div>
			<form method="post" action="memberUpdate.jsp">
			<input type="hidden" name="num" value="<%=vo.getNum() %>">
			<!-- 사용자의  ip -->
			<!-- hidden: 웹브라우저에서는 안보이지만 폼의 데이터를 전송하기 위한 파라미터의 역할을 한다.-->
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			
				아이디: <input type="text" required="required" maxlength="10" readonly="readonly"
				value="<%=vo.getUserid()%>"><br> 
				비밀번호: <input type="password" name="pwd" required="required" maxlength="10" value="<%=vo.getPwd() %>"><br>
				이름: <input type="text"	name="username" value="<%=vo.getUsername() %>"><br>
				좋아하는 색: <input type="color" name="col" value="<%=vo.getCol() %>"><br>
				선호 검색사이트: <input type="url" name="website" list="s_data" required="required"
				pattern="http://.*" placeholder="http://www.ikosmo.co.kr" value="<%=vo.getWebsite()%>"><br>
				<%-- userloc의 값이 02,032,031이냐에 따라서 selected="selected" --%>
				등록지역: <%=vo.getUserloc() %><select name="userloc">
				<option value="">지역검색</option>
				<%
				String locv= vo.getUserloc();
				if(locv.equals("02")){ %>
					<option value="02" selected="selected">서울</option>
					<option value="032">인천</option>
					<option value="031">경기</option>
				<%}else if(locv.equals("032")){ %>
				<option value="02" >서울</option>
					<option value="032" selected="selected">인천</option>
					<option value="031">경기</option>
				<%}else if(locv.equals("031")){ %>
				<option value="02">서울</option>
					<option value="032" selected="selected">인천</option>
					<option value="031">경기</option>
				<%} %>
				
				
				</select> <br>
				이메일: <input type="email" name="email" required="required" value="<%=vo.getEmail()%>">
				성별: 				     
   				<input	type="radio" name="gender" value="f" <%if(vo.getGender() == 'f') {%> checked="checked"<%} %>>여자
   				<input	type="radio" name="gender" value="m" <%if(vo.getGender() == 'm') {%> checked="checked"<%} %>>남자
   				<br>
	
				자신있는 과목: 
				<%
				//check box 처리
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
			
				 <span style="margin-left: 300px"><input type="submit" value="수정하기"></span>
				 <!-- html5에서 데이터를 취급할 수 있는 요소이다. -->
				 <datalist id="s_data">
				 <option label="네이버" value="http://www.naver.com">
				 <option label="다음" value="http://www.daum.net">
				 <option label="코스모" value="http://www.ikosmo.co.kr">
				 <option label="구글" value="http://www.google.com">
				 <option label="신한" value="http://www.shinhan.com">
				 </datalist>
			</form>
		</div>
	</div>
</body>
</html>