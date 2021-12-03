package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class BaseDeDados {
	
	public void configuraBanco() {
		
	    // Connection Configuration
	    Properties connConfig = new Properties();
	    connConfig.setProperty("user", "root");
	    connConfig.setProperty("password", "123");
	    //connConfig.setProperty("useSsl", "true");
	    //connConfig.setProperty("serverSslCert", "/path/to/ca-bundle.pem");

	    // Create Connection to MariaDB Enterprise
	    try (Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3308", connConfig)) {

	        // Disable Auto-Commit
	        conn.setAutoCommit(false);

	        // Use Statement to Create library.books table
	        try (Statement stmt = conn.createStatement()) {
	        	
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.publisher (" +
	                       "publisher_id INT PRIMARY KEY AUTO_INCREMENT)" +
	                    "ENGINE=InnoDB;");
	            
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.author (" +
	                       "author_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "nome_autor VARCHAR(25))" +
	                    "ENGINE=InnoDB;");
	        	
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.books (" +
	                       "book_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "title VARCHAR(25)," +
	                       "email VARCHAR(100)," +
	                       "publisher_id int NOT NULL," +
	                       "author_id int NOT NULL," +
	                       "FOREIGN KEY (publisher_id) " +
	                       "REFERENCES publisher(publisher_id)," +
	                       "FOREIGN KEY (author_id) " +
	                       "REFERENCES author(author_id))" +
	                    "ENGINE=InnoDB;");
	            
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.shipper (" +
	                       "shipper_id INT PRIMARY KEY AUTO_INCREMENT)" +
	                    "ENGINE=InnoDB;");
	            
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.shipping_info (" +
	                       "shipping_info_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "rua VARCHAR(25)," +
	                       "bairro VARCHAR(25)," +
	                       "numero VARCHAR(25)," +
	                       "shipper_id int NOT NULL," +
	                       "FOREIGN KEY (shipper_id) " +
	                       "REFERENCES shipper(shipper_id))" +
	                    "ENGINE=InnoDB;");
	            
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.billing_info (" +
	                       "billing_info_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "numero_cartao VARCHAR(25)," +
	                       "parcelas VARCHAR(25))" +
	                    "ENGINE=InnoDB;");
	            
	            
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.user (" +
	                       "user_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "nome_usuario VARCHAR(25))" +
	                    "ENGINE=InnoDB;");
	            
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.order (" +
	                       "order_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "user_id INT NOT NULL," +
	                       "shipping_info_id INT NOT NULL," +
	                       "billing_info_id INT NOT NULL," +
	                       "FOREIGN KEY (user_id) " +
	                       "REFERENCES user(user_id)," +
	                       "FOREIGN KEY (shipping_info_id) " +
	                       "REFERENCES shipping_info(shipping_info_id)," +
	                       "FOREIGN KEY (billing_info_id) " +
	                       "REFERENCES billing_info(billing_info_id))" +
	                    "ENGINE=InnoDB;");
	            
	      /*    CREATE TABLE `order_book` (
							`order_book_id` INT(11) NULL DEFAULT NULL,
							`order_id` INT(11) NULL DEFAULT NULL,
							`book_id` INT(11) NULL DEFAULT NULL,
							INDEX `order_book_id` (`order_book_id`),
							INDEX `book_id` (`book_id`),
							INDEX `order_id` (`order_id`),
							CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
							CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`)
)
				COLLATE='utf8_general_ci'
				ENGINE=InnoDB */
	           
	            stmt.execute(
	                    "CREATE TABLE IF NOT EXISTS library.account (" +
	                       "account_id INT PRIMARY KEY AUTO_INCREMENT," +
	                       "user_id INT NOT NULL," +
	                       "emailAddress VARCHAR(25)," +
	                       "password VARCHAR(25)," +
	                       "FOREIGN KEY (user_id) " +
	                       "REFERENCES user(user_id))" +
	                    "ENGINE=InnoDB;");
	            
	//          conn.commit();
	          
	     }

	     // Catch SQL Exceptions for Queries
	     catch (SQLException exc) {
	          exc.printStackTrace();
	          conn.rollback();
	     }
	   }

	   // Catch SQL Exceptions from Connection
	   catch (SQLException e) {
	      e.printStackTrace();
	   }
		
	}


}


	
	

