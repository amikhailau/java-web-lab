package controller.command;

import internal.entity.Order;
import internal.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class OrderCommand implements Command {
    private final String urlPattern = "order";

    public OrderCommand() {
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
        Locale loc = (Locale) session.getAttribute("userLocale");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.locale", loc, this.getClass().getClassLoader());
        if (loginedUser.getRole() == 0) {
            request.setAttribute("serve", bundle.getString("authTotalOrderError"));
            request.setAttribute("back", "/newWeb_6");
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/view/info.jsp");
            dispatcher.forward(request, response);
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/order.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
    }
}