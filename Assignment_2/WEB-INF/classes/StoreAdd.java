import java.io.*;

/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class StoreAdd implements Serializable {
    private String storeId;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    // private String creditCardNo;

    public StoreAdd(String storeId, String street, String city, String state, String zipcode) {
        this.storeId = storeId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    // Getter methods
    public String getStoreId() {
        return storeId;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    // Setter methods
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
