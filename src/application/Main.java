package application;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import entities.Cliente;
import entities.Conta;
import entities.Transacao;
import exception.ContaDuplicadaException;
import exception.SaldoInsuficienteException;
import exception.TransferenciaInvalidaException;
import exception.ValorInvalidoException;

public class Main {

	public static void main(String[] args) {

		try {
			
			Banco banco = new Banco();
			Cliente cliente1 = new Cliente("Joao", "1111111111");
			Conta conta1 = new Conta(1001);
			banco.cadastrarConta(cliente1, conta1);
			
			Cliente cliente2 = new Cliente("Maria", "22222222222");
			Conta conta2 = new Conta(1002);
			banco.cadastrarConta(cliente2, conta2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Scanner sc = new Scanner(System.in);

			System.out.printf("Bem vindo %s!", cliente1.getNome());
			exibirMenu();

			int escolha = lerInt(sc);

			while (escolha != 0) {

				switch (escolha) {
				case 1:
					Conta contaEscolhaCs = selecionarConta(sc, conta1, conta2);
					
					System.out.println("\nVocê escolheu: Consultar Saldo");
					System.out.printf("Seu saldo é de: %.2f%n", contaEscolhaCs.consultarSaldo());

					break;

				case 2:

					realizarDeposito(conta1, sc);
					break;

				case 3:
					Conta contaEscolhaE = selecionarConta(sc, conta1, conta2);
					
					System.out.println("Você escolheu: Consultar extrato");
					for (Transacao contas : contaEscolhaE.historicoTransacoes()) {
						System.out.printf("%s - R$ %.2f - %s%n", 
								contas.getTipo(),
								contas.getValor(), 
								sdf.format(contas.getData()));
					}
					break;
					
					

				case 4:
					realizarSaque(conta1, sc);
					break;
					
				case 5:
					
					realizarPix(conta1, conta2, sc);
					break;

				default:
					System.out.println("Opção inválida");
					break;
				}

				exibirMenu();
				escolha = lerInt(sc);

			}

			
			
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
				"\nEscolha uma opção: \n1- Consultar Saldo \n2- Depositar \n3- Consultar extrato \n4- Sacar \n5- Transferir via PIX \n0- Sair");
	}
	
	private static void realizarDeposito(Conta conta, Scanner sc) {
		boolean depositoRealizado = false;
		System.out.println("Você escolheu: Depositar");
		System.out.printf("Insira o valor de Deposito: R$");

		double deposito = lerDouble(sc);

		while (!depositoRealizado) {
			try {
				if(deposito == 0) {
					System.out.println("Voltando para o Menu...");
					depositoRealizado = true;
				}else {
					conta.depositar(deposito);
					depositoRealizado = true;
				}
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
				if (saque == 0) {
					System.out.println("Voltando para o Menu...");
					saqueRealizado = true;
				} else {
					conta.sacar(saque);
					saqueRealizado = true;
				}
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
	private static void realizarPix(Conta origem, Conta destino, Scanner sc) {
		boolean transferenciaRealizada = false;
		System.out.println("Insira um valor para transferencia: ");
		double transferencia = lerDouble(sc);
		
		while(!transferenciaRealizada) {
			try {
				if (transferencia == 0) {
					System.out.println("Voltando para o Menu...");
					transferenciaRealizada = true;
				} else {
					origem.transferir(destino, transferencia);
					transferenciaRealizada = true;
				}
			}catch(ValorInvalidoException e) {
				System.out.println("\n" + e.getMessage());
				System.out.println("Insira um valor valido: ");
				transferencia = lerDouble(sc);
			}catch(SaldoInsuficienteException e) {
				System.out.println("\n" + e.getMessage());
				System.out.println("Insira um valor valido: ");
				transferencia = lerDouble(sc);
			}
			catch(TransferenciaInvalidaException e) {
				System.out.println("\n" + e.getMessage());
				break;
			}
		}
	}
	
	private static Conta selecionarConta(Scanner sc, Conta conta1, Conta conta2) {
		
		System.out.println("Escolha uma conta!");
		int contaEscolha; 
		
		while (true) {
			System.out.println("1- Joao \n2-Maria");
			contaEscolha = lerInt(sc);
			
			if(contaEscolha == 1) {
				return conta1;
			}
			else if(contaEscolha == 2) {
				return conta2;
			}else {
				System.out.println("Opção invalida, tente novamente:\n");
			}
		}
	}
}
