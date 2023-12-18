
import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		review(request, response);
	}

	protected void review(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			if (!utility.isLoggedin()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login to view Review");
				response.sendRedirect("Login");
				return;
			}
			String productName = request.getParameter("name");
			HashMap<String, ArrayList<Review>> hm = MongoDBDataStoreUtilities.selectReview();
			String userName = "";
			String reviewRating = "";
			String reviewDate;
			String reviewText = "";
			String price = "";
			String city = "";
			//
			String productType = "";
			String storeid = "";
			String retailerpin = "";
			String retailercity = "";
			String storeState = "";
			String productOnSale = "";
			String productMaker = "";
			String manufacturerRebate = "";
			String userid = "";
			String userage = "";
			String usergender = "";
			String useroccupation = "";

			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");

			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Review</a>");
			pw.print("</h2><div class='entry'>");

			// if there are no reviews for product print no review else iterate over all the
			// reviews using cursor and print the reviews in a table
			if (hm == null) {
				pw.println("<h2>Mongo Db server is not up and running</h2>");
			} else {
				if (!hm.containsKey(productName)) {
					pw.println("<h2>There are no reviews for this product.</h2>");
				} else {
					for (Review r : hm.get(productName)) {
						pw.print("<table class='gridtable'>");
						pw.print("<tr>");
						pw.print("<td> Product Model Name: </td>");
						productName = r.getProductName();
						pw.print("<td>" + productName + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> Product Category: </td>");
						productType = r.getProductType();
						pw.print("<td>" + productType + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> Product Price: </td>");
						price = r.getPrice();
						pw.print("<td>" + price + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> StoreID: </td>");
						storeid = r.getStoreid();
						pw.print("<td>" + storeid + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> Store Zip: </td>");
						storeid = r.getRetailerPin();
						pw.print("<td>" + storeid + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> Store City: </td>");
						city = r.getRetailerCity();
						pw.print("<td>" + city + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> Store State: </td>");
						storeState = r.getStoreState();
						pw.print("<td>" + storeState + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> Product On Sale: </td>");
						productOnSale = r.getProductOnSale();
						pw.print("<td>" + productOnSale + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> ManufacturerName : </td>");
						productMaker = r.getProductMaker();
						pw.print("<td>" + productMaker + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> ManufacturerRebate : </td>");
						manufacturerRebate = r.getManufacturerRebate();
						pw.print("<td>" + manufacturerRebate + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> UserID: </td>");
						userName = r.getUserName();
						pw.print("<td>" + userName + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> UserAge: </td>");
						userage = r.getUserage();
						pw.print("<td>" + userage + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> UserGender: </td>");
						usergender = r.getUserGender();
						pw.print("<td>" + usergender + "</td>");
						pw.print("</tr>");

						pw.print("<tr>");
						pw.print("<td> UserOccupation: </td>");
						useroccupation = r.getUserOccupation();
						pw.print("<td>" + useroccupation + "</td>");
						pw.print("</tr>");

						pw.println("<tr>");
						pw.println("<td> Review Rating: </td>");
						reviewRating = r.getReviewRating().toString();
						pw.print("<td>" + reviewRating + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<td> Review Date: </td>");
						reviewDate = r.getReviewDate().toString();
						pw.print("<td>" + reviewDate + "</td>");
						pw.print("</tr>");
						pw.print("<tr>");
						pw.print("<td> Review Text: </td>");
						reviewText = r.getReviewText();
						pw.print("<td>" + reviewText + "</td>");
						pw.print("</tr>");
						pw.print("<tr></tr>");
						pw.print("<tr></tr>");
						pw.println("</table>");
					}

				}
			}
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

	}
}
