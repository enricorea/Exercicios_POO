package ProjetoFiguras;

public class ProjetoFiguras {
	
	public static void main(String[] args) {
		
		Quadrado quadrado = new Quadrado(); // definindo um quadrado
		quadrado.draw(); // recebe por heran�a o metodo draw de figura
		
		Circulo circ = new Circulo();
		circ.draw();
		
		Triangulo tri = new Triangulo();
		tri.draw();
		
		passaParametroPorHeranca(tri); //usa uma abstra��o, e com ela, � possivel passar todos os filhos
		
		/*exemplo de heran�a
	     *
		 * Abstra��o gera Heran�a, e por isso que � poss�vel
		 * aproveitar c�digos das classes de mais alta instancia,
		 * para as classes de mais baixa instancia
		 * super classes para sub classes
		 * 
		 * */
		
	}
	
	public static void passaParametroPorHeranca(Figura fig) {
		fig.drawShape(); //chama o m�todo. Ele precisa estar presente na abstral�ao e no filho, nem q seja via heran�a
		fig.draw();
	}
	
}
