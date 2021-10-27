package TratamentoDeErros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TratamentoDeErros {
	
	public static void main(String[] args) {
		
		ler("teste.txt");
		erroMatriz();
		System.out.println("Fim do Programa.");
		
		try {
			escrever("ttt.txt");
		}catch(Exception ex) {
			System.out.println("Tem algo estranho aí");
			ex.printStackTrace();
		}
				
		System.out.println("Fim do Programa");
		
	}
	
	public static void testeString(String texto) throws SemNocaoException{
		if(texto.contains("nocao"))
			throw new SemNocaoException();
	}
	
	
	public static void erroMatriz() {
		int[] valores = new int[2];
		for(int i = 0;i < 3; i++) {
			try{
				valores[i] = i;
				System.out.println("Valor i:" + i);
			}catch(Exception e) {
				System.out.println("Tentou acessar a posicao errada");
			}
		}
	}
	
	
	
	public static void ler(String nomeDoArquivo) {
		
		try {
			FileInputStream arquivo = new FileinputStream(nomeDoArquivo);
			int i = 0;
			while(i >= 0) {
				i = arquivo.read();
				System.out.println((char)i);
			}
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException f) {
			f.printStackTrace();
		}
		
	}
	
	public static void escrever(String nomeDoArquivo) throws Exception{
		FileOutputStream arquivo = new FileOutputStream(nomeDoArquivo);
		String mensagem = "testeabcd";
		arquivo.write(mensagem.getBytes());
		arquivo.close();
	}

}
