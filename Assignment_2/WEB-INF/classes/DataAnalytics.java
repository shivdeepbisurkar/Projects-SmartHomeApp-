import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;
import javax.servlet.http.HttpSession;

@WebServlet("/DataAnalytics")

public class DataAnalytics extends HttpServlet {
	static DBCollection myReviews;
	/*
	 * Trending Page Displays all the Consoles and their Information in Game Speed
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		// check if the user is logged in
		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Data Analytics on Review</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table id='bestseller'>");
		pw.print("<form method='post' action='FindReviews'>");

		pw.print("<table id='bestseller'>");
		pw.print("<tr>");
		pw.print("<td> <input type='checkbox' name='queryCheckBox' value='ProductName'> Select </td>");
		pw.print("<td> Product Name: </td>");
		pw.print("<td>");
		pw.print("<select name='ProductName'>");
		pw.print("<option value='ALL_PRODUCTS'>All Products</option>");
		pw.print("<option value='Eufy'>Eufy</option>");
		pw.print("<option value='eufy_2K'>eufy_2K</option>");
		pw.print("<option value='GoogleNest'>GoogleNest</option>");
		pw.print(" <option value='Google Nest Hello'>Google Nest Hello</option>");
		pw.print("<option value='Blink'>Blink</option>");

		pw.print("<option value='Kwikset'>Kwikset</option>");
		pw.print("<option value='Kwikset_2'>Kwikset_2</option>");
		pw.print("<option value='Linnea'>Linnea</option>");
		pw.print("<option value='Linnea_2'>Linnea_2</option>");
		pw.print("<option value='Yale'>Yale</option>");
		pw.print("<option value='Yale_2'>Yale_2</option>");
		pw.print("<option value='Apple Homepad'>Apple Homepad</option>");
		pw.print("<option value='Apple Mini'>Apple Mini</option>");
		pw.print("<option value='Bose'>Bose</option>");
		pw.print("<option value='Bose_1'>Bose_1</option>");
		pw.print("<option value='Echo'>Echo</option>");
		pw.print("<option value='Echo_1'>Echo_1</option>");
		pw.print("<option value='Dals'>Dals</option>");
		pw.print("<option value='Dals_2'>Dals_2</option>");
		pw.print("<option value='Smart_2'>Smart_2</option>");
		pw.print("<option value='Smart-1'>Smart-1</option>");
		pw.print("<option value='pk_green'>pk_green</option>");
		pw.print("<option value='pk_green_2'>pk_green_2</option>");
		pw.print("<option value='Amazon'>Amazon</option>");
		pw.print("<option value='Amazon_2'>Amazon_2</option>");
		pw.print("<option value='Google_nest'>Google_nest</option>");
		pw.print("<option value='Google_nest_2'>Google_nest_2</option>");

		pw.print("</td>");
		pw.print("<tr>");
		pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>");
		pw.print("<td> Product Price: </td>");
		pw.print(" <td>");
		pw.print("  <input type='number' name='productPrice' value = '0' size=10  /> </td>");
		pw.print("<td>");
		pw.print("<input type='radio' name='comparePrice' value='EQUALS_TO' checked> Equals <br>");
		pw.print("<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>");
		pw.print("<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than");
		pw.print("</td></tr>");

		pw.print("<tr><td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>");
		pw.print(" <td> Review Rating: </td>");
		pw.print(" <td>");
		pw.print(" <select name='reviewRating'>");
		pw.print(" <option value='1' selected>1</option>");
		pw.print(" <option value='2'>2</option>");
		pw.print(" <option value='3'>3</option>");
		pw.print("   <option value='4'>4</option>");
		pw.print("  <option value='5'>5</option>");
		pw.print("</td>");
		pw.print("<td>");
		pw.print("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
		pw.print("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than");
		pw.print("</td></tr>");

		pw.print("<tr>");
		pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerCity'> Select </td>");
		pw.print("<td> Retailer City: </td>");
		pw.print("<td>");
		pw.print("<input type='text' name='retailerCity' /> </td>");

		pw.print("</tr>");

		pw.print("<tr>");
		pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerZipcode'> Select </td>");
		pw.print(" <td> Retailer Zip code: </td>");
		pw.print(" <td>");
		pw.print("<input type='text' name='retailerZipcode' /> </td>");
		pw.print("</tr>");
		pw.print("<tr><td>");
		pw.print("<input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By");
		pw.print("</td>");
		pw.print("<td>");
		pw.print("<select name='groupByDropdown'>");
		pw.print("<option value='GROUP_BY_CITY' selected>City</option>");
		pw.print("<option value='GROUP_BY_PRODUCT'>Product Name</option>");
		pw.print("</td><td>");
		pw.print("<input type='radio' name='dataGroupBy' value='Count' checked> Count <br>");
		pw.print("<input type='radio' name='dataGroupBy' value='Detail'> Detail <br>");
		pw.print("</td></tr>");

		pw.print("<tr>");
		pw.print("<td colspan = '4'> <input type='submit' value='Find Data' class='btnbuy' /> </td>");
		pw.print("</tr>");

		pw.print("</table>");
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
