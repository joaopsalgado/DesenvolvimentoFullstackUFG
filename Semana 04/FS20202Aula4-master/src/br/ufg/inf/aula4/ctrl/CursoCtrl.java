package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.CursoExection;
import br.ufg.inf.aula4.ctrl.negocio.CursoNegocio;
import br.ufg.inf.aula4.model.entities.Curso;


public class CursoCtrl {

	CursoNegocio negocio = new CursoNegocio();

	public Curso inserir(Curso curso) {
		try {
			curso = negocio.inserir(curso);
			System.out.println("Curso cadastrado com sucesso: " + curso);
		} catch (CursoExection e) {
			System.out.println("Erro ao tentar cadastrar Curso.");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public List<Curso> buscaTodos() {
		List<Curso> curso = null;
		try {
			curso = negocio.buscaTodos();
		} catch (CursoExection e) {
			System.out.println("Erro tentar buscar os cursos cadastrados.");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public Curso buscaPorId(Integer id) {
		Curso curso = null;
		try {
			curso = negocio.buscaPorId(id);
		} catch (CursoExection e) {
			System.out.println("Erro tentar buscar curso do ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public Curso alterar(Curso curso) {
		try {
			curso = negocio.alterar(curso);
			System.out.println("Curso alterado com sucesso: " + curso);
		} catch (CursoExection e) {
			System.out.println("Erro ao tentar alterar curso com ID: " + curso.getIdCurso() + ".");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public void excluir(Integer id) {
		try {
			negocio.excluir(id);
			System.out.println("Curso excluído com sucesso.");
		} catch (CursoExection e) {
			System.out.println("Erro ao tentar excluir o Curso");
			System.out.println(e.getMessage());
		}
	}
}
