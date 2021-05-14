package aula02.br.ufg.inf.model.entidade;

import java.util.Random;

import aula02.br.ufg.inf.model.enumerador.Categoria;

public class ContaEspecial extends Conta {
	
	private Double limite;
	
	public ContaEspecial (Pessoa cliente, Integer nrConta, Double saldo, Categoria ctg,
			Double limite) {
		super(cliente, nrConta, saldo, ctg);
		this.limite = limite;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}
	
	protected Boolean temSaldo(Double valor) {
		return (valor <= (this.getSaldo() + this.limite));
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\nLimite: " + this.getLimite());
		return sb.toString();
	}
}
