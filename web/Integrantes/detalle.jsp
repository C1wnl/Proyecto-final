<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.entidades.Integrante" %>
<jsp:include page="/includes/header.jsp" />

<div class="container mt-4">
    <h2 class="text-center mb-4">Detalle del Integrante</h2>

    <%
        Integrante integrante = (Integrante) request.getAttribute("integrante");
        if (integrante != null) {
    %>
    <div class="card shadow-sm">
        <div class="card-body">
            <h4 class="card-title"><%= integrante.getNombre() %></h4>
            <p class="card-text">
                <strong>Cargo:</strong> <%= integrante.getCargoNombre() %><br>
                <strong>Correo:</strong> <%= integrante.getCorreo() != null ? integrante.getCorreo() : "No disponible" %><br>
                <strong>Teléfono:</strong> <%= integrante.getTelefono() != null ? integrante.getTelefono() : "No disponible" %><br>
                <strong>Jefe directo:</strong> <%= integrante.getJefeNombre() != null ? integrante.getJefeNombre() : "Sin asignar" %>
            </p>
        </div>
    </div>

    <div class="mt-3 text-center">
        <a href="<%=request.getContextPath()%>/IntegranteServlet?accion=listar" class="btn btn-secondary">Volver al listado</a>
        <a href="<%=request.getContextPath()%>/IntegranteServlet?accion=editar&id=<%=integrante.getId()%>" class="btn btn-primary">Editar</a>
        <a href="<%=request.getContextPath()%>/IntegranteServlet?accion=eliminar&id=<%=integrante.getId()%>" 
           class="btn btn-danger" onclick="return confirm('¿Seguro que deseas eliminar este integrante?');">Eliminar</a>
    </div>
    <%
        } else {
    %>
    <div class="alert alert-warning text-center">No se encontró el integrante solicitado.</div>
    <%
        }
    %>
</div>

<jsp:include page="/includes/footer.jsp" />

