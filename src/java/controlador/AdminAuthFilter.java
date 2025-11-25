package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        // Detectar si está accediendo al login o al servlet de login
        boolean esLogin = request.getRequestURI().endsWith("/admin/login.jsp")
                        || request.getRequestURI().contains("LoginServlet");

        // Validar sesión: permitir si tiene usuario_admin o admin
        boolean sesionValida = session != null &&
                              (session.getAttribute("usuario_admin") != null || session.getAttribute("admin") != null);

        // Si no es login y no tiene sesión válida, redirigir
        if (!esLogin && !sesionValida) {
            response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
            return;
        }

        // Si todo está bien, continuar con el servlet o JSP
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No se necesita inicialización
    }

    @Override
    public void destroy() {
        // No se necesita limpieza
    }
}
