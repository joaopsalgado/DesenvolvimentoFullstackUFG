package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.CursoExection;
import br.ufg.inf.aula4.model.dao.CursoDAO;
import br.ufg.inf.aula4.model.entities.Curso;

public class CursoNegocio {


		CursoDAO dao = new CursoDAO();
		
		public Curso inserir(Curso curso) throws CursoExection {
			this.validarCurso(curso);
			dao.inserir(curso);
			return curso;
		}
		
		public List<Curso> buscaTodos() throws CursoExection{
			return dao.buscaTodos();	
		}
		
		public Curso buscaPorId(Integer id) throws CursoExection {
			
			return dao.buscaPorId(id);
		}
		
		public Curso alterar(Curso curso) throws CursoExection {		
			this.validarCurso(curso);
			return dao.alterar(curso);
		}
		
		public void excluir(Integer id) throws CursoExection {
			dao.excluir(id);
		}
		
		private void validarCurso(Curso curso) throws CursoExection {
			if (curso.getNmCurso() == null) {
				throw new CursoExection("É necessário criar um novo Curso com nome! ");
			}
		}
}
