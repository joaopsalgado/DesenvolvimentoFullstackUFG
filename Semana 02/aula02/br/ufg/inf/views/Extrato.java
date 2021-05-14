package aula02.br.ufg.inf.views;

import java.util.Scanner;

public class Extrato extends Tela {
	public Extrato(Scanner scanner) {
		super(scanner, 3);
	}

	@Override
	public void imprimirMenu() {
		this.println("[1] - Saldo das Contas");
		this.println("[2] - Total das Contas");
		this.println("[3] - Retornar");
	}
	
	@Override
	public void opcao(int op) {
		
	}
}
