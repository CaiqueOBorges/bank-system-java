package entities;

import java.util.Date;

import enums.TipoTransacao;

public class Transacao {
	
	private TipoTransacao tipo;
	private Double valor;
	private Date data;
	private Conta contaOrigem;
	private Conta contaDestino;
	
	public Transacao() {
	}
	
	

	public Transacao(TipoTransacao tipo, Double valor, Date data, Conta contaOrigem, Conta contaDestino) {
		super();
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
	}



	public Transacao(TipoTransacao tipo, Double valor, Date data) {
		this.tipo = tipo;
		this.valor = valor;
		this.data = data;
	}



	public Double getValor() {
		return valor;
	}
	
	public TipoTransacao getTipo() {
		return tipo;
	}

	public Date getData() {
		return data;
	}



	public Conta getContaOrigem() {
		return contaOrigem;
	}



	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}



	public Conta getContaDestino() {
		return contaDestino;
	}



	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
	
	
}
