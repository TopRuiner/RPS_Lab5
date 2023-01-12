<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
    <body>
    <h3>Create patient form</h3>
        <form action="patient" method="post">
            <p>Name<input type="text" name="name"/>
            <p>Snils Number<input type="text" name="snilsNum"/>
            <p>Polis Id<input type="text" name="polisId"/>
            <p><input type="submit" value="Submit"/>
        </form>
    </body>
</html>