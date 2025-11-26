# Noticias Institucionales

Aplicación web desarrollada en Java con JSP y Servlets para gestionar y mostrar noticias institucionales. Incluye un panel de administración para CRUD de noticias y un portal público para visualizarlas.  
Ahora también incorpora un **módulo de gestión de documentos PDF institucionales**, con control de versiones y vistas públicas/administrativas.  
Además, se añadió un **módulo de organigrama institucional y directorio de integrantes**, para visualizar la estructura jerárquica y consultar el listado de personas.

## Tecnologías utilizadas

- Java EE (Servlets, JSP)
- Apache Tomcat
- MySQL
- JDBC
- HTML5, CSS3
- NetBeans IDE
- Git + GitHub
- Commons FileUpload + Commons IO 

## Estructura del proyecto
- NoticiasInstitucionales/ 
├── Web Pages/  
│   ├── Noticias/ 
│   │   ├── listar.jsp 
│   │   └── detalle.jsp 
│   ├── Documentos/  
│   │   ├── VistaDocumentos.jsp  
│   ├── Integrantes/  
│   │   ├── listar.jsp  
│   │   └── organigrama.jsp  
│   ├── admin/ 
│   │   ├── login.jsp, crear.jsp, editar.jsp, admin.jsp  
│   │   ├── documentos.jsp, nuevo.jsp, editar.jsp, versionar.jsp  
│   ├── includes/ 
│   │   ├── header.jsp, footer.jsp 
│   ├── resources/ 
│   │   ├── css/, img/
│   ├── index.jsp 
├── Source Packages/ 
│   ├── controlador/ 
│   │   ├── AdminServlet.java, LoginServlet.java, DocumentoServlet.java, AdminAuthFilter.java  
│   │   ├── IntegranteServlet.java  
│   ├── modelo.dao/ 
│   │   ├── DocumentoDAO.java  
│   │   ├── IntegranteDAO.java, CargoDAO.java  
│   ├── modelo.entidades/ 
│   │   ├── Documento.java  
│   │   ├── Integrante.java, Cargo.java  
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
- CRUD de noticias (crear, editar, eliminar, listar)
- Subida de imágenes con validación
- Filtro por categoría y fecha
- Portal público con vista de detalle
- Mensajes de éxito y error
- Confirmación al eliminar noticias
- Diseño modular con includes para header/footer
- **Módulo de documentos PDF institucionales**:
  - Subir documentos PDF desde el panel administrativo
  - Editar metadatos de documentos
  - Versionar documentos (mantener historial de versiones)
  - Eliminar documentos
  - Vista pública de documentos institucionales
  - Filtro de acceso mediante `AdminAuthFilter`
- **Módulo de organigrama institucional y directorio**:
  - Visualización del organigrama institucional en HTML+CSS
  - Jerarquía estática de cargos y nombres
  - Directorio dinámico de integrantes con listado
  - Navegación entre organigrama y directorio

## ️ Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/Proyecto-final.git


2. Abre NetBeans y ve a File > Open Project, selecciona la carpeta clonada

3. Configura la base de datos MySQL:
    Crea la base de datos:

CREATE DATABASE noticiasdb;
USE noticiasdb;

CREATE TABLE categoria (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

CREATE TABLE noticia (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(200) NOT NULL,
  contenido TEXT NOT NULL,
  fecha DATE NOT NULL,
  imagen VARCHAR(255) NOT NULL,
  categoria_id INT,
  autor VARCHAR(100) NOT NULL,
  FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE usuario (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  usuario VARCHAR(50),
  clave VARCHAR(50)
);

CREATE TABLE documento (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  version INT NOT NULL DEFAULT 1,
  fecha DATE NOT NULL,
  ruta VARCHAR(255) NOT NULL
);

CREATE TABLE integrante (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  cargo VARCHAR(100) NOT NULL,
  correo VARCHAR(100),
  telefono VARCHAR(50)
);

4. Ajusta la conexión en Conexion.java

5.Verifica que las siguientes librerías estén agregadas:

mysql-connector-j-9.4.0.jar

commons-fileupload-1.4.jar

commons-io-2.11.0.jar

6. Haz clic derecho en el proyecto y selecciona Clean and Build

7. Ejecuta el proyecto en Apache Tomcat (Run)

Licencia
Uso académico y educativo


## Video

[![Ver en YouTube](https://img.youtube.com/vi/cpEbc_NDXoQ/0.jpg)](https://youtu.be/cpEbc_NDXoQ)


