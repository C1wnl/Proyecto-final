package controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.NoticiaDAO;
import modelo.entidades.Noticia;

@WebServlet(name = "NoticiaServlet", urlPatterns = {"/NoticiaServlet"})
@MultipartConfig
public class NoticiaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        NoticiaDAO dao = new NoticiaDAO();

        if ("listar".equals(accion)) {
            List<Noticia> noticias = dao.listar();
            request.setAttribute("noticias", noticias);
            request.getRequestDispatcher("Noticias/listar.jsp").forward(request, response);
            return;
        }

        if ("detalle".equals(accion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Noticia noticia = dao.obtenerPorId(id);
            request.setAttribute("noticia", noticia);
            request.getRequestDispatcher("Noticias/detalle.jsp").forward(request, response);
            return;
        }

        // Por defecto redirige a index
        List<Noticia> noticias = dao.listar();
        request.setAttribute("noticias", noticias);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String contenido = request.getParameter("contenido");
        String fecha = request.getParameter("fecha");
        String categoriaId = request.getParameter("categoria");
        Part imagen = request.getPart("imagen");

        // Validaciones
        if (titulo == null || titulo.trim().isEmpty() ||
            autor == null || autor.trim().isEmpty() ||
            contenido == null || contenido.trim().isEmpty() ||
            categoriaId == null || categoriaId.trim().isEmpty() ||
            imagen == null || imagen.getSize() == 0) {

            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("Noticias/crear.jsp").forward(request, response);
            return;
        }

        String tipo = imagen.getContentType();
        if (!tipo.equals("image/jpeg") && !tipo.equals("image/png") && !tipo.equals("image/webp")) {
            request.setAttribute("error", "Solo se permiten imágenes JPG, PNG o WEBP.");
            request.getRequestDispatcher("Noticias/crear.jsp").forward(request, response);
            return;
        }

        // Guardar imagen evitando sobrescribir
        String nombreArchivo = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
        String ruta = getServletContext().getRealPath("/resources/img");
        File carpeta = new File(ruta);
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

        imagen.write(destino.getAbsolutePath());

        // Crear noticia
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setAutor(autor);
        noticia.setContenido(contenido);
        noticia.setImagen(nombreArchivo);
        noticia.setFecha(fecha);
        noticia.setCategoriaId(Integer.parseInt(categoriaId));

        NoticiaDAO dao = new NoticiaDAO();
        dao.insertar(noticia);

        response.sendRedirect("NoticiaServlet?accion=listar");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar noticias";
    }
}