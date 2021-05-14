package br.ufg.inf.fullstack.ctrl.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.ufg.inf.fullstack.model.entities.Curso;
import br.ufg.inf.fullstack.model.repositories.CursoRepository;
import br.ufg.inf.fullstack.ctrl.exception.CursoException;

@Service
public class CursoBusiness {

  @Autowired
  private CursoRepository repository;

  public List<Curso> findall(){
    return repository.findAll();
  }

  public Curso findById(Integer id) throws CursoException{
    this.validarEntradaPorId(id);
    Optional<Curso> retorno = repository.findById(id);
    this.validarRetornoDoBanco(retorno);
    return retorno.get();
  }

  public Curso insert(Curso curso) throws CursoException {
    this.validarCurso(curso);
    return repository.save(curso);
  }

  public void delete(Integer id) throws CursoException{
    this.validarEntradaPorId(id);
    repository.deleteById(id);
  }

  public Curso update(Curso curso)  throws CursoException {
    Curso cursoUpd = repository.findById( curso.getIdCurso() ).get();
    cursoUpd.setNmCurso(curso.getNmCurso());
    return repository.save(cursoUpd);
  }

  private void validarCurso(Curso curso) throws CursoException {

    if(curso.getNmCurso() == null || curso.getNmCurso().length() == 0){
      throw new CursoException("0116");
    }

	}

  private void validarEntradaPorId(Integer id) throws CursoException{
    if(id == null || id < 0 ){ throw new CursoException("0114"); }
  }

  private void validarRetornoDoBanco(Optional<Curso> retorno) throws CursoException{
    if(retorno.isEmpty()){ throw new CursoException("0115");}
  }

  public List<Curso> findByNnCurso(String str) {
    return repository.findByNmCurso(str);
  }

}
