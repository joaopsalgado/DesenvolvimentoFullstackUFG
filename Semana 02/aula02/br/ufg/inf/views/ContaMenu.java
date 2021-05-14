package aula02.br.ufg.inf.views;

import java.util.Scanner;

public class ContaMenu extends Tela {
	public ContaMenu(Scanner sc) {
		super(sc, 7);
	}
	
	@Override
	public void imprimirMenu() {
		this.println("[1] - Alterar Conta");
		this.println("[2] - Deposito");
		this.println("[3] - Saque");
		this.println("[4] - Transferência");
		this.println("[5] - Saldo");	
		this.println("[6] - Extrato");	
		this.println("[7] - Retornar");	
	}
	
	@Override
	public void opcao(int op) {
		
	}
}
