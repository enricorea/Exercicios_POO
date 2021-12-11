package BLL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAL.*;

public class livraria {
	
	public static void main(String[] args) {
		
		BaseDeDados baseDeDados  = new BaseDeDados();
		baseDeDados.configuraBanco();
	

		System.out.println("Selecione a opcao desejada: ");
		int numero = -1;
		
		while(numero != 0) {
			Scanner scanner = new Scanner(System.in);
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
			scanner.close();
		}

	}
	
	public static void criarUsuario() {
		user usuario = new user();  //instanciando
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite seu nome: ");
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
	
	public static List<user> listarUsuario() {
		DAOUser daoUser = new DAOUser();
		List<user> listaUsuarios = daoUser.listar("");
		System.out.println("listaUsuarios.size: " + listaUsuarios.size());
		for(user user : listaUsuarios) {
			System.out.println("ID = " + user.getId() + "  | Nome = " + user.getNome());
		}
		
		return listaUsuarios;
	}
	
	public static void deletarUsuario() {
		DAOUser daoUser = new DAOUser();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Informe o ID: ");
		daoUser.excluir(scanner.nextInt());
		scanner.close();
	}
	
	public static List<book> consultarLivros() {
		DAOBook daoBook = new DAOBook();
		List<book> listaLivros = daoBook.listar("");
		for(book book : listaLivros) {
			System.out.println("ID = " + book.getId() + "  | Nome = " + book.getTitle() + " | Autor = "
						               + book.getAuthor().getName() + " | Editora = " + book.getPublisher().getName());
		}
		return listaLivros;
	}
	
	public static void comprarLivro() {
		List<book> listaLivros = consultarLivros(); //disponibiliza a lista de livros p/ acessar 1x
		List<book> livrosCompras = new ArrayList<>(); //criando uma lista de livros vazia p/ compra, para ir p/ order
		DAOOrder daoOrder = new DAOOrder();
		int id;
		int doneSelecting = 9999;
		order order = new order();
		Scanner scanner = new Scanner(System.in);
		while(doneSelecting != 100) {
			System.out.print("Informe o ID do livro a ser comprado: ");
			id = (scanner.nextInt());
			for(int i = 0 ; i < listaLivros.size() ; i++ ) {
				if(listaLivros.get(i).getId() == id ) {
					livrosCompras.add(listaLivros.get(i)); //add na lista de livros p/ compra o objeto que corresponde com o ID informado
				}
			}
			System.out.print("Digite 100 para parar ou qualquer outro numero para continuar: ");
			doneSelecting = scanner.nextInt();
		}
	//	order.setListaLivros(livrosCompras);
		
		List<user> listaUsuario = listarUsuario();
		
		System.out.print("Informe o ID do comprador: ");
		id = (scanner.nextInt());
		scanner.close();
		for(int i = 0 ; i < listaUsuario.size() ; i++ ) {
			if(listaUsuario.get(i).getId() == id ) {
				order.setConsumidor(listaUsuario.get(i));
			}
		}
		try {
			daoOrder.incluir(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//pegar id do order feito, sera colocado no order book pra fazer associação de 1 - n
		for(book book : livrosCompras) {
			DAOOrderBook daoOrderBook = new DAOOrderBook();
			orderBook orderBook = new orderBook();
			orderBook.setOrder(daoOrder.retornarUltimo()); //pegando o ultimo order feito, e colocando no objeto orderBook
			orderBook.setBook(book);
			try {
				daoOrderBook.incluir(orderBook); //inclundo um registro na order_book p/ cada livro comprado com o metodo incluir.
				System.out.println("Você concluiu sua venda!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
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