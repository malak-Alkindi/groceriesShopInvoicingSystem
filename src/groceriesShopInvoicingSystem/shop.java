package groceriesShopInvoicingSystem;

public class shop {
	
	private String telephoneNumber; 
	private	String Fax ;
	private	String Email ;
	private	String name; 

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	private	String Website;
	
	public void setTel(String tel) {
		telephoneNumber = tel;
	}
	
	public void setFax(String fax) {
		Fax = fax;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public void setWebsite(String website) {
		Website = website;
	}

	public String getTel() {
		return telephoneNumber;
	}
	
	public String getFax() {
		return Fax;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public String getWebsite() {
		return Website;
	}
}

