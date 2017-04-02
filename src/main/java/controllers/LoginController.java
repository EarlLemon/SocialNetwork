package controllers;

import dao.DaoFactory;
import exeptions.UserNotFoundExeption;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import security.EncryptUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static Logger Log = LogManager.getLogger(LoginController.class);
    private static final String KEY = "key";
    private static final String USER_ID = "userId";

    private DaoFactory daoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoFactory = (DaoFactory)config.getServletContext().getAttribute("daoFactory");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String typeEmail = request.getParameter("email");
        String typePassword = request.getParameter("password");
        String hash = EncryptUtil.encrypt(typePassword);
        try {
            User user = daoFactory.getUserDao().getUserByEmail(typeEmail);
            if (user.getPassword().equals(hash)) {
                session.setAttribute(KEY, new Object());
                session.setAttribute(USER_ID, user.getId());
                if (session.getAttribute("lang") == null) {
                    session.setAttribute("lang", "ru_RU");
                }
                Log.info("user logged in, userId = " + user.getId());

                forward("/login/successLog.jsp", request, response);

            } else {
                forward("/login/error.jsp", request, response);
            }

        } catch (UserNotFoundExeption exeption) {
            Log.warn("Sign in failed", exeption);
            forward("/login/login.jsp", request, response);
        } catch (NullPointerException exeption) {
            Log.warn("Fields empty", exeption);
            forward("/login/error.jsp", request, response);
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
