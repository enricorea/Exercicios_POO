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

public class DAOAuthor {

	
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
    public void incluir(author author) throws SQLException {
        conectar();

        String query = "INSERT INTO library.author (nome_autor) "
                + "VALUES (?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, author.getName());    
        prep.execute();
        conection.commit();
        
        conection.close();
    }
    
    //alterar - a model deve estar com o id preenchido
    public void alterar(author author) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.author "
                    + "SET nome_autor=? WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(2, author.getId());
            prep.setString(1, author.getName());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //excluir
    public void excluir(author author) {
        conectar();
        try {
            String query = "DELETE FROM library.author "
                    + "WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, author.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //consultar
    public int consultarPorNome(author author) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.author "
                + "WHERE nome_autor = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, author.getName());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        author.setId(idTmp);
        return idTmp;
    }
    
    public List<author> listar(String params) {
        conectar();
        List<author> listaAutores = new ArrayList<>();
        String query = "SELECT * from library.author " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery();

            while (lista.next()) {
                author author = new author();
                author.setId(lista.getInt("author_id"));
                author.setName(lista.getString("nome_autor"));
                listaAutores.add(author);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAutores;
    }
    
	
	
}
