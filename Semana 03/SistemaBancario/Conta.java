import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Conta {

	private Pessoa cliente;
	private Integer nrConta;
	protected Double saldo;
	protected Categoria categoria;
	static List<Conta> contas = new ArrayList<Conta>();
	
	public Conta(Pessoa cliente, Integer nrConta, Double saldo,Categoria categoria) {
		super();
		this.cliente = cliente;
		this.nrConta = nrConta;
		this.saldo = saldo;
		this.categoria = categoria;
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
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		System.out.println("Depósito realizado com sucesso!");
		System.out.println("Novo Saldo: "+this.saldo);
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
	
	public void abrirContaPoupanca(Pessoa cliente,Integer numConta, Double saldo,  Double txCorrecao,Categoria tipoConta) {
		ContaPoupanca conta = new ContaPoupanca(cliente, numConta, saldo, txCorrecao,tipoConta);
	}
	
	public void abrirContaEspecial(Pessoa cliente,Integer numConta, Double saldo, Double limite, Categoria tipoConta) {
		ContaEspecial conta = new ContaEspecial(cliente, numConta, saldo, limite, tipoConta);
	}
	

	public void alterarConta(Conta conta) {
	    Categoria categoria = null;
		Scanner sc = new Scanner(System.in);

	try {		
			System.out.println("1 - Alterar Categoria");
			Integer escolha = sc.nextInt();
			
			if(escolha == 1) {
				System.out.println("---- Escolha qual Categoria deseja alterar sua conta! -----");
				System.out.println("1 - Simples");
				System.out.println("2 - Executiva");
				System.out.println("3 - Premium");
				System.out.println("4 - Personalite");
				
				Integer cat = sc.nextInt();
				
				if(cat == 1 && conta != null) {
				  conta.categoria = categoria.Simples;	
					System.out.println("Categoria alterada para simples!");
				}
				
				if(cat == 2 && conta != null) {
				  conta.setCategoria(categoria.Executiva);	
				   System.out.println("Categoria alterada para executiva!");
				}
				
				if(cat == 3 && conta != null) {
				 conta.setCategoria(categoria.Premium);	
				 System.out.println("Categoria alterada para Premium!");
				}
				
				if(cat == 4 && conta != null) {
				conta.setCategoria(categoria.Personalite);	
				System.out.println("Categoria alterada para Personalite!");
		
				}
		 }else {
				System.out.println("Não foi possivel verificar sua escolha!");
		 }
	}catch(Exception e) {
		System.out.println("Não foi possivel verificar sua escolha!" + e.getMessage().toString());
	}
	
}
	}













