<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.entidades.Noticia" %>
<%
    List<Noticia> noticias = (List<Noticia>) request.getAttribute("noticias");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Noticias Institucionales</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .contenido {
            padding: 40px 30px;
        }
        .noticia {
            border: 1px solid #ccc;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 8px;
        }
        .noticia img {
            max-width: 300px;
            height: auto;
            display: block;
            margin-bottom: 10px;
            border-radius: 6px;
        }
        .noticia h2 {
            margin-top: 0;
            color: #002147;
        }
        .leer-mas {
            display: inline-block;
            margin-top: 10px;
            padding: 6px 12px;
            background-color: #004080;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .leer-mas:hover {
            background-color: #0066cc;
        }
    </style>
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <div class="contenido">
        <h1>Noticias Institucionales</h1>

        <% if (noticias != null && !noticias.isEmpty()) {
            for (Noticia n : noticias) { %>
                <div class="noticia">
                    <% if (n.getImagen() != null && !n.getImagen().isEmpty()) { %>
                        <img src="<%= request.getContextPath() %>/resources/img/<%= n.getImagen() %>" alt="Imagen de noticia">
                    <% } %>
                    <h2><%= n.getTitulo() %></h2>
                    <p><strong>Autor:</strong> <%= n.getAutor() %></p>
                    <% if (n.getFecha() != null && !n.getFecha().isEmpty()) { %>
                        <p><strong>Fecha:</strong> <%= n.getFecha() %></p>
                    <% } %>
                    <p><%= n.getContenido().length() > 200 ? n.getContenido().substring(0, 200) + "..." : n.getContenido() %></p>
                    <a class="leer-mas" href="<%= request.getContextPath() %>/NoticiaServlet?accion=detalle&id=<%= n.getId() %>">Leer más</a>
                </div>
        <%  }
        } else { %>
            <p>No hay noticias disponibles.</p>
        <% } %>
    </div>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>