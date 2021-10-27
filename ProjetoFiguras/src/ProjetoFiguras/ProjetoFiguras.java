package ProjetoFiguras;

public class ProjetoFiguras {
	
	public static void main(String[] args) {
		
		Quadrado quadrado = new Quadrado(); // definindo um quadrado
		quadrado.draw(); // recebe por herança o metodo draw de figura
		
		Circulo circ = new Circulo();
		circ.draw();
		
		Triangulo tri = new Triangulo();
		tri.draw();
		
		passaParametroPorHeranca(tri); //usa uma abstração, e com ela, é possivel passar todos os filhos
		
		/*exemplo de herança
	     *
		 * Abstração gera Herança, e por isso que é possível
		 * aproveitar códigos das classes de mais alta instancia,
		 * para as classes de mais baixa instancia
		 * super classes para sub classes
		 * 
		 * */
		
	}
	
	public static void passaParametroPorHeranca(Figura fig) {
		fig.drawShape(); //chama o método. Ele precisa estar presente na abstralçao e no filho, nem q seja via herança
		fig.draw();
	}
	
}
