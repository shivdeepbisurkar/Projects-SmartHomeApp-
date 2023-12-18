import java.io.*;

/* 
	Users class contains class variables id,name,password,usertype.

	Users class has a constructor with Arguments name, String password, String usertype.
	  
	Users  class contains getters and setters for id,name,password,usertype.

*/

public class User implements Serializable {
	private int id;
	private String name;
	private String password;
	private String Street;
	private String City;
	private String State;
	private String ZipCode;

	private String usertype;

	public User(String name, String password, String usertype, String Street, String City, String State,
			String ZipCode) {
		this.name = name;
		this.password = password;
		this.usertype = usertype;
		this.Street = Street;
		this.City = City;
		this.State = State;
		this.ZipCode = ZipCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	// Getter for Street
	public String getStreet() {
		return Street;
	}

	// Setter for Street
	public void setStreet(String street) {
		this.Street = street;
	}

	// Getter for City
	public String getCity() {
		return City;
	}

	// Setter for City
	public void setCity(String city) {
		this.City = city;
	}

	// Getter for State
	public String getState() {
		return State;
	}

	// Setter for State
	public void setState(String state) {
		this.State = state;
	}

	// Getter for ZipCode
	public String getZipCode() {
		return ZipCode;
	}
}
