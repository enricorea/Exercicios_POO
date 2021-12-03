package cadastrobolos;

public class CadastroBolos {
	
	public static void main(String[] args) {
		
		Bolo bolo1 = new Bolo(); //instancia de bolo
		
		//Aributos do bolo
		bolo1.setNome("Floresta Negra");
		bolo1.setTamanho("20 cm");
		bolo1.setPeso(2000);
		bolo1.setPreco(51.5);
		
		Bolo bolo2 = new Bolo();
		
		bolo2.setNome("Abacaxi");
		bolo2.setTamanho("20 cm");
		bolo2.setPeso(3000);
		bolo2.setPreco(45.2);
		
		CRUDBolos cadBolos = new CRUDBolos();
		cadBolos.adicionarBolo(bolo1);
		cadBolos.adicionarBolo(bolo2);
		
		System.out.println(cadBolos.gerarRelatorio());
		
		Bolo boloBusca = cadBolos.buscarBolo("Abacaxi");
		if(boloBusca != null) {
			System.out.println("Bolo: " + boloBusca.getPreco()); 
		}
		else {
			System.out.println("Bolo nao encontrado!");
		}
		
		if(cadBolos.removerBolo("Abacaxi")) {
			System.out.println("Bolo removido com sucesso!");
		}else {
			System.out.println("Problema ao remover bolo!");
		}
		
		System.out.println(cadBolos.gerarRelatorio());
		
		//update
		
		Bolo boloBuscaUpdate = cadBolos.buscarBolo("Abacaxi");
		boloBuscaUpdate.setNome("Abacaxi Super"); //ja faz a atualizacao
		boloBuscaUpdate.setPreco(23.4);
		cadBolos.atualizarBolo(boloBuscaUpdate);
		
		
	}

}
