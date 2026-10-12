package application;

import entities.Cliente;
import entities.Conta;
import exception.ClienteDuplicadoException;

public class Main {

    public static void main(String[] args) {
    	
    	
    	try {
	        Banco banco = new Banco();
	
	        Cliente cliente1 = new Cliente("João", "11111111111");
	        Cliente cliente2 = new Cliente("Maria", "11111111111");
	
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
	            for (Conta conta : clienteEncontrado.getContas()) {
	            	System.out.println(conta.getNumero());
	            }
	        } else {
	            System.out.println("Cliente não encontrado.");
	        }
	
	
	        if (contaEncontrada != null) {
	            System.out.println("Conta encontrada: " + contaEncontrada.getNumero());
	        } else {
	            System.out.println("Conta não encontrada.");
	        }
	        
	    	}
    	catch(ClienteDuplicadoException e) {
    		System.err.println(e.getMessage());
    	}
    }
}

