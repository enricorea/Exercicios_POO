package ProjetoFiguras;

public abstract class Figura { // abstração geral do conceito (circulo é um tipo de figura, quadrado, etc..)
	
	//classes abstratas não são instanciadas
	//Figura é instanciado indiretamente via quadrado, ou via circulo, ou via triangulo
	
	int a = 2;
	public void draw() { // metodo draw
		System.out.println("Desenhando...");
	}
	
	public abstract void drawShape(); //metodo abstrato ou metodo virtual
	//não aceita desenvolvimento de corpo, só aceita a assinatura do método

}
