package controllers;

import dao.DaoFactory;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/GetAllUsers")
public class AllUsersController extends HttpServlet {
    private static final String USERS = "users";

    private DaoFactory daoFactory;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<User> users = daoFactory.getUserDao().getAll();
        request.setAttribute(USERS, users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/allUsers.jsp");
        requestDispatcher.forward(request, response);
    }
}

