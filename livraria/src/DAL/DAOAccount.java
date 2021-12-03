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

import BLL.account;
import BLL.user;


public class DAOAccount {
	
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
    public void incluir(account account) throws SQLException {
        conectar();

        String query = "INSERT INTO library.account (user_id, emailAddress, password) "
                + "VALUES (?,?,?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, account.getUsuario().getId());    
        prep.setString(2, account.getEmailAddress()); 
        prep.setString(3, account.getPassword());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(account account) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.books "
                    + "SET user_id=?, emailAddress=?, password=? WHERE account_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, account.getId());
            prep.setInt(1, account.getUsuario().getId());
            prep.setString(2, account.getEmailAddress()); //acessando ID do autor dentro do objeto autor
            prep.setString(3, account.getPassword());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(account account) {
        conectar();
        try {
            String query = "DELETE FROM library.account "
                    + "WHERE account_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, account.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(account account) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.account "
                + "WHERE emailAddress = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setString(1, account.getEmailAddress());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("account_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        account.setId(idTmp);
        return idTmp;
    }
    
    //qd cria um objeto de algum tipo, vc pode implementar construtores customizados onde vc já passa o parametro
    //o padrão é () sem nada, pq é omitido

    public List<account> listar(String params) {
    	//puxar lista de Account
    	DAOUser daoUser = new DAOUser();
    	
    	List<user> listaUser = daoUser.listar(""); //puxando lista p/ criar os objetos com seus objetos de autores respectivos
    	
        conectar();
        List<account> listaAccount = new ArrayList<>();
        String query = "SELECT * from library.account " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	account account = new account();
            	account.setId(lista.getInt("account_id"));
            	account.setEmailAddress(lista.getString("EmailAddress"));
                
                for (user user : listaUser) {
                    if (user.getId() == lista.getInt("user_id")) { //objeto da lista == author_id do banco de dados
                    	
                    	account.setUsuario(user);
                    	
                    	break;
                    }
                }
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAccount;
    }

}

