import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

@WebServlet("/SaleReport")

public class SaleReport extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String msg = "good";
        String Customername = "";
        HttpSession session = request.getSession(true);

        Utilities utility = new Utilities(request, pw);

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        HashMap<String, OrderPayment> store = new HashMap<>();
        try {
            store = MySqlDataStoreUtilities.getSaleInfo();

        } catch (Exception e) {

        }
        int size = 0;
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'> Product List</a></h2>"
                + "<div class='entry'>");
        // Embedded CSS styles
        pw.println("<style type='text/css'>");
        pw.println(".table-earnings { background: #F3F5F6; }");
        pw.println("table { display: block; height: 200px; overflow-y: auto; }");
        pw.println("</style>");

        /*
         * get the order size and check if there exist an order with given order number
         * if there is no order present give a message no order stored with this id
         */

        // display the orders if there exist order with order id
        if (store.size() > 0) {
            pw.print(
                    "<div><table  class='gridtable table table-earnings'>");

            pw.print("<td>product name:</td>");
            pw.print("<td>Price</td>");
            pw.print("<td>No of Items sold:</td>");
            pw.print("<td>Total sale:</td>");
            HashMap<String, OrderPayment> sale = new HashMap<>();
            for (Map.Entry<String, OrderPayment> entry : store.entrySet()) {
                String storeid = entry.getKey();

                OrderPayment store1 = entry.getValue();
                OrderPayment instore = new OrderPayment(store1.getOrderId(), store1.getOrderName(),
                        store1.getOrderPrice(), store1.getPurdate());
                if (sale.containsKey(instore.getOrderName())) {
                    OrderPayment old = sale.get(instore.getOrderName());
                    OrderPayment instore1 = new OrderPayment(old.getOrderName(), old.getOrderPrice(),
                            old.getTotal() + 1);
                    sale.put(instore.getOrderName(), instore1);
                } else {
                    OrderPayment instore1 = new OrderPayment(instore.getOrderName(), instore.getOrderPrice(), 1);
                    sale.put(instore.getOrderName(), instore1);
                }

            }
            for (Map.Entry<String, OrderPayment> entry : sale.entrySet()) {
                String storeid = entry.getKey();

                OrderPayment store1 = entry.getValue();
                pw.print("<tr>");

                pw.print("<td>" + store1.getOrderName() + "</td><td>"
                        + store1.getOrderPrice() + "</td><td>" + store1.getTotal() + "</td><td>"
                        + store1.getOrderPrice() * store1.getTotal() + "</td>");

                pw.print("</tr>");

            }
            pw.print("</table></div>");
        }
        pw.print("</div></div></div>");

        displayPage(request, response, pw);

        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'> Daily Report</a></h2>"
                + "<div class='entry'>");
        if (store.size() > 0) {
            pw.print(
                    "<div><table  class='gridtable table table-earnings'>");

            pw.print("<td>product name:</td>");
            pw.print("<td>Total sale:</td>");
            HashMap<String, Double> sale = new HashMap<>();
            for (Map.Entry<String, OrderPayment> entry : store.entrySet()) {
                String storeid = entry.getKey();

                OrderPayment store1 = entry.getValue();
                OrderPayment instore = new OrderPayment(store1.getOrderId(), store1.getOrderName(),
                        store1.getOrderPrice(), store1.getPurdate());
                if (sale.containsKey(store1.getPurdate())) {

                    sale.put(store1.getPurdate(), sale.get(store1.getPurdate()) + store1.getOrderPrice());
                } else {

                    sale.put(store1.getPurdate(), store1.getOrderPrice());
                }

            }
            for (Map.Entry<String, Double> entry : sale.entrySet()) {
                String storeid = entry.getKey();

                pw.print("<tr>");

                pw.print("<td>" + storeid + "</td><td>"
                        + entry.getValue() + "</td>");

                pw.print("</tr>");

            }
            pw.print("</table></div>");
        }
        pw.print("</div></div></div>");

    }

    protected void displayPage(HttpServletRequest request, HttpServletResponse response, PrintWriter pw)
            throws ServletException, IOException {

        Utilities utility = new Utilities(request, pw);

        pw.print("<div id='content'><div class='post'>");

        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
                + "<div class='entry'>");

        pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
        pw.println("<div id='chart_div'></div>");
        pw.println("</div></div></div>");
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript' src='saleReport.js'></script>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {

            HashMap<String, OrderPayment> product = MySqlDataStoreUtilities.getSaleInfo();

            String reviewJson = new Gson().toJson(product);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {

        }

    }

}