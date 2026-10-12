package application;

import entities.Cliente;
import entities.Conta;

public class Main {

    public static void main(String[] args) {

        Banco banco = new Banco();

        Cliente cliente1 = new Cliente("João", "11111111111");
        Cliente cliente2 = new Cliente("Maria", "22222222222");

        Conta conta1 = new Conta(1001);
        Conta conta2 = new Conta(1002);
        
        
        banco.adicionarCliente(cliente1);
        banco.cadastrarConta(cliente1, conta1);
        
        banco.adicionarCliente(cliente2);
        banco.cadastrarConta(cliente2, conta2);

        Cliente clienteEncontrado = banco.buscarCliente("11111111111");   
        Conta contaEncontrada = banco.buscarConta(1001);
        
        
        
        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }


        if (contaEncontrada != null) {
            System.out.println("Conta encontrada: " + contaEncontrada.getNumero());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
}

