<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Introduzca los datos a actualizar:</h2>

<form method="post" action="EditarServlet">

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
