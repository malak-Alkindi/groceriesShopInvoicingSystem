package groceriesShopInvoicingSystem;

public class ProgramStatistics {

//	"chose one of the follwing :\n  "
//	+ "a) Shop Settings\n  "
//	+ "b) Manage Shop Items  \n "
//	+ " c)"
//	+ " create Invoice \n  "
//	+ "d) create items/invoices statics report \n "
//	+ " e) create All Invoices report \n "
//	+ " f) show Program Statistics \n  "
//	+ "x) exit 
	
	
	private int ShopSettings;
	private int ManageShopItems;
	private int createInvoice ;
	private int staticsreport;
	private int  AllInvoicesreport;
	private int  showProgramStatistics;

	public int getShopSettings() {
		return ShopSettings;
	}
	
	public void setShopSettings(int shopSettings) {
		ShopSettings = shopSettings;
	}
	
	public int getManageShopItems() {
		return ManageShopItems;
	}
	
	public void setManageShopItems(int manageShopItems) {
		ManageShopItems = manageShopItems;
	}

	public int getCreateInvoice() {
		return createInvoice;
	}

	public void setCreateInvoice(int createInvoice) {
		this.createInvoice = createInvoice;
	}
	
	public int getStaticsreport() {
		return staticsreport;
	}
	
	public void setStaticsreport(int staticsreport) {
		this.staticsreport = staticsreport;
	}
	
	public int getAllInvoicesreport() {
		return AllInvoicesreport;
	}
	
	public void setAllInvoicesreport(int allInvoicesreport) {
		AllInvoicesreport = allInvoicesreport;
	}
	
	public int getShowProgramStatistics() {
		return showProgramStatistics;
	}
	
	public void setShowProgramStatistics(int showProgramStatistics) {
		this.showProgramStatistics = showProgramStatistics;
	}
	
	
	

}
