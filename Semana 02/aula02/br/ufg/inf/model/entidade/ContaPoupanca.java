package aula02.br.ufg.inf.model.entidade;

import aula02.br.ufg.inf.model.enumerador.Categoria;

public class ContaPoupanca extends Conta {
	
	private Double txCorrecao;
	
	public ContaPoupanca (Pessoa cliente, Integer nrConta, Double saldo, Categoria ctg, Double txCorrecao) {
		super(cliente, nrConta, saldo, ctg);
		this.txCorrecao = txCorrecao;
	}

	public Double getTxCorrecao() {
		return txCorrecao;
	}

	public void setTxCorrecao(Double txCorrecao) {
		this.txCorrecao = txCorrecao;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\nTaxa de Correção: " + this.getTxCorrecao());
		return sb.toString();
	}

}
