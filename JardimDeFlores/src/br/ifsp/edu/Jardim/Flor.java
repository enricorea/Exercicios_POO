package br.ifsp.edu.Jardim;

public class Flor {
	
	Petala petala1;    //atributo da flor
	Petala petala2;
	
	//metodos para a petala 1
	public void setPetala1(Petala petala) {        // definir a petala 1
		this.petala1 = petala;
	}
	
	public boolean temPetala1() {                 // acessor pra ver se tem petala 1
		if(petala1 == null) {
			return false;
		}else {
			return true;
		}
			
	}
	
	public Petala getPetala1(){
		return petala1;              // vai retornar do proprio objeto petala1 do tipo Petala
	}
	
	//metodos para a petala 2
	public void setPetala2(Petala petala) {       // definir a petala 2
		this.petala2 = petala;
	}
	
	public boolean temPetala2() {                 // acessor pra ver se tem petala 2
		if(petala2 == null) {
			return false;
		}else {
			return true;
		}
			
	}
	
	public Petala getPetala2() {
		return petala2;
	}

}
