package BLL;

public class shippingInfo {
	
	private String address;
	private shipper shipper;
	private user user;
	private int id;
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public shipper getShipper() {
		return shipper;
	}
	
	public void setShipper(shipper shipper) {
		this.shipper = shipper;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
