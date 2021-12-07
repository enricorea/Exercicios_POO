package cadastrobolos;

import java.util.ArrayList;
import java.util.List;

public class CRUDBolos {
	
	List<Bolo> listaDeBolos = new ArrayList<>(); //lista
	
	public void adicionarBolo(Bolo bolo) {
		listaDeBolos.add(bolo); //metodo pra adicionar o bolo a lista
	}
	
	public Bolo buscarBolo(String nomeDoBolo) {
		for(Bolo b:listaDeBolos) {   //objeto por objeto da lista de bolos entra ai
			if(b.getNome().equals(nomeDoBolo)) { //getnome retorna uma string
				return b;						//pega objeto, pega nome dele e comparando com o nome do bolo que foi passado
			}
		}
		return null;
	}
	
	public Bolo buscarBolo(Bolo bolo) {  //polimorfismo
		for(Bolo b:listaDeBolos) {   
			if(b == bolo) { //comparando ponteiro de memoria
				return b;						
			}
		}
		return null;
	}
	
	public boolean removerBolo(String nomeDoBolo) {
		Bolo b = buscarBolo(nomeDoBolo);
		if(b != null) {
			listaDeBolos.remove(b);
			return true;
		}
		return false;
	}
	
	//polimorfismo (tem 2 remover bolo) - no segundo remove pelo objeto
	
	public boolean removerBolo(Bolo bolo) {

		if(bolo != null) {
			listaDeBolos.remove(bolo);
			return true;
		}
		return false;
	}
	
	public boolean atualizarBolo(Bolo bolo) {
		Bolo b = buscarBolo(bolo);
		if(b != null) {
			b.setNome(bolo.getNome());
			b.setPeso(bolo.getPeso());
			b.setTamanho(bolo.getTamanho());
			b.setPreco(bolo.getPreco());		
			return true;
		}else {
			return false;
		}
	}
		
	public String gerarRelatorio() {
		String relatorio = "";
		for(Bolo b:listaDeBolos) {
			relatorio += "Nome: "    + b.getNome()    + "\n";
			relatorio += "Tamanho: " + b.getTamanho() +"\n";
			relatorio += "Peso: "    + b.getPeso()    + "\n";
			relatorio += "Preco: "   + b.getPreco()   + "\n";
			relatorio += "====================\n";
		}
		return relatorio;
	}

		
}

//colocar um metodo de remoção por objeto
//da mesma forma que teve o atualizar, vai ter o remover utilizando (Bolo bolo)

