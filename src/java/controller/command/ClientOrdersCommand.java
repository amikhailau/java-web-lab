package controller.command;

import internal.dao.ClientDAOInterface;
import internal.dao.OrderDAOInterface;
import internal.entity.Client;
import internal.entity.Order;
import internal.exception.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

public class ClientOrdersCommand implements Command {
    private final String urlPattern = "clientorders";

    private ClientDAOInterface clientDAO;
    private OrderDAOInterface orderDAO;

    public ClientOrdersCommand(ClientDAOInterface clientDAO, OrderDAOInterface orderDAO) {
        this.clientDAO = clientDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/clientorders.jsp");
        String clientId = request.getParameter("id");
        // Обращение к базе
        Client client = null;
        try {
            client = clientDAO.readClientById(Long.parseLong(clientId));
        } catch (DAOException ex) {
            Logger.getLogger(ClientOrdersCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Order> orders = null;
        try {
            orders = orderDAO.readOrdersByClientId(client.getId());
        } catch (DAOException ex) {
            Logger.getLogger(ClientOrdersCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        request.setAttribute("orders", orders);
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
    }
}
