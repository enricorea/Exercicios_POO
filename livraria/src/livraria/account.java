package livraria;


public class account{
	private String emailAddress;
	private long id;
	private String password;
	
	
		//metodos de operacao
	public boolean validateLogin(long ID, String password) {
		if(this.password.equals(password) && this.id == ID) 
			return true;
		return false;
	}
	
	
	//metodos acessores
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
