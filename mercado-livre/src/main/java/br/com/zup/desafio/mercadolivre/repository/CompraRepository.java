package br.com.zup.desafio.mercadolivre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.desafio.mercadolivre.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long>{

}
