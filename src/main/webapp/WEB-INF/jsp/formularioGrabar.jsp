<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  </head>
  <body>
    <h2>Introduzca los datos del nuevo modelo:</h2>
    <form method="post" action="GrabarServlet">

      Nombre <input type="text" name="nombre"/></br>
      Estatura <input type="text" name="estatura"/></br>
      Edad <input type="text" name="edad"/></br>
      Localidad <input type="text" name="localidad"/></br>
      <input type="submit" value="Aceptar">
    </form>

  <%
    // Si hay error
    String error = (String) request.getAttribute("error");

    if (error != null) {
      %>
      <div style="color: red"><%=error%></div>
    <%
      }
  %>

  </body>
</html>