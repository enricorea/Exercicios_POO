package BLL;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAL.*;

public class livraria {
	
	public static void main(String[] args) {
		
		BaseDeDados baseDeDados  = new BaseDeDados();
		baseDeDados.configuraBanco();
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Selecione a opcao desejada: ");
		
		int numero = -1;
		
		while(numero != 0) {
			System.out.println("Digite 1 para cadastrar um novo usuario");
			System.out.println("Digite 2 para listar os usuarios");
			System.out.println("Digite 3 para deletar seu usuario");
			System.out.println("Digite 4 para consultar os livros");
			System.out.println("Digite 5 para comprar um livro");
			System.out.println("Digite 0 para encerrar");
			numero = scanner.nextInt();
			
			switch(numero) {
				case 1:
					criarUsuario();
					break;
				case 2:
					listarUsuario();
					break;
				case 3:
					deletarUsuario();
					break;
				case 4:
					consultarLivros();
					break;
				case 5:
					comprarLivro();
					break;
				case 0:
					break;
			
			}
		}
	}
	
	public static void criarUsuario() {
		user usuario = new user();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite seu nome: ");
		String nome = scanner.next();
		scanner.close();
		usuario.setNome(nome);

		DAOUser daoUser = new DAOUser();
		try {
			daoUser.incluir(usuario);
			System.out.println("Usuario " + usuario.getNome() + " cadastrado.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarUsuario() {
		DAOUser daoUser = new DAOUser();
		List<user> listaUsuarios = daoUser.listar("");
		System.out.println("listaUsuarios.size: " + listaUsuarios.size());
		for(user user : listaUsuarios) {
			System.out.println("ID = " + user.getId() + "  | Nome = " + user.getNome());
		}
	}
	
	public static void deletarUsuario() {
		DAOUser daoUser = new DAOUser();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Informe o ID: ");
		
	}
	
	public static void consultarLivros() {
		
	}
	
	public static void comprarLivro() {
		
	}
}
		
		
/*		
		//criação do usuario
		user usuario = new user();
		usuario.setNome("Joao");
		System.out.println(usuario.getNome());
		DAOUser daoUser = new DAOUser();
		try {
			daoUser.incluir(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
*/		
		
		
		
		
		/*
		//criação do usuario
		user usuario = new customer();
		usuario.setName("Joao");
		
		//criação do autor
		author autor = new author();
		autor.setName("Julio Verne");
		
		//criação da publicadora
		publisher publicadora = new publisher();
		publicadora.setName("Attila");
		
		//criação do livro
		book livro = new book();
		livro.setTitle("Duna");
		livro.setPublisher(publicadora);
		livro.setAuthor(autor);
		livro.setValor(20);
		
		//criação do shipper
		shipper entregador = new shipper();
		entregador.setName("Jadelog");
		
		//criação do shippinginfo compras
		shippingInfo compras = new shippingInfo();
		compras.setShipper(entregador);
		compras.setAddress("endereço de entrega");
		compras.setNomeUsuario(usuario.getName());
		
		//criação da conta
		account conta = new account();
		conta.setId(11111);
		conta.setPassword("1234");
		conta.setEmailAddress("aaaaa@gmail.com");
		conta.setUsuario(usuario);
		
		//criação da informação de pagamento
		billingInfo pagInfo = new billingInfo();
		pagInfo.setConta(conta);
		pagInfo.setValor(livro.getValor());
		
		//ordem de compra
		order ordemCompra = new order();
		ordemCompra.setConsumidor(usuario);
		ordemCompra.setLivro(livro);
		ordemCompra.setEntregador(compras);
		ordemCompra.setInfoCompras(pagInfo);
		
		if(ordemCompra.execVenda()) {
			System.out.println("Venda realizada com sucesso");
		}else {
			System.out.println("Venda nao realizada");
		}
		
		*/
	















/*	Criar um livro.
Criar um autor, e vincular ao livro.
Criar um publicador, e vincular ao livro.
Criar um usuario(editorial/consumidor), e vincular ao livro (Vincular a ordem de compra).
Criar um Entregador.
Criar informações de entrega e vincular o entregador.
Criar uma conta com validação de senha
Criar uma classe de informações de conta e vincular a conta
Criar uma ordem de compra
	vincular tudo acima. */