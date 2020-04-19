/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import internal.PersistanceUtil;
import internal.dao.ClientDAOInterface;
import internal.dao.UsersDAOInterface;
import internal.dao.implementation.ClientDAO;
import internal.dao.implementation.UsersDAO;
import internal.entity.Client;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import internal.entity.User;
import internal.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author TheProthean
 */
@WebFilter(filterName = "registrationFormFilter", urlPatterns = {"/newWeb_6/*"})
public class RegistrationFormFilter implements Filter {
    
    private static Logger logger = LogManager.getLogger();

    private UsersDAOInterface usersDAO;
    
    private ClientDAOInterface clientDAO;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.clientDAO = new ClientDAO(PersistanceUtil.getEntityManager());
        this.usersDAO = new UsersDAO(PersistanceUtil.getEntityManager());
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String command = request.getParameter("command");
        if (command == null || !command.equals("register")) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getMethod().equalsIgnoreCase("POST")) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            HttpSession session = httpServletRequest.getSession();
            User loginedUser = (User) session.getAttribute("loginedUser");
            if (loginedUser == null) {
                if (userName.length() > 4 && userName.matches("[A-Za-z0-9]+")) {
                    if (password.length() > 5) {
                        User user = null;
                        try {
                            user = usersDAO.readUserByName(userName);
                        }
                        catch (DAOException ex) {
                            logger.error("Error while trying to go through registration: ", ex);
                        }
                        if (user == null) {
                            try {
                                loginedUser = new User(userName, password, 0);
                                Client client = new Client(0L, userName, phone);
                                usersDAO.insertUser(loginedUser);
                                clientDAO.insertClient(client);
                            }
                            catch (DAOException ex) {
                                logger.error("Error while trying to go through registration: ", ex);
                            }
                            session.setAttribute("loginedUser", loginedUser);
                            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/view/index.jsp");
                            dispatcher.forward(request, response);
                            return;
                        } else {
                            request.setAttribute("errorCode", "userExists");
                        }
                    } else {
                        request.setAttribute("errorCode", "passwordFormat");
                    }
                } else {
                    request.setAttribute("errorCode", "loginForm");
                }
            }
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/view/registration.jsp");
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

}
