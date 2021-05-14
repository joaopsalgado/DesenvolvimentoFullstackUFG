package aula02.br.ufg.inf.servico;

import java.util.ArrayList;
import java.util.List;

import aula02.br.ufg.inf.model.entidade.Conta;
import aula02.br.ufg.inf.model.entidade.ContaEspecial;
import aula02.br.ufg.inf.model.entidade.ContaPoupanca;
import aula02.br.ufg.inf.model.entidade.Pessoa;
import aula02.br.ufg.inf.model.enumerador.Categoria;


public class ContaServico {
	private static ContaServico instance;
	
	private List<Conta> contas = new ArrayList<Conta>();
	
	public ContaServico() {}
	
	public List<Conta> getContas() {
		return contas;
	}

	public static ContaServico getInstance() {
		if (instance == null) {
			instance = new ContaServico();
		}
		
		return instance;
	}
	
	public void abrirContaPoupanca(Pessoa cliente, Double saldo, Categoria tipoConta, Double txCorrecao) {
		ContaPoupanca conta = new ContaPoupanca(cliente, this.contas.size() + 1, saldo, tipoConta, txCorrecao);
		this.contas.add(conta);
	}
	
	public void abrirContaEspecial(Pessoa cliente, Double saldo, Categoria tipoConta, Double limite) {
		ContaEspecial conta = new ContaEspecial(cliente, this.contas.size() + 1, saldo, tipoConta, limite);
		this.contas.add(conta);
	}
}
