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

@WebServlet("/HomeDelivery")

public class HomeDelivery extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String msg = "good";
        String Customername = "";
        HttpSession session = request.getSession(true);

        Utilities utility = new Utilities(request, pw);
        if (!utility.isLoggedin()) {
            session = request.getSession(true);
            session.setAttribute("login_msg", "Please Login to Pay");
            response.sendRedirect("Login");
            return;
        }
        // get the payment details like credit card no address processed from cart
        // servlet

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        String userAddress = request.getParameter("shippingaddress");
        String creditCardNo = request.getParameter("creditCardNo");
        String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
        String afterdiscount = request.getParameter("afterdiscount");
        String purchasedate = request.getParameter("purchasedate");
        String shippingdate = request.getParameter("shippingdate");
        String quantity = request.getParameter("quantity");
        int orderId = utility.getOrderPaymentSize() + 1;
        String deliveryType = request.getParameter("deliveryType");
        String zip = request.getParameter("zip");
        HashMap<String, StoreAdd> store = new HashMap<>();
        pw.print("<form name ='ViewOrder' action='Payment' method='post'>");

        pw.print("<td><input type='hidden' name='deliveryType' value='" + deliveryType + "'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
        pw.print(userName);
        pw.print("</td></tr>");

        pw.print("<tr><td>");
        pw.print("Customer /shipping address</td>");
        pw.print("<td><input type='hidden' name='userAddress' value='" + userAddress + "'>");
        pw.print(userAddress + "</td></tr>");

        pw.print("<tr><td>");
        pw.print("Credit/accountNo</td>");
        pw.print("<td><input type='hidden' name='creditCardNo' value='" + creditCardNo + "'>");
        pw.print(creditCardNo + "</td></tr>");

        pw.print("<tr><td>");
        pw.print("OrderId</td>");
        pw.print("<td>" + orderId + "</td></tr>");
        pw.print("<input type='hidden' name='orderid' value='" + orderId + "'>");

        pw.print("<tr><td>");
        pw.print("Purchase Date</td>");
        pw.print("<td><input type='hidden' name='purchasedate' value='" + purchasedate
                + "'>" + purchasedate + "</td></tr>");
        // 7
        pw.print("<tr><td>");
        pw.print("Shipping Date</td>");
        pw.print("<td><input type='hidden' name='shippingdate' value='" + shippingdate
                + "'>" + shippingdate + "</td></tr>");

        pw.print("<tr><td>");
        pw.print("Total Quantity</td>");
        pw.print("<td>" + quantity + "</td></tr>");
        pw.print("<input type='hidden' name='quantity' value='" + quantity + "'>");

        pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>" + orderTotal);
        pw.print("<input type='hidden' name='orderTotal' value='" + orderTotal + "'>");

        pw.print("<tr><td>");
        pw.print("Price with Discount</td><td>" + afterdiscount);
        pw.print("<input type='hidden' name='orderTotal' value='" + afterdiscount + "'>");

        pw.print("<tr><td>");
        pw.print("Customer Zipcode</td>");
        pw.print("<td><input type='hidden' name='zip' value='" + zip + "'>");
        pw.print(zip + "</td></tr>");

        if (deliveryType.equals("pickup")) {
            try {
                store = MySqlDataStoreUtilities.selectStore(zip);

            } catch (Exception e) {

            }
            int size = 0;

            /*
             * get the order size and check if there exist an order with given order number
             * if there is no order present give a message no order stored with this id
             */

            // display the orders if there exist order with order id
            if (store.size() > 0) {
                pw.print("<table  class='gridtable'>");
                pw.print("<tr><td></td>");
                pw.print("<td>Street:</td>");
                pw.print("<td>City:</td>");
                pw.print("<td>State:</td>");
                pw.print("<td>Zipcode:</td></tr>");
                for (Map.Entry<String, StoreAdd> entry : store.entrySet()) {
                    String storeid = entry.getKey();
                    StoreAdd store1 = entry.getValue();
                    pw.print("<tr>");
                    pw.print("<td><input type='radio' name='storeName' value='" + store1.getStoreId() + "'></td>");
                    pw.print("<td>" + store1.getStreet() + ".</td><td>" + store1.getCity() + "</td><td>"
                            + store1.getState() + "</td><td> " + store1.getZipcode() + "</td>");
                    pw.print("<td><input type='submit' name='add' value='Add' class='btnbuy'></td>");
                    pw.print("</tr>");

                }
                pw.print("</table>");
            } else {
                pw.print("<h4 style='color:red'>No Store available at this Location</h4>");
            }

            if (request.getParameter("add") != null && request.getParameter("add").equals("Add")) {
                String stid = request.getParameter("storeName");
                StoreAdd store2 = store.get(stid);
                String address = store2.getStreet() + " " + store2.getCity() + " " + store2.getState() + " "
                        + store2.getZipcode();
                pw.print(address);

                pw.print("<input type='hidden' name='address' value='" + address + "'>");

            }
        } else {
            // pw.print("pickup");
            String shippingCost = "10";
            if (zip.equals("60606"))
                shippingCost = "15";
            else if (zip.equals("60605"))
                shippingCost = "12";
            else if (zip.equals("60603"))
                shippingCost = "11";
            double finalcost = Double.parseDouble(shippingCost) + Double.parseDouble(afterdiscount);
            pw.print("<tr><td>");
            pw.print("Shipping Charges</td><td>" + shippingCost);
            pw.print("<input type='hidden' name='orderTotal' value='" + shippingCost + "'>");
            pw.print("</td></tr>");

            pw.print("<tr><td>");
            pw.print("Final Total</td><td>" + finalcost);
            pw.print("<input type='hidden' name='finalcost' value='" + finalcost + "'>");
            pw.print("<tr><td>");
            pw.print("<tr><td colspan='2'>");
            pw.print("<input type='submit' name='Next' class='btnbuy'>");
        }

        pw.print("</td></tr></table></form>");

        /* start form */
        // utility.printHtml("Footer.html");
        // response.sendRedirect("HomeDelivery");
    }

}
