package br.ufg.inf.aula4.app;

import br.ufg.inf.aula4.ctrl.MatriculaCtrl;
import br.ufg.inf.aula4.ctrl.exception.AlunoExection;
import br.ufg.inf.aula4.ctrl.exception.CursoExection;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;

public class aplication {

	public static void main(String[] args) throws CursoExection, PessoaExection, AlunoExection, OfertaExection {

		System.out.println("Começando por aqui");

		TesteApp testeApp = new TesteApp();
		
		//testeApp.testeCrudDisciplina(new DisciplinaCtrl());
		//testeApp.testeCrudPessoa(new PessoaCtrl());
		//testeApp.testeCrudProfessor(new ProfessorCtrl(), new PessoaCtrl());
		//testeApp.testeCrudOferta(new OfertaCtrl(), new DisciplinaCtrl(), new ProfessorCtrl());
		//testeApp.testeCrudCurso(new CursoCtrl());
		//testeApp.testeCrudAluno(new AlunoCtrl(), new PessoaCtrl(), new CursoCtrl());
		testeApp.testeCrudMatricula(new MatriculaCtrl());
		

	}
	
	

}
