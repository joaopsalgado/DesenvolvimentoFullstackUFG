package br.ufg.inf.fullstack.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufg.inf.fullstack.model.entities.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {

  @Query("SELECT c FROM Curso c WHERE c.nmCurso like %:str%")
  List<Curso> findByNmCurso(String str);

}
