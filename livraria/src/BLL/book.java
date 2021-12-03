package BLL;

public class book {  //modelo que mantem o status do livro
	
	private int Id;     // atributos da classe book
	
	private author authorId;
	
	private String email;
		
	private String title;
	
	// metodos da classe book
	
	public int getId() {  
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public author getAuthor() {
		return authorId;
	}

	public void setAuthor(author author) {
		this.authorId = author;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	private double valor;

	public publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(publisher publisher) {
		this.publisher = publisher;
	}

	publisher publisher;   // 



}
