import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

@WebServlet("/CheckOut")

// once the user clicks buy now button page is redirected to checkout page where
// user has to give checkout information

public class CheckOut extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}

	protected void storeOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);

			Date currentDate = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);

			calendar.add(Calendar.DAY_OF_MONTH, 5);
			// Format the date as a string (you can choose your desired date format)
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormat.format(currentDate);

			Date dateAfter5Days = calendar.getTime();

			// Format the date as a string (you can choose your desired date format)

			String fiveformattedDate = dateFormat.format(dateAfter5Days);

			if (!utility.isLoggedin()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session = request.getSession();

			// get the order product details on clicking submit the form will be passed to
			// submitorder page

			String userName = session.getAttribute("username").toString();
			String orderTotal = request.getParameter("orderTotal");
			String afterdiscount = request.getParameter("after");

			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<form name ='CheckOut' action='HomeDelivery' method='post'>");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
			pw.print(userName);
			pw.print("</td></tr>");

			pw.print("<tr><td>");
			pw.print("Customer /shipping address</td>");
			pw.print("<td><input type='text' name='shippingaddress' >");
			pw.print("</td></tr>");

			pw.print("<tr><td>");
			pw.print("Credit/accountNo</td>");
			pw.print("<td><input type='text' name='creditCardNo' >");
			pw.print("</td></tr>");

			// OderId i have to find

			int orderId = utility.getOrderPaymentSize() + 1;
			// String category = utility.getProducts();
			pw.print("<tr><td>");
			pw.print("OrderId</td>");
			pw.print("<td>" + orderId + "</td></tr>");
			pw.print("<input type='hidden' name='orderid' value='" + orderId + "'>");

			// 6
			pw.print("<tr><td>");
			pw.print("Purchase Date</td>");
			pw.print("<td><input type='hidden' name='purchasedate' value='" + formattedDate
					+ "'>" + formattedDate + "</td></tr>");
			// 7
			pw.print("<tr><td>");
			pw.print("Shipping Date</td>");
			pw.print("<td><input type='hidden' name='shippingdate' value='" + fiveformattedDate
					+ "'>" + fiveformattedDate + "</td></tr>");

			int quantity = 0;
			// for each order iterate and display the order name price
			for (OrderItem oi : utility.getCustomerOrders()) {
				pw.print("<tr><td> Product Id:</td><td>");
				pw.print(oi.getName() + "</td></tr>");
				pw.print("<tr><td> Category:</td><td>");
				String cat = "";
				if (oi.getCategory().equals("consoles")) {
					cat = "Smart Doorbell";
				} else if (oi.getCategory().equals("tablets")) {
					cat = "Smart DoorLock";
				} else if (oi.getCategory().equals("games")) {
					cat = "Smart Speakers";
				} else if (oi.getCategory().equals("lights")) {
					cat = "Smart Lights";
				} else {
					cat = "Smart Thermostat";
				}

				pw.print(cat + "</td></tr><tr><td>");
				pw.print("<input type='hidden' name='orderPrice' value='" + oi.getPrice() + "'>");
				pw.print("<input type='hidden' name='orderName' value='" + oi.getName() + "'>");
				pw.print("Product Price:</td><td>" + oi.getPrice());
				pw.print("</td></tr>");
				pw.print("<tr><td>");
				pw.print("Discount :</td><td>" + oi.getDiscount());
				pw.print("</td></tr>");
				quantity++;
			}

			pw.print("<tr><td>");
			pw.print("Total Quantity</td>");
			pw.print("<td>" + quantity + "</td></tr>");
			pw.print("<input type='hidden' name='quantity' value='" + quantity + "'>");

			pw.print("<tr><td>");
			pw.print("Total Order Cost</td><td>" + orderTotal);
			pw.print("<input type='hidden' name='orderTotal' value='" + orderTotal + "'>");

			pw.print("<tr><td>");
			pw.print("Ater Discount Order Cost</td><td>" + afterdiscount);
			pw.print("<input type='hidden' name='afterdiscount' value='" + afterdiscount + "'>");
			// pw.print("</td></tr></table><table><tr></tr><tr></tr>");

			pw.print("<tr><td>");
			pw.println("<label for='deliveryType'>Delivery Type:</label></td>");
			pw.println("<td><select id='deliveryType' name='deliveryType'>");
			pw.println("<option value='delivery'>Home Delivery</option>");
			pw.println("<option value='pickup'>In-Store Pickup</option>");
			pw.print("</td></tr>");

			pw.print("<tr><td>");
			pw.print("Customer Zipcode</td>");
			pw.print("<td><input type='text' name='zip'>");
			pw.print("</td></tr>");

			if (session.getAttribute("usertype").equals("retailer")) {
				pw.print("<tr><td>");
				pw.print("Customer Name</td>");
				pw.print("<td><input type='text' name='customername'>");
				pw.print("</td></tr>");
			}

			pw.print("<tr><td colspan='2'>");
			pw.print("<input type='submit' name='Next' class='btnbuy'>");
			pw.print("</td></tr></table></form>");
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");
		} catch (Exception e) {

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	}
}
