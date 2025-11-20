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

        
        if ("admin".equals(usuario) && "1234".equals(clave)) {
            HttpSession sesion = request.getSession();
            sesion.setAttribute("admin", usuario);

           
            response.sendRedirect(request.getContextPath() + "/AdminServlet?accion=panel");
        } else {
            
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp?error=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "LoginServlet que valida credenciales y redirige al panel";
    }
}
