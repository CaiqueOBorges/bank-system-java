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
        	
        	
        	
        	
        	
            // 1. Cadastro de clientes e contas
            Banco banco = new Banco();

            Cliente cliente1 = new Cliente("João", "11111111111");
            Cliente cliente2 = new Cliente("Maria", "2222222222");

            Conta conta1 = new Conta(1001);
            Conta conta2 = new Conta(1002);

            banco.adicionarCliente(cliente1);
            banco.cadastrarConta(cliente1, conta1);

            banco.adicionarCliente(cliente2);
            banco.cadastrarConta(cliente2, conta2);


            
            
            
            
            // 2. Testes de depósitos
            conta1.depositar(120);
            conta1.sacar(20);
            System.out.println("Saldo apos o saque: R$ " + conta1.getSaldo());
            conta2.depositar(50);

            try {
                conta1.depositar(-50);
            } catch (ValorInvalidoException e) {
                System.err.println("\nDepósito inválido: " + e.getMessage());
            }

            
            
            // 2.1. Testes de saques
            try {
            	conta1.sacar(-10);
            } catch (ValorInvalidoException e) {
            	System.err.println("\nSaque inválido " + e.getMessage());
            	System.out.println("Saldo da conta 1001: R$ " + conta1.getSaldo());
            }
            
            try {
            	conta1.sacar(150);
            	
            } catch (SaldoInsuficienteException e) {
            	System.err.println("\nSaldo insuficiente " + e.getMessage());
            	System.out.println("Saldo da conta 1001: R$ " + conta1.getSaldo());
            	System.out.println("Quantidade de transações: "  + conta1.historicoTransacoes().size());
            }
            
            

            // 2.2. Testes de transferências
            try {
                conta1.transferir(conta2, 150);
            } catch (SaldoInsuficienteException e) {
                System.err.println("\nTransferência inválida: " + e.getMessage());
                System.out.println("Saldo da conta 1001: R$ " + conta1.getSaldo());
                System.out.println("Saldo da conta 1002: R$ " + conta2.getSaldo());
            }

            conta1.transferir(conta2, 30);
            
            System.out.println("\nHistórico da conta 1002:");

            for (Transacao transacao : conta2.historicoTransacoes()) {
                System.out.println(
                    transacao.getTipo() + " - R$ " + transacao.getValor()
                );
            }

            System.out.println("\nSaldos após transferência válida:");
            System.out.println("Conta 1001: R$ " + conta1.getSaldo());
            System.out.println("Conta 1002: R$ " + conta2.getSaldo());


            
            
            
            
            
            
            // 3. Consulta do histórico de transações
            System.out.println("\nHistórico da conta 1001:");

            for (Transacao transacao : conta1.historicoTransacoes()) {
                System.out.println(
                    transacao.getTipo() + " - R$ " + transacao.getValor()
                );
            }

            
            
            
            
            
            
            
            

            // 4. Busca de cliente
            Cliente clienteEncontrado = banco.buscarCliente("11111111111");

            if (clienteEncontrado != null) {
                System.out.println("\nCliente encontrado: "
                    + clienteEncontrado.getNome());

                for (Conta conta : clienteEncontrado.getContas()) {
                    System.out.println("Conta: " + conta.getNumero());
                }
            } else {
                System.out.println("Cliente não encontrado.");
            }


            
            
            
            
            
            
            // 5. Busca de conta
            Conta contaEncontrada = banco.buscarConta(1001);

            if (contaEncontrada != null) {
                System.out.println("Conta encontrada: "
                    + contaEncontrada.getNumero());
            } else {
                System.out.println("Conta não encontrada.");
            }


        } catch (ClienteDuplicadoException e) {
            System.err.println(e.getMessage());

        } catch (ContaDuplicadaException e) {
            System.err.println(e.getMessage());

        } catch (ValorInvalidoException e) {
            System.err.println(e.getMessage());

        } catch (SaldoInsuficienteException e) {
            System.err.println(e.getMessage());

        } catch (TransferenciaInvalidaException e) {
            System.err.println(e.getMessage());
        }
    }
}
