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
import BLL.user;

public class DAOOrder {

	
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
    public void incluir(order order) throws SQLException {
        conectar();

        String query = "INSERT INTO library.order (user_id) "
                + "VALUES (?)";    
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, order.getConsumidor().getId());    // aqui
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(order order) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.order "
                    + "SET user_id=? WHERE order_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(2, order.getId());
            prep.setInt(1, order.getConsumidor().getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(order order) {
        conectar();
        try {
            String query = "DELETE FROM library.order "
                    + "WHERE order_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, order.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(order order) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.order "
                + "WHERE order_id = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setInt(1, order.getId());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("order_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        order.setId(idTmp);
        return idTmp;
    }
    
    public order retornarUltimo() {
    	
    	String query = "SELECT * FROM library.`order` WHERE order_id=(SELECT max(order_id) FROM library.`order`);"; // query que pega o ultimo order id da lista
    	order order = new order();
        conectar();
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 
            
            while (lista.next()) {
            	
            	order.setId(lista.getInt("order_id"));
                
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	
		return order;
    }

    public List<order> listar(String params) {
    	
    	DAOUser daoUser = new DAOUser(); 
    	
    	List<user> listaUser = daoUser.listar(""); 

        conectar();
        List<order> listaOrder = new ArrayList<>();
        String query = "SELECT * from library.order " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	order order = new order();
            	order.setId(lista.getInt("order_id"));
                
                
                for (user user : listaUser) {
                    if (user.getId() == lista.getInt("user_id")) { 
                    	
                    	order.setConsumidor(user);
                    	
                    	break;
                    }
                }
                
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaOrder;
    }

}

