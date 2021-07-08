package br.com.zup.desafio.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.desafio.mercadolivre.dto.FechamentoPagSeguroDTO;
import br.com.zup.desafio.mercadolivre.dto.FechamentoPayPalDTO;
import br.com.zup.desafio.mercadolivre.interfaces.RetornoPagamento;
import br.com.zup.desafio.mercadolivre.model.Compra;
import br.com.zup.desafio.mercadolivre.repository.CompraRepository;

@RestController
public class FechamentoCompraController {

	private final CompraRepository compraRepository;

	public FechamentoCompraController(CompraRepository compraRepository) {
		super();
		this.compraRepository = compraRepository;
	}

	@PostMapping(value = "/compra/pagseguro/{id}")
	public String processarPagSeguro(@PathVariable Long id, @Valid FechamentoPagSeguroDTO fechamentoDto) {
		return processa(id, fechamentoDto);
	}

	@PostMapping(value = "/compra/paypal/{id}")
	public String processarPayPal(@PathVariable Long id, @Valid FechamentoPayPalDTO fechamentoDto) {
		return processa(id, fechamentoDto);
	}

	private String processa(Long id, RetornoPagamento retornoDto) {
		Compra compra = compraRepository.findById(id).orElseThrow();

		compra.adicionarPagamento(retornoDto);

		compraRepository.save(compra);

		return compra.toString();
	}
}
