<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.dao.NoticiaDAO" %>
<%@ page import="modelo.entidades.Noticia" %>
<%
    String categoriaParam = request.getParameter("categoria");
    String fechaParam = request.getParameter("fecha");

    Integer categoria = (categoriaParam != null && !categoriaParam.isEmpty()) ? Integer.parseInt(categoriaParam) : null;
    String fecha = (fechaParam != null && !fechaParam.isEmpty()) ? fechaParam : null;

    NoticiaDAO dao = new NoticiaDAO();
    List<Noticia> noticias = dao.filtrar(categoria, fecha);
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
            padding: 30px;
        }
        form {
            margin-bottom: 30px;
        }
        label {
            margin-right: 10px;
            font-weight: bold;
        }
        select, input[type="month"], button {
            margin-right: 20px;
            padding: 5px 10px;
        }
        .noticia {
            border-bottom: 1px solid #ccc;
            padding: 20px 0;
            overflow: hidden;
        }
        .noticia img {
            max-width: 200px;
            float: left;
            margin-right: 20px;
            border-radius: 6px;
        }
        .noticia h2 {
            margin-top: 0;
            color: #002147;
        }
        .noticia p {
            margin: 5px 0;
        }
        .vermas {
            display: inline-block;
            margin-top: 10px;
            color: #0066cc;
            text-decoration: none;
        }
        .vermas:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <jsp:include page="/includes/header.jsp" />

    <div class="contenido">

        <!-- ✅ Filtros por categoría y fecha -->
        <form method="get" action="index.jsp">
            <label for="categoria">Categoría:</label>
            <select name="categoria" id="categoria">
                <option value="">Todas</option>
                <option value="1" <%= "1".equals(categoriaParam) ? "selected" : "" %>>Académica</option>
                <option value="2" <%= "2".equals(categoriaParam) ? "selected" : "" %>>Eventos</option>
                <option value="3" <%= "3".equals(categoriaParam) ? "selected" : "" %>>Administrativa</option>
            </select>

            <label for="fecha">Fecha (YYYY-MM):</label>
            <input type="month" name="fecha" id="fecha" value="<%= fechaParam != null ? fechaParam : "" %>">

            <button type="submit">Filtrar</button>
        </form>

        <% if (noticias != null && !noticias.isEmpty()) {
            for (Noticia n : noticias) { %>
                <div class="noticia">
                    <% if (n.getImagen() != null && !n.getImagen().isEmpty()) { %>
                        <img src="<%= request.getContextPath() %>/resources/img/<%= n.getImagen() %>" alt="Imagen de noticia">
                    <% } %>
                    <h2><%= n.getTitulo() %></h2>
                    <p><strong>Autor:</strong> <%= n.getAutor() %></p>
                    <p><strong>Fecha:</strong> <%= n.getFecha() %></p>
                    <p><%= n.getContenido().length() > 100 ? n.getContenido().substring(0, 100) + "..." : n.getContenido() %></p>
                    <a class="vermas" href="<%= request.getContextPath() %>/NoticiaServlet?accion=detalle&id=<%= n.getId() %>">Ver más</a>
                    <div style="clear: both;"></div>
                </div>
        <%  }
        } else { %>
            <p>No hay noticias disponibles.</p>
        <% } %>
    </div>

    <jsp:include page="/includes/footer.jsp" />

</body>
</html>