package controllers;

import dao.DaoFactory;
import exeptions.UserAlreadyExistExeption;
import exeptions.UserNotFoundExeption;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import security.EncryptUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AddUserController")
public class AddUserController extends HttpServlet {
    private static final Logger Log = LogManager.getLogger(AddUserController.class);
    private DaoFactory daoFactory;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        daoFactory = (DaoFactory) context.getAttribute("daoFactory");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String repPass = request.getParameter("repeatPassword");

        String[] args = new String[]{firstname, lastname, password, repPass, email, username};

        for (String arg : args) {
            if (arg.equals("") || arg.matches("\\s+")) {

                forward("/registration/error.jsp", request, response);
            }
        }
        if (isValid(email, password, repPass)) {
            try {
                User user = new User();
                user.setUsername(username);
                user.setPassword(EncryptUtil.encrypt(password));
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setId(daoFactory.getUserDao().addUser(user));

                Log.info("new user added");
            } catch (UserAlreadyExistExeption e) {
                Log.warn("user cannot register again", e);
            }
            forward("/registration/success.jsp", request, response);
        } else {
            Log.error("user cannot register 1) incorrect email or 2) password repeat or 3) he is already registered");
            forward("/registration/error.jsp", request, response);
        }

    }

    private boolean isValid(String email, String password, String checkPas) {
        return checkPas.equals(password) && correctEmail(email) && !isRegistered(email);
    }

    private boolean correctEmail(String email) {
        return email.matches("\\w{1,25}[@]\\w{1,10}\\.\\w{2,3}");
    }

    private boolean isRegistered(String email) {
        boolean isRegistered = false;
        try {
            User user = daoFactory.getUserDao().getUserByEmail(email);
            if (user != null) {
                isRegistered = true;
                Log.error("user cannot register, he is already registered email=" + email);
            }
        } catch (UserNotFoundExeption e) {
            Log.info("user not registered email=" + email, e);
            isRegistered = false;
        }
        return isRegistered;
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }


}
