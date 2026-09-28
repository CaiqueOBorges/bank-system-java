package application;

import entities.Cliente;
import entities.Conta;

public class Main {

	public static void main(String[] args) {
	   
		
		Cliente cliente1 = new Cliente("João", "11111111111");
	    Cliente cliente2 = new Cliente("Maria", "22222222222");

	    Conta conta1 = new Conta(1001);
	    Conta conta2 = new Conta(1002);

	    cliente1.adicionarConta(conta1);
	    cliente2.adicionarConta(conta2);

	    try {

	        conta1.depositar(1000);

	        System.out.println("Saldo conta 1: " + conta1.consultarSaldo());
	        System.out.println("Saldo conta 2: " + conta2.consultarSaldo());

	        conta1.sacar(200);

	        System.out.println("\nDepois do saque:");
	        System.out.println("Saldo conta 1: " + conta1.consultarSaldo());

	        conta1.transferir(conta2, 300);

	        System.out.println("\nDepois do PIX:");
	        System.out.println("Saldo conta 1: " + conta1.consultarSaldo());
	        System.out.println("Saldo conta 2: " + conta2.consultarSaldo());

	    } catch (Exception e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	}

}
