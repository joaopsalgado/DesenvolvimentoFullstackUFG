package br.ufg.inf.fullstack.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufg.inf.fullstack.model.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

  @Query("SELECT p FROM Pessoa as p WHERE p.nmPessoa like %:name%")
	public List<Integer> findIDByNmAluno(@Param("name") String name);
	
	@Query("SELECT d FROM Aluno as  d WHERE d.ativo = ?1")
	public List<Aluno> findByStatus(Boolean bl);
  
}