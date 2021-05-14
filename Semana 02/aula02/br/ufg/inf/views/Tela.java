package aula02.br.ufg.inf.views;

import java.util.Scanner;

public abstract class Tela {
	protected int exitOption;
	protected Scanner sc;

	public abstract void imprimirMenu();
	public abstract void opcao(int op);

	Tela(Scanner sc, int exitOption) {
		this.sc = sc;
		this.exitOption = exitOption;
	}

	public void execute() {
		int selectedOption = 0;

		do {
			this.limparTela();

			this.println("Selecione uma das opções abaixo:\n");
			this.imprimirMenu();

			try {
				System.out.print("\nOpção: ");
				selectedOption = this.sc.nextInt();
			} catch (Exception e) {
				sc = new Scanner(System.in);
			}

			this.opcao(selectedOption);
		} while (selectedOption != this.exitOption);
	}


	protected void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	protected void print(String str) {
		System.out.print(str);
	}

	protected void println(String str) {
		System.out.println(str);
	}

	protected void printf(String format, Object ... args) {
		System.out.printf(format, args);
	}
}