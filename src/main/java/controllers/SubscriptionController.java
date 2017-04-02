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
import java.util.Collection;

@WebServlet("/subscribeList/")
public class SubscriptionController extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final String SUBSCRIBES = "subscribes";
    private static final String PAGE_ID = "pageId";
    private DaoFactory daoFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory)context.getAttribute("daoFactory");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubscriptionDao subscriptionDao = daoFactory.getSubscriptionDao();
        HttpSession session = request.getSession();

        Collection<Integer> subIds;

        if (request.getParameter(PAGE_ID) != null) {
            int pageId = Integer.parseInt(request.getParameter(PAGE_ID));
            subIds = subscriptionDao.getFollowers(pageId);
        } else {
            int userId = (int) session.getAttribute(USER_ID);
            subIds = subscriptionDao.getFollowers(userId);
        }

        request.setAttribute(SUBSCRIBES, subIds);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/page/subList.jsp");
        requestDispatcher.forward(request, response);
    }
}