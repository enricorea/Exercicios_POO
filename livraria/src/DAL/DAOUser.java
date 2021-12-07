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

import BLL.user;

public class DAOUser {
	
	
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
    public void incluir(user user) throws SQLException {
        conectar();

        String query = "INSERT INTO library.user (nome_usuario) "
                + "VALUES (?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, user.getNome());    
        System.out.println(prep.toString());
        System.out.println(user.getNome());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(user user) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.user "
                    + "SET nome_usuario WHERE id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, user.getId());
            prep.setString(1, user.getNome());

            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(int id) {
        conectar();
        try {
            String query = "DELETE FROM library.user "
                    + "WHERE user_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, id);
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(user user) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.user "
                + "WHERE nome_usuario = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, user.getNome());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("user_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.setId(idTmp);
        return idTmp;
    }
    
    //qd cria um objeto de algum tipo, vc pode implementar construtores customizados onde vc já passa o parametro
    //o padrão é () sem nada, pq é omitido

    public List<user> listar(String params) {
    	
    	List<user> listaUser = new ArrayList<>();
    	
        conectar();
        String query = "SELECT * from library.user " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	user user = new user();
            	user.setId(lista.getInt("user_id"));
            	user.setNome(lista.getString("nome_usuario"));
            	listaUser.add(user);
                
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUser;
    }
}
