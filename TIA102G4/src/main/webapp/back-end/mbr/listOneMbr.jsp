<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.mbr.model.*"%>

<%
  MbrVO mbrVO = (MbrVO) request.getAttribute("mbrVO");
%>

<html>
<head>
<title>�|����� - listOneMbr.jsp</title>

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
	width: 600px;
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

<table id="table-1">
	<tr><td>
		 <h3>�|����� - listOneMbr.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
	    <th>�|���s��</th>
	    <th>�|�����U���</th>
	    <th>�|���m�W</th>
	    <th>�|���b��</th>
	    <th>�|���K�X</th>
	    <th>�|���H�c</th>
	    <th>�|���ʧO</th>
	    <th>�|���q��</th>
	    <th>�|���Y��</th>
	 </tr>   
     <tr>
            <td><%=mbrVO.getMemberId()%></td>
	        <td><%=mbrVO.getRegisdate()%></td>
	        <td><%=mbrVO.getName()%></td>
	        <td><%=mbrVO.getAccount()%></td>
	        <td><%=mbrVO.getPassword()%></td>
	        <td><%=mbrVO.getEmail()%></td>
	        <td><%=mbrVO.getGender()%></td>
	        <td><%=mbrVO.getMobileno()%></td>
	        <td><%=mbrVO.getSticker()%></td>
</tr>
</table>


</body>
</html>