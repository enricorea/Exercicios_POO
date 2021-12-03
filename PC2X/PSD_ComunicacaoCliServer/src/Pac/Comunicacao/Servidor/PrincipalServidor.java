/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pac.Comunicacao.Servidor;

/**
 *
 * @author felip
 */
public class PrincipalServidor {
    public static void main(String[] args) {
        
        ServidorProdutor servidorProdutor = null;
        ServidorConsumidor servidorConsumidor;
        
        MensagemBuffer mensagensServidorEnvio=new MensagemBuffer(10);
        MensagemBuffer mensagensServidorRecepcao=new MensagemBuffer(10);
        ServidorSocket servidorSocket=new ServidorSocket(2301);
        servidorSocket.conect();
        
        if (servidorSocket.getSocket() != null) {
            servidorProdutor = new ServidorProdutor(mensagensServidorEnvio, servidorSocket.getSocket());
            servidorConsumidor = new ServidorConsumidor(mensagensServidorRecepcao, servidorSocket.getSocket());
        }
        
        while (true) {

            servidorProdutor.enviarMensagem("Ola sou servidor");
            servidorProdutor.enviarMensagem("Ola sou servidor");

            System.out.println(mensagensServidorRecepcao.get());
            System.out.println(mensagensServidorRecepcao.get());
        }
          
    }
}
