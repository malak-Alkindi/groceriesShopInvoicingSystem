package groceriesShopInvoicingSystem;

public class Product {

	private	String itemName; 
	private	float unitPrice;
	private	Integer quantity ; 
	private	Integer qtyAmount;
	
	
	
	
	
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
	
	
}
