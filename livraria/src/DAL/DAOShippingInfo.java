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

import BLL.shipper;
import BLL.shippingInfo;
import BLL.user;

public class DAOShippingInfo {

	
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
    public void incluir(shippingInfo shippingInfo ) throws SQLException {
        conectar();

        String query = "INSERT INTO library.shipping_info (address, shipper_id, user_id) "
                + "VALUES (?,?,?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, shippingInfo.getAddress());    
        prep.setInt(2, shippingInfo.getShipper().getId()); 
        prep.setInt(3, shippingInfo.getUser().getId());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(shippingInfo shippingInfo) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.shipping_info "
                    + "SET address=?, shipper_id=?, user_id=? WHERE shipping_info_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, shippingInfo.getId());
            prep.setString(1, shippingInfo.getAddress());
            prep.setInt(2, shippingInfo.getShipper().getId()); //acessando ID do autor dentro do objeto autor
            prep.setInt(3, shippingInfo.getUser().getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(shippingInfo shippingInfo) {
        conectar();
        try {
            String query = "DELETE FROM library.shipping_info "
                    + "WHERE shipping_info_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, shippingInfo.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(shippingInfo shippingInfo) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.shipping_info "
                + "WHERE shipping_info_id = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setInt(1, shippingInfo.getId());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("shipping_info_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shippingInfo.setId(idTmp);
        return idTmp;
    }

    public List<shippingInfo> listar(String params) {

    	DAOShipper daoShipper = new DAOShipper(); //criação do objeto
    	DAOUser daoUser = new DAOUser();
    	
    	List<shipper> listaShipper = daoShipper.listar(""); //puxando lista p/ criar os objetos com seus objetos de autores respectivos
    	List<user> listaUser = daoUser.listar("");
    	
        conectar();
        List<shippingInfo> listaShippingInfo = new ArrayList<>();
        String query = "SELECT * from library.shipping_info " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	shippingInfo shippingInfo = new shippingInfo();
            	shippingInfo.setId(lista.getInt("shipping_info_id"));
                
                for (shipper shipper : listaShipper) {
                    if (shipper.getId() == lista.getInt("shipper_id")) { 
                    	
                    	shippingInfo.setShipper(shipper);
                    	
                    	break;
                    }
                }
                
                for (user user : listaUser) {
                    if (user.getId() == lista.getInt("user_id")) { 
                    	
                    	shippingInfo.setUser(user);
                    	
                    	break;
                    }
                }
                
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaShippingInfo;
    }
}