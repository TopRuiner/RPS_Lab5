<%--
  Created by IntelliJ IDEA.
  User: Egor
  Date: 23.11.2022
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Main</h3>

    <h3>Navigation</h3>
<p>
    <a href="vListPatients">
        List of patients
    </a>
</p>
<p>
    <a href="patient">
        Create patient
    </a>
</p>


<p>
<h2>Delete</h2>
    <form action="mainDelete" method="post">
      <p>Id<input type="text" name="id"/>
      <p><input type="submit" value="Submit"/>
    </form>
</p>
<p>
<h2>View</h2>
<form action="mainView" method="post">
    <p>Id<input type="text" name="id"/>
    <p><input type="submit" value="Submit"/>
</form>
</p>
</body>
</html>
