<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header style="background-color:#002147; color:white; padding:15px 30px; display:flex; justify-content:space-between; align-items:center;">
    <h2 style="margin:0;">Noticias Institucionales</h2>
    <nav>
        <a href="<%= request.getContextPath() %>/index.jsp"
           style="color:white; margin-left:20px; text-decoration:none;">Inicio</a>

        <a href="<%= request.getContextPath() %>/NoticiaServlet?accion=listar"
           style="color:white; margin-left:20px; text-decoration:none;">Noticias</a>

        <a href="<%= request.getContextPath() %>/documentos_publicos/VistaDocumentos.jsp"
           style="color:white; margin-left:20px; text-decoration:none;">Documentos</a>

        <a href="<%= request.getContextPath() %>/admin/login.jsp"
           style="color:white; margin-left:20px; text-decoration:none;">Acceso</a>

        <%-- AQUÍ YA NO SE REVISA esAdmin --%>
        <% if (request.getSession(false) != null 
               && request.getSession(false).getAttribute("usuario_admin") != null) { %>
            <a href="<%= request.getContextPath() %>/LogoutServlet"
               style="color:#ffdd57; margin-left:20px; text-decoration:none;">Cerrar sesión</a>
        <% } %>
    </nav>
</header>
