package aula02.br.ufg.inf.model.entidade;

public class PessoaJuridica extends Pessoa {
	
	private String cnpj;
	private String atividade;
	
	public PessoaJuridica (Integer id, String nome, String endereco,
			String cnpj, String atividade) {
		super(id, nome, endereco);
		this.cnpj = cnpj;
		this.atividade = atividade;
	}
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\nCnpj: " + this.getCnpj());
		sb.append("\nAtividade: " + this.getAtividade());
		return sb.toString();
	}
	
	

}
