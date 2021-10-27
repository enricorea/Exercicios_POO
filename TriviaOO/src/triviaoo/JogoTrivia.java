package triviaoo;

public class JogoTrivia {
	
	private TriviaBase x;
	
	public TriviaBase getTrivia() {
		return x;
	}
	
	public void setTrivia (TriviaBase trivia){
		
		this.x = trivia;
		
	}
	
	public void jogar() {
		x.sortear();
		x.perguntar();
		x.mostrarResultado();
	}
	
}















/*public class JogoTrivia {
	
	TriviaAnimais triviaAnimais;
	
	// preenchendo atributos com os valores passados nos parametros
	
	public void setTriviaAnimais(TriviaAnimais ta) {
		
		triviaAnimais = ta;
	}
	
	public void setTriviaCores(TriviaCores ta) {
		
		triviaCores = ta;
	}
	
	public void setTriviaFrutas(TriviaFrutas ta) {
		
		triviaFrutas = ta;
	}
	
	public void jogarTriviaFrutas() {
		
		triviaAnimais.sortearPergunta();
		triviaAnimais.perguntar();
		triviaAnimais.mostrarResultado();
		
	}
	
}
*/