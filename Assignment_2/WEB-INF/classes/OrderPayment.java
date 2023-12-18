import java.io.*;

/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable {
	private int orderId;
	private int total;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private String purdate;
	private String shipdate;
	private String deliveryType;
	private String storeAddress;

	public OrderPayment(int orderId, String userName, String orderName, double orderPrice, String userAddress,
			String creditCardNo, String purdate, String shipdate, String deliveryType, String storeAddress) {
		this.orderId = orderId;
		this.userName = userName;
		this.orderName = orderName;
		this.orderPrice = orderPrice;
		this.userAddress = userAddress;
		this.creditCardNo = creditCardNo;
		this.purdate = purdate;
		this.shipdate = shipdate;
		this.deliveryType = deliveryType;
		this.storeAddress = storeAddress;
	}

	public OrderPayment(int orderId, String userName, double orderPrice, String purdate) {
		this.orderId = orderId;
		this.orderName = userName;
		this.orderPrice = orderPrice;
		this.purdate = purdate;
	}

	public OrderPayment(String userName, double orderPrice, int total) {
		this.orderName = userName;
		this.orderPrice = orderPrice;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	// Getter for purdate
	public String getPurdate() {
		return purdate;
	}

	// Setter for purdate
	public void setPurdate(String purdate) {
		this.purdate = purdate;
	}

	// Getter for shipdate
	public String getShipdate() {
		return shipdate;
	}

	// Setter for shipdate
	public void setShipdate(String shipdate) {
		this.shipdate = shipdate;
	}

	// Getter for deliveryType
	public String getDeliveryType() {
		return deliveryType;
	}

	// Setter for deliveryType
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	// Getter for storeAddress
	public String getStoreAddress() {
		return storeAddress;
	}

	// Setter for storeAddress
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
}
