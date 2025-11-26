package controlador;

import modelo.dao.CargoDAO;
import modelo.dao.IntegranteDAO;
import modelo.entidades.Integrante;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IntegranteServlet", urlPatterns = {"/IntegranteServlet"})
public class IntegranteServlet extends HttpServlet {

    private IntegranteDAO integranteDAO = new IntegranteDAO();
    private CargoDAO cargoDAO = new CargoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        try {
            switch (accion) {
                case "listar":
                    List<Integrante> lista = integranteDAO.listar();
                    request.setAttribute("integrantes", lista);
                    request.getRequestDispatcher("/Integrantes/listar.jsp").forward(request, response);
                    break;

                case "organigrama":
                    // Traemos todos los integrantes para el organigrama
                    List<Integrante> listaOrg = integranteDAO.listar();
                    request.setAttribute("integrantes", listaOrg);
                    // El JSP usará nombre, cargo, jefe_id, correo y teléfono
                    request.getRequestDispatcher("/Integrantes/organigrama.jsp").forward(request, response);
                    break;

                case "detalle":
                    int idDetalle = Integer.parseInt(request.getParameter("id"));
                    Integrante detalle = integranteDAO.obtenerPorId(idDetalle);
                    request.setAttribute("integrante", detalle);
                    request.getRequestDispatcher("/Integrantes/detalle.jsp").forward(request, response);
                    break;

                case "crear":
                    request.setAttribute("cargos", cargoDAO.listar());
                    request.setAttribute("jefes", integranteDAO.listar());
                    request.getRequestDispatcher("/Integrantes/crear.jsp").forward(request, response);
                    break;

                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Integrante editar = integranteDAO.obtenerPorId(idEditar);
                    request.setAttribute("integrante", editar);
                    request.setAttribute("cargos", cargoDAO.listar());
                    request.setAttribute("jefes", integranteDAO.listar());
                    request.getRequestDispatcher("/Integrantes/editar.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    integranteDAO.eliminar(idEliminar);
                    response.sendRedirect(request.getContextPath() + "/IntegranteServlet?accion=listar&deleted=1");
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=integrantes");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if ("crear".equals(accion)) {
                Integrante i = new Integrante();
                i.setNombre(request.getParameter("nombre"));
                i.setCorreo(request.getParameter("correo"));
                i.setTelefono(request.getParameter("telefono"));
                i.setCargoId(Integer.parseInt(request.getParameter("cargo_id")));
                String jefeId = request.getParameter("jefe_id");
                i.setJefeId(jefeId != null && !jefeId.isEmpty() ? Integer.parseInt(jefeId) : 0);

                integranteDAO.insertar(i);
                response.sendRedirect(request.getContextPath() + "/IntegranteServlet?accion=listar&success=1");

            } else if ("editar".equals(accion)) {
                Integrante i = new Integrante();
                i.setId(Integer.parseInt(request.getParameter("id")));
                i.setNombre(request.getParameter("nombre"));
                i.setCorreo(request.getParameter("correo"));
                i.setTelefono(request.getParameter("telefono"));
                i.setCargoId(Integer.parseInt(request.getParameter("cargo_id")));
                String jefeId = request.getParameter("jefe_id");
                i.setJefeId(jefeId != null && !jefeId.isEmpty() ? Integer.parseInt(jefeId) : 0);

                integranteDAO.actualizar(i);
                response.sendRedirect(request.getContextPath() + "/IntegranteServlet?accion=listar&updated=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/IntegranteServlet?accion=listar&error=1");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para gestionar integrantes del organigrama";
    }
}



