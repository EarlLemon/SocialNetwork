package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login/login.jsp", "/index.jsp"})
public class LoginFilter implements Filter {

    @Override
    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        Object tempUser = session.getAttribute("User");
        if (tempUser == null) {
            request.getRequestDispatcher("/index.jsp").forward(req, response);
        } else {
            request.getRequestDispatcher("/login/login.jsp").forward(req,response);
        }
        chain.doFilter(req, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}

