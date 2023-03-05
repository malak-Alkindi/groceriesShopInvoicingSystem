package groceriesShopInvoicingSystem;

import java.util.HashMap;
import java.util.Map;

public class Menu {
	  Map<Integer, Invoice> mapOfInvoices ;
		 Map<Integer, Product> listOfItems  ;
		  

		

		public HashMap<Integer, Invoice> getMapOfInvoices() {
			return (HashMap<Integer, Invoice>) mapOfInvoices;
		}

		
		public void setMapOfInvoices(Map<Integer, Invoice> mapOfInvoices2) {
			this.mapOfInvoices = mapOfInvoices2;
		}


		public HashMap<Integer, Product> getListOfItems() {
			return (HashMap<Integer, Product>) listOfItems;
		}


		public void setListOfItems(Map<Integer, Product> listOfItems2) {
			this.listOfItems = listOfItems2;
		}
		public void setListOfItems(String arr[]) {
	for(String a:arr) {
	System.out.println(a +"\n");	
	}
		}


	}

