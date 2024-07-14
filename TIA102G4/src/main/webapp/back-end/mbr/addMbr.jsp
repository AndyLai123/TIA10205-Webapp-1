<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mbr.model.*"%>

<% 
   MbrVO mbrVO = (MbrVO) request.getAttribute("mbrVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料新增 - addMbr.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料新增 - addMbr.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/mbr/mbr.do" name="form1" enctype="multipart/form-data">
<table>

    <tr>
      <td>會員註冊日期:</td>
      <td><input name="regisdate" id="f_date1" type="text" ></td>
	</tr>
	<tr>
      <td>會員姓名:</td>
      <td><input type="TEXT" name="name" value="<%= (mbrVO==null)? "" : mbrVO.getName()%>" size="45"/></td>
	</tr>
	<tr>
      <td>會員帳號:</td>
      <td><input type="TEXT" name="account" value="<%= (mbrVO==null)? "" : mbrVO.getAccount()%>" size="45"/></td>
	</tr>
	<tr>
      <td>使用者密碼:</td>
      <td><input type="PASSWORD" name="password" value="<%= (mbrVO==null)? "" : mbrVO.getPassword()%>" size="45"/></td>
	</tr>
	<tr>
      <td>會員信箱:</td>
      <td><input type="TEXT" name="email" value="<%= (mbrVO==null)? "" : mbrVO.getEmail()%>" size="45"/></td>
	</tr>
	<tr>
      <td>會員性別:</td>
      <td>
          <select name="gender">
              <option value="1" <%= (mbrVO!=null && mbrVO.getGender() == 1) ? "selected" : "" %>>男</option>
              <option value="0" <%= (mbrVO!=null && mbrVO.getGender() == 0) ? "selected" : "" %>>女</option>
          </select>
      </td>
	</tr>
	<tr>
      <td>會員電話:</td>
      <td><input type="TEXT" name="mobileno" value="<%= (mbrVO==null)? "" : mbrVO.getMobileno()%>" size="45"/></td>
	</tr>
	<tr>
	 <td>會員頭像:</td>
	 <td><input type="file" name="sticker" accept="image/*" /></td>
	</tr>
	
	</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

</body>

<% 
  java.sql.Date regisdate = null;
  try {
	  regisdate = mbrVO.getRegisdate();
   } catch (Exception e) {
	   regisdate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              
	       timepicker:false,       
	       step: 1,                
	       format:'Y-m-d',         
		   value: '<%=regisdate%>', 
        });
</script>
</html>
