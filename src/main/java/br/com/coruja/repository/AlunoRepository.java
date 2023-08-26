package br.com.coruja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coruja.model.Aluno;

public interface AlunoRepository  extends JpaRepository<Aluno, Long>{

}
