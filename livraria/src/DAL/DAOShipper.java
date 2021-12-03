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

public class DAOShipper {

	
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
    public void incluir(shipper shipper) throws SQLException {
        conectar();

        String query = "INSERT INTO library.shipper (shipper_name) "
                + "VALUES (?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, shipper.getName());    
        prep.execute();
        conection.commit();
        
        conection.close();
    }
    
    //alterar - a model deve estar com o id preenchido
    public void alterar(shipper shipper) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.shipper "
                    + "SET shipper_name=? WHERE shipper_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(2, shipper.getId());
            prep.setString(1, shipper.getName());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //excluir
    public void excluir(shipper shipper) {
        conectar();
        try {
            String query = "DELETE FROM library.shipper "
                    + "WHERE shipper_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, shipper.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //consultar
    public int consultarPorNome(shipper shipper) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.shipper "
                + "WHERE shipper_name = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, shipper.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("shipper_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shipper.setId(idTmp);
        return idTmp;
    }
    
    public List<shipper> listar(String params) {
        conectar();
        List<shipper> listaShipper = new ArrayList<>();
        String query = "SELECT * from library.shipper " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
            	shipper shipper = new shipper();
            	shipper.setId(lista.getInt("shipper_id"));
            	shipper.setName(lista.getString("shipper_name"));
                listaShipper.add(shipper);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaShipper;
    }
}