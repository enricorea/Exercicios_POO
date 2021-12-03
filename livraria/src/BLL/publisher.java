package BLL;

public class publisher extends BaseName {

	private String name;
	private int    id;
	
	//qd ta private vc controla a forma de acesso da variavel, controlando o q entra e o que sai dela
	//vc acessa ela a partir de set e get
	//permite que sejam colocadas regras tanto na entrada quanto na saida
	//protege a variavel
	
	public String getName() { //retornar valor da variavel
		return name;
	}
	
	public void setName(String name) { //configurar valor da variavel
		if(name == null) return;
		if(name.contains("sex")) return;
		if(name.contains("@")) return;
		
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}