package cs.sbs.web.servlet;

import cs.sbs.web.DataStore;
import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // 获取表单参数
        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String qtyStr = req.getParameter("quantity");

        // 异常1：参数为空
        if (customer == null || customer.isBlank() ||
                food == null || food.isBlank() ||
                qtyStr == null || qtyStr.isBlank()) {
            out.println("<h2 style='color:red'>Error: all fields (customer, food, quantity) are required!</h2>");
            out.println("<a href='/order.html'>Go back</a>");
            return;
        }

        // 异常2：数量不是合法数字
        int quantity;
        try {
            quantity = Integer.parseInt(qtyStr);
            if (quantity <= 0) {
                out.println("<h2 style='color:red'>Error: quantity must be a positive number!</h2>");
                out.println("<a href='/order.html'>Go back</a>");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("<h2 style='color:red'>Error: quantity must be a valid number</h2>");
            out.println("<a href='/order.html'>Go back</a>");
            return;
        }

        // 创建订单
        DataStore store = DataStore.getInstance();
        int orderId = store.orderIdCounter++;
        store.orderList.add(new Order(orderId, customer, food, quantity));

        // 成功页面
        out.println("<h2>Order created successfully!</h2>");
        out.println("<p>OrderID:" + orderId + " " + customer + " " + food + " x" + quantity + "</p>");
        out.println("<p><a href='/orderList'>View all orders</a></p>");
        out.println("<p><a href='/order.html'>Back to home</a></p>");
    }
}