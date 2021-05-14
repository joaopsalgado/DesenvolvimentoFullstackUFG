package br.ufg.inf.fullstack.ctrl.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.ufg.inf.fullstack.model.entities.Aluno;
import br.ufg.inf.fullstack.model.repositories.AlunoRepository;
import br.ufg.inf.fullstack.ctrl.exception.AlunoException;

@Service
public class AlunoBusiness {

  @Autowired
  private AlunoRepository repository;

  public List<Aluno> findall(){
    return repository.findAll();
  }

  public Aluno findById(Integer id) throws AlunoException{
    this.validarEntradaPorId(id);
    Optional<Aluno> retorno = repository.findById(id);
    this.validarRetornoDoBanco(retorno);
    return retorno.get();
  }

  public Aluno insert(Aluno aluno) throws AlunoException {
    this.validarAluno(aluno);
    return repository.save(aluno);
  }

  public void delete(Integer id){
    repository.deleteById(id);
  }

  public Aluno update(Aluno aluno)  throws AlunoException {
    this.validarAluno(aluno);
    Aluno alunoUpd = repository.findById( aluno.getIdAluno() ).get();
    alunoUpd.setAtivo(aluno.getAtivo());
    alunoUpd.setCurso(aluno.getCurso());
    alunoUpd.setDtInicio(aluno.getDtInicio());
    alunoUpd.setPessoa(aluno.getPessoa());
    return this.repository.save(alunoUpd);
  }


  public List<Aluno> findByNnAluno(String str) throws AlunoException{
    List<Integer> idList = repository.findIDByNmAluno(str);
    List<Aluno> alunoList;

    alunoList = new ArrayList<Aluno>();

    for( Integer i : idList ){
      alunoList.add(this.findById(i));
    }
		return alunoList;
	}

  public List<Aluno> findByStatus(Boolean bl){
		return repository.findByStatus(bl);
	}

  private void validarAluno(Aluno aluno) throws AlunoException {

    if(aluno.getAtivo() == null ){
      throw new AlunoException("0112");
    }

    if(aluno.getDtInicio() == null ){
      throw new AlunoException("0113");
    }

	}

  private void validarEntradaPorId(Integer id) throws AlunoException{
    if(id == null || id < 0 ){ throw new AlunoException("0114"); }
  }

  private void validarRetornoDoBanco(Optional<Aluno> retorno) throws AlunoException{
    if(retorno.isEmpty()){ throw new AlunoException("0115");}
  }


}
