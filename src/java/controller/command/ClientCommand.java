package controller.command;

import internal.dao.ClientDAOInterface;
import internal.entity.Client;
import internal.entity.User;
import internal.exception.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;

public class ClientCommand implements Command {
    
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    
    private final String urlPattern = "client";

    private ClientDAOInterface clientDAO;

    public ClientCommand(ClientDAOInterface clientDao) {
        this.clientDAO = clientDao;
    }

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        List<Client> clients = null;
        if (loginedUser.getRole() == 0) {
            Client client = null;
            try {
                client = clientDAO.readClientByName(loginedUser.getName());
            } catch (DAOException ex) {
                logger.error("Error while trying to fetch clients: ", ex.getMessage());
                return;
            }
            if (client == null) {
                request.setAttribute("serve", "Authentication failed.");
                request.setAttribute("back", "/newWeb_6");
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/view/info.jsp");
                dispatcher.forward(request, response);
                return;
            }
            clients = new ArrayList<Client>();
            clients.add(client);
        } else {
            try {
                clients = clientDAO.readClients();
            } catch (DAOException ex) {
                logger.error("Error while trying to fetch clients: ", ex.getMessage());
                return;
            }
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/client.jsp");
        request.setAttribute("clients", clients);
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
    }
}
