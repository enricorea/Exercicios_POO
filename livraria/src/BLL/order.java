package BLL;

import java.util.Scanner;

public class order {
	
	//atributos criados (na uml estão ligados ao order)
	
	private book livro;
	
	private user consumidor;
	
	private shippingInfo entregador;
	
	private billingInfo infoCompras;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public book getLivro() {
		return livro;
	}

	public void setLivro(book livro) {
		this.livro = livro;
	}

	public user getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(user consumidor) {
		this.consumidor = consumidor;
	}

	public shippingInfo getEntregador() {
		return entregador;
	}

	public void setEntregador(shippingInfo entregador) {
		this.entregador = entregador;
	}

	public billingInfo getInfoCompras() {
		return infoCompras;
	}

	public void setInfoCompras(billingInfo infoCompras) {
		this.infoCompras = infoCompras;
	}
	
	
	public boolean isFulffiled() {
		
		return (livro != null && consumidor != null && entregador != null);
	
	}
	
	public boolean execVenda() {
		
		if (isFulffiled()) {
			//classe // objeto criado a partir da classe
			Scanner leitor = new Scanner(System.in);
			System.out.println("Digite o Numero da conta: ");
			int conta = leitor.nextInt();
			System.out.println("Digite a senha: ");
			String password = leitor.next();
			
			leitor.close();
			
			if(infoCompras.getConta().validateLogin(conta, password)) {
				return true;
			}			
		}
		
		return false;
		
		
		
	}
	
}
