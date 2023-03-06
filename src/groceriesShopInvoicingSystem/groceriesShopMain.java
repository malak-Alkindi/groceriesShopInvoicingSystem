package groceriesShopInvoicingSystem;

import java.io.IOException;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class groceriesShopMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		// sql database authentcations
		Date n = new Date(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=groceriesShopInvoicingSystem;" + "encrypt=true;"
				+ "trustServerCertificate=true";
//			 System.out.println("enter the user to access the database");
//		        String user = scanner.nextLine()  ;
//		        System.out.println("enter the user the password");
//		        String pass = scanner.nextLine()  ;

		Connection con = null;

		try {
			String[] invoiceMenuArray = { "chose from the Shop Settings", "a. Load Data (Items and invoices)",
					"b. Set Shop Name", "c. add shop", "d. search Invoice",
					"f. Go Back" };

			String[] itemMenuArray = { "chose from the Shop Settings", "a. Add Items", "b. Delete Items",
					"c. Change Item Price", "d. Report All Items", "f. go Back" };

			String[] mainMenuArray = { "chose one of the follwing", "a) Shop Settings", "b) Manage Shop Items",
					"c) create Invoice", "d) create items/invoices statics report", "e) create All Invoices report",
					"f) show Program Statistics", "x) exit " };
			Integer shopSettings;
			Integer manageShopItems;
			Integer createInvoice;
			Integer staticsreport;
			Integer allInvoicesreport;
			Integer showProgramStatistics;

			try {
				shopSettings = Reporting.getProgramStatisticsReport().getShopSettings();
				manageShopItems = Reporting.getProgramStatisticsReport().getManageShopItems();
				createInvoice = Reporting.getProgramStatisticsReport().getCreateInvoice();
				staticsreport = Reporting.getProgramStatisticsReport().getStaticsreport();
				allInvoicesreport = Reporting.getProgramStatisticsReport().getAllInvoicesreport();
				showProgramStatistics = Reporting.getProgramStatisticsReport().getShowProgramStatistics();//
			} catch (Exception error) {
				shopSettings = 0;
				manageShopItems = 0;
				createInvoice = 0;
				staticsreport = 0;
				allInvoicesreport = 0;
				showProgramStatistics = 0;
				error.getMessage();
			}
			// create a new table

			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			// con = DriverManager.getConnection(url, user, pass);
			con = DriverManager.getConnection(url, "sa", "root");
			Statement st = con.createStatement();

//			    
//			    String itemsTableStr = "CREATE TABLE items (" +
//			    		 "item_id  INTEGER IDENTITY(1,1) Primary Key, " +
//			    		 " item_name TEXT, " +
//			    		 "item_unit float,"+
//			    		 "item_quantity INTEGER, " +
//			    	       "item_qty INTEGER )" ;

			// st.executeUpdate(itemsTableStr);

			String invopiceTableStr = "CREATE TABLE invocie  (" + "invoice_id  INTEGER IDENTITY(1,1) Primary Key, "
					+ "customerFullName text," + " phoneNumber Integer," + "	invoiceDate	date ,"
					+ "item_id_fk  INTEGER," + "FOREIGN KEY (item_id_fk) REFERENCES items(item_id) ,"
					+ " numberOfItems Integer," + " totalAmount Integer," + " paidAmount Integer,"
					+ " balance Integer)";
			// st.execute(invopiceTableStr);

			String shopsTableStr = "CREATE TABLE shops  (" + "shop_id  INTEGER IDENTITY(1,1) Primary Key, "
					+ "name text," + "email text," + "website text," + "fax text)";
			// st.execute(shopsTableStr);
			try (Scanner scan = new Scanner(System.in)) {
				Menu menu = new Menu();

				Map<Integer, Product> listOfItems = new HashMap<>();
				Map<Integer, Invoice> mapOfInvoices = new HashMap<>();

				boolean programFlag = true;
				System.out.println("\t\tstarting of shop program");
				menu.setListOfItems(mainMenuArray);
				while (programFlag) {

					String MainMenuResponce = scan.nextLine().toLowerCase();

					switch (MainMenuResponce) {
					case "a":// Shop Settings

						boolean settingFlag = true;
						while (settingFlag) {
							shop shop = new shop();
							shopSettings++;
							menu.setListOfItems(invoiceMenuArray);

							String subMenuResponce = scan.nextLine().toLowerCase();

							switch (subMenuResponce) {

							case "a": // Load Data (Items and invoices)
								System.out.println("\tLoad Data (Items)");

								showItemMenu(con);
								System.out.println("\n\tLoad Data (invoices)");
								showAllInvoices(con);

								break;
							case "b":
								
								showAllshops(con);
								System.out.println("\npls enter Shop id you want to update");
								int id =scan.nextInt();
								
								System.out.println("\n\npls enter the new name");
								
								scan.nextLine();
								String updateShopName = "UPDATE shops SET name = '" + scan.nextLine()
								+ "' WHERE shop_id =" + id;
					
						st.execute(updateShopName);
								break;
							case "c":
								System.out.println("add shop info (name/ Tel / Fax / Email / Website)");
								System.out.println("pls enter Shop Name");
								shop.setName(scan.nextLine());
							
								System.out.println("pls enter Tel ");
								shop.setTel(scan.nextLine());

								System.out.println("pls enter Fax ");
								shop.setFax(scan.nextLine());
								System.out.println("pls enter Email ");
								shop.setEmail(scan.nextLine());
								System.out.println("pls enter Website ");
								shop.setWebsite(scan.nextLine());

								String sql = "insert into shops values ('" + shop.getName() + "','" + shop.getEmail()
										+ "','" + shop.getWebsite() + "','" + shop.getFax()+ "')";
								st.execute(sql);
								break;
							case "d":// search Invoice

								System.out.println("enter the invoice id you want to search");
								String searchInvo = "select * from invocie where invoice_id =" + scan.nextInt();

								scan.nextLine();

								ResultSet resultSet = st.executeQuery(searchInvo);

								while (resultSet.next()) {
									System.out.println("\ninvoice_id: " + resultSet.getInt("invoice_id")
											+ " |customerFullName:  " + resultSet.getString("customerFullName")
											+ " |phoneNumber:  " + resultSet.getString("phoneNumber")
											+ " |invoiceDate:  " + resultSet.getDate("invoiceDate") + " |item_id_fk: "
											+ resultSet.getInt("item_id_fk") + " |numberOfItems: "
											+ resultSet.getInt("numberOfItems") + " |totalAmount: "
											+ resultSet.getInt("totalAmount") + " |paidAmount: "
											+ resultSet.getInt("paidAmount") + " |balance: "
											+ resultSet.getInt("balance") + "\n\n");

								}
								;
								break;

							case "f": // Go Back

								settingFlag = false;
								break;
							}
						}
						break;
					case "b":
						manageShopItems++;
						boolean itemsFlag = true;
						while (itemsFlag) {

							menu.setListOfItems(itemMenuArray);
							String subMenuResponce = scan.nextLine().toLowerCase();

							switch (subMenuResponce) {

							case "a": // Add Items
								// itemCount++;

								System.out.println("Add Items");
								Product product = new Product();

								System.out.println("what is the item name");
								product.setItemName(scan.nextLine());
								System.out.println("what is the item unit price");
								product.setUnitPrice(scan.nextFloat());
								System.out.println("what is the item quantity");
								product.setQuantity(scan.nextInt());
								scan.nextLine();
								System.out.println("what is the item qtyAmount");
								product.setQtyAmount(scan.nextInt());
								System.out.println(product.getItemName());

								scan.nextLine();
								String sql = "insert into items values ('" + product.getItemName() + "',"
										+ product.getUnitPrice() + "," + product.getQuantity() + ","
										+ product.getQtyAmount() + ")";
								st.execute(sql);

								break;
							case "b":// Delete Items

								showItemMenu(con);
								System.out.println("\n\nselect the id you want to delete from above list:");
								String deleteItemSTR = "DELETE FROM items WHERE item_id=" + scan.nextInt();
								scan.nextLine();
								st.executeUpdate(deleteItemSTR);
								break;
							case "c":// Change Item Price
								showItemMenu(con);
								System.out.println("\n\nselect the id you want to change it is price :");
								int id = scan.nextInt();
								System.out.println("\n\nenter the new price :");
								String updatePrice = "UPDATE items SET item_unit = " + scan.nextInt()
										+ "WHERE item_id =" + id;
								scan.nextLine();
								st.execute(updatePrice);
								break;
							case "d":// Report All Items
								showItemMenu(con);
								break;
							case "f": // go Back

								itemsFlag = false;
								break;
							}
							menu.setListOfItems(listOfItems);
						}

						break;
					case "c": //
						createInvoice++;
						// inoviceCount++;
						int totalAmount = 0;
						Invoice invoice = new Invoice();
						boolean purchaseFlag = true;
						ArrayList<Product> listOfPurchaseItems = new ArrayList<>();
						showItemMenu(con);
						System.out.println("\n\n enter the item id , customer purchase from above list of items ");

						while (purchaseFlag) {

							ResultSet resultSet = st
									.executeQuery("Select * from items where item_id =" + scan.nextInt());
							scan.nextLine();
							while (resultSet.next()) {

								Product p = new Product();

								p.setItemID(resultSet.getInt("item_id"));
								p.setItemName(resultSet.getString("item_name"));
								p.setUnitPrice(resultSet.getFloat("item_unit"));
								listOfPurchaseItems.add(p);

							}
							;
							System.out.println("do you want to add another purchase? yes/no");
							if (scan.nextLine().equals("no")) {
								for (Product p : listOfPurchaseItems) {
									totalAmount += p.getUnitPrice();
									System.out.println("the purches");
									System.out.println(p.getItemID() + " " + p.getItemName());
								}
								purchaseFlag = false;
							} else {
								System.out.println("enter the item id , customer purchase from above list of items ");
							}

						}

						invoice.setListOfPurchaseItems(listOfPurchaseItems);
						System.out.println("what is the customer Full Name ?");
						invoice.setCustomerFullName(scan.nextLine());
						System.out.println("what is the customer phone Number ?");
						invoice.setPhoneNumber(scan.nextInt());
						scan.nextLine();
						DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDateTime now = LocalDateTime.now();
						String formatDateTime = now.format(format);
						invoice.setInvoiceDate(formatDateTime);
						invoice.setNumberOfItems(listOfPurchaseItems.size());
						invoice.setTotalAmount(totalAmount);
						System.out.println("what is the customer paid Amount ? total amount\t:" + totalAmount);
						int paidAmount = scan.nextInt();
						while (paidAmount < totalAmount) {

							System.out.println("the customer paid less Amount then the total amount\nleft to pay\t:"
									+ (totalAmount - paidAmount) + "\n ");
							paidAmount = scan.nextInt();
						}

						invoice.setPaidAmount(paidAmount);
						scan.nextLine();
						invoice.setBalance(invoice.getPaidAmount() - totalAmount);

						for (Product p : listOfPurchaseItems) {

							String sql = "insert into  invocie values('" + invoice.getCustomerFullName() + "',"
									+ invoice.getPhoneNumber() + ",'"

									+ invoice.getInvoiceDate() + "'," + p.getItemID() + "," + invoice.getNumberOfItems()
									+ "," + invoice.getTotalAmount() + "," + invoice.getPaidAmount() + ","
									+ invoice.getBalance() + ")";
							st.execute(sql);
						}

						// mapOfInvoices.put(inoviceCount, invoice);
						// Reporting.createInvoiceReport(invoice);
						// menu.setMapOfInvoices(mapOfInvoices);
						System.out.println("invoice created secufully");
						break;

					case "d"://
						staticsreport++;

						break;

					case "e":

						break;

					case "f":
						showProgramStatistics++;

						break;
					}

					menu.setListOfItems(mainMenuArray);

				}
			} catch (Exception error) {

				System.out.println(error.getMessage());
			}
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	// showAllInvoices(con)
	// showItemMenu(con)

	static void showItemMenu(Connection con) throws SQLException {

		Statement st = con.createStatement();

		ResultSet resultSet = st.executeQuery("Select * from items");

		while (resultSet.next()) {
			System.out.println(
					"item id: " + resultSet.getInt("item_id") + " | item name:  " + resultSet.getString("item_name")
							+ " | unit price:  " + resultSet.getFloat("item_unit") + " | quantity:  "
							+ resultSet.getInt("item_quantity") + " | qtyAmount: " + resultSet.getInt("item_qty"));

		}
		;

	}

	static void showAllInvoices(Connection con) throws SQLException {

		Statement st = con.createStatement();

		ResultSet resultSet = st.executeQuery("Select * from invocie");

		while (resultSet.next()) {
			System.out.println("invoice_id: " + resultSet.getInt("invoice_id") + " |customerFullName:  "
					+ resultSet.getString("customerFullName") + " |phoneNumber:  " + resultSet.getString("phoneNumber")
					+ " |invoiceDate:  " + resultSet.getDate("invoiceDate") + " |item_id_fk: "
					+ resultSet.getInt("item_id_fk") + " |numberOfItems: " + resultSet.getInt("numberOfItems")
					+ " |totalAmount: " + resultSet.getInt("totalAmount") + " |paidAmount: "
					+ resultSet.getInt("paidAmount") + " |balance: " + resultSet.getInt("balance"));

		}
		;
	}
	
	String shopsTableStr = "CREATE TABLE shops  (" + "shop_id  INTEGER IDENTITY(1,1) Primary Key, "
			+ "name text," + "email text," + "website text," + "fax text)";
	static void showAllshops(Connection con) throws SQLException {

		Statement st = con.createStatement();

		ResultSet resultSet = st.executeQuery("Select * from shops");

		while (resultSet.next()) {
			System.out.println("shop_id: " + resultSet.getInt("shop_id") +" "
					+"|\tname: "+ resultSet.getString("name") +" "
					+"|\temail: "+ resultSet.getString("email")+" "
					+"|\twebsite: "+ resultSet.getString("website")+" "
					+"|\tfax: "+ resultSet.getString("fax"));

		}
		;
	}
}
