import java.util.Calendar;
import java.util.Date;

public class Pessoa {
	Integer id;
	String nome;
	String endereco;
	
	public Pessoa(Integer id, String nome, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Pessoa pesquisarCliente(String documento) {
		Pessoa pessoa = null;
		for (Pessoa item : Main.clientes) {
			if (item instanceof PessoaFisica && ((PessoaFisica) item).getCpf().equals(documento)) {
				System.out.println("Pesquisei por uma Pessoa Física!\n");
				pessoa = item;
			} else if (item instanceof PessoaJuridica && ((PessoaJuridica) item).getCnpj().equals(documento)) {
				System.out.println("Pesquisei por uma Pessoa Jurídica!\n");
				pessoa = item;
			}

			if (pessoa != null) break;
		}

		return pessoa;
	}
	
	public void adicionarClientePessoaFisica(Integer pessoaTamanho,String nome, String endereco, Integer cpf, Date dtNascimento, String genero) {
		Main.clientes.add(new PessoaFisica(pessoaTamanho + 1, nome, endereco, cpf, dtNascimento, genero));
	}

	public void adicionarClientePessoaJuridica(Integer pessoaTamanho,String nome, String endereco, Integer cnpj, String atividade) {
		Main.clientes.add(new PessoaJuridica(pessoaTamanho +1, nome, endereco, cnpj, atividade));
	}
}
