package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import BLL.author;
import BLL.book;
import BLL.publisher;

public class DAOBook {
	
    private Connection conection = null;

    public void conectar() {
        Properties prop = new Properties();
        prop.setProperty("user", "root");
        prop.setProperty("password", "123");
        try {
            conection = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:3308", prop);
            conection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //incluir
    public void incluir(book book) throws SQLException {
        conectar();

        String query = "INSERT INTO library.books (title, author, email) "
                + "VALUES (?,?,?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, book.getTitle());    // vai colocar title author email na ordem, pra isso que serve PreparedStatement
        prep.setInt(2, book.getAuthor().getId()); //acessando id do autor dentro do objeto autor
        prep.setString(3, book.getEmail());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(book book) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.books "
                    + "SET title=?, author_id=?, email=? WHERE book_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, book.getId());
            prep.setString(1, book.getTitle());
            prep.setInt(2, book.getAuthor().getId()); //acessando ID do autor dentro do objeto autor
            prep.setString(3, book.getEmail());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(book book) {
        conectar();
        try {
            String query = "DELETE FROM library.books "
                    + "WHERE book_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, book.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(book book) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.books "
                + "WHERE title = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, book.getTitle());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("book_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        book.setId(idTmp);
        return idTmp;
    }
    
    //qd cria um objeto de algum tipo, vc pode implementar construtores customizados onde vc já passa o parametro
    //o padrão é () sem nada, pq é omitido

    public List<book> listar(String params) {
    	//puxar lista de autores
    	DAOAuthor daoAuthor = new DAOAuthor(); //criação do objeto
    	DAOPublisher daoPublisher = new DAOPublisher();
    	
    	List<author> listaAutores = daoAuthor.listar(""); //puxando lista p/ criar os objetos com seus objetos de autores respectivos
    	List<publisher> listaPublisher = daoPublisher.listar("");
    	
        conectar();
        List<book> listaLivros = new ArrayList<>();
        String query = "SELECT * from library.books " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
                book book = new book();
                book.setId(lista.getInt("book_id"));
                book.setTitle(lista.getString("title"));
                
                for (author author : listaAutores) {
                    if (author.getId() == lista.getInt("author_id")) { //objeto da lista == author_id do banco de dados
                    	
                    	book.setAuthor(author);
                    	
                    	break;
                    }
                }
                
                for (publisher publisher : listaPublisher) {
                    if (publisher.getId() == lista.getInt("publisher_id")) { //objeto da lista == author_id do banco de dados
                    	
                    	book.setPublisher(publisher);
                    	
                    	break;
                    }
                }
                
                book.setEmail(lista.getString("email"));
                listaLivros.add(book);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLivros;
    }

}

