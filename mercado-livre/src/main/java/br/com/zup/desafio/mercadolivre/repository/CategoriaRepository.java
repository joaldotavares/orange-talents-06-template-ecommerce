package br.com.zup.desafio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.desafio.mercadolivre.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
