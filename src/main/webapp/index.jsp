<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Redirigiendo...</title>
</head>
<body>
Redirigiendo. Por favor, espere...
<%
    response.sendRedirect("ListarServlet");
%>
</body>
</html>