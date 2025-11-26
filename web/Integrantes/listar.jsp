<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.entidades.Integrante" %>
<jsp:include page="/includes/header.jsp" />

<div class="container mt-4">
    <h2 class="text-center mb-4">Directorio de Integrantes</h2>

    <div class="row">
        <%
            List<Integrante> integrantes = (List<Integrante>) request.getAttribute("integrantes");
            if (integrantes != null && !integrantes.isEmpty()) {
                for (Integrante i : integrantes) {
        %>
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm h-100">
                <div class="card-body">
                    <h5 class="card-title"><%= i.getNombre() %></h5>
                    <p class="card-text">
                        <strong>Cargo:</strong> <%= i.getCargoNombre() %><br>
                        <strong>Correo:</strong> <%= i.getCorreo() != null ? i.getCorreo() : "No disponible" %><br>
                        <strong>Teléfono:</strong> <%= i.getTelefono() != null ? i.getTelefono() : "No disponible" %><br>
                        <strong>Jefe directo:</strong> <%= i.getJefeNombre() != null ? i.getJefeNombre() : "Sin asignar" %>
                    </p>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <div class="col-12">
            <div class="alert alert-info text-center">No hay integrantes registrados.</div>
        </div>
        <%
            }
        %>
    </div>
</div>

<jsp:include page="/includes/footer.jsp" />

