<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.entidades.Documento" %>

<%
    Documento doc = (Documento) request.getAttribute("documento");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Documento</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/estilos.css">
    <style>
        body {
            background-color: #f4f6f9;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 40px auto;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }
        label {
            font-weight: 600;
            margin-top: 10px;
            display: block;
        }
        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        button, a.btn-cancel {
            display: inline-block;
            padding: 10px 20px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
        }
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            margin-right: 10px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        a.btn-cancel {
            background-color: #6c757d;
            color: #fff;
        }
        a.btn-cancel:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <%@ include file="/includes/header.jsp" %>

    <div class="container">
        <h2>Editar Documento</h2>

        <form action="<%=request.getContextPath()%>/DocumentoServlet" method="post">
            <input type="hidden" name="accion" value="actualizar">
            <input type="hidden" name="id" value="<%= doc.getId() %>">

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= doc.getNombre() %>" required>

            <label for="tipoId">Tipo de documento (ID):</label>
            <input type="number" id="tipoId" name="tipoId" value="<%= doc.getTipoId() %>" required>

            <label for="version">Versión:</label>
            <input type="text" id="version" name="version" value="<%= doc.getVersion() %>" required>

            <button type="submit">Guardar cambios</button>
            <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=listar" class="btn-cancel">Cancelar</a>
        </form>
    </div>

    <%@ include file="/includes/footer.jsp" %>
</body>
</html>

