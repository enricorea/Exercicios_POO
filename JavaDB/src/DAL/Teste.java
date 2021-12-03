/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Models.Book;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author felip
 */
public class Teste {
    public static void main(String[] args) throws SQLException{
        Book b1=new Book();
        b1.setTitle("A volta ao mundo");
        b1.setAuthor("julio Verne");
        b1.setEmail("editora@verne.br");
        try{
        DAOBook db=new DAOBook();
        db.incluir(b1);
        
        List<Book> books=db.listar("");
        
        
               
        for(Book b:books){
            System.out.println(b.getId());
            System.out.println(b.getTitle());
        }
        
        Book b2=new Book();
        b2.setTitle("A volta ao mundo");
        int id=db.consultarPorTitulo(b2);
        db.excluir(b2);

            
        
        for(Book b:books){
            System.out.println(b.getId());
            System.out.println(b.getTitle());
        }
        
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
