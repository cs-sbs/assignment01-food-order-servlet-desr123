package cs.sbs.web.servlet;

import cs.sbs.web.DataStore;
import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 从URL获取orderId，比如 /order/1001
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            out.println("<h2 style='color:red'>Error: orderId is required!</h2>");
            out.println("<a href='/orderList'>Back to order list</a>");
            return;
        }

        String orderIdStr = pathInfo.substring(1); // 去掉开头的 '/'

        // 异常1：订单号不是数字
        int orderId;
        try {
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            out.println("<h2 style='color:red'>Error: invalid orderId format!</h2>");
            out.println("<a href='/orderList'>Back to order list</a>");
            return;
        }

        // 异常2：找不到订单
        Order target = null;
        for (Order o : DataStore.getInstance().orderList) {
            if (o.getId() == orderId) {
                target = o;
                break;
            }
        }

        if (target == null) {
            out.println("<h2 style='color:red'>Error: order not found!</h2>");
            out.println("<a href='/orderList'>Back to order list</a>");
            return;
        }

        // 正常展示详情
        out.println("<h1>Order Details</h1>");
        out.println("<p>OrderID:" + target.getId() + "</p>");
        out.println("<p>Customer:" + target.getCustomer() + "</p>");
        out.println("<p>Food:" + target.getFood() + "</p>");
        out.println("<p>Quantity:" + target.getQuantity() + "</p>");
        out.println("<br><a href='/orderList'>Back to order list</a>");
    }
}