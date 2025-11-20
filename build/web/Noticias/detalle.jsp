<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.entidades.Noticia" %>
<%
    Noticia noticia = (Noticia) request.getAttribute("noticia");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= noticia.getTitulo() %></title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .contenedor {
            max-width: 800px;
            margin: 40px auto;
            padding: 0 20px;
        }
        img {
            max-width: 100%;
            margin-bottom: 20px;
            border-radius: 6px;
        }
        h1 {
            color: #002147;
        }
        .meta {
            color: #666;
            font-size: 14px;
            margin-bottom: 15px;
        }
        .volver {
            display: inline-block;
            margin-top: 20px;
            padding: 6px 12px;
            background-color: #004080;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .volver:hover {
            background-color: #0066cc;
        }
    </style>
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <div class="contenedor">
        <h1><%= noticia.getTitulo() %></h1>
        <div class="meta">
            <strong>Autor:</strong> <%= noticia.getAutor() %>
            <% if (noticia.getFecha() != null && !noticia.getFecha().isEmpty()) { %>
                | <strong>Fecha:</strong> <%= noticia.getFecha() %>
            <% } %>
            <% if (noticia.getCategoriaNombre() != null && !noticia.getCategoriaNombre().isEmpty()) { %>
                | <strong>Categoría:</strong> <%= noticia.getCategoriaNombre() %>
            <% } %>
        </div>

        <% if (noticia.getImagen() != null && !noticia.getImagen().isEmpty()) { %>
            <img src="<%= request.getContextPath() %>/resources/img/<%= noticia.getImagen() %>" alt="Imagen de noticia">
        <% } %>

        <p><%= noticia.getContenido() %></p>

        <a class="volver" href="<%= request.getContextPath() %>/NoticiaServlet?accion=listar">← Volver a noticias</a>
    </div>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>