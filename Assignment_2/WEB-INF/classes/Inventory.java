import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

@WebServlet("/Inventory")

public class Inventory extends HttpServlet {

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
        // pw.print("<div id='content'><div class='post'>");
        // pw.print("<h2 class='title meta'><a style='font-size: 24px;'> Product
        // List</a></h2>"
        // + "<div class='entry'>");
        HashMap<String, Product> store = new HashMap<>();
        try {
            store = MySqlDataStoreUtilities.getAllproduct();

        } catch (Exception e) {

        }
        int size = 0;
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'> Product List (No of product" + store.size()
                + ")</a></h2>"
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
            pw.print("<td>price:</td>");
            pw.print("<td>count:</td>");

            for (Map.Entry<String, Product> entry : store.entrySet()) {
                String storeid = entry.getKey();
                Product store1 = entry.getValue();
                pw.print("<tr>");

                pw.print("<td>" + store1.getId() + "</td><td>"
                        + store1.getPrice() + "</td><td> " + store1.getCount() + "</td>");

                pw.print("</tr>");

            }
            pw.print("</table></div>");
        }
        pw.print("</div></div></div>");

        displayPage(request, response, pw);
        // Sale

        HashMap<String, Product> sale = new HashMap<>();
        try {
            sale = MySqlDataStoreUtilities.getOnSale();

        } catch (Exception e) {

        }
        size = 0;
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'> Product on sale: Total no. of product on Sale: "
                + sale.size() + "</a></h2>"
                + "<div class='entry'>");
        if (sale.size() > 0) {
            pw.print(
                    "<div><table  class='gridtable table table-earnings'>");

            pw.print("<td>product name:</td>");

            for (Map.Entry<String, Product> entry : sale.entrySet()) {
                String storeid = entry.getKey();
                Product store1 = entry.getValue();
                pw.print("<tr>");

                pw.print("<td>" + store1.getId() + "</td>");

                pw.print("</tr>");

            }
            pw.print("</table></div>");
        }
        pw.print("</div></div></div>");

        // Rebat

        HashMap<String, Product> rebat = new HashMap<>();
        try {
            rebat = MySqlDataStoreUtilities.getOnRabet();

        } catch (Exception e) {

        }
        size = 0;
        pw.print("<div id='content'><div class='post'>");
        pw.print("<h2 class='title meta'><a style='font-size: 24px;'> product with Manufacturer Rebat (No of product : "
                + rebat.size()
                + ")</a></h2>"
                + "<div class='entry'>");
        if (rebat.size() > 0) {
            pw.print(
                    "<div><table  class='gridtable table table-earnings table-earnings__challenge'>");

            pw.print("<td>product name:</td>");

            for (Map.Entry<String, Product> entry : rebat.entrySet()) {
                String storeid = entry.getKey();
                Product store1 = entry.getValue();
                pw.print("<tr>");

                pw.print("<td>" + store1.getId() + "</td>");

                pw.print("</tr>");

            }
            pw.print("</table></div>");
        }
        pw.print("</div></div></div>");

        utility.printHtml("Footer.html");

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
        pw.println("<script type='text/javascript' src='inventory.js'></script>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {

            HashMap<String, Product> product = MySqlDataStoreUtilities.getAllproduct();

            String reviewJson = new Gson().toJson(product.values());

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);

        } catch (Exception ex) {

        }

    }

}