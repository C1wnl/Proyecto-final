<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="modelo.entidades.Documento" %>
<%@ page import="modelo.dao.DocumentoDAO" %>

<%
    DocumentoDAO dao = new DocumentoDAO();
    List<Documento> documentos = dao.listarTodos();

    // Usamos la variable implícita "session"
    boolean esAdmin = (session != null && session.getAttribute("usuario_admin") != null);
    int columnas = esAdmin ? 6 : 5;

    // Formateador de fecha
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Documentos Institucionales</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/estilos.css">
</head>
<body>

    <%@ include file="/includes/header.jsp" %>

    <div style="padding: 20px;">
        <h2>Documentos Institucionales</h2>

        <% if (esAdmin) { %>
            <a href="<%=request.getContextPath()%>/admin/documentos/nuevo.jsp"
               style="display:inline-block; margin-bottom:15px; padding:10px 15px; background-color:#004080; color:white; text-decoration:none; border-radius:4px;">
               Subir nuevo documento PDF
            </a>
        <% } %>

        <table border="1" cellpadding="8" cellspacing="0" style="width:100%; border-collapse:collapse;">
            <thead style="background-color:#002147; color:white;">
                <tr>
                    <th>Nombre</th>
                    <th>Tipo</th>
                    <th>Versión</th>
                    <th>Fecha</th>
                    <th>Archivo</th>
                    <% if (esAdmin) { %><th>Acciones</th><% } %>
                </tr>
            </thead>
            <tbody>
            <%
                if (documentos != null && !documentos.isEmpty()) {
                    for (Documento d : documentos) {
                        String version = (d.getVersion() != null && !d.getVersion().isEmpty()) ? d.getVersion() : "-";

                        Date fechaSubida = d.getFechaSubida();
                        String fecha = (fechaSubida != null) ? sdf.format(fechaSubida) : "-";

                        String ruta = d.getRuta();
                        String enlace = (ruta != null && !ruta.isEmpty())
                            ? (request.getContextPath() + "/" + ruta)
                            : "#";
            %>
                <tr>
                    <td><%= d.getNombre() %></td>
                    <td><%= d.getTipoId() %></td>
                    <td><%= version %></td>
                    <td><%= fecha %></td>
                    <td>
                        <% if (ruta != null && !ruta.isEmpty()) { %>
                            <a href="<%= enlace %>" target="_blank">Ver PDF</a>
                        <% } else { %>
                            -
                        <% } %>
                    </td>

                    <% if (esAdmin) { %>
                    <td>
                        <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=editar&id=<%= d.getId() %>">Editar</a> |
                        <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=versionar&id=<%= d.getId() %>">Versionar</a> |
                        <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=eliminar&id=<%= d.getId() %>">Eliminar</a>
                    </td>
                    <% } %>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="<%= columnas %>" style="text-align:center;">No hay documentos disponibles.</td>
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
