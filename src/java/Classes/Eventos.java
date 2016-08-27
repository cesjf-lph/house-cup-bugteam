/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author aluno
 */
@Entity
public class Eventos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Aluno aluno;
    private Professor professor;
    private Integer pontos;

    public Eventos() {
    }

    
    public Eventos(Aluno a, Professor p, Integer pontos) {
        this.aluno = a;
        this.professor = p;
        this.pontos = pontos;
        
    }

    @Override
    public String toString() {
      
        return "Eventos: {O professor" + professor.getNome() + ", pontuou o aluno " + aluno.getNome() + ", com " + pontos + "ponto(s)" + '}';
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
    
    
    
}
