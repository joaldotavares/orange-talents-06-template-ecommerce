package br.com.zup.desafio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.desafio.mercadolivre.model.Pergunta;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long>{

}
