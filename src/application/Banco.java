package application;

import java.util.ArrayList;
import java.util.List;

import entities.Cliente;
import entities.Conta;

public class Banco {
	
	private List<Conta> contas = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	
	
	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void adicionarConta(Conta conta) {
		contas.add(conta);
	}
	
	public Cliente buscarCliente(String cpf) {
		for(int i = 0; i < clientes.size(); i++) {
			if (cpf.equals(clientes.get(i).getCpf())) {
				return clientes.get(i);
			}
		}
		return null;
	}
	
	public Conta buscarConta(Integer id) {
		for(int i = 0; i < contas.size(); i++) {
			if (id.equals(contas.get(i).getNumero())) {
				return contas.get(i);
			}
		}
		return null;
		
	}
	
}
