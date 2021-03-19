
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Menu {

	public void menuPrincipal(Scanner sc) {
		Integer escolha = 1;
		Conta conta;
		do {
			this.showMenuPrincipal();
			try {
				escolha = sc.nextInt();
				switch (escolha) {
				case 1:
					this.criarUmaConta(sc);
					break;
				case 2:
					conta = this.buscarConta(sc);
					this.menuConta(sc, conta);
					break;

				case 3:
					this.cadastrarCliente();
					break;
				case 4:
					this.showRelatorios(sc);
					break;
				case 5:
					System.out.println("5 – Sair");
					break;

				default:
					System.out.println("Opção Incorreta");
				}
			} catch (Exception e) {
				System.out.println("Opção Incorreta, sair.");
				escolha = 5;
			}
		} while (escolha != 5);

	}

   private void cadastrarCliente() {
        
		try {
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Nome:");
			
			String nome = sc.nextLine();

			System.out.println("Endereço:");
			
			String endereco = sc.nextLine();

			System.out.println("Tipo (CPF(1) ou CNPJ(2)): ");
			int tipo = sc.nextInt();

			if (tipo == 1) {
				System.out.print("CPF: ");
				Integer cpf = sc.nextInt();

				System.out.println("--- Informe a Data de Nascimento -----");
			    String dtAux = sc.next();
				Date dtNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dtAux);

				System.out.println("Genero: ");
				String genero = sc.nextLine();
				
				Pessoa pessoa = new Pessoa(Main.clientes.size(),nome,endereco);
				pessoa.adicionarClientePessoaFisica(Main.clientes.size(), nome, endereco, cpf, dtNascimento, genero);
			
			} else {
				System.out.println("CNPJ: ");
				Integer cnpj = sc.nextInt();
				
				System.out.println("Atividade: ");
				String atividade = sc.nextLine();
				
				Pessoa pessoa = new Pessoa(Main.clientes.size(), nome, endereco);
				pessoa.adicionarClientePessoaJuridica(Main.clientes.size(), nome, endereco, cnpj, atividade);
			} 


			try {
				System.in.read();
			} catch (Exception e) {}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCliente adicionado com sucesso... (Pressione enter)");
	}

	private void menuConta(Scanner sc, Conta conta) {

		Integer escolha = 1;
		do {
			this.showMenuConta(conta);
			try {
				escolha = sc.nextInt();
				Double vr;
				Integer id;
				switch (escolha) {
				case 1:
					conta.alterarConta(conta);
					break;
				case 2:
					System.out.println("Informe o Valor do Depósito");
					vr = sc.nextDouble();
					conta.depositar(vr);
					break;
				case 3:
					System.out.println("Informe o Valor para Saque");
					vr = sc.nextDouble();
					conta.sacar(vr);
					break;
				case 4:
					Conta dest = this.buscarConta(sc);
					System.out.println("Informe o Valor para Transferência");
					vr = sc.nextDouble();
					conta.transferir(vr, dest);
					break;
				case 5:
					System.out.println("-------------------------");
					System.out.println("--- SALDO: R$ " + conta.getSaldo());
					System.out.println("-------------------------");

					break;
				}

			} catch (Exception e) {
				System.out.println("Opção Incorreta, sair.");
				escolha = 6;
			}
		} while (escolha != 6);
	}

	private void showMenuPrincipal() {
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 – Abrir Nova Conta");
		System.out.println("2 – Selecionar Conta");
		System.out.println("3 – Cadastrar Cliente");
		System.out.println("4 – Relatórios");
		System.out.println("5 – Sair");
		System.out.println("-------------------------");
	}

	private void showMenuConta(Conta conta) {
		System.out.println("-------------------------");
		System.out.println("Cliente: " + conta.getCliente().getNome());
		System.out.println("Nr Conta: " + conta.getNrConta());
		System.out.println("Categoria: " + conta.getCategoria());
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 – Alterar Conta");
		System.out.println("2 – Deposito");
		System.out.println("3 – Saque");
		System.out.println("4 – Transferência");
		System.out.println("5 – Saldo");
		System.out.println("6 – Sair");
		System.out.println("-------------------------");
	}

	public Conta buscarConta(Scanner sc) {

		Conta conta = null;
		do {
			System.out.println("------------------------------");
			System.out.println("---Digite o número da Conta---");
			System.out.println("------------------------------");
			Integer escolha = sc.nextInt();
			for (Conta c : Main.contas) {

				if (c.getNrConta().equals(escolha)) {
					conta = c;
					break;
				}
			}
			if (conta == null) {
				System.out.println("------------------------------");
				System.out.println("-----Conta Não Encontrada-----");
				System.out.println("------------------------------");
			}

		} while (conta == null);

		return conta;
	}

	private void showRelatorios(Scanner sc) {
		String retornoContas = "";
		Double retornoTotalContas = 0.00;
		
		System.out.println("-------------------------");
		System.out.println("---Selecione Uma Opção---");
		System.out.println("-------------------------");
		System.out.println("1 – Saldo das Contas");
		System.out.println("2 – Total das Contas");
		System.out.println("-------------------------");
		
		Integer escolha = sc.nextInt();

		if(escolha.equals(1)) {
			System.out.println("Saldo das Contas:");
			System.out.println(buscarSaldoContas(retornoContas));
		}else if(escolha.equals(2)) {
			System.out.println("Total das Contas:");
			System.out.println(buscarTotalContas(retornoTotalContas));
		}
	}
	
	private String buscarSaldoContas(String retornoContas) {
		
		for (Conta c : Main.contas) {
				retornoContas += "\nConta número: " + c.getNrConta() + "|" + "Saldo: " + c.getSaldo();
		}
		return retornoContas;
	}
	
	private String buscarTotalContas(Double retornoTotalContas) {
		 
		for (Conta c : Main.contas) {
			retornoTotalContas +=  c.getSaldo();
		}
		return "Saldo Total das Contas é de R$: " + retornoTotalContas;
	}


	public double buscarSaldoContas(Scanner sc) throws Exception {

		double sadoConta = 0;
		try {
			
			for (Conta c : Main.contas) {
				sadoConta += c.getSaldo();
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return sadoConta;
	}
	
	public void criarUmaConta(Scanner sc) {
	
		try {
			
			Pessoa cliente = null;
			
			System.out.println("Informe o ID do cliente que deseja abrir a conta: ");
			Integer identificador = sc.nextInt();
			
			for (Pessoa item : Main.clientes) {
				if (item.getId().equals(identificador)) {
					System.out.println("Cliente encontrado!\n");
					cliente = item;
				}
			}

			if (cliente != null) {
			
				System.out.println("Cliente: " + cliente.getNome() + "\n");
				
				System.out.println("-------------------------");
				System.out.println("Selecione uma opção:\n");
				System.out.println("-------------------------");
				
				System.out.println("1 - Poupança");
				System.out.println("2 - Especial\n");
				
				int tipoConta = sc.nextInt();
				System.out.println("Cliente: " + cliente.getNome() + "\n");
				
				System.out.println("-------------------------");
				System.out.println("Selecione o tipo da conta:\n");
				System.out.println("-------------------------");
				
				System.out.println("1 - Simples");
				System.out.println("2 - Executiva");
				System.out.println("3 - Premium");
				System.out.println("4 - Personalite\n");
				
				System.out.println("Opção: ");
				int opcao = sc.nextInt();
				
				System.out.println("Insira seu número de conta! ");
				int numeroConta = sc.nextInt();
				
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
					Conta conta = new Conta(cliente, numeroConta,0.0,tipoContaEspecial);
					Main.contas.add(new ContaPoupanca(conta.getCliente(), conta.getNrConta(), conta.getSaldo(),0.0 ,tipoContaEspecial));
				} else {
					Conta conta = new Conta(cliente, numeroConta,0.0,tipoContaEspecial);
					Main.contas.add(new ContaEspecial(conta.getCliente(), conta.getNrConta(), conta.getSaldo(),0.0 ,tipoContaEspecial));
				}
				
				System.out.println("Conta criada com sucesso");
			} else {
				System.out.println("\nCliente não encontrado");
			}

			System.out.print("\nPressione enter (return) para continuar...");
			try {
				System.in.read();
			} catch (Exception e) {}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
