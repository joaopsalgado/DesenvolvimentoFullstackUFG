package aula02.br.ufg.inf.model.entidade;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class PessoaFisica extends Pessoa {
	
	private String cpf;
	private Calendar dtNascimento;
	private String genero;
	
	public PessoaFisica (Integer id, String nome, String endereco,
			String cpf, Calendar dtNascimento, String genero) {
		super(id, nome, endereco);
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Calendar getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getIdate() {
		LocalDate dataInicio = LocalDate.ofInstant(
					this.dtNascimento.toInstant(),
					this.dtNascimento.getTimeZone().toZoneId()
				);

		return Period.between(dataInicio, LocalDate.now()).getYears();
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("\nData de Nascimento: " + this.getDtNascimento());
		sb.append("\nCpf: " + this.getCpf());
		sb.append("\nGenero: " + this.getGenero());
		return sb.toString();
	}
	
}
