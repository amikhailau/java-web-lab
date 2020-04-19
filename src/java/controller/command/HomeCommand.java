package controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletContext;

public class HomeCommand implements Command {
    final String urlPattern = "home";

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        // Экземпляр страницы
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/index.jsp");
        dispatcher.forward(request, response);
    }
}
