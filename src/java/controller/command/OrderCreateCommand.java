package controller.command;

import internal.dao.ClientDAOInterface;
import internal.dao.OrderDAOInterface;
import internal.entity.Client;
import internal.entity.Order;
import internal.entity.User;
import internal.exception.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderCreateCommand implements Command {
    private final String urlPattern = "ordercreate";
    
    private static Logger logger = LogManager.getLogger();

    private OrderDAOInterface orderDAO;
    private ClientDAOInterface clientDAO;

    public OrderCreateCommand(OrderDAOInterface orderDAO,
                              ClientDAOInterface clientDAO) {
        this.orderDAO = orderDAO;
        this.clientDAO = clientDAO;
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
        if (loginedUser.getRole() != 0) {
            request.setAttribute("serve", "Permission denied. Admins cannot access order creation.");
            request.setAttribute("back", "/newWeb_6");
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/view/info.jsp");
            dispatcher.forward(request, response);
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/createorderform.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        Double orderSum = Double.parseDouble(request.getParameter("orderSum"));
        Date deliveryDeadline = Date.valueOf(request.getParameter("deliveryDeadline"));
        Boolean isPaid = request.getParameter("isPaid") != null;
        Client client = null;
        Order order = new Order();
        System.out.println(orderSum);
        System.out.println(deliveryDeadline);
        System.out.println(isPaid);
        order.setOrderSum(orderSum);
        order.setDeliveryDeadline(deliveryDeadline);
        order.setPaid(isPaid);
        try {
            client = clientDAO.readClientByName(loginedUser.getName());
            order.setClient(client);
            orderDAO.insertOrder(order);
        } catch (DAOException ex) {
            logger.info("Insert order: ", ex);
            System.out.println(ex.getMessage());
        }
        this.doGet(request, response, servletContext);
    }
}
