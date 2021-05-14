package br.ufg.inf.fullstack.ctrl.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import br.ufg.inf.fullstack.model.repositories.MatriculaRepository;
import br.ufg.inf.fullstack.model.entities.Matricula;
import br.ufg.inf.fullstack.ctrl.exception.MatriculaException;

@Service
public class MatriculaBusiness {

  @Autowired
  private MatriculaRepository repository;

  public List<Matricula> findall(){
    return repository.findAll();
  }

  public Matricula findById(Integer id) throws MatriculaException{
    this.validarEntradaPorId(id);
    Optional<Matricula> retorno = repository.findById(id);
    this.validarRetornoDoBanco(retorno);
    return retorno.get();
  }

  public Matricula insert(Matricula matricula) throws MatriculaException {
    this.validarMatricula(matricula);
    return repository.save(matricula);
  }

  public void delete(Integer id) throws MatriculaException{
    this.validarEntradaPorId(id);
    repository.deleteById(id);
  }

  public Matricula update(Matricula matricula) throws MatriculaException{
  
    this.validarEntradaPorId(matricula.getIdMatricula());
    Matricula matriculaUpd = repository.findById( matricula.getIdMatricula() ).get();
    matriculaUpd.setOferta( matricula.getOferta() );
    return repository.save(matriculaUpd);

  }

  private void validarMatricula(Matricula matricula) throws MatriculaException {

    if ( matricula.equals(null) ){
      throw new MatriculaException("0117");
    }

    if (matricula.getAluno().getAtivo().equals(false) ){
      throw new MatriculaException("0118");
    }

    if ( matricula.getIdMatricula().equals(null) || matricula.getIdMatricula() > 0 ){
      throw new MatriculaException("0119");
    }
    
    if ( matricula.getOferta().equals(null) ){
      throw new MatriculaException("0120");
    }

	}

  private void validarEntradaPorId(Integer id) throws MatriculaException{
    if(id == null || id < 0 ){ throw new MatriculaException("0114"); }
  }

  private void validarRetornoDoBanco(Optional<Matricula> retorno) throws MatriculaException{
    if(retorno.isEmpty()){ throw new MatriculaException("0115");}
  }


}
