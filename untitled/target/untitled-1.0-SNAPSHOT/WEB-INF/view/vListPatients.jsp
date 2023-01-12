<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head></head>
   <body>
      <h1>List of patients</h1>
        <table width="50%" border="1">
        	<tr>
        		<th>Id</th>
        		<th>Name</th>
        		<th>Snils Number</th>
        		<th>Polis id</th>
        	</tr>
        	<c:forEach items="${patients}" var="patient" varStatus="status">
        		<tr>
        			<td>${patient.id}</td>
        			<td>${patient.name}</td>
        			<td>${patient.snilsNum}</td>
        			<td>${patient.polisId}</td>
        		</tr>
        	</c:forEach>
        </table>
        <button type="submit" width=100px height=200px>Submit</button>
        <p><a href="/untitled-1.0-SNAPSHOT">
                Main page
                </a>
   </body>
</html>