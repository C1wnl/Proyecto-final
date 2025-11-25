package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        // Validación básica de credenciales
        if ("admin".equals(usuario) && "1234".equals(clave)) {
            // Crear sesión y guardar atributos
            HttpSession sesion = request.getSession(true);

            // Guardamos ambos atributos para compatibilidad
            sesion.setAttribute("admin", usuario);
            sesion.setAttribute("usuario_admin", usuario);

            sesion.setMaxInactiveInterval(3600); // 1 hora de sesión

            // Redirigir al panel de control (AdminServlet)
            response.sendRedirect(request.getContextPath() + "/AdminServlet?accion=panel");
        } else {
            // Redirigir al login con mensaje de error
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si accede por GET, mostrar el login
        response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "LoginServlet que valida credenciales y redirige al panel de control";
    }
}
