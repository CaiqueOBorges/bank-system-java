package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
	private String cpf;
	
	
	private List<Conta> contas = new ArrayList<>();
	
	
	public Cliente() {
	}


	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}
	
	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}
	
	public List<Conta> getContas(){
		return contas;
	}
	
}
