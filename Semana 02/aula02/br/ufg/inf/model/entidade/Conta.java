package aula02.br.ufg.inf.model.entidade;
import aula02.br.ufg.inf.model.enumerador.Categoria;

public abstract class Conta {
	
	private Pessoa cliente;
	private Integer nrConta;
	private Double saldo;
	private Categoria ctg;
	
	
	public Conta (Pessoa cliente, Integer nrConta, Double saldo, Categoria ctg) {
		this.cliente = cliente;
		this.nrConta = nrConta;
		this.saldo = saldo;
		this.ctg = ctg;
	}
	
	
	public Pessoa getCliente() {
		return cliente;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public Integer getNrConta() {
		return nrConta;
	}
	public void setNrConta(Integer nrConta) {
		this.nrConta = nrConta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public Categoria getCtg() {
		return ctg;
	}


	public void setCtg(Categoria ctg) {
		this.ctg = ctg;
	}


	protected Boolean temSaldo(Double valor) {
		return (valor <= this.saldo);
	}
	
	private void debitar(Double valor) {
		this.saldo -= valor;
	}
	private void acrescentar(Double valor) {
		this.saldo += valor;
	}
	
	public void sacar(Double valor) {
		if(this.temSaldo(valor)) {
			this.debitar(valor);
			System.out.println("Saque realizado com sucesso! Novo Saldo: "+this.saldo);
		}else {
			System.out.println("Saldo Insuficiente");
		}
	}
	
	public void depositar(Double valor) {
		this.acrescentar(valor);
		System.out.println("Depósito realizado com sucesso! Novo Saldo: "+this.saldo);
	}
	
	public void transferir(Double valor, Conta destino) {
		if(this.temSaldo(valor)) {
			this.debitar(valor);
			destino.acrescentar(valor);
			System.out.println("Transferência com sucesso! Novo Saldo: "+this.saldo);
		}else {
			System.out.println("Saldo Insuficiente");
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nDados Cadastrais: \n" + this.getCliente().toString());
		sb.append("\nNúmero da Conta: " + this.getNrConta());
		sb.append("\nSaldo: " + this.getSaldo());
		return sb.toString();
	}
	
	

}
