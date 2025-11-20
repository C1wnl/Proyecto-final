package controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.dao.NoticiaDAO;
import modelo.entidades.Noticia;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            return;
        }

        String accion = request.getParameter("accion");
        NoticiaDAO dao = new NoticiaDAO();

        if ("eliminar".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.eliminar(id);
            sesion.setAttribute("mensaje", "Noticia eliminada correctamente");
            response.sendRedirect(request.getContextPath() + "/AdminServlet?accion=panel");
            return;
        }

        // Mostrar el panel con noticias
        if ("panel".equals(accion) || accion == null) {
            List<Noticia> noticias = dao.listar();
            request.setAttribute("noticias", noticias);
            request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            return;
        }

        if (!ServletFileUpload.isMultipartContent(request)) {
            String accion = request.getParameter("accion");
            int id = Integer.parseInt(request.getParameter("id"));

            if ("eliminar".equals(accion)) {
                NoticiaDAO dao = new NoticiaDAO();
                dao.eliminar(id);
                sesion.setAttribute("mensaje", "Noticia eliminada correctamente");
            }

            response.sendRedirect(request.getContextPath() + "/AdminServlet?accion=panel");
            return;
        }

        String accion = "", titulo = "", contenido = "", autor = "", imagen = "";
        int categoriaId = 0, idNoticia = 0;

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "accion": accion = item.getString("UTF-8"); break;
                        case "id": idNoticia = Integer.parseInt(item.getString()); break;
                        case "titulo": titulo = item.getString("UTF-8"); break;
                        case "contenido": contenido = item.getString("UTF-8"); break;
                        case "categoria": categoriaId = Integer.parseInt(item.getString()); break;
                        case "autor": autor = item.getString("UTF-8"); break;
                    }
                } else if ("imagen".equals(item.getFieldName())) {
                    String nombreArchivo = new File(item.getName()).getName();
                    if (nombreArchivo != null && !nombreArchivo.trim().isEmpty()) {
                        File carpeta = new File(getServletContext().getRealPath("/resources/img/"));
                        if (!carpeta.exists()) {
                            carpeta.mkdirs();
                        }

                        File destino = new File(carpeta, nombreArchivo);
                        if (destino.exists()) {
                            String extension = "";
                            int punto = nombreArchivo.lastIndexOf(".");
                            if (punto > 0) {
                                extension = nombreArchivo.substring(punto);
                                nombreArchivo = nombreArchivo.substring(0, punto);
                            }
                            nombreArchivo = nombreArchivo + "_" + System.currentTimeMillis() + extension;
                            destino = new File(carpeta, nombreArchivo);
                        }

                        item.write(destino);
                        imagen = nombreArchivo;
                    }
                }
            }

            NoticiaDAO dao = new NoticiaDAO();

            if ("crear".equals(accion)) {
                Noticia noticia = new Noticia();
                noticia.setTitulo(titulo);
                noticia.setContenido(contenido);
                noticia.setCategoriaId(categoriaId);
                noticia.setAutor(autor);
                noticia.setImagen(imagen);
                dao.insertar(noticia);
                sesion.setAttribute("mensaje", "Noticia creada correctamente");
            }

            if ("actualizar".equals(accion)) {
                if (imagen == null || imagen.trim().isEmpty()) {
                    imagen = dao.obtenerPorId(idNoticia).getImagen();
                }

                Noticia noticia = new Noticia();
                noticia.setId(idNoticia);
                noticia.setTitulo(titulo);
                noticia.setContenido(contenido);
                noticia.setCategoriaId(categoriaId);
                noticia.setAutor(autor);
                noticia.setImagen(imagen);
                dao.actualizar(noticia);
                sesion.setAttribute("mensaje", "Noticia actualizada correctamente");
            }

        } catch (Exception e) {
            sesion.setAttribute("mensaje", "Error al procesar la noticia");
            System.err.println("Error al procesar la noticia: " + e.getMessage());
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath() + "/AdminServlet?accion=panel");
    }
}