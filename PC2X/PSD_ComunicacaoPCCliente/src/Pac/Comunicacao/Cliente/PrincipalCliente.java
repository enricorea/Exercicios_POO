/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pac.Comunicacao.Cliente;

/**
 *
 * @author felip
 */
public class PrincipalCliente {

    public static void main(String[] args) {
        ClienteProdutor clienteProdutor = null;
        ClienteConsumidor clienteConsumidor;

        MensagemBuffer mensagensClienteEnvio = new MensagemBuffer(10);
        MensagemBuffer mensagensClienteRecepcao = new MensagemBuffer(10);
        ClienteSocket clienteSocket = new ClienteSocket("127.0.0.1", 2301);

        clienteSocket.conect();

        if (clienteSocket.getSocket() != null) {
            clienteConsumidor = new ClienteConsumidor(mensagensClienteRecepcao, clienteSocket.getSocket());
            clienteProdutor = new ClienteProdutor(mensagensClienteEnvio, clienteSocket.getSocket());

        }
        while (true) {
            clienteProdutor.enviarMensagem("Ola sou Cliente");
            clienteProdutor.enviarMensagem("Ola sou Cliente");

            System.out.println(mensagensClienteRecepcao.get());
            System.out.println(mensagensClienteRecepcao.get());
        }

    }
}
