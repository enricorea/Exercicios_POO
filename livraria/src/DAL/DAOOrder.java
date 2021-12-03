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

import BLL.billingInfo;
import BLL.order;
import BLL.shippingInfo;
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

        String query = "INSERT INTO library.order (user_id, shipping_info_id, billing_info_id) "
                + "VALUES (?,?,?)";    
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, order.getConsumidor().getId());    
        prep.setInt(2, order.getEntregador().getId());
        prep.setInt(3, order.getInfoCompras().getId());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(order order) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.books "
                    + "SET user_id=?, shipping_info_id=?, billing_info_id=? WHERE order_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, order.getId());
            prep.setInt(1, order.getConsumidor().getId());
            prep.setInt(2, order.getEntregador().getId()); //acessando ID do autor dentro do objeto autor
            prep.setInt(3, order.getInfoCompras().getId());
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
    

    public List<order> listar(String params) {
    	
    	DAOUser daoUser = new DAOUser(); 
    	DAOShippingInfo daoShippingInfo = new DAOShippingInfo();
    	DAOBillingInfo daoBillingInfo = new DAOBillingInfo();
    	
    	List<user> listaUser = daoUser.listar(""); 
    	List<shippingInfo> listaShippingInfo = daoShippingInfo.listar("");
    	List<billingInfo> listaBillingInfo = daoBillingInfo.listar("");
    	
        conectar();
        List<order> listaOrder = new ArrayList<>();
        String query = "SELECT * from library.order " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	order order = new order();
            	order.setId(lista.getInt("order_id"));
                
                for (shippingInfo shippingInfo : listaShippingInfo) {
                    if (shippingInfo.getId() == lista.getInt("shipping_info_id")) { //objeto da lista == author_id do banco de dados
                    	
                    	order.setEntregador(shippingInfo);
                    	
                    	break;
                    }
                }
                
                for (billingInfo billingInfo : listaBillingInfo) {
                    if (billingInfo.getId() == lista.getInt("billing_info_id")) { 
                    	
                    	order.setInfoCompras(billingInfo);
                    	
                    	break;
                    }
                }
                
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

