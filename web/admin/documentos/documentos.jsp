<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.entidades.Documento" %>
<%@ page import="modelo.dao.DocumentoDAO" %>

<%
    // Ya no es necesario validar sesión aquí, el filtro lo hace
    DocumentoDAO dao = new DocumentoDAO();
    List<Documento> documentos = dao.listarTodos();

    // Mensajes de retroalimentación
    String success = request.getParameter("success");
    String updated = request.getParameter("updated");
    String deleted = request.getParameter("deleted");
    String versioned = request.getParameter("versioned");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Documentos</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/estilos.css">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 1000px;
            margin: 40px auto;
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .alert {
            padding: 10px;
            border-radius: 6px;
            margin-bottom: 15px;
            font-weight: 600;
        }
        .alert-success { background: #d4edda; color: #155724; }
        .alert-info { background: #cce5ff; color: #004085; }
        .alert-danger { background: #f8d7da; color: #721c24; }
        .alert-purple { background: #e2d6f9; color: #4b0082; }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        a.btn {
            display: inline-block;
            padding: 8px 15px;
            margin: 10px 0;
            background-color: #28a745;
            color: #fff;
            border-radius: 6px;
            text-decoration: none;
        }
        a.btn:hover {
            background-color: #218838;
        }
        .acciones a {
            margin: 0 5px;
            text-decoration: none;
            font-weight: 600;
        }
        .acciones a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%@ include file="/includes/header.jsp" %>

    <div class="container">
        <h2>Panel de Documentos</h2>

        <!-- Mensajes -->
        <% if (success != null) { %>
            <div class="alert alert-success">Documento creado correctamente.</div>
        <% } else if (updated != null) { %>
            <div class="alert alert-info">Documento actualizado correctamente.</div>
        <% } else if (deleted != null) { %>
            <div class="alert alert-danger">Documento eliminado correctamente.</div>
        <% } else if (versioned != null) { %>
            <div class="alert alert-purple">Nueva versión registrada correctamente.</div>
        <% } %>

        <a href="<%=request.getContextPath()%>/admin/documentos/nuevo.jsp" class="btn">Subir nuevo documento PDF</a>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Versión</th>
                    <th>Fecha</th>
                    <th>Ruta</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
            <%
                if (documentos != null && !documentos.isEmpty()) {
                    for (Documento d : documentos) {
            %>
                <tr>
                    <td><%= d.getId() %></td>
                    <td><%= d.getNombre() %></td>
                    <td><%= d.getTipoId() %></td>
                    <td><%= d.getVersion() %></td>
                    <td><%= d.getFechaSubida() %></td>
                    <td><a href="<%=request.getContextPath()%>/<%= d.getRuta() %>" target="_blank">Ver PDF</a></td>
                    <td class="acciones">
                        <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=editar&id=<%= d.getId() %>">Editar</a>
                        <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=versionar&id=<%= d.getId() %>">Versionar</a>
                        <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=eliminar&id=<%= d.getId() %>">Eliminar</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="7">No hay documentos registrados.</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <%@ include file="/includes/footer.jsp" %>
</body>
</html>


