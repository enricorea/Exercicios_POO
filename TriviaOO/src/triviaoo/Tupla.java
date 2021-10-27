package triviaoo;

public class Tupla {  // tem uma pergunta, uma resposta e se acertou ou nao
	
	private String pergunta; // precisam ter set e get
	private String resposta;
	private boolean acertou = false;
	
	public Tupla(String pergunta, String resposta) { //construtor da tupla
		setPergunta(pergunta); //quem eh vc
		setResposta(resposta); //eu sou eu e mais ngm
	}
	
	//encapsulou (colocou private antes) atributo pergunta, e criou set e get dele	
	
	public String getPergunta() {
		return pergunta;
	}
	
	public void setPergunta(String pergunta) {
		if(!pergunta.isEmpty())
			return;
		this.pergunta = pergunta;
	}
	
	public String getResposta() {
		return resposta;
	}

	public String setResposta() {
		this.resposta = resposta;
	}
	
	public boolean acertou() {
		return acertou;
	}
	
	public void setAcertou() { 
		this.acertou = true;
	}

	
}
