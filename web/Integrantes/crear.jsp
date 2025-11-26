<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.entidades.Cargo, modelo.entidades.Integrante" %>
<jsp:include page="/includes/header.jsp" />

<div class="container mt-4">
    <h2 class="text-center mb-4">Registrar nuevo integrante</h2>

    <form action="<%=request.getContextPath()%>/IntegranteServlet" method="post" class="row g-3">
        <input type="hidden" name="accion" value="crear">

        <div class="col-md-6">
            <label class="form-label">Nombre completo</label>
            <input type="text" name="nombre" class="form-control" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">Correo electrónico</label>
            <input type="email" name="correo" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Teléfono</label>
            <input type="text" name="telefono" class="form-control">
        </div>

        <div class="col-md-6">
            <label class="form-label">Cargo</label>
            <select name="cargo_id" class="form-select" required>
                <option value="">Seleccione un cargo</option>
                <%
                    List<Cargo> cargos = (List<Cargo>) request.getAttribute("cargos");
                    if (cargos != null) {
                        for (Cargo c : cargos) {
                %>
                <option value="<%=c.getId()%>"><%=c.getNombre()%></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="col-md-6">
            <label class="form-label">Jefe directo</label>
            <select name="jefe_id" class="form-select">
                <option value="">Sin jefe</option>
                <%
                    List<Integrante> jefes = (List<Integrante>) request.getAttribute("jefes");
                    if (jefes != null) {
                        for (Integrante j : jefes) {
                %>
                <option value="<%=j.getId()%>"><%=j.getNombre()%></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="col-12 text-center">
            <button type="submit" class="btn btn-primary">Guardar</button>
            <a href="<%=request.getContextPath()%>/IntegranteServlet?accion=listar" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

<jsp:include page="/includes/footer.jsp" />
