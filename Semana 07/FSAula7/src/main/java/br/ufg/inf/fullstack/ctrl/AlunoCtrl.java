package br.ufg.inf.fullstack.ctrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.ufg.inf.fullstack.ctrl.business.AlunoBusiness;
import br.ufg.inf.fullstack.ctrl.exception.AlunoException;
import br.ufg.inf.fullstack.model.entities.Aluno;
import br.ufg.inf.fullstack.util.Message;

@RestController
@RequestMapping(value="/aluno")
public class AlunoCtrl {

  @Autowired
  private AlunoBusiness business;

  @GetMapping
  public ResponseEntity<List<Aluno>> findall(){
    HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
    List<Aluno> list = business.findall();
    if( list.size() == 0 ){ 
      status = HttpStatus.NO_CONTENT;
			headers.add("message", Message.get("0111"));
    }
    return new ResponseEntity<List<Aluno>>(list, headers, status);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Aluno> findByID(@PathVariable Integer id){

    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;
    Aluno retorno = new Aluno();
    try {
      
      retorno = business.findById(id);

    } catch (AlunoException e) {
      headers.add("message", Message.get(e.getMessage()));
      status = HttpStatus.NO_CONTENT;
    } catch (Exception e) {
      headers.add("message", Message.get(e.getMessage()));
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    return new ResponseEntity<Aluno>(retorno, headers, status);

  }

  @GetMapping(value="/find/{str}")
	public ResponseEntity<List<Aluno>> findByName(@PathVariable("str") String str){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		List<Aluno> list = new ArrayList<Aluno>();
    try {
      list = business.findByNnAluno(str);
      if(list.size() == 0) {
        status = HttpStatus.NO_CONTENT;
        headers.add("message", Message.get("0111"));
      }

    } catch (AlunoException e) {
      status = HttpStatus.NO_CONTENT;
      headers.add("message", Message.get("0111"));
    } 

		
		return new ResponseEntity<List<Aluno>>(list, headers, status);
	}


  @GetMapping(value="/findByStatus/{bln}")
	public ResponseEntity<List<Aluno>> findByStatus(@PathVariable Optional<Boolean> bln){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		List<Aluno> list = business.findByStatus(bln.get()); 
		if(list.size() == 0) {
			status = HttpStatus.NO_CONTENT;
			headers.add("message", Message.get("0111"));
		}
		return new ResponseEntity<List<Aluno>>(list, headers, status);
	}


  @PostMapping
  public ResponseEntity<Aluno> insert(@RequestBody Aluno aluno){
    HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
	
    try {
      aluno = business.insert(aluno);
    } catch (AlunoException e) {
      status = HttpStatus.BAD_REQUEST;
			headers.add("message", Message.get(e.getMessage()));
    } catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			headers.add("message", Message.get("0102"));
		}

    return new ResponseEntity<Aluno>(aluno, headers, status);
  }

  @DeleteMapping(value="/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id){
    HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();

    try {
			business.delete(id);
			headers.add("message", Message.get("0107"));
		}catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			headers.add("message", Message.get("0108"));
		}
		return new ResponseEntity<Void>(null, headers, status);

  }

  @PutMapping
  public ResponseEntity<Aluno> update(@RequestBody Aluno aluno){
    
    HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();

    try {
      aluno = business.update(aluno);
      headers.add("message", Message.get("0105"));
    } catch (AlunoException e) {
      status = HttpStatus.BAD_REQUEST;
			headers.add("message", Message.get(e.getMessage()));
    } catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			headers.add("message", Message.get("0106"));	
		}

    return new ResponseEntity<Aluno>(aluno, headers, status);
  }

  
}
