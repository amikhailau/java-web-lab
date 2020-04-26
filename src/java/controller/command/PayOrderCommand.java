/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command;

import internal.dao.ClientDAOInterface;
import internal.dao.OrderDAOInterface;
import internal.entity.Client;
import internal.entity.Order;
import internal.exception.DAOException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TheProthean
 */
public class PayOrderCommand implements Command {

    final String urlPattern = "payorder";
    
    private ClientDAOInterface clientDAO;
    private OrderDAOInterface orderDAO;

    public PayOrderCommand(ClientDAOInterface clientDAO, OrderDAOInterface orderDAO) {
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
        String clientId = request.getParameter("clientId");
        String orderId = request.getParameter("id");
        // Обращение к базе
        Client client = null;
        Order chOrder = null;
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
        for (Order order: orders) {
            if (order.getId() == Long.parseLong(orderId)) {
                chOrder = order;
                order.setPaid(true);
                break;
            }
        }
        try {
            orderDAO.changeOrderState(chOrder);
        } catch (DAOException ex) {
            Logger.getLogger(PayOrderCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(orders);
        request.setAttribute("orders", orders);
        dispatcher.forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
    }
}