package controllers;

import dao.DaoFactory;
import exeptions.UserNotFoundExeption;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user"})
public class UserPageController extends HttpServlet {
    private static final Logger Log = LogManager.getLogger(AddUserController.class);
    private DaoFactory daoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        daoFactory = (DaoFactory) config.getServletContext().getAttribute("daoFactory");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("friend_id");
        String sessionAttributeId = String.valueOf(req.getSession().getAttribute("id"));
        if (id != null && !id.equals(sessionAttributeId)) {
            User user = null;
            try {
                user = daoFactory.getUserDao().getUserById(Integer.parseInt(id));

            } catch (UserNotFoundExeption exeption) {
                Log.error("Can not find user by id in UserPageController", exeption);
                req.getRequestDispatcher("/error/error.jsp").forward(req, resp);
            }


            req.setAttribute("id", id);
            req.setAttribute("firstname", user.getFirstname());
            req.setAttribute("lastname", user.getLastname());
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/page/followersList.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/jsp/userPage.jsp").forward(req, resp);
        }

    }
}
