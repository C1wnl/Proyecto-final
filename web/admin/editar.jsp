<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.dao.NoticiaDAO" %>
<%@ page import="modelo.entidades.Noticia" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    NoticiaDAO dao = new NoticiaDAO();
    Noticia noticia = dao.obtenerPorId(id);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Noticia</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .contenedor {
            max-width: 800px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #002147;
            margin-bottom: 25px;
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
        .volver {
            display: inline-block;
            margin-top: 30px;
            color: #004080;
            text-decoration: none;
            font-weight: bold;
        }
        .volver:hover {
            text-decoration: underline;
        }
        img {
            margin-top: 10px;
            border-radius: 6px;
        }
    </style>
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <div class="contenedor">
        <h1>Editar Noticia</h1>

        <form action="<%= request.getContextPath() %>/AdminServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="id" value="<%= noticia.getId() %>">

            <label for="titulo">Título:</label>
            <input type="text" name="titulo" id="titulo" value="<%= noticia.getTitulo() %>" required>

            <label for="contenido">Contenido:</label>
            <textarea name="contenido" id="contenido" rows="5" required><%= noticia.getContenido() %></textarea>

            <label for="categoria">Categoría:</label>
            <select name="categoria" id="categoria" required>
                <option value="1" <%= noticia.getCategoriaId() == 1 ? "selected" : "" %>>Académica</option>
                <option value="2" <%= noticia.getCategoriaId() == 2 ? "selected" : "" %>>Eventos</option>
                <option value="3" <%= noticia.getCategoriaId() == 3 ? "selected" : "" %>>Administrativa</option>
            </select>

            <label for="autor">Autor:</label>
            <input type="text" name="autor" id="autor" value="<%= noticia.getAutor() %>" required>

            <label>Imagen actual:</label>
            <% if (noticia.getImagen() != null && !noticia.getImagen().isEmpty()) { %>
                <img src="<%= request.getContextPath() %>/resources/img/<%= noticia.getImagen() %>" width="200" alt="Imagen actual">
            <% } else { %>
                <em>No hay imagen</em>
            <% } %>

            <label for="imagen">Subir nueva imagen (opcional):</label>
            <input type="file" name="imagen" id="imagen">

            <input type="submit" value="Actualizar Noticia">
        </form>
        <a class="volver" href="<%= request.getContextPath() %>/AdminServlet?accion=panel">← Volver al panel</a>
    </div>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>