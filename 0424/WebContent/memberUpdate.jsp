<%@page import="dao.MemberDao"%>
<%@page import="vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 


request.setCharacterEncoding("euc-kr"); //�ѱ�ó��

MemberVO vo = new MemberVO();

//�������� �Ѿ���� num ����
vo.setNum(Integer.parseInt(request.getParameter("num")));
vo.setReip(request.getParameter("reip"));
vo.setPwd(request.getParameter("pwd"));
vo.setUsername(request.getParameter("username"));
vo.setCol(request.getParameter("col"));
vo.setWebsite(request.getParameter("website"));
vo.setUserloc(request.getParameter("userloc"));
vo.setEmail(request.getParameter("email"));
char gender = request.getParameter("gender").charAt(0);
vo.setGender(gender);
StringBuffer sb = new StringBuffer();
String[] sub = request.getParameterValues("sub");
for(String e : sub){
	sb.append(e).append("/");
}
vo.setSub(sb.toString());

MemberDao.getDao().updateMember(vo);

response.sendRedirect("ex1_memberDetail.jsp?num="+vo.getNum());










%>