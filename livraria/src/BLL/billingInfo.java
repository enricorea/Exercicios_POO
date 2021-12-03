package BLL;

public class billingInfo {
	
	private account conta;
	private double valor;
	private int numCartao;
	private int id;
	private account account;
	
	public account getAccount() {
		return account;
	}
	public void setAccount(account account) {
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(int numCartao) {
		this.numCartao = numCartao;
	}
	public account getConta() {
		return conta;
	}
	public void setConta(account conta) {
		this.conta = conta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
