package controller;

import controller.command.*;
import internal.dao.OrderDAOInterface;
import internal.dao.ClientDAOInterface;
import internal.dao.MenuDAOInterface;
import internal.dao.UsersDAOInterface;
import internal.PersistanceUtil;
import internal.dao.implementation.ClientDAO;
import internal.dao.implementation.MenuDAO;
import internal.dao.implementation.OrderDAO;
import internal.dao.implementation.UsersDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/newWeb_6")
public class MainServlet extends HttpServlet {
    
    private ClientDAOInterface clientDAO;
    
    private MenuDAOInterface menuDAO;
    
    private OrderDAOInterface orderDAO;
    
    private UsersDAOInterface usersDAO;

    private Map<String, Command> commands;

    public MainServlet() {
        super();
        this.clientDAO = new ClientDAO(PersistanceUtil.getEntityManager());
        this.menuDAO = new MenuDAO(PersistanceUtil.getEntityManager());
        this.orderDAO = new OrderDAO(PersistanceUtil.getEntityManager());
        this.usersDAO = new UsersDAO(PersistanceUtil.getEntityManager());
        this.commands = new HashMap<>();
    }

    @Override
    public void init() {
        Command[] commands = {new HomeCommand(),
                new ClientCommand(clientDAO),
                new MenuCommand(menuDAO),
                new OrderDueDateCommand(orderDAO),
                new OrderOverdueCommand(orderDAO),
                new ClientOrdersCommand(clientDAO, orderDAO),
                new OrderCreateCommand(orderDAO, clientDAO),
                new OrderCommand(),
                new LoginCommand(usersDAO),
                new RegisterCommand(),
                new PayOrderCommand(clientDAO, orderDAO)};

        for (Command c : commands) {
            this.commands.put(c.getPattern(), c);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            commands.get("home").doGet(request, response, this.getServletContext());
        } else if (commands.containsKey(command)) {
            commands.get(command).doGet(request, response, this.getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            commands.get("home").doPost(request, response, this.getServletContext());
        } else if (commands.containsKey(command)) {
            commands.get(command).doPost(request, response, this.getServletContext());
        }
    }

}
