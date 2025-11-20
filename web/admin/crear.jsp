<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Nueva Noticia</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../resources/css/estilos.css">
</head>
<body>
    <h1>Crear Nueva Noticia</h1>

    <form action="../AdminServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="accion" value="crear">

        <label>Título:</label><br>
        <input type="text" name="titulo" required><br><br>

        <label>Contenido:</label><br>
        <textarea name="contenido" rows="5" cols="40" required></textarea><br><br>

        <label>Categoría:</label><br>
        <select name="categoria" required>
            <option value="">-- Selecciona una categoría --</option>
            <option value="1">Académica</option>
            <option value="2">Eventos</option>
            <option value="3">Administrativa</option>
        </select><br><br>

        <label>Autor:</label><br>
        <input type="text" name="autor" required><br><br>

        <label>Imagen:</label><br>
        <input type="file" name="imagen" accept=".jpg,.jpeg,.png" required><br><br>

        <input type="submit" value="Guardar Noticia">
    </form>

    <br>
    <a href="admin.jsp">← Volver al panel</a>
</body>
</html>