package application;

import java.util.Scanner;

import entities.Cliente;
import entities.Conta;
import entities.Transacao;
import exception.ContaDuplicadaException;
import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;

public class Main {

    public static void main(String[] args) {
    	
    	try {
    		
	    	Banco banco = new Banco();
	    	Cliente cliente1 = new Cliente("Joao", "1111111111");
	    	Conta conta1 = new Conta(1001);
	    	banco.cadastrarConta(cliente1, conta1);
	    	
	    	
	    	
	    	Scanner sc = new Scanner(System.in);
	
	        System.out.printf("Bem vindo %s!", cliente1.getNome());
	        System.out.println("\nEscolha uma opção: \n1- Consultar Saldo \n2- Depositar \n3- Consultar extrato \n4- Sacar \n0- Sair");
	        int escolha = sc.nextInt();
	    	
	        
	        while (escolha != 0) {
	        	
		        switch (escolha) {
		        case 1:
		        	System.out.println("Você escolheu: Consultar Saldo");
		        	System.out.printf("Seu saldo é de: %.2f%n", conta1.consultarSaldo());
		        	
		        	break;
		        	
		        	
		        	
		        	
		        	
		        	
		        case 2:
		        	System.out.println("Você escolheu: Depositar");
		        	System.out.printf("Quanto você quer depositar? ");
		        	double deposito = sc.nextDouble();
		        	
		        	while (deposito <= 0) {
		        		System.out.println("\nValor invalido. Insira um valor acima de 0");
			        	System.out.println("Quanto você quer depositar?\n");
			        	deposito = sc.nextDouble();
		        	}
		        	
		        	conta1.depositar(deposito);
		        	break;
		        	
		        	
		        	
		        	
		        	
		        
		        case 3:
		        	System.out.println("Você escolheu: Consultar extrato");
		        	for (Transacao contas : conta1.historicoTransacoes()) {
		        		System.out.printf("%s - R$ %.2f%n", contas.getTipo(), contas.getValor());
		        	}
		        	break;
		        	
		        	
		        	
		        	
		        	
		        	
		        case 4:
		        	System.out.println("Você escolheu: Sacar");
		        	System.out.printf("Insira o valor de Saque: R$");
		        	double saque = sc.nextDouble();
		        	
		        	while (saque <= 0 || saque > conta1.getSaldo()){
			        	if (saque <=0){
			        		System.out.println("\nValor inserido é invalido");
				        	System.out.printf("Insira o valor de Saque: R$");
				        	saque = sc.nextDouble();
			        	}
			        	else if(saque > conta1.getSaldo()) {
			        		System.out.println("\nSaldo insuficiente");
				        	System.out.printf("Insira o valor de Saque: R$");
				        	saque = sc.nextDouble();
			        	}
		        	}
		        	
		        	conta1.sacar(saque);
		        	break;
		        	
		        	
		        	
		        	
		        	
		        	
		        default:
		        	System.out.println("Opção inválida");
		        	break;
		        	}
		        
		        System.out.println("\nEscolha uma opção: \n1- Consultar Saldo \n2- Depositar \n3- Consultar extrato \n4- Sacar \n0- Sair");
	        	escolha = sc.nextInt();
	        }
	        
	        
	        System.out.println("Você saiu do sistema!");
	    	
	    	sc.close();
	    }
    	catch(ContaDuplicadaException e) {
    		System.out.println(e.getMessage());
    	}
    	catch(ValorInvalidoException e) {
    		System.out.println(e.getMessage());
    	}
    	catch(SaldoInsuficienteException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
