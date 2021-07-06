package br.com.zup.desafio.mercadolivre.form;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.desafio.mercadolivre.interfaces.UploadImagem;

@Component
public class UploadImagemFake implements UploadImagem{

	@Override
	public Set<String> enviarImagem(List<MultipartFile> imagens) {
		return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
