import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrderItem")

/*
 * OrderItem class contains class variables name,price,image,retailer.
 * 
 * OrderItem class has a constructor with Arguments name,price,image,retailer.
 * 
 * OrderItem class contains getters and setters for name,price,image,retailer.
 */

public class OrderItem extends HttpServlet {
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String category;
	private String discount;
	private int id;

	public OrderItem(String name, double price, String image, String retailer, String category, String discount) {
		this.name = name;
		this.price = price;
		this.image = image;
		this.retailer = retailer;
		this.category = category;
		this.discount = discount;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
