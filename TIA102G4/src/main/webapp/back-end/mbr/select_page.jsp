<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Mbr: HOME</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
<tr><td><h3>IBM Mbr: HOME</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Mbr: Home</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMbr.jsp'>List</a> all Mbrs.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/mbr/mbr.do" >
        <b>��J�|���s��(�p1):</b>
        <input type="text" name="memberId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  <jsp:useBean id="mbrSvc" scope="page" class="com.mbr.model.MbrService" />
  
   <li>
     <FORM METHOD="post" ACTION="/mbr/mbr.do" >
       <b>��ܷ|���s��:</b>
       <select size="1" name="memberId">
         <c:forEach var="mbrVO" items="${mbrSvc.all}" > 
          <option value="${mbrVO.memberId}">${mbrVO.memberId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="/mbr/mbr.do">
      <b>��ܭ��u�m�W:</b>
      <select size="1" name="memberId">
        <c:forEach var="mbrVO" items="${mbrSvc.all}" > 
          <option value="${mbrVO.memberId}">${mbrVO.name}
         </c:forEach>
         </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>
         
  <h3>���u�޲z</h3>

<ul>
  <li><a href='addMbr.jsp'>Add</a> a new Mbr.</li>
</ul>
  
</body>
</html>  
  
  


  