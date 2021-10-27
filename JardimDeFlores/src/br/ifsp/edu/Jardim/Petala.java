package br.ifsp.edu.Jardim;

public class Petala {
	
	private String cor="";                      //atributos de uma classe deve ser private
		                         /* eh necessario ter um set e um get 
	                             pra acessar esse atributo indiretamente*/
	
	//metodos acessores:
	
	// push de valores
	public void setCorPetala(String cor) {              //função dentro da classe é um método
		this.cor=cor;                        //this faz ref ao objeto petala
	}
	//pull
	public String getCorPetala() {
		return cor;
	}
	
}
