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
			exibirMenu();

			int escolha = lerInt(sc);

			while (escolha != 0) {

				switch (escolha) {
				case 1:
					System.out.println("\nVocê escolheu: Consultar Saldo");
					System.out.printf("Seu saldo é de: %.2f%n", conta1.consultarSaldo());

					break;

				case 2:

					realizarDeposito(conta1, sc);
					break;

				case 3:
					System.out.println("Você escolheu: Consultar extrato");
					for (Transacao contas : conta1.historicoTransacoes()) {
						System.out.printf("%s - R$ %.2f%n", contas.getTipo(), contas.getValor());
					}
					break;
					
					

				case 4:
					realizarSaque(conta1, sc);
					break;

				default:
					System.out.println("Opção inválida");
					break;
				}

				exibirMenu();
				escolha = lerInt(sc);

			}

			System.out.println("Você saiu do sistema!");

			sc.close();

		} catch (ContaDuplicadaException e) {
			System.out.println(e.getMessage());
		}
	}

	
	
	
	
	
	private static double lerDouble(Scanner sc) {

		while (!sc.hasNextDouble()) {
			System.out.println("Digite um valor valido:");
			sc.next();
		}

		return sc.nextDouble();

	}

	private static int lerInt(Scanner sc) {

		while (!sc.hasNextInt()) {
			System.out.println("Digite um valor valido");
			sc.next();
		}

		return sc.nextInt();
	}

	private static void exibirMenu() {
		System.out.println(
				"\nEscolha uma opção: \n1- Consultar Saldo \n2- Depositar \n3- Consultar extrato \n4- Sacar \n0- Sair");
	}
	
	private static void realizarDeposito(Conta conta, Scanner sc) {
		boolean depositoRealizado = false;
		System.out.println("Você escolheu: Depositar");
		System.out.printf("Quanto você quer depositar? ");

		double deposito = lerDouble(sc);

		while (!depositoRealizado) {
			try {
				conta.depositar(deposito);
				depositoRealizado = true;
			} catch (ValorInvalidoException e) {
				System.out.println("\n" + e.getMessage());
				System.out.printf("Quanto você quer depositar? ");

				deposito = lerDouble(sc);
			}
		}
	}
	
	private static void realizarSaque(Conta conta, Scanner sc) {
		boolean saqueRealizado = false;
		System.out.println("Você escolheu: Sacar");
		System.out.printf("Insira o valor de Saque: R$");
		double saque = lerDouble(sc);

		while (!saqueRealizado) {
			try {
				conta.sacar(saque);
				saqueRealizado = true;
			} catch (ValorInvalidoException e) {
				System.out.println("\n" + e.getMessage());
				System.out.printf("Insira o valor de Saque: R$ ");

				saque = lerDouble(sc);
			} catch (SaldoInsuficienteException e) {
				System.out.println("\n" + e.getMessage());
				System.out.printf("Insira o valor de Saque: R$ ");

				saque = lerDouble(sc);
			}
		}
	}
}
