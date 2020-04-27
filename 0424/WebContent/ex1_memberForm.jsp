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
		<h1>이동현의 코딩 강좌</h1>
		<h2>야 너두 할수있어!</h2>
<!-- https://coding-factory.tistory.com/188 div 설명 -->
		<div>
			<form method="post" action="memberAdd.jsp">
			<!-- 사용자의  ip -->
			<!-- hidden: 웹브라우저에서는 안보이지만 폼의 데이터를 전송하기 위한 파라미터의 역할을 한다.-->
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			
				아이디: <input type="text" name="userid" required="required" maxlength="10"><br> 
				비밀번호: <input type="password" name="pwd" required="required" maxlength="10"><br>
				이름: <input type="text"	name="username"><br>
				좋아하는 색: <input type="color" name="col"><br>
				선호 검색사이트: <input type="url" name="website" list="s_data" required="required"
				pattern="http://.*" placeholder="http://www.ikosmo.co.kr"><br>
				등록지역: <select name="userloc">
					<option value="">지역검색</option>
					<option value="02">서울</option>
					<option value="032">인천</option>
					<option value="031">경기</option>
				</select> <br>
				이메일: <input type="email" name="email" required="required">
				성별: <input	type="radio" name="gender" value="f" checked="checked">여자
				     <input type="radio" name="gender" value="m">남자 <br>
				자신있는 과목: <input type="checkbox" name="sub" value="java" checked="checked"> JAVA
				           <input type="checkbox" name="sub" value="python" checked="checked"> Python
				           <input type="checkbox" name="sub" value="web"> Web
				           <input type="checkbox" name="sub" value="oracle"> Oracle
				           <input type="checkbox" name="sub" value="javascript"> Javascript <br>
				 <span style="margin-left: 300px"><input type="submit" value="가입하기"></span>
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