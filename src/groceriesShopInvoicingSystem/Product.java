package groceriesShopInvoicingSystem;

public class Product {

	private	String itemName; 
	private	float unitPrice;
	private	Integer quantity ; 
	private	Integer qtyAmount;
	private Integer itemID;
	private Integer shopId;
	
	
	
	/**
	 * @return the shopId
	 */
	public Integer getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getQtyAmount() {
		return qtyAmount;
	}
	
	public void setQtyAmount(Integer qtyAmount) {
		this.qtyAmount = qtyAmount;
	}
	
	public Integer getItemID() {
		return itemID;
	}
	
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}
	
}
