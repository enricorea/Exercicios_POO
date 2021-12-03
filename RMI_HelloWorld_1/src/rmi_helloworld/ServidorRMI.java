/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_helloworld;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author felip
 */
public class ServidorRMI {
        public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, InterruptedException {
        DAORMIClient msg=new DAORMIClient("Ola mundo cruel");
        RMIServer sv=new RMIServer(msg);   
        
        //enviar e receber mensagem
        //msg.put("servidor na parada mandando pro cliente");
        
        Thread.sleep(15000); //uma espera para poder enviar ao cliente
        
        //System.out.println(""+msg.get()); //um recebimento
    }
}
