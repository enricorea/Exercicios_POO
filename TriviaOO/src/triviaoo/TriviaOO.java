package triviaoo;

import java.util.List;

public class TriviaOO {
	
	
	public static void main(String[] args) {
		Tupla gato = new Tupla("Animal felino e noturno", "gato");
		Tupla rato = new Tupla("Animal ratinio e noturno", "rato");
		
		Tupla f1 = new Tupla ("Fruta Verde por fora e vermelha por dentro", "melancia");
		Tupla f2 = new Tupla ("Fruta amarela por dentro com caroço", "manga");
		
		Tupla c1 = new Tupla("Cor do Sol", "laranja");
		Tupla c2 = new Tupla("Cor do limão", "verde");
		
		TriviaBase triviaDeAnimais = new TriviaAnimais();
		
		triviaDeAnimais.adicionaQuestao("Animal felino e noturno", "gato");
		triviaDeAnimais.adicionaQuestao("Animal ratinio e noturno", "rato");
		
		TriviaFrutas triviaDeFrutas = new TriviaFrutas();
		triviaDeFrutas.adicionaQuestao(f1);
		triviaDeFrutas.adicionaQuestao(f2);
		
		//fazer o terceiro e fazer funcionar, modificando alguns metodos do 3 pra ele ter comportamento 
		//especifico do metodo igual foi feito com trivia frutas
		
		JogoTrivia jogo=new JogoTrivia();
		jogo.setTrivia(triviaDeAnimais);
		jogo.jogar();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

/*	public static void main(String[] args) {
		
		List<Tupla> listaT = new ArrayList<>();
		
		Tupla obj1 = new Tupla("Quem eh voce?", "Eu sou eu e mais ninguem"); 
		Tupla obj2new Tupla("Eu sou eu?","Nao voce sou eu amanha");
		
		listaT.add(obj1); //add um ponteiro ao conteudo da lista, não um objeto
		listaT.add(obj2);
		
		Tupla x = listaT.get(0); // x ta apontando do endereco do objeto q ta na posicao 0 da lista
		
		//toda vez que trabalha com objetos, se trabalha com ponteiros. 2:05 da aula
		//quando nao eh ponteiro: int, double, char, floar, boolean, byte
		//quando se trabalha com qualquer objeto ele instancia
		
		x.setPergunta("aaaa");
		listaT.get(0).setPergunta("bbbbbb");
		System.out.println("Valor da posicao 0: "+x.getPergunta());
		
		
		System.out.println(x.getPergunta());
		System.out.println(x.getResposta());
		
		System.out.println("Acertou: "+x.acertou());
		x.setAcertou();
		System.out.println("Acertou: "+x.acertou());
		
	}
*/
	
	
}
