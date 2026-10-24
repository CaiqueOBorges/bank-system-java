package testes;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import entities.Conta;
import enums.TipoTransacao;
import exception.SaldoInsuficienteException;
import exception.TransferenciaInvalidaException;
import exception.ValorInvalidoException;

public class ContaTest {
	
	@Test
	public void saldoInicialDeveSerZero() {
		Conta conta = new Conta(1001);
		
		assertEquals(0.0, conta.consultarSaldo());
		
	}
	
	@Test
	public void depositoDeveAumentarSaldo() throws ValorInvalidoException {
		Conta conta = new Conta(1002);
		conta.depositar(100.0);
		
		assertEquals(100.0, conta.consultarSaldo());
	}
	
	@Test
	public void depositoNegativoDeveExcecao() {
		Conta conta = new Conta(1003);
		
		assertThrows(ValorInvalidoException.class, () ->
			{conta.depositar(-50); 
			});
	}
	
	@Test
	public void saqueDeveDiminuirSaldo() throws ValorInvalidoException, SaldoInsuficienteException {
		Conta conta = new Conta(1004);
		conta.depositar(100.0);
		conta.sacar(30.0);
		
		assertEquals(70.0, conta.consultarSaldo());
	}
	
	@Test
	public void saqueMaiorSaldo() throws ValorInvalidoException {
		Conta conta = new Conta(1005);
		conta.depositar(100);
		
		assertThrows(SaldoInsuficienteException.class, () ->
			{conta.sacar(130);
			});
		
		assertEquals(100.0, conta.consultarSaldo());
	}
	
	@Test
	public void transferenciaDeveAtualizarSaldos() throws ValorInvalidoException, SaldoInsuficienteException, TransferenciaInvalidaException {
		Conta conta = new Conta(1005);
		Conta conta2 = new Conta(1006);
		conta.depositar(100.0);
		conta2.depositar(50.0);
		
		conta.transferir(conta2, 30);
		
		assertEquals(70.0, conta.consultarSaldo());
		assertEquals(80.0, conta2.consultarSaldo());
	}
	
	@Test 
	public void transferenciaComValorInvalido() throws SaldoInsuficienteException, ValorInvalidoException {
		Conta conta = new Conta(1007);
		Conta conta2 = new Conta(1008);
		conta.depositar(100.0);
		conta2.depositar(50.0);
		
		
		assertThrows(ValorInvalidoException.class, () ->
				{conta.transferir(conta2, -30.0);
				});

		assertEquals(100.0, conta.consultarSaldo());
		assertEquals(50.0, conta2.consultarSaldo());
	}
	
	@Test
	public void transferenciaComSaldoInsuficiente() throws ValorInvalidoException, TransferenciaInvalidaException {
		Conta conta = new Conta(1009);
		Conta conta2 = new Conta(1010);
		conta.depositar(100.0);
		conta2.depositar(50.0);
		
		assertThrows(SaldoInsuficienteException.class, () -> 
		{conta.transferir(conta2, 150);
		});
		
		assertEquals(100.0, conta.consultarSaldo());
		assertEquals(50.0, conta2.consultarSaldo());
	}
	
	@Test
	public void transferenciaParaMesmaConta() throws ValorInvalidoException {
		Conta conta = new Conta(1011);
		conta.depositar(100.0);
		
		assertThrows(TransferenciaInvalidaException.class, () -> 
			{conta.transferir(conta, 30);
			});
		
		assertEquals(100.0, conta.consultarSaldo());
	}
	
	@Test
	public void transferenciaRegistrarTransacao() throws ValorInvalidoException, SaldoInsuficienteException, TransferenciaInvalidaException {
		Conta conta = new Conta(1012);
		Conta conta2 = new Conta(1013);
		conta.depositar(100.0);
		conta2.depositar(50.0);
		
		conta.transferir(conta2, 30.0);
		
		assertEquals(70.0, conta.consultarSaldo());
		assertEquals(80.0, conta2.consultarSaldo());
		assertEquals(TipoTransacao.PIX, conta.historicoTransacoes().get(1).getTipo());
		assertEquals(TipoTransacao.PIX, conta2.historicoTransacoes().get(1).getTipo());
	}
}


	