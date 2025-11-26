<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/includes/header.jsp" />

<style>
.org-container {
    padding: 2em;
    display: flex;
    justify-content: center;
    flex-direction: column;
    align-items: center;
}

.org-container ul {
    padding-top: 20px;
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.org-container li {
    list-style-type: none;
    text-align: center;
    position: relative;
    padding: 20px 5px 0 5px;
}

.org-container .box {
    padding: 12px 20px;
    background-color: #005baa;
    color: white;
    border-radius: 6px;
    display: inline-block;
    font-size: 14px;
    line-height: 1.4;
    box-shadow: 0 2px 6px rgba(0,0,0,0.2);
    max-width: 300px;
}

.org-container .box strong {
    display: block;
    margin-top: 4px;
    font-weight: normal;
    font-size: 13px;
    color: #f8f9fa;
}
</style>

<div class="container" style="max-width: 1200px; margin: 20px auto;">
    <h2 style="margin-bottom: 16px;">Organigrama Institucional</h2>

    <!-- Organigrama estático en HTML -->
    <div class="org-container">
      <ul>
        <li>
          <div class="box">Director de la división<br><strong>Irene Gómez</strong></div>
          <ul>
            <li><div class="box">Secretaria de división<br><strong>Daniela Ortiz Ríos</strong></div></li>
            <li>
              <div class="box">Jefes de departamento</div>
              <ul>
                <li><div class="box">Estudios del agua y energías<br><strong>José Antonio Rubí</strong></div></li>
                <li><div class="box">Ciencias de información y desarrollo tecnológico<br><strong>María Guadalupe Montelongo</strong></div></li>
                <li><div class="box">Ciencias básicas y aplicadas<br><strong>Víctor Hugo Arellano</strong></div></li>
              </ul>
            </li>
            <li><div class="box">Presidentes de academia</div></li>
            <li><div class="box">Secretarios de academia</div></li>
          </ul>
        </li>
      </ul>
    </div>

    <!-- Botón para ir al directorio -->
    <div style="margin-top: 16px; text-align:right;">
        <a href="<%=request.getContextPath()%>/IntegranteServlet?accion=listar" 
           style="padding:8px 12px; border:1px solid #0d6efd; border-radius:6px; color:#0d6efd; text-decoration:none;">
           Ver directorio
        </a>
    </div>
</div>

<jsp:include page="/includes/footer.jsp" />
