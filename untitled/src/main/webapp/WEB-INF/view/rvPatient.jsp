<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
   <head></head>
   <body>
      <h1>Read patient</h1>
      Id: ${patient.id} <br/>
      Name: ${patient.name} <br/>
      SnilsNumber: ${patient.snilsNum} <br/>
      PolisId: ${patient.polisId} <br/>
      <p>
         <a href="main">
            Main page
         </a>
      </p>

   </body>

</html>