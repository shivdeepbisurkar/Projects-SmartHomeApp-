import java.io.*;

/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable {

	private String productName;
	private String userName;
	private String productType;
	private String price;
	private String storeid;
	private String retailerpin;
	private String retailercity;
	private String storeState;
	private String productOnSale;
	private String productMaker;
	private String manufacturerRebate;
	private String userid;
	private String userage;
	private String usergender;
	private String useroccupation;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;

	public Review(String productName, String userName, String productType, String price, String storeid,
			String retailerpin, String retailercity,
			String storeState, String productOnSale, String productMaker, String manufacturerRebate, String userid,
			String userage, String usergender,
			String useroccupation, String reviewRating, String reviewDate, String reviewText) {
		this.productName = productName;//
		this.userName = userName;//
		this.productType = productType;//
		this.productMaker = productMaker;//
		this.reviewRating = reviewRating;//
		this.reviewDate = reviewDate;//
		this.reviewText = reviewText;//
		this.retailerpin = retailerpin;//
		this.price = price;//
		this.storeid = storeid;//
		this.retailercity = retailercity;
		this.storeState = storeState;
		this.productOnSale = productOnSale;
		this.manufacturerRebate = manufacturerRebate;
		this.userid = userid;
		this.userage = userage;
		this.usergender = usergender;
		this.useroccupation = useroccupation;
	}

	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
		this.productName = productName;
		this.retailerpin = retailerpin;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
	}

	public String getProductName() {
		return productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}

	public String getRetailerCity() {
		return retailercity;
	}

	public void setRetailerCity(String retailercity) {
		this.retailercity = retailercity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStoreid() {
		return storeid;
	}

	public String getRetailercity() {
		return retailercity;
	}

	public String getStoreState() {
		return storeState;
	}

	public String getProductOnSale() {
		return productOnSale;
	}

	public String getManufacturerRebate() {
		return manufacturerRebate;
	}

	public String getUserId() {
		return userid;
	}

	public String getUserage() {
		return userage;
	}

	public String getUserGender() {
		return usergender;
	}

	public String getUserOccupation() {
		return useroccupation;
	}

}
