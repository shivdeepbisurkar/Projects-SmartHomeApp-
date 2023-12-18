import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ThermoList")

public class ThermoList extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        HashMap<String, Thermo> allgames = new HashMap<String, Thermo>();

        /* Checks the Tablets type whether it is microsft or sony or nintendo */
        try {
            allgames = MySqlDataStoreUtilities.getThermos();
        } catch (Exception e) {

        }

        String name = null;
        String CategoryName = request.getParameter("maker");
        HashMap<String, Thermo> hm = new HashMap<String, Thermo>();

        if (CategoryName == null) {
            hm.putAll(allgames);
            name = "";
        } else {
            if (CategoryName.equals("Amazon")) {
                for (Map.Entry<String, Thermo> entry : allgames.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Amazon")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Amazon";
            } else if (CategoryName.equals("Google")) {
                for (Map.Entry<String, Thermo> entry : allgames.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Google")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Google";
            } else if (CategoryName.equals("Comed")) {
                for (Map.Entry<String, Thermo> entry : allgames.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Comed")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Comed";
            }
        }

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + name + " Thermostat</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1;
        int size = hm.size();
        for (Map.Entry<String, Thermo> entry : hm.entrySet()) {
            Thermo thermo = entry.getValue();
            if (i % 3 == 1)
                pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + thermo.getName() + "</h3>");
            pw.print("<strong>" + "$" + thermo.getPrice() + "</strong><ul>");
            pw.print("<li id='item'><img src='images/tablets/" + thermo.getImage() + "' alt='' /></li>");
            pw.print("<li><form method='post' action='Cart'>" +
                    "<input type='hidden' name='name' value='" + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='thermos'>" +
                    "<input type='hidden' name='discount' value='" + thermo.getDiscount() + "'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
            pw.print("<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='"
                    + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='thermos'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='price' value='" + thermo.getPrice() + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='"
                    + entry.getKey() + "'>" +
                    "<input type='hidden' name='type' value='thermos'>" +
                    "<input type='hidden' name='maker' value='" + CategoryName + "'>" +
                    "<input type='hidden' name='access' value=''>" +
                    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
            pw.print("</ul></div></td>");
            if (i % 3 == 0 || i == size)
                pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div>");
        utility.printHtml("Footer.html");

    }

}
