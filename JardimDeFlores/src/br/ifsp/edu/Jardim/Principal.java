package br.ifsp.edu.Jardim;

public class Principal {

	public static void main(String[] args) {
		
	//	System.out.println("Hello World!");
		
	//	System.out.println("Cor: "+petalaAtual.getCorPetala());
	//	System.out.println("Cor: "+margarida.getPetala1().getCorPetala());
		
//		p1.setCorPetala("Preto");
		
	//	System.out.println("Cor: "+margarida.getPetala1().getCorPetala());
		
		//flor 2
		
		Petala p1 = new Petala();	 //petala criada
		p1.setCorPetala("Amarelo");  //amarelo no atributo
	//	System.out.println("Memoria: "+p1);
		
		Petala p2 = new Petala();    //segunda petala criada
		p2.setCorPetala("Laranja");  
		
		Petala p3 = new Petala();
		p3.setCorPetala("roxo");
		
		Petala p4 = new Petala();
		p4.setCorPetala("Azul");
		
		Petala p5 = new Petala();
		p5.setCorPetala("roxo");
		
		Petala p6 = new Petala();
		p6.setCorPetala("ciano");
		
		Petala p7 = new Petala();
		p7.setCorPetala("laranja");
		
		Petala p8 = new Petala();
		p8.setCorPetala("verde");
		
		Flor margarida=new Flor();   
		margarida.setPetala1(p1);   //passou petala para o setPetala do margarida
		margarida.setPetala2(p2);
		
		Petala petalaAtual = margarida.getPetala1(); // retorna o ponteiro do objeto petala1
		
		Flor girassol = new Flor();
		girassol.setPetala1(p3);
		girassol.setPetala2(p4);
		
		Flor rosa = new Flor();
		rosa.setPetala1(p5);
		rosa.setPetala2(p6);
		
		Flor violeta = new Flor();
		violeta.setPetala1(p7);
		violeta.setPetala2(p8);
		
		
		Jardim j1 = new Jardim();	//instanciei jardim
		j1.setFlor1(margarida);     //passei valores dos atributos
		j1.setFlor2(girassol);
		 
		Jardim j2 = new Jardim();
		j2.setFlor1(rosa);
		j2.setFlor2(violeta);
		
		
		/* Ctrl + Shift + L -> exibe atalhos do teclado */
		
		Pracinha pracinha = new Pracinha();  //instanciei a pracinha

		pracinha.setJ1(j1);  //passei valores dos atributos (qual o jardim da pracinha)      
		pracinha.setJ2(j2);
		
		
		//Caminho a partir da pracinha
		System.out.println("Cor:"+pracinha.getJ1().getFlor1().getPetala1().getCorPetala());    
		System.out.println("Cor:"+pracinha.getJ1().getFlor1().getPetala2().getCorPetala());    
		
		System.out.println("Cor:"+pracinha.getJ1().getFlor2().getPetala1().getCorPetala());    
		System.out.println("Cor:"+pracinha.getJ1().getFlor2().getPetala2().getCorPetala()); 
		
		System.out.println("Cor:"+pracinha.getJ2().getFlor1().getPetala1().getCorPetala());  
		System.out.println("Cor:"+pracinha.getJ2().getFlor1().getPetala2().getCorPetala());    
		
		System.out.println("Cor:"+pracinha.getJ2().getFlor2().getPetala1().getCorPetala());    
		System.out.println("Cor:"+pracinha.getJ2().getFlor2().getPetala2().getCorPetala());    
		
		
		//Caminho a partir do jardim
		System.out.println("Cor:"+j1.getFlor1().getPetala1().getCorPetala());    
		System.out.println("Cor:"+j1.getFlor1().getPetala2().getCorPetala());  
		
		System.out.println("Cor:"+j1.getFlor2().getPetala1().getCorPetala());    
		System.out.println("Cor:"+j1.getFlor2().getPetala2().getCorPetala());    
		
		System.out.println("Cor:"+j2.getFlor1().getPetala1().getCorPetala());    
		System.out.println("Cor:"+j2.getFlor1().getPetala2().getCorPetala()); 
		
		System.out.println("Cor:"+j2.getFlor2().getPetala1().getCorPetala());    
		System.out.println("Cor:"+j2.getFlor2().getPetala2().getCorPetala());    
		
		
		//Caminho a partir da petala
		System.out.println("Cor:"+margarida.getPetala1().getCorPetala());  
		System.out.println("Cor:"+margarida.getPetala2().getCorPetala());  
		
		System.out.println("Cor:"+girassol.getPetala1().getCorPetala());  
		System.out.println("Cor:"+girassol.getPetala2().getCorPetala());  
		
		System.out.println("Cor:"+rosa.getPetala1().getCorPetala());  
		System.out.println("Cor:"+rosa.getPetala2().getCorPetala());  
		
		System.out.println("Cor:"+violeta.getPetala1().getCorPetala());  
		System.out.println("Cor:"+violeta.getPetala2().getCorPetala());  
		
		
	
		// pra pegar uma cor de uma petala partindo de pracinha tem q fazer todo esse caminho;
		
		/*Do jardim retorna o objeto flor, do objeto flor retorna o objeto petala, e do objeto petala
		 retorna a cor da petala que está sendo pedida*/
		
		//Criar +1 Jardim, +2 Flores, +4 Petalas
		//Criar classe Pracinha, que contem diversos jardins
		//Colocar 2 Jardins dentro dessa pracinha
		
  
		
	} 

}

