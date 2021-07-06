package br.com.zup.desafio.mercadolivre.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImagem {
	
	Set<String> enviarImagem(List<MultipartFile> imagens);

}
