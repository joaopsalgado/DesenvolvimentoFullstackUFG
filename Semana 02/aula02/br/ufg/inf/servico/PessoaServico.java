package aula02.br.ufg.inf.servico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import aula02.br.ufg.inf.model.entidade.Pessoa;
import aula02.br.ufg.inf.model.entidade.PessoaFisica;
import aula02.br.ufg.inf.model.entidade.PessoaJuridica;



public class PessoaServico {
	private static PessoaServico instance;
	
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	
	public PessoaServico() {}
	
	public static PessoaServico getInstance() {
		if (instance == null) {
			instance = new PessoaServico();
		}
		
		return instance;
	}
	
	public Pessoa pesquisarCliente(String documento) {
		Pessoa pessoa = null;

		for (Pessoa item : this.pessoas) {
			if (item instanceof PessoaFisica && ((PessoaFisica) item).getCpf().equals(documento)) {
				pessoa = item;
			} else if (item instanceof PessoaJuridica && ((PessoaJuridica) item).getCnpj().equals(documento)) {
				System.out.println("Entrei na PJ");
				pessoa = item;
			}

			if (pessoa != null) break;
		}

		return pessoa;
	}

	public void adicionarClientePF(String nome, String endereco, String cpf, Calendar dtNascimento, String genero) {
		Pessoa cliente = new PessoaFisica(this.pessoas.size() + 1, nome, endereco, cpf.trim(), dtNascimento, genero);
		this.pessoas.add(cliente);
	}

	public void adicionarClientePJ(String nome, String endereco, String cnpj, String atividade) {
		Pessoa cliente = new PessoaJuridica(this.pessoas.size() + 1, nome, endereco, cnpj, atividade);
		this.pessoas.add(cliente);
	}
}
