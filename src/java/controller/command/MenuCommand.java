package controller.command;

import internal.dao.MenuDAOInterface;
import internal.entity.MenuItem;
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

public class MenuCommand implements Command {
    private final String urlPattern = "menu";

    private MenuDAOInterface menuDAO;

    public MenuCommand(MenuDAOInterface menuDAO) {
        this.menuDAO = menuDAO;
    }

    @Override
    public String getPattern() {
        return urlPattern;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
        // Обращение к базе
        List<MenuItem> menu = null;
        try {
            menu = menuDAO.readMenu();
        } catch (DAOException ex) {
            Logger.getLogger(MenuCommand.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        request.setAttribute("menu", menu);
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
    }
}
