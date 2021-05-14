package aula02.br.ufg.inf.views;

import java.util.Calendar;
import java.util.Scanner;

import aula02.br.ufg.inf.model.entidade.Pessoa;
import aula02.br.ufg.inf.model.enumerador.Categoria;
import aula02.br.ufg.inf.servico.ContaServico;
import aula02.br.ufg.inf.servico.PessoaServico;


public class PrincipalMenu extends Tela {
	private PessoaServico pessoaService = PessoaServico.getInstance();
	private ContaServico contaService = ContaServico.getInstance();
	
	public PrincipalMenu(Scanner sc) {
		super(sc, 5);
	}
	
	@Override
	public void imprimirMenu() {
		this.println("[1] - Abrir Nova Conta");
		this.println("[2] - Selecionar Conta");
		this.println("[3] - Cadastrar Cliente");
		this.println("[4] - Relatórios");
		this.println("[5] - Sair");
	}
	
	@Override
	public void opcao(int op) {
		switch (op) {
		case 1:
			this.abrirConta();
			break;
		case 2:
			new ContaMenu(sc).execute();
			break;
		case 3:
			this.cadastrarCliente();
			break;
		case 4:
			new Extrato(sc).execute();
		default:
			break;
		}
	}
	
	public void abrirConta() {
		this.limparTela();
		
		try {
			this.print("Informe o documento (CPF ou CNPJ) do cliente: ");
			String documento = this.sc.next();
			
			Pessoa cliente = this.pessoaService.pesquisarCliente(documento);
			
			if (cliente != null) {
				this.limparTela();

				this.println("Cliente: " + cliente.getNome() + "\n");

				this.println("Selecione uma opção:\n");
				this.println("[1] - Poupança");
				this.println("[2] - Especial\n");
				int tipoConta = this.sc.nextInt();

				this.limparTela();


				this.println("Cliente: " + cliente.getNome() + "\n");
				this.println("Selecione o tipo da conta:\n");

				this.println("[1] - Simples");
				this.println("[2] - Executiva");
				this.println("[3] - Premium");
				this.println("[4] - Personalite\n");
				
				this.print("Opção: ");
				int opcao = this.sc.nextInt();
				
				Categoria tipoContaEspecial;
				
				if (opcao == 1) {
					tipoContaEspecial = Categoria.Simples;
				} else if (opcao == 2) {
					tipoContaEspecial = Categoria.Executiva;
				} else if (opcao == 3) {
					tipoContaEspecial = Categoria.Premium;
				} else {
					tipoContaEspecial = Categoria.Personalite;
				}

				if (tipoConta == 1) {
					this.contaService.abrirContaPoupanca(cliente, 0.0, tipoContaEspecial, 4.5);
				} else {
					this.contaService.abrirContaEspecial(cliente, 0.0, tipoContaEspecial, 1000.0);
				}
				
				this.println("Conta aberta com sucesso");
			} else {
				this.println("\nCliente não encontrado");
			}

			System.out.print("\nPressione enter (return) para continuar...");
			try {
				System.in.read();
			} catch (Exception e) {}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cadastrarCliente() {
		this.limparTela();

		
		try {
			this.sc.nextLine();

			this.print("Nome: ");
			String nome = this.sc.nextLine();

			this.print("Endereço: ");
			String endereco = this.sc.nextLine();

			this.print("Tipo (CPF(1) ou CNPJ(2)): ");
			int tipo = this.sc.nextInt();

			if (tipo == 1) {
				this.sc.nextLine();

				this.print("CPF: ");
				String cpf = this.sc.nextLine();

				final Calendar dtNascimento = Calendar.getInstance();
				dtNascimento.set(1942, 1, 8);

				this.print("Genero: ");
				String genero = this.sc.nextLine();

				this.pessoaService.adicionarClientePF(nome, endereco, cpf, dtNascimento, genero);
			} else {
				this.sc.nextLine();
				
				this.print("CNPJ: ");
				String cnpj = this.sc.nextLine();
				
				this.print("Atividade: ");
				String atividade = this.sc.nextLine();
				
				this.pessoaService.adicionarClientePJ(nome, endereco, cnpj, atividade);
			}

			System.out.print("\nCliente adicionado com sucesso... (Pressione enter)");
			try {
				System.in.read();
			} catch (Exception e) {}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
