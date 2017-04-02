package controllers;

import dao.DaoFactory;
import model.Lesson;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddLessonController")
public class AddLessonController extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final int LESSON_LENGTH = 140;
    private DaoFactory daoFactory;

    @Override
    public void init() throws ServletException {
        daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        ;
        if (user != null) {
            String lessonText = request.getParameter("newLesson");
            if (lessonText.length() > LESSON_LENGTH || lessonText.matches("\\s+") || lessonText.equals("")) {

                forward("page/errorLesson.jsp", request, response);
            } else {
                Lesson lesson = new Lesson(0,user.getId(),lessonText);
                int resl = daoFactory.getLessonDao().addLesson(lesson);
                response.getWriter().write(Integer.toString(resl));


                forward("/page/", request, response);
            }

        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
