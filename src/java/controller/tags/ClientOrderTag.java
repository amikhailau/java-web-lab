/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.tags;

import internal.entity.Order;
import internal.entity.User;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author TheProthean
 */
public class ClientOrderTag extends TagSupport {
    private List<Order> clientOrders;

    public void setClientOrders(List<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }

    public int doStartTag() throws JspException {
        Locale loc = (Locale) pageContext.getAttribute("userLocale", PageContext.SESSION_SCOPE);
        ResourceBundle bundle = ResourceBundle.getBundle("resources.locale", loc, this.getClass().getClassLoader());
        String context = pageContext.getServletContext().getContextPath();
        User loginedUser = (User) pageContext.getAttribute("loginedUser", PageContext.SESSION_SCOPE);
        String table =
                "<div class=\"page-container\">\n" +
                "    <div class=\"page-table\">\n" +
                "        <table class=\"table\">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th scope=\"col\">#</th>\n" +
                "                <th scope=\"col\">" + bundle.getString("clientName") + "</th>\n" +
                "                <th scope=\"col\">" + bundle.getString("orderValue") + "</th>\n" +
                "                <th scope=\"col\">" + bundle.getString("deadline") + "</th>\n" +
                "                <th scope=\"col\">" + bundle.getString("paid") + "</th>\n";
        if (loginedUser.getRole() > 0) {
            table += "                <th scope=\"col\">" + bundle.getString("payOrder") + "</th>\n";
        }
        table +=
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n";
        for (Order order : clientOrders) {
            table += "                <tr>\n";
            table += "                    <td>" + order.getId() +"</td>\n";
            table += "                    <td>" + order.getClient().getName() +"</td>\n";
            table += "                    <td>" + order.getOrderSum() +"</td>\n";
            table += "                    <td>" + order.getDeliveryDeadline() +"</td>\n";
            table += "                    <td>" + order.isPaid() +"</td>\n";
            if (loginedUser.getRole() > 0) {
                table += "                    <td>" + "<a href=\"" + context + "/newWeb_6?command=payorder&id=" + order.getId() + "&clientId=" + order.getClient().getId() + "\">" + bundle.getString("payOrder") + "</a>" +"</td>\n";
            }
            table += "                </tr>\n";
        }
        table += "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(table);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
