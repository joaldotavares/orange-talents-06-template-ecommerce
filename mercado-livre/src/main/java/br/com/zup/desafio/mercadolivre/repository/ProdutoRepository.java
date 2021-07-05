package br.com.zup.desafio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.desafio.mercadolivre.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
