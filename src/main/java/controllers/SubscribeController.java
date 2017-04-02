package controllers;

import dao.DaoFactory;
import dao.SubscriptionDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SubscribeController")
public class SubscribeController extends HttpServlet {
    private static final String USER_ID = "userId";

    private DaoFactory daoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory)context.getAttribute("daoFactory");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = daoFactory.getSubscriptionDao();

        HttpSession session = request.getSession();

        String isSubscribe = request.getParameter("subButton");
        int userId = (int) session.getAttribute(USER_ID);
        int pageId = Integer.parseInt(request.getParameter("idButton"));


        if (isSubscribe.equals("subscribe") || isSubscribe.equals("подписаться")) {
            subscriptionDao.createSubscription(userId, pageId);

        } else {
            subscriptionDao.deleteSubscription(userId, pageId);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/GetUserController/?userHref=" + pageId);
        requestDispatcher.forward(request,response);

    }

}
