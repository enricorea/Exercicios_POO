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


import BLL.order;
import BLL.orderBook;
import BLL.book;

public class DAOOrderBook {

	
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
    public void incluir(orderBook orderBook) throws SQLException {
        conectar();

        String query = "INSERT INTO library.order_book (order_id, book_id) "
                + "VALUES (?,?)";    
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, orderBook.getOrder().getId());    // aqui
        prep.setInt(2, orderBook.getBook().getId());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(orderBook orderBook) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.order_book "
                    + "SET order_id=?, book_id=? WHERE order_book_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(3, orderBook.getId());
            prep.setInt(1, orderBook.getOrder().getId());
            prep.setInt(2, orderBook.getBook().getId()); 
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(orderBook orderBook) {
        conectar();
        try {
            String query = "DELETE FROM library.order_book "
                    + "WHERE order_book_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, orderBook.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorID(orderBook orderBook) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.order_book "
                + "WHERE order_book_id = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setInt(1, orderBook.getId());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("order_book_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        orderBook.setId(idTmp);
        return idTmp;
    }
    

    public List<orderBook> listar(String params) {
    	

    	DAOOrder daoOrder = new DAOOrder();
    	DAOBook daoBook = new DAOBook();
    	List<order> listaOrder = daoOrder.listar(""); 
    	List<book> listaBook = daoBook.listar("");
    	List<orderBook> listaOrderBook = new ArrayList<>();
    	
        conectar();
        String query = "SELECT * from library.order_book " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	orderBook orderBook = new orderBook();
            	orderBook.setId(lista.getInt("orderBook_id"));
                
                for (order order : listaOrder) {
                    if (order.getId() == lista.getInt("order_id")) { //objeto da lista == author_id do banco de dados
                    	
                    	orderBook.setOrder(order);
                    	
                    	break;
                    }
                }
                
                for (book book : listaBook) {
                    if (book.getId() == lista.getInt("book_id")) { 
                    	
                    	orderBook.setBook(book);
                    	
                    	break;
                    }
                }
                
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaOrderBook;
    }
	
}
