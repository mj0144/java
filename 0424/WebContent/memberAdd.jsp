<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!-- �������� ȸ���� �����͸� ��� �޾Ƽ� MemberVO�� �����ϰ� Dao�� ����� vo�� �ּҸ� ���� ����
���� �� index.jsp�� �ڵ����� �̵���Ű�� ���
-->

<% //�ڹ��� �޼��� ����
// �ѱ� ���ڵ� ó��
request.setCharacterEncoding("euc-kr");

//1. �Ķ���͸� ��� �޾Ƽ� MemberVO setter�� ȣ���ؼ� �����س��´�.
MemberVO vo = new MemberVO();
//request.getParameter("userid") => ������ ���� userid �Ķ���͸� �ް� �� �Ŀ�
//vo��ü�� �ּҷκ��� setterȣ���ؼ� ���� ����.
vo.setReip(request.getParameter("reip"));
vo.setUserid(request.getParameter("userid"));
vo.setPwd(request.getParameter("pwd"));
vo.setUsername(request.getParameter("username"));
vo.setCol(request.getParameter("col"));
vo.setWebsite(request.getParameter("website"));
vo.setUserloc(request.getParameter("userloc"));
vo.setEmail(request.getParameter("email"));
//---------------------------------------------------------
// charAt(0) : ���ڿ����� ù��° �� ���ڸ� ��ȯ
char gender = request.getParameter("gender").charAt(0);
vo.setGender(gender);
// checkbox�� ��� name="sub" ��, �迭�� ��ȯ�Ǳ� ������ �迭�� ó��
StringBuffer sb = new StringBuffer();
String[] sub = request.getParameterValues("sub");
for(String e : sub){
	//�� ���ð��� �����ڷ� �Է½�Ų��.
	sb.append(e).append("/");
}
vo.setSub(sb.toString());

//�����ǰ� 0:0:0:0:0:0:0:1 �̶�°� ipv6�� �ּҸ� �����°����� ipv4�� ������ 127.0.0.1 �� �´�.
//localhost�� ������ �������� �� ����� �����̸�, 10.10.10.1 �� ���� �����Ƿ� ������������ ���������� �����´�.
//������7 ���� �⺻������ IPv6�� �����ϹǷ� WAS �������� �ٲپ� �ָ�ȴ�.


System.out.println("reip: "+vo.getReip());
System.out.println("userid: "+vo.getUserid());
System.out.println("pwd: "+vo.getPwd());
System.out.println("username: "+vo.getUsername());
System.out.println("col: "+vo.getCol());
System.out.println("website: "+vo.getWebsite());
System.out.println("userloc: "+vo.getUserloc());
System.out.println("email: "+vo.getEmail());
System.out.println("gender: "+vo.getGender());
System.out.println("sub: "+vo.getSub());
//System.out.println(gender);
//System.out.println(sb);

// Dao�� ���� �����Ѵ�.
MemberDao.getDao().addMember(vo);
// MemberDao dao = new MemberDao

// ������ �̵� index.jsp
response.sendRedirect("index.jsp");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>