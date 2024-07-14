<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mbr.model.*" %>

<% 
    MbrVO mbrVO = (MbrVO) request.getAttribute("mbrVO");
    if (mbrVO == null) {
        mbrVO = new MbrVO(); // 這裡可以根據實際情況初始化
    }
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員修改資料 - update_mbr_input.jsp</title>

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
        <h3>會員資料修改 - update_mbr_input.jsp</h3>
        <h4><a href="select_page.jsp"><img src="images/black1.gif" width="100" height="32" border="0">回首頁</a></h4>
    </td></tr>
</table>

<h3>資料修改</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="/mbr/mbr.do" name="form1">
<table>
     <tr>
         <td>會員編號:<font color=red><b>*</b></font></td>
         <td><%=mbrVO.getMemberId() != null ? mbrVO.getMemberId() : "" %></td>
     </tr>
     <tr>
         <td>會員註冊日期:</td>
         <td><input name="regisdate" id="f_date1" type="text" value="<%=mbrVO.getRegisdate() != null ? mbrVO.getRegisdate() : "" %>"></td>
     </tr>
     <tr>
         <td>會員姓名:</td>
         <td><input type="TEXT" name="name" value="<%=mbrVO.getName() != null ? mbrVO.getName() : "" %>" size="45"/></td>
     </tr>
     <tr>
         <td>會員帳號:</td>
         <td><input type="TEXT" name="account" value="<%=mbrVO.getAccount() != null ? mbrVO.getAccount() : "" %>" size="45"/></td>
     </tr>
     <tr>    
         <td>會員密碼:</td>
         <td><input type="TEXT" name="password" value="<%=mbrVO.getPassword() != null ? mbrVO.getPassword() : "" %>" size="45"/></td>
     </tr>
     <tr> 
         <td>會員信箱:</td>
         <td><input type="TEXT" name="email" value="<%=mbrVO.getEmail() != null ? mbrVO.getEmail() : "" %>" size="45"/></td>
     </tr>
     <tr> 
         <td>會員性別:</td>
         <td>
             <select name="gender">
                 <option value="1" <%= (mbrVO != null && mbrVO.getGender() != null && mbrVO.getGender() == 1) ? "selected" : "" %>>男</option>
                 <option value="0" <%= (mbrVO != null && mbrVO.getGender() != null && mbrVO.getGender() == 0) ? "selected" : "" %>>女</option>
             </select>
         </td>
     </tr>
     <tr> 
         <td>會員頭像(暫無):</td>
         <td><input type="file" name="sticker" size="45"/></td>
     </tr>
</table>
<br>
<input type="hidden" name="action" value="update">  
<input type="hidden" name="memberId" value="<%=mbrVO.getMemberId() != null ? mbrVO.getMemberId() : "" %>">
<input type="submit" value="送出修改"></FORM>  
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
 		   value: '<%=mbrVO.getRegisdate() != null ? mbrVO.getRegisdate() : "" %>'
        });
</script>
</html>

