package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.MatriculaExection;
import br.ufg.inf.aula4.model.dao.MatriculaDAO;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaNegocio {


		MatriculaDAO dao = new MatriculaDAO();
		
		public Matricula inserir(Matricula matricula) throws MatriculaExection {
			this.validarMatricula(matricula);
			dao.inserir(matricula);
			return matricula;
		}
		
		public List<Matricula> buscaTodos() throws MatriculaExection{
			return dao.buscaTodos();	
		}
		
		public Matricula buscaPorId(Integer id) throws MatriculaExection {
			
			return dao.buscaPorId(id);
		}
		
		public Matricula alterar(Matricula matricula) throws MatriculaExection {		
			this.validarMatricula(matricula);
			return dao.alterar(matricula);
		}
		
		public void excluir(Integer id) throws MatriculaExection {
			dao.excluir(id);
		}
		
		private void validarMatricula(Matricula matricula) throws MatriculaExection {
			if (matricula.getAluno().getIdAluno() == null) {
				throw new MatriculaExection("É necessário vincular um aluno a matricula!");
			}
		}
}
