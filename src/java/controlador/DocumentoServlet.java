package controlador;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import modelo.entidades.Documento;
import modelo.dao.DocumentoDAO;

@WebServlet(name = "DocumentoServlet", urlPatterns = {"/DocumentoServlet"})
public class DocumentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        DocumentoDAO dao = new DocumentoDAO();

        switch (accion) {
            case "listar":
                request.getRequestDispatcher("/admin/documentos/documentos.jsp").forward(request, response);
                break;

            case "eliminar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Documento doc = dao.obtenerPorId(id);

                    if (doc != null) {
                        String rutaReal = getServletContext().getRealPath("/") + doc.getRuta();
                        File archivo = new File(rutaReal);
                        if (archivo.exists()) archivo.delete();

                        dao.eliminar(id);
                    }
                    response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&deleted=1");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=delete");
                }
                break;

            case "editar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Documento documento = dao.obtenerPorId(id);

                    if (documento != null) {
                        request.setAttribute("documento", documento);
                        request.getRequestDispatcher("/admin/documentos/editar.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=notfound");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=edit");
                }
                break;

            case "versionar":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Documento documento = dao.obtenerPorId(id);

                    if (documento != null) {
                        request.setAttribute("documento", documento);
                        request.getRequestDispatcher("/admin/documentos/versionar.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=notfound");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=versionar");
                }
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        // 🔹 Caso actualizar documento
        if ("actualizar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                int tipoId = Integer.parseInt(request.getParameter("tipoId"));
                String version = request.getParameter("version");

                DocumentoDAO dao = new DocumentoDAO();
                Documento doc = dao.obtenerPorId(id);

                if (doc != null) {
                    doc.setNombre(nombre);
                    doc.setTipoId(tipoId);
                    doc.setVersion(version);

                    dao.actualizar(doc);
                }

                response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&updated=1");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=update");
            }
            return;
        }

        // 🔹 Guardar nueva versión
        if ("guardarVersion".equals(accion)) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");

                List<FileItem> items = upload.parseRequest(request);

                String nombre = null;
                int tipoId = 0;
                String version = null;
                String rutaFinal = null;

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "nombre": nombre = item.getString("UTF-8"); break;
                            case "tipoId": tipoId = Integer.parseInt(item.getString("UTF-8")); break;
                            case "version": version = item.getString("UTF-8"); break;
                        }
                    } else {
                        if (!item.getContentType().equals("application/pdf")) {
                            response.sendRedirect(request.getContextPath() + "/admin/documentos/versionar.jsp?error=tipo");
                            return;
                        }

                        String uploadPath = getServletContext().getRealPath("/") + "uploads/docs/";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) uploadDir.mkdirs();

                        String fileName = System.currentTimeMillis() + "_" + item.getName();
                        File archivo = new File(uploadDir, fileName);
                        item.write(archivo);

                        rutaFinal = "uploads/docs/" + fileName;
                    }
                }

                Documento nuevaVersion = new Documento(nombre, tipoId, version, new Date(System.currentTimeMillis()), rutaFinal);
                DocumentoDAO dao = new DocumentoDAO();
                dao.versionar(nuevaVersion);

                response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&versioned=1");

            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&error=versionar");
            }
            return;
        }

        // 🔹 Crear documento
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        try {
            List<FileItem> items = upload.parseRequest(request);

            String nombre = null;
            int tipoId = 0;
            String version = null;
            String rutaFinal = null;

            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "nombre": nombre = item.getString("UTF-8"); break;
                        case "tipoId": tipoId = Integer.parseInt(item.getString("UTF-8")); break;
                        case "version": version = item.getString("UTF-8"); break;
                    }
                } else {
                    if (!item.getContentType().equals("application/pdf")) {
                        response.sendRedirect(request.getContextPath() + "/admin/documentos/nuevo.jsp?error=tipo");
                        return;
                    }

                    String uploadPath = getServletContext().getRealPath("/") + "uploads/docs/";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    String fileName = System.currentTimeMillis() + "_" + item.getName();
                    File archivo = new File(uploadDir, fileName);
                    item.write(archivo);

                    rutaFinal = "uploads/docs/" + fileName;
                }
            }

            Documento doc = new Documento(nombre, tipoId, version, new Date(System.currentTimeMillis()), rutaFinal);
            DocumentoDAO dao = new DocumentoDAO();
            dao.crear(doc);

            response.sendRedirect(request.getContextPath() + "/DocumentoServlet?accion=listar&success=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin/documentos/nuevo.jsp?error=upload");
        }
    }
}




