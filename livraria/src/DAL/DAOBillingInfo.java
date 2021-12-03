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
import BLL.billingInfo;

public class DAOBillingInfo {

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
    public void incluir(billingInfo billingInfo) throws SQLException {
        conectar();

        String query = "INSERT INTO library.billing_info (numero_cartao, parcelas, account_id) "
                + "VALUES (?,?,?)";    //o que inserir na query vai inserir em ordem na interrogação
        PreparedStatement prep = conection.prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setInt(1, billingInfo.getNumCartao());    // vai colocar title author email na ordem, pra isso que serve PreparedStatement
        prep.setDouble(2, billingInfo.getValor()); //acessando id do autor dentro do objeto autor
        prep.setInt(3, billingInfo.getConta().getId());
        prep.execute();
        conection.commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(billingInfo billingInfo) throws SQLException {
        conectar();
        try {
            String query = "UPDATE library.billing_info "
                    + "SET numero_cartao=?, parcelas=?, account_id=? WHERE billing_info_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(4, billingInfo.getId());
            prep.setInt(1, billingInfo.getNumCartao());
            prep.setDouble(2, billingInfo.getValor()); //acessando ID do autor dentro do objeto autor
            prep.setInt(3, billingInfo.getConta().getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(billingInfo billingInfo) {
        conectar();
        try {
            String query = "DELETE FROM library.billing_info "
                    + "WHERE billing_info_id=?";
            PreparedStatement prep = conection.prepareStatement(query);

            prep.setInt(1, billingInfo.getId());
            prep.execute();

            conection.commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    public int consultarPorTitulo(billingInfo billingInfo) {
        conectar();
        int idTmp = -1;
        String query = "SELECT * from library.billing_info "
                + "WHERE billing_info_id = ?";
        try {
            PreparedStatement prep = conection.prepareStatement(query);
            prep.setInt(1, billingInfo.getId());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("billing_info_id");
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        billingInfo.setId(idTmp);
        return idTmp;
    }
    
    //qd cria um objeto de algum tipo, vc pode implementar construtores customizados onde vc já passa o parametro
    //o padrão é () sem nada, pq é omitido

    public List<billingInfo> listar(String params) {
    	//puxar lista de autores
    	DAOAccount daoAccount = new DAOAccount(); //criação do objeto
    	
    	List<account> listaAccount = daoAccount.listar("");
    	
        conectar();
        List<billingInfo> listaBillingInfo = new ArrayList<>();
        String query = "SELECT * from library.billing_info " + params;

        try {
            PreparedStatement prep = conection.prepareStatement(query);
            ResultSet lista = prep.executeQuery(); 

            while (lista.next()) {
            	billingInfo billingInfo = new billingInfo();
            	billingInfo.setId(lista.getInt("billing_info_id"));
                
                for (account account : listaAccount) {
                    if (account.getId() == lista.getInt("account_id")) { 
                    	
                    	billingInfo.setAccount(account);
                    	
                    	break;
                    }
                }
              
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaBillingInfo;
    }

}

