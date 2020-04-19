package controller.command;

import internal.dao.OrderDAOInterface;
import internal.entity.Order;
import internal.entity.User;
import internal.exception.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class OrderDueDateCommand implements Command {
    private final String urlPattern = "orderduedate";

    private OrderDAOInterface orderDAO;

    public OrderDueDateCommand(OrderDAOInterface orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
       
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        if (loginedUser.getRole() == 0) {
            request.setAttribute("serve", "Permission denied. Client can't access total order information");
            request.setAttribute("back", "/newWeb_6");
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/view/info.jsp");
            dispatcher.forward(request, response);
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/orderduedate.jsp");
        String dueDateStr = request.getParameter("dueDate");
        System.out.println(dueDateStr);
        Date dueDate = Date.valueOf(dueDateStr);
        // Обращение к базе
        List<Order> orders = null;
        try {
            orders = orderDAO.readOrdersDueToDate(dueDate);
        } catch (DAOException ex) {
            Logger.getLogger(OrderDueDateCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        request.setAttribute("orders", orders);
        dispatcher.forward(request, response);
    }
}
