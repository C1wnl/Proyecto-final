<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header style="background-color:#002147; color:white; padding:15px 30px; display:flex; justify-content:space-between; align-items:center;">
    <h2 style="margin:0;">Noticias Institucionales</h2>
    <nav>
        <a href="<%= request.getContextPath() %>/index.jsp" style="color:white; margin-left:20px; text-decoration:none;">Inicio</a>
        <a href="<%= request.getContextPath() %>/NoticiaServlet?accion=listar" style="color:white; margin-left:20px; text-decoration:none;">Noticias</a>
        <a href="<%= request.getContextPath() %>/admin/login.jsp" style="color:white; margin-left:20px; text-decoration:none;">Login</a>
    </nav>
</header>