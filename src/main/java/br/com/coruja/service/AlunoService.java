package br.com.coruja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coruja.model.Aluno;
import br.com.coruja.repository.AlunoRepository;
import br.com.coruja.service.exception.ResourceNotFoundException;

@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository repository;

    public Aluno findById(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Aluno> findAll() {
        return  repository.findAll();
    }

    public Aluno update(Long id, Aluno aluno) {
        Aluno updatedAluno = repository.getById(id);
        updatedAluno.setNome(aluno.getNome()) ;
        updatedAluno.setEmail(aluno.getEmail()) ;
        return repository.save(updatedAluno);
    }

     public Aluno save(Aluno aluno) {
        return repository.save(aluno);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
