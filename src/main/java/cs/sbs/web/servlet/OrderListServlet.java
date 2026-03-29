package cs.sbs.web.servlet;

import cs.sbs.web.DataStore;
import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        List<Order> orders = DataStore.getInstance().orderList;

        out.println("<h1>Order List</h1>");
        if (orders.isEmpty()) {
            out.println("<p>No orders yet.</p>");
        } else {
            for (Order o : orders) {
                out.println("<p><a href='/order/" + o.getId() + "'>Order #" + o.getId() + " (Click to view details)</a> - " + o.getCustomer() + "</p>");
            }
        }
        out.println("<br><a href='/order.html'>Back to home</a>");
    }
}