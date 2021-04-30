package br.ufg.inf.fullstack.ctrl.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import br.ufg.inf.fullstack.model.repositories.CursoRepository;
import br.ufg.inf.fullstack.model.entities.Curso;

@Service
public class CursoBusiness {

  @Autowired
  private CursoRepository repository;

  public List<Curso> findAll(){
    return repository.findAll();
  }

  public Curso findById(Integer id){
    Optional<Curso> retorno = repository.findById(id);
    return retorno.get();
  }

  public Curso insert(Curso curso){
    return repository.save(curso);
  }

  public void delete(Integer id){
    repository.deleteById(id);
  }

  public Curso update(Curso curso){
    Curso cursoUpd = repository.findById(
      curso.getIdCurso()
    ).get();

    cursoUpd.setNmCurso(curso.getNmCurso());
    return repository.save(cursoUpd);

  }

  
}
