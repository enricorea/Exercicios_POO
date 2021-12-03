package BLL;


public class account{
	private String emailAddress;
	private int id;
	private String password;
	private user usuario;
	
	
		//metodos de operacao
	public boolean validateLogin(int ID, String password) {
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
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	
	public user getUsuario() {
		return usuario;
	}

	public void setUsuario(user usuario) {
		this.usuario = usuario;
	}
	

}
