package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.TipoTransacao;
import exception.SaldoInsuficienteException;
import exception.TransferenciaInvalidaException;
import exception.ValorInvalidoException;

public class Conta {
	
	private Integer numero;
	private Double saldo = 0.0;
	private Cliente cliente;
	
	private List<Transacao> transacoes = new ArrayList<>();
	
	public Conta() {
	}
	
	public Conta(Integer numero) {
		this.numero = numero;
	}

	public Integer getNumero() {
		return numero;
	}

	
	public double consultarSaldo() {
		return saldo;
	}
	
	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	public void depositar(double valor) throws ValorInvalidoException {
		
		if (valor <= 0) {

			throw new ValorInvalidoException("Valor inserido é invalido");
		}
		
		
		saldo += valor;
			
		Transacao transacao = new Transacao(
				TipoTransacao.DEPOSITO,
				valor, 
				new Date()
				);
		
		transacoes.add(transacao);
		
	}
	
	
	
	public void sacar(double valor)throws SaldoInsuficienteException, ValorInvalidoException {
		
		if (valor <= 0) {
			throw new ValorInvalidoException("Valor inserido é invalido");
		}
		else if(valor > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente.");
		}
		
			saldo -= valor;
			
			Transacao transacao = new Transacao(
					TipoTransacao.SAQUE, 
					valor, 
					new Date()
					);
			
			transacoes.add(transacao);
		
		
	}
	
	
	
	
	
	
	public void transferir(Conta contaDestino, double valor) throws SaldoInsuficienteException, ValorInvalidoException, TransferenciaInvalidaException {
		
		if (valor <= 0) {
			throw new ValorInvalidoException("Valor inserido é invalido");
		}
		
		else if(valor > saldo) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		
		else if(contaDestino.getNumero().equals(this.getNumero())) {
			throw new TransferenciaInvalidaException("Transferencia Invalida");
		}
		
		
		saldo -= valor;
		contaDestino.saldo += valor;
		
		Transacao transacao = new Transacao(
				TipoTransacao.PIX, 
				valor, new Date(), 
				this, 
				contaDestino
				);
		
		
		transacoes.add(transacao);
		contaDestino.transacoes.add(transacao);
		
	}
	
	
	
	
	
	public List<Transacao> historicoTransacoes() {
		return transacoes;
	}
	
	
}
