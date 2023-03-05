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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
public class groceriesShopMain {

	public static void main(String[] args)  throws IOException, ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
		//sql database authentcations
		  String url = "jdbc:sqlserver://localhost:1433;" +
	                "databaseName=groceriesShopInvoicingSystem;" +
	                "encrypt=true;" +
	                "trustServerCertificate=true";
			 System.out.println("enter the user to access the database");
		        String user = scanner.nextLine()  ;
		        System.out.println("enter the user the password");
		        String pass = scanner.nextLine()  ;



		        Connection con = null;
		        
		        try {
		    		String[] invoiceMenuArray= {"chose from the Shop Settings","a. Load Data (Items and invoices)",
		   				 "b. Set Shop Name" , "c. Set Invoice Header(Tel / Fax / Email / Website)"
		   				,"d. search Invoice" , "f. Go Back"};

		   		String[] itemMenuArray = {"chose from the Shop Settings","a. Add Items" , "b. Delete Items", "c. Change Item Price" , "d. Report All Items", "f. go Back"};
		   		
		   		String[] mainMenuArray= {"chose one of the follwing","a) Shop Settings",  "b) Manage Shop Items",  "c) create Invoice" ,"d) create items/invoices statics report",
		   				"e) create All Invoices report" , "f) show Program Statistics","x) exit "};
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
			            con = DriverManager.getConnection(url, user, pass);
			 Statement st = con.createStatement();
		
//			    
//			    String itemsTableStr = "CREATE TABLE items (" +
//			    		 "item_id  INTEGER IDENTITY(1,1) Primary Key, " +
//			    		 " item_name TEXT, " +
//			    		 "item_unit float,"+
//			    		 "item_quantity INTEGER, " +
//			    	       "item_qty INTEGER )" ;
				           
			  //  st.executeUpdate(itemsTableStr);
			    
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
						case "a"://Shop Settings

							boolean settingFlag = true;
							while (settingFlag) {
								shopSettings++;
								menu.setListOfItems(invoiceMenuArray);
					
								String subMenuResponce = scan.nextLine().toLowerCase();

								switch (subMenuResponce) {

								case "a": //Load Data (Items and invoices)
									break;
								case "b":// Set Shop Name
							

									break;
								case "c"://Set Invoice Header(Tel / Fax / Email / Website)

									
									break;

								case "d":// search Invoice

									break;

								case "f": //Go Back

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

								case "a": //Add Items
								//	itemCount++;
									
									
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
									   String sql = "insert into items values ('"+product.getItemName()+"',"
											  + product.getUnitPrice()+","+product.getQuantity()+","+product.getQtyAmount()+")";
st.execute(sql);
									break;
								case "b"://Delete Items
							
									break;
								case "c":// Change Item Price
									
									break;
								case "d"://Report All Items
								
									break;
								case "f": //go Back

									itemsFlag = false;
									break;
								}
								menu.setListOfItems(listOfItems);
							}

							break;
						case "c": //
							createInvoice++;
							//inoviceCount++;
							

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

}
