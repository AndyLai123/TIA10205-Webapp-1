<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mbr.model.*"%>


<%
    MbrService mbrSvc = new MbrService();
    List<MbrVO> list = mbrSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有會員資料 - listAllMbr.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllMbr.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	    <th>會員編號</th>
	    <th>會員註冊日期</th>
	    <th>會員姓名</th>
	    <th>會員帳號</th>
	    <th>會員密碼</th>
	    <th>會員信箱</th>
	    <th>會員性別</th>
	    <th>會員電話</th>
	    <th>會員頭像</th>
	    <th>修改</th>
	    <th>刪除</th>
	    
	 </tr>
	  <%@ include file="page1.file" %> 
	  <c:forEach var="mbrVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
	  
	    <tr>
	        <td>${mbrVO.memberId}</td>
	        <td>${mbrVO.regisdate}</td>
	        <td>${mbrVO.name}</td>
	        <td>${mbrVO.account}</td>
	        <td>${mbrVO.password}</td>
	        <td>${mbrVO.email}</td>
	        <td>${mbrVO.gender}</td>
	        <td>${mbrVO.mobileno}</td>
	        <td>${mbrVO.sticker}</td>
	        <td>
	         <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbr/mbr.do" style="margin-bottom: 0px;">
	              <input type="submit" value="修改">
			     <input type="hidden" name="memberId"  value="${mbrVO.memberId}">
			     <input type="hidden" name="action" value="getOne_For_Update"></FORM>
			     </td>
			<td>     
	        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mbr/mbr.do" style="margin-bottom: 0px;">
	              <input type="submit" value="刪除">
			     <input type="hidden" name="memberId"  value="${mbrVO.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			     </td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>