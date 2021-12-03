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

import BLL.publisher;

public class DAOPublisher {

	
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
    public void incluir(publisher publisher) throws SQLException {
        conectar();

        String query = "INSERT INTO library.publisher (nome_publisher) "
                + "VALUES (?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, publisher.getName());    
        prep.execute();
        conection.commit();
        
        conection.close();
    }
    
    //alterar - a model deve estar com o id preenchido
    public void alterar(publisher publisher) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.publisher "
                    + "SET nome_publisher=? WHERE publisher_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(2, publisher.getId());
            prep.setString(1, publisher.getName());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //excluir
    public void excluir(publisher publisher) {
        conectar();
        try {
            String query = "DELETE FROM library.publisher "
                    + "WHERE publisher_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, publisher.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //consultar
    public int consultarPorNome(publisher publisher) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.publisher "
                + "WHERE nome_autor = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, publisher.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("publisher_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        publisher.setId(idTmp);
        return idTmp;
    }
    
    public List<publisher> listar(String params) {
        conectar();
        List<publisher> listaPublisher = new ArrayList<>();
        String query = "SELECT * from library.publisher " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
            	publisher publisher = new publisher();
            	publisher.setId(lista.getInt("publisher_id"));
            	publisher.setName(lista.getString("nome_publisher"));
                listaPublisher.add(publisher);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPublisher;
    }
    
	
	
}
