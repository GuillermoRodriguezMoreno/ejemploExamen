<%@ page import="org.iesvdm.ejemploexamen.models.Modelo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado</title>
</head>
<body>

    <h1>Listado:</h1>
    <form method="get" action="GrabarServlet">
    <input class="btn"  type="submit" value="Crear Nuevo">
    </form>

    <table>

        <tr><th>Código</th><th>Nombre</th><th>Estatura</th><th>Edad</th><th>Localidad</th></tr>

        <%
            // Obtengo listado
            List<Modelo> listado = (List<Modelo>) request.getAttribute("listado");

            for(Modelo modelo: listado) {
        %>

        <tr>

            <td>
                <%=modelo.getId()%>
            </td>
            <td>
                <%=modelo.getNombre() %>
            </td>
            <td>
                <%=modelo.getEdad() %>
            </td>
            <td>
                <%=modelo.getEstatura() %>
            </td>
            <td>
                <%= modelo.getLocalidad()%>
            </td>

            <td>
                <form method="post" action="BorrarServlet">
                    <input type="hidden" name="codigo" value="<%=modelo.getId() %>"/>
                    <input type="submit" value="Borrar">
                </form>
            </td>

            <td>
                <form method="get" action="EditarServlet">
                    <input type="hidden" name="codigo" value="<%=modelo.getId() %>"/>
                    <input type="submit" value="Editar">
                </form>
            </td>

        </tr>
        <%
            }
        %>
    </table>

</body>
</html>
