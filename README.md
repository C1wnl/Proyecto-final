# Noticias Institucionales

Aplicación web desarrollada en Java con JSP y Servlets para gestionar y mostrar noticias institucionales. Incluye un panel de administración para CRUD de noticias y un portal público para visualizarlas.

## Tecnologías utilizadas

- Java EE (Servlets, JSP)
- Apache Tomcat
- MySQL
- JDBC
- HTML5, CSS3
- NetBeans IDE
- Git + GitHub

## Estructura del proyecto
- NoticiasInstitucionales/ 
├── Web Pages/  
│   ├── Noticias/ 
│   │   ├── listar.jsp 
│   │   └── detalle.jsp 
│   ├── WEB-INF/
│   ├── META-INF/      
│   ├── admin/ 
│   │   │   ├── login.jsp, crear.jsp, editar.jsp, admin.jsp 
│   ├── includes/ 
│   │   │   ├── header.jsp, footer.jsp 
│   ├── resources/ 
│   │       ├── css/, img/
│   ├── index.jsp 
├── Source Packages/ 
│   
├── controlador/ 
│   │   ├── AdminServlet.java, LoginServlet.java, etc. 
│   ├── modelo.dao/ 
│   ├── modelo.entidades/ 
│   └── modelo.util/ 
├── Libraries/ 
│   ├── mysql-connector-j-9.4.0.jar 
│   ├── commons-fileupload-1.4.jar 
│   └── commons-io-2.11.0.jar 
├── Configuration Files/ 
│   ├── MANIFEST.MF 
│   ├── context.xml 
│   └── web-fragment.xml



## Funcionalidades

- Panel de administración con login
- Crear, editar, eliminar y listar noticias
- Subida de imágenes con validación
- Filtro por categoría y fecha
- Portal público con vista de detalle
- Mensajes de éxito y error
- Confirmación al eliminar noticias
- Diseño modular con includes para header/footer

## ️ Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/Proyecto-final.git
2. Abre NetBeans y ve a File > Open Project, selecciona la carpeta clonada
3. Configura la base de datos MySQL:
- Crea la base de datos:
    CREATE DATABASE noticiasdb;
- Crea las tablas necesarias (categoria, noticia, etc.)

4. Ajusta la conexión en Conexion.java:

5. Verifica que las siguientes librerías estén agregadas:
- mysql-connector-j-9.4.0.jar
- commons-fileupload-1.4.jar
- commons-io-2.11.0.jar

6. Haz clic derecho en el proyecto y selecciona Clean and Build
7. Ejecuta el proyecto en Apache Tomcat (Run)

## Licencia
Uso académico y educativo

