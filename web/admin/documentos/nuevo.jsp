<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Subir nuevo documento PDF</title>
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
        input[type="number"],
        input[type="file"] {
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
        <h2> Subir nuevo documento PDF</h2>

        <form action="<%=request.getContextPath()%>/DocumentoServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="accion" value="crear">

            <label for="nombre">Nombre del documento:</label>
            <input type="text" name="nombre" id="nombre" required>

            <label for="tipoId">Tipo de documento (ID):</label>
            <input type="number" name="tipoId" id="tipoId" required>

            <label for="version">Versión:</label>
            <input type="text" name="version" id="version" placeholder="Ejemplo: v1.0" required>

            <label for="archivo">Archivo PDF:</label>
            <input type="file" name="archivo" id="archivo" accept="application/pdf" required>

            <button type="submit"> Subir documento</button>
            <a href="<%=request.getContextPath()%>/DocumentoServlet?accion=listar" class="btn-cancel">Cancelar</a>
        </form>
    </div>

    <%@ include file="/includes/footer.jsp" %>
</body>
</html>
