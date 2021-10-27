package triviaoo;

import java.until,ArrayList; //importa bibliotecas 
import java.until.List;
import java.util.ArrayList;

public class TriviaBase {  //comum a todos eles
	
	java.util.List<Tupla> perguntasERespostas=new ArrayList<>();
	int perguntaEscolhida = -1;
	String respostaDigitada = "";
	
	public void adicionaQuestao(String pergunta, String resposta) {
	/*	Tupla t = new Tupla(pergunta, resposta); new tupla        //instancia o objeto do tipo tupla, o t tem uma instancia do objeto do tipo tupla que 
		perguntasERespostas.add(t);         //tem uma perguta e uma resposta, guarda essa instancia em t e adiciona t na lista de perguntas e respostas
		                               //instância do objeto que guarda uma pergunta e uma resposta */
		Tupla t = new Tupla(pergunta, resposta);
		perguntasERespostas.add(t);
	}
	
	public Tupla getTuplaSorteada() { //retorna a tupla de acordo com a pergunta escolhida
		return perguntasERespostas.get(perguntaEscolhida);
	}
	
	public void sortear() {
		
		perguntaEscolhida = (int)(Math.random()*perguntasERespostas.size()); //biblioteca math, metodo random que sorteia entre 0 e 1
	}
	
	
	public void perguntar() {
		
		Scanner leitor = new Scanner(System.in); // cria scanner pra ler o teclado
		Tupla t = getTuplaSorteada();
		System.out.println(t.getPergunta());
		respostaDigitada = leitor.nextt();
		
	/*	//pega a pergunta sorteada
		System.out.println(getTuplaSorteada.getPergunta()); // faz a pergunta na tela
		
		//espera o usuario digitar um valor
		respostaDigitada = leitor.next(); // pega o que digitou e coloca no resposta digitada, que é a string criada */
		
	}
	
	public void mostrarresultado() {
		
		Tupla t = perguntasERespostas.get(perguntaEscolhida);
		String res = t.getResposta();
		
		if(respostaDigitada.equals(res)) {
			System.out.println("Resposta Correta! ");
		}else {
			System.out.println("Resposta Errada! ");
		}
		
	}

}
