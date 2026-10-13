package application;

import entities.Cliente;
import entities.Conta;
import entities.Transacao;
import exception.ClienteDuplicadoException;
import exception.ContaDuplicadaException;
import exception.SaldoInsuficienteException;
import exception.TransferenciaInvalidaException;
import exception.ValorInvalidoException;

public class Main {

    public static void main(String[] args) {
    	
    	
    	try {
	        Banco banco = new Banco();
	
	        Cliente cliente1 = new Cliente("João", "11111111111");
	        Cliente cliente2 = new Cliente("Maria", "2222222222");
	
	        Conta conta1 = new Conta(1001);
	        Conta conta2 = new Conta(1002);
	        
	        
	        banco.adicionarCliente(cliente1);
	        banco.cadastrarConta(cliente1, conta1);
	        banco.adicionarCliente(cliente2);
	        banco.cadastrarConta(cliente2, conta2);
	        
	        conta1.depositar(100);
	        conta2.depositar(50);
	        try {
	            conta1.depositar(-50);
	        } catch (ValorInvalidoException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        System.out.println(conta1.historicoTransacoes().size());
	        
	        
	        conta1.transferir(conta2, 30);
	        System.out.println(conta1.getSaldo());
	        System.out.println(conta2.getSaldo());
	        System.out.println(conta1.historicoTransacoes().size());
	        
	        
	        for (Transacao transacao : conta1.historicoTransacoes()) {
	        	System.out.println();
	        	System.out.println(transacao.getTipo() + " R$" + transacao.getValor());
	        }
	        
	        System.out.println();
	
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
    	catch(ContaDuplicadaException e) {
    		System.err.println(e.getMessage());
    	}
    	catch(ValorInvalidoException e) {
    		System.err.println(e.getMessage());
    	}
    	catch(SaldoInsuficienteException e) {
    		System.err.println(e.getMessage());
    	}
    	catch(TransferenciaInvalidaException e) {
    		System.err.println(e.getMessage());
    	}
    }
    
}


