package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoExection;
import br.ufg.inf.aula4.model.dao.AlunoDAO;
import br.ufg.inf.aula4.model.entities.Aluno;

public class AlunoNegocio {


		AlunoDAO dao = new AlunoDAO();
		public Aluno inserir(Aluno aluno) throws AlunoExection {
			this.validarAluno(aluno);
			dao.inserir(aluno);
			return aluno;
		}
		
		public List<Aluno> buscaTodos() throws AlunoExection{
			return dao.buscaTodos();	
		}
		
		public Aluno buscaPorId(Integer id) throws AlunoExection {
			
			return dao.buscaPorId(id);
		}
		
		public Aluno alterar(Aluno aluno) throws AlunoExection {		
			this.validarAluno(aluno);
			return dao.alterar(aluno);
		}
		
		public void excluir(Integer id) throws AlunoExection {
			dao.excluir(id);
		}
		
		private void validarAluno(Aluno aluno) throws AlunoExection {
			if (aluno.getPessoa() == null) {
				throw new AlunoExection("É necessário vicular uma pessoa ao aluno.");
			}
		}
}
