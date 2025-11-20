<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.entidades.Noticia" %>
<%
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("admin") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        return;
    }

    List<Noticia> noticias = (List<Noticia>) request.getAttribute("noticias");
    String mensaje = (String) sesion.getAttribute("mensaje");
    if (mensaje != null) {
%>
    <div style="background-color: #e0ffe0; padding: 10px; border: 1px solid green; margin: 20px auto; max-width: 800px; border-radius: 6px;">
        <strong><%= mensaje %></strong>
    </div>
<%
        sesion.removeAttribute("mensaje");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Nueva Noticia</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .contenedor {
            max-width: 900px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1, h2 {
            color: #002147;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input[type="text"],
        textarea,
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="file"] {
            margin-top: 10px;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #004080;
            color: white;
            border: none;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0066cc;
        }
        table {
            width: 100%;
            margin-top: 40px;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: left;
        }
        table th {
            background-color: #002147;
            color: white;
        }
        .acciones a,
        .acciones button {
            margin-right: 10px;
            color: #0066cc;
            text-decoration: none;
            font-weight: bold;
            background: none;
            border: none;
            cursor: pointer;
        }
        .acciones button:hover,
        .acciones a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <div class="contenedor">
        <h1>Crear Nueva Noticia</h1>

        <form action="<%= request.getContextPath() %>/AdminServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="accion" value="crear">

            <label for="titulo">Título:</label>
            <input type="text" name="titulo" id="titulo" required>

            <label for="contenido">Contenido:</label>
            <textarea name="contenido" id="contenido" rows="5" required></textarea>

            <label for="categoria">Categoría:</label>
            <select name="categoria" id="categoria">
                <option value="1">Académica</option>
                <option value="2">Eventos</option>
                <option value="3">Administrativa</option>
            </select>

            <label for="autor">Autor:</label>
            <input type="text" name="autor" id="autor" required>

            <label for="imagen">Imagen:</label>
            <input type="file" name="imagen" id="imagen">

            <input type="submit" value="Guardar Noticia">
        </form>

        <h2>Noticias existentes</h2>
        <table>
            <tr>
                <th>Título</th>
                <th>Autor</th>
                <th>Acciones</th>
            </tr>
            <% if (noticias != null && !noticias.isEmpty()) {
                for (Noticia n : noticias) { %>
                    <tr>
                        <td><%= n.getTitulo() %></td>
                        <td><%= n.getAutor() %></td>
                        <td class="acciones">
                            <a href="<%= request.getContextPath() %>/admin/editar.jsp?id=<%= n.getId() %>">Editar</a>
                            <form action="<%= request.getContextPath() %>/AdminServlet" method="post" style="display:inline;" onsubmit="return confirm('¿Estás seguro de eliminar esta noticia?');">
                                <input type="hidden" name="accion" value="eliminar">
                                <input type="hidden" name="id" value="<%= n.getId() %>">
                                <button type="submit">Eliminar</button>
                            </form>
                        </td>
                    </tr>
            <%  }
            } else { %>
                <tr><td colspan="3">No hay noticias registradas.</td></tr>
            <% } %>
        </table>
    </div>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>