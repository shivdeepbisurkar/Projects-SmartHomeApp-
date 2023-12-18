import java.sql.*;
import java.util.*;

public class MySqlDataStoreUtilities {
	static Connection conn = null;
	static String message;

	public static String getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/AssignTwo", "root", "Parshwa@3103");
			message = "Successfull";
			return message;
		} catch (SQLException e) {
			message = "unsuccessful";
			return message;
		} catch (Exception e) {
			message = e.getMessage();
			return message;
		}
	}

	public static void Insertproducts() {
		try {

			getConnection();

			String truncatetableacc = "delete from Product_accessories;";
			PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
			pstt.executeUpdate();

			String truncatetableprod = "delete from  Productdetails;";
			PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
			psttprod.executeUpdate();

			String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,count,rebat)"
					+
					"VALUES (?,?,?,?,?,?,?,?,?,?);";
			for (Map.Entry<String, Accessory> entry : SaxParserDataStore.accessories.entrySet()) {
				String name = "accessories";
				Accessory acc = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1, name);
				pst.setString(2, acc.getId());
				pst.setString(3, acc.getName());
				pst.setDouble(4, acc.getPrice());
				pst.setString(5, acc.getImage());
				pst.setString(6, acc.getRetailer());
				pst.setString(7, acc.getCondition());
				pst.setDouble(8, acc.getDiscount());
				pst.setDouble(9, 5.0);
				pst.setString(10, "Yes");
				pst.executeUpdate();

			}

			for (Map.Entry<String, Console> entry : SaxParserDataStore.consoles.entrySet()) {
				Console con = entry.getValue();
				String name = "consoles";

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1, name);
				pst.setString(2, con.getId());
				pst.setString(3, con.getName());
				pst.setDouble(4, con.getPrice());
				pst.setString(5, con.getImage());
				pst.setString(6, con.getRetailer());
				pst.setString(7, con.getCondition());
				pst.setDouble(8, con.getDiscount());
				pst.setDouble(9, 1.0);
				pst.setString(10, "Yes");
				pst.executeUpdate();
				try {
					HashMap<String, String> acc = con.getAccessories();
					String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
							"VALUES (?,?);";
					for (Map.Entry<String, String> accentry : acc.entrySet()) {
						PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
						pstacc.setString(1, con.getId());
						pstacc.setString(2, accentry.getValue());
						pstacc.executeUpdate();
					}
				} catch (Exception et) {
					et.printStackTrace();
				}
			}
			for (Map.Entry<String, Game> entry : SaxParserDataStore.games.entrySet()) {
				String name = "games";
				Game game = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1, name);
				pst.setString(2, game.getId());
				pst.setString(3, game.getName());
				pst.setDouble(4, game.getPrice());
				pst.setString(5, game.getImage());
				pst.setString(6, game.getRetailer());
				pst.setString(7, game.getCondition());
				pst.setDouble(8, game.getDiscount());
				pst.setDouble(9, 3.0);
				pst.setString(10, "Yes");

				pst.executeUpdate();

			}
			for (Map.Entry<String, Tablet> entry : SaxParserDataStore.tablets.entrySet()) {
				String name = "tablets";
				Tablet tablet = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1, name);
				pst.setString(2, tablet.getId());
				pst.setString(3, tablet.getName());
				pst.setDouble(4, tablet.getPrice());
				pst.setString(5, tablet.getImage());
				pst.setString(6, tablet.getRetailer());
				pst.setString(7, tablet.getCondition());
				pst.setDouble(8, tablet.getDiscount());
				pst.setDouble(9, 4.0);
				pst.setString(10, "No");
				pst.executeUpdate();

			}

			for (Map.Entry<String, Light> entry : SaxParserDataStore.lights.entrySet()) {
				String name = "lights";
				Light light = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1, name);
				pst.setString(2, light.getId());
				pst.setString(3, light.getName());
				pst.setDouble(4, light.getPrice());
				pst.setString(5, light.getImage());
				pst.setString(6, light.getRetailer());
				pst.setString(7, light.getCondition());
				pst.setDouble(8, light.getDiscount());
				pst.setDouble(9, 5.0);
				pst.setString(10, "Yes");
				pst.executeUpdate();

			}

			for (Map.Entry<String, Thermo> entry : SaxParserDataStore.thermos.entrySet()) {
				String name = "thermos";
				Thermo thermo = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1, name);
				pst.setString(2, thermo.getId());
				pst.setString(3, thermo.getName());
				pst.setDouble(4, thermo.getPrice());
				pst.setString(5, thermo.getImage());
				pst.setString(6, thermo.getRetailer());
				pst.setString(7, thermo.getCondition());
				pst.setDouble(8, thermo.getDiscount());
				pst.setDouble(9, 1.0);
				pst.setString(10, "No");
				pst.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, Console> getConsoles() {
		HashMap<String, Console> hm = new HashMap<String, Console>();
		try {
			getConnection();

			String selectConsole = "select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectConsole);
			pst.setString(1, "consoles");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Console con = new Console(rs.getString("productName"), rs.getDouble("productPrice"),
						rs.getString("productImage"), rs.getString("productManufacturer"),
						rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));

				try {
					String selectaccessory = "Select * from Product_accessories where productName=?";
					PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
					pstacc.setString(1, rs.getString("Id"));
					ResultSet rsacc = pstacc.executeQuery();

					HashMap<String, String> acchashmap = new HashMap<String, String>();
					while (rsacc.next()) {
						if (rsacc.getString("accessoriesName") != null) {

							acchashmap.put(rsacc.getString("accessoriesName"), rsacc.getString("accessoriesName"));
							con.setAccessories(acchashmap);
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static HashMap<String, Tablet> getTablets() {
		HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
		try {
			getConnection();

			String selectTablet = "select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectTablet);
			pst.setString(1, "tablets");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Tablet tab = new Tablet(rs.getString("productName"), rs.getDouble("productPrice"),
						rs.getString("productImage"), rs.getString("productManufacturer"),
						rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), tab);
				tab.setId(rs.getString("Id"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static HashMap<String, Light> getLights() {
		HashMap<String, Light> hm = new HashMap<String, Light>();
		try {
			getConnection();

			String selectLight = "select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectLight);
			pst.setString(1, "lights");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Light light = new Light(rs.getString("productName"), rs.getDouble("productPrice"),
						rs.getString("productImage"), rs.getString("productManufacturer"),
						rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), light);
				light.setId(rs.getString("Id"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static HashMap<String, Thermo> getThermos() {
		HashMap<String, Thermo> hm = new HashMap<String, Thermo>();
		try {
			getConnection();

			String selectLight = "select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectLight);
			pst.setString(1, "thermos");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Thermo thermo = new Thermo(rs.getString("productName"), rs.getDouble("productPrice"),
						rs.getString("productImage"), rs.getString("productManufacturer"),
						rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), thermo);
				thermo.setId(rs.getString("Id"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static HashMap<String, Game> getGames() {
		HashMap<String, Game> hm = new HashMap<String, Game>();
		try {
			getConnection();

			String selectGame = "select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectGame);
			pst.setString(1, "games");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Game game = new Game(rs.getString("productName"), rs.getDouble("productPrice"),
						rs.getString("productImage"), rs.getString("productManufacturer"),
						rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), game);
				game.setId(rs.getString("Id"));
			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static HashMap<String, Accessory> getAccessories() {
		HashMap<String, Accessory> hm = new HashMap<String, Accessory>();
		try {
			getConnection();

			String selectAcc = "select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectAcc);
			pst.setString(1, "accessories");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Accessory acc = new Accessory(rs.getString("productName"), rs.getDouble("productPrice"),
						rs.getString("productImage"), rs.getString("productManufacturer"),
						rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static String addproducts(String producttype, String productId, String productName, double productPrice,
			String productImage, String productManufacturer, String productCondition, double productDiscount,
			String prod, double count, String rebat) {
		String msg = "Product is added successfully";
		try {

			getConnection();
			String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,count,rebat)"
					+
					"VALUES (?,?,?,?,?,?,?,?,?,?);";

			String name = producttype;

			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1, name);
			pst.setString(2, productId);
			pst.setString(3, productName);
			pst.setDouble(4, productPrice);
			pst.setString(5, productImage);
			pst.setString(6, productManufacturer);
			pst.setString(7, productCondition);
			pst.setDouble(8, productDiscount);
			pst.setDouble(9, count);
			pst.setString(10, rebat);
			pst.executeUpdate();
			try {
				if (!prod.isEmpty()) {
					String addaprodacc = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
							"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1, prod);
					pst1.setString(2, productId);
					pst1.executeUpdate();

				}
			} catch (Exception e) {
				msg = "Erro while adding the product";
				e.printStackTrace();

			}

		} catch (Exception e) {
			msg = "Erro while adding the product";
			e.printStackTrace();

		}
		return msg;
	}

	public static String updateproducts(String producttype, String productId, String productName, double productPrice,
			String productImage, String productManufacturer, String productCondition, double productDiscount,
			double count, String rebat) {
		String msg = "Product is updated successfully";
		try {

			getConnection();
			String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=?,count=?,rebat=? where Id =?;";

			PreparedStatement pst = conn.prepareStatement(updateProductQurey);

			pst.setString(1, productName);
			pst.setDouble(2, productPrice);
			pst.setString(3, productImage);
			pst.setString(4, productManufacturer);
			pst.setString(5, productCondition);
			pst.setDouble(6, productDiscount);
			pst.setDouble(7, count);
			pst.setString(8, rebat);
			pst.setString(9, productId);

			pst.executeUpdate();

		} catch (Exception e) {
			msg = "Product cannot be updated";
			e.printStackTrace();

		}
		return msg;
	}

	public static String deleteproducts(String productId) {
		String msg = "Product is deleted successfully";
		try {

			getConnection();
			String deleteproductsQuery = "Delete from Productdetails where Id=?";
			PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
			pst.setString(1, productId);

			pst.executeUpdate();
		} catch (Exception e) {
			msg = "Proudct cannot be deleted";
		}
		return msg;
	}

	public static void deleteOrder(int orderId, String orderName) {
		try {

			getConnection();
			String deleteOrderQuery = "Delete from customerorders where OrderId=? and orderName=?";
			PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
			pst.setInt(1, orderId);
			pst.setString(2, orderName);
			pst.executeUpdate();
		} catch (Exception e) {

		}
	}

	public static void insertOrder(int orderId, String userName, String orderName, double orderPrice,
			String userAddress, String creditCardNo, String purchasedate, String shippingdate, String quantity,
			String deliveryType, String zip,
			String address) {
		try {

			getConnection();

			String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo,purdate,shipdate,quantity,deliveryType,zip,storeAddress) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
			// set the parameter for each column and execute the prepared statement
			pst.setInt(1, orderId);
			pst.setString(2, userName);
			pst.setString(3, orderName);
			pst.setDouble(4, orderPrice);
			pst.setString(5, userAddress);
			pst.setString(6, creditCardNo);
			pst.setString(7, purchasedate);
			pst.setString(8, shippingdate);
			pst.setString(9, quantity);
			pst.setString(10, deliveryType);
			pst.setString(11, zip);
			pst.setString(12, address);
			// pst.setString(7, purchasedate);
			pst.execute();
		} catch (Exception e) {

		}
	}

	/* getAllproduct on sale */
	public static HashMap<String, Product> getOnSale() {
		HashMap<String, Product> hm = new HashMap<String, Product>();
		try {
			getConnection();

			String selectTablet = "select * from  productdetails Where productDiscount>0";
			PreparedStatement pst = conn.prepareStatement(selectTablet);

			// pst.setString(1, ZipCode);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product instore = new Product(rs.getString("ID"), rs.getString("productName"),
						rs.getDouble("productPrice"), rs.getDouble("count"));
				hm.put(rs.getString("ID"), instore);
				// instore.setId(rs.getString("StoreID"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	/* getAllproduct on rebat */
	public static HashMap<String, Product> getOnRabet() {
		HashMap<String, Product> hm = new HashMap<String, Product>();
		try {
			getConnection();

			String selectTablet = "select * from  productdetails Where rebat='Yes'";
			PreparedStatement pst = conn.prepareStatement(selectTablet);

			// pst.setString(1, ZipCode);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product instore = new Product(rs.getString("ID"), rs.getString("productName"),
						rs.getDouble("productPrice"), rs.getDouble("count"));
				hm.put(rs.getString("ID"), instore);
				// instore.setId(rs.getString("StoreID"));

			}
		} catch (Exception e) {
		}
		return hm;
	}
	/* getproduct from database */

	public static HashMap<String, Product> getAllproduct() {
		HashMap<String, Product> hm = new HashMap<String, Product>();
		try {
			getConnection();

			String selectTablet = "select * from  productdetails";
			PreparedStatement pst = conn.prepareStatement(selectTablet);

			// pst.setString(1, ZipCode);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product instore = new Product(rs.getString("ID"), rs.getString("productName"),
						rs.getDouble("productPrice"), rs.getDouble("count"));
				hm.put(rs.getString("ID"), instore);
				// instore.setId(rs.getString("StoreID"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	/* getproduct end here */

	/* getSales from database */

	public static HashMap<String, OrderPayment> getSaleInfo() {
		HashMap<String, OrderPayment> hm = new HashMap<String, OrderPayment>();
		try {
			getConnection();

			String selectTablet = "SELECT OrderId,orderName,orderPrice,purdate FROM CustomerOrders;";
			PreparedStatement pst = conn.prepareStatement(selectTablet);

			// pst.setString(1, ZipCode);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				OrderPayment instore = new OrderPayment(rs.getInt("OrderId"), rs.getString("orderName"),
						rs.getDouble("orderPrice"),rs.getString("purdate"));
				hm.put(rs.getString("orderId"), instore);
				// instore.setId(rs.getString("StoreID"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

	/* getproduct end here */

	public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {

		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

		try {

			getConnection();
			// select the table
			String selectOrderQuery = "select * from customerorders";
			PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
			ResultSet rs = pst.executeQuery();
			ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
			while (rs.next()) {
				if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(rs.getInt("orderId"), arr);
				}
				ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));

				// add to orderpayment hashmap
				OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"),
						rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("userAddress"),
						rs.getString("creditCardNo"), rs.getString("purdate"), rs.getString("shipdate"),
						rs.getString("deliveryType"), rs.getString("storeAddress"));
				listOrderPayment.add(order);

			}

		} catch (Exception e) {

		}
		return orderPayments;
	}

	public static void insertUser(String username, String password, String repassword, String usertype, String Street,
			String City, String State,
			String ZipCode) {
		try {

			getConnection();
			String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype,Street,City,State,zip) "
					+ "VALUES (?,?,?,?,?,?,?,?);";

			PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, repassword);
			pst.setString(4, usertype);
			pst.setString(5, Street);
			pst.setString(6, City);
			pst.setString(7, State);
			pst.setString(8, ZipCode);
			pst.execute();
		} catch (Exception e) {

		}
	}

	public static HashMap<String, User> selectUser() {
		HashMap<String, User> hm = new HashMap<String, User>();
		try {
			getConnection();
			Statement stmt = conn.createStatement();
			String selectCustomerQuery = "select * from  Registration";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while (rs.next()) {
				User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"),
						rs.getString("Street"), rs.getString("City"), rs.getString("State"), rs.getString("zip"));
				hm.put(rs.getString("username"), user);
			}
		} catch (Exception e) {
		}
		return hm;
	}

	public static HashMap<String, StoreAdd> selectStore(String ZipCode) {
		HashMap<String, StoreAdd> hm = new HashMap<String, StoreAdd>();
		try {
			getConnection();

			String selectTablet = "select * from  StoreLocations where ZipCode=? ";
			PreparedStatement pst = conn.prepareStatement(selectTablet);

			pst.setString(1, ZipCode);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				StoreAdd instore = new StoreAdd(rs.getString("StoreID"), rs.getString("Street"), rs.getString("City"),
						rs.getString("State"), rs.getString("ZipCode"));
				hm.put(rs.getString("StoreID"), instore);
				// instore.setId(rs.getString("StoreID"));

			}
		} catch (Exception e) {
		}
		return hm;
	}

}