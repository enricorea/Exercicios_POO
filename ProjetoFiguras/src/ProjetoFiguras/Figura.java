package ProjetoFiguras;

public abstract class Figura { // abstra��o geral do conceito (circulo � um tipo de figura, quadrado, etc..)
	
	//classes abstratas n�o s�o instanciadas
	//Figura � instanciado indiretamente via quadrado, ou via circulo, ou via triangulo
	
	int a = 2;
	public void draw() { // metodo draw
		System.out.println("Desenhando...");
	}
	
	public abstract void drawShape(); //metodo abstrato ou metodo virtual
	//n�o aceita desenvolvimento de corpo, s� aceita a assinatura do m�todo

}
