package controller.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import internal.entity.User;
import controller.utils.Utils;
import internal.dao.UsersDAOInterface;
import internal.exception.DAOException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;

public class LoginCommand implements Command {
    
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    final String urlPattern = "login";
    UsersDAOInterface usersDAO;

    @Override
    public String getPattern() {
        return urlPattern;
    }

    public LoginCommand(UsersDAOInterface usersDAO) {
        super();
        this.usersDAO = usersDAO;
    }

    // Show Login page.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");

        // Not logged in
        if (loginedUser != null) {
            // Redirect to login page.
            Utils.deleteUserCookie(response);
            session.removeAttribute("loginedUser");
        }
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/view/login.jsp");
        dispatcher.forward(request, response);

    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        User user = null;
        boolean hasError = false;
        String errorString = null;

        Locale loc = (Locale) request.getSession().getAttribute("userLocale");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.locale", loc, this.getClass().getClassLoader());
        if (userName == null || password == null || userName.length() < 4 || password.length() < 5) {
            hasError = true;
            errorString = bundle.getString("invalidPassUser");
        } else {
            try {
                // Find the user in the DB.
                user = usersDAO.readUserByName(userName);
            } catch (DAOException ex) {
                logger.error("Error while trying to login: ", ex);
            }

            if (user == null || !user.getPassword().equals(password)) {
                hasError = true;
                errorString = bundle.getString("notAuthed");
            }
        }
        // If error, forward to /WEB-INF/view/login.jsp
        if (hasError) {
            user = new User(userName, password, -1);
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward to /WEB-INF/view/login.jsp
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/view/login.jsp");

            dispatcher.forward(request, response);
        } // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            // On the JSP can access via ${loginedUser}
            session.setAttribute("loginedUser", user);
            // If user checked "Remember me".
            if (remember) {
                Utils.storeUserCookie(response, user);
            } // Else delete cookie.
            else {
                Utils.deleteUserCookie(response);
            }

            response.sendRedirect(request.getContextPath() + "/newWeb_6");
        }
    }

}
