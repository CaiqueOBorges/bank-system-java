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
	        
	       int escolha = 0;
	        
	       while (!sc.hasNextInt()) {				System.out.println("Digite um número inteiro.");
				 sc.nextLine();
	       }
	       
	       escolha = sc.nextInt();
	        
		        while (escolha != 0) {
		        	
			        switch (escolha) {
			        case 1:
			        	System.out.println("\nVocê escolheu: Consultar Saldo");
			        	System.out.printf("Seu saldo é de: %.2f%n", conta1.consultarSaldo());
			        	
			        	break;
			        	
			        	
			        	
			        	
			        	
			        	
			        case 2:
			        	
			        	boolean depositoRealizado = false;
			        	System.out.println("Você escolheu: Depositar");
			        	System.out.printf("Quanto você quer depositar? ");
			        	double deposito = sc.nextDouble();
			        	
			        	
			        	while (!depositoRealizado) {
			        		try {
			        			conta1.depositar(deposito);
			        			depositoRealizado = true;
			        		}catch(ValorInvalidoException e) {
			        			System.out.println("\n" + e.getMessage());
			        			System.out.printf("Quanto você quer depositar? ");
			        			deposito = sc.nextDouble();
			        		}
			        	}
			        	
			        	break;
			        	
			        	
			        	
			        	
			        	
			        
			        case 3:
			        	System.out.println("Você escolheu: Consultar extrato");
			        	for (Transacao contas : conta1.historicoTransacoes()) {
			        		System.out.printf("%s - R$ %.2f%n", contas.getTipo(), contas.getValor());
			        	}
			        	break;
			        	
			        	
			        	
			        	
			        	
			        	
			        case 4:
			        	
			        	boolean saqueRealizado = false;
			        	System.out.println("Você escolheu: Sacar");
			        	System.out.printf("Insira o valor de Saque: R$");
			        	double saque = sc.nextDouble();
			        	
			        	
			        	while (!saqueRealizado) {
				        	try {
				        		conta1.sacar(saque);
				        		saqueRealizado = true;
				        	}catch(ValorInvalidoException e) {
				            	System.out.println("\n" + e.getMessage());
				            	System.out.printf("Insira o valor de Saque: R$ ");
					        	saque = sc.nextDouble();
				            }
				            catch(SaldoInsuficienteException e) {
				            	System.out.println("\n" + e.getMessage());
				            	System.out.printf("Insira o valor de Saque: R$ ");
					            saque = sc.nextDouble();
				            }
			        	}
			        	
			        	break;
			        	
			        	
			        	
			        	
			        	
			        	
			        default:
			        	System.out.println("Opção inválida");
			        	break;
			        	}
			        
		        
	    
		        
		        
		        
		        
			        System.out.println("\nEscolha uma opção: \n1- Consultar Saldo \n2- Depositar \n3- Consultar extrato \n4- Sacar \n0- Sair");
			        
			        while (!sc.hasNextInt()){
			        	System.out.println("Digite um numero inteiro.");
			        	sc.nextLine();
			        }
		        	escolha = sc.nextInt();
		        }
	        
	       
	        
	        System.out.println("Você saiu do sistema!");
	        
	        
	        
	    	
	    	sc.close();
	    }
    	catch(ContaDuplicadaException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
