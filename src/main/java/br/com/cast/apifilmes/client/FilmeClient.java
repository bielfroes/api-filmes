package br.com.cast.apifilmes.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.cast.apifilmes.dto.FilmeDTO;
import br.com.cast.apifilmes.dto.FilmeDataDTO;
import br.com.cast.apifilmes.dto.FilmeDetalhesDTO;

@Component
public class FilmeClient {

	private final RestTemplate rest;
	
	private static final String FILME_URL = "http://www.omdbapi.com/?s={titulo}&apikey=d0037c02&";
	private static final String BUSCAR_FILME_ID_URL = "http://www.omdbapi.com/?i={id}&apikey=d0037c02&";

	public FilmeClient(RestTemplateBuilder builder) {
		this.rest = builder.build();
	}
	
	public FilmeDTO getFilmeNaNuvem(String titulo) {
		return this.rest.getForObject(FILME_URL, FilmeDTO.class, titulo);
	}
	
	public FilmeDataDTO getFilmeData(String id) {
		return this.rest.getForObject(BUSCAR_FILME_ID_URL, FilmeDataDTO.class, id);
	}
	
	public FilmeDetalhesDTO getFilmeDetalhe(String id) {
		return this.rest.getForObject(BUSCAR_FILME_ID_URL, FilmeDetalhesDTO.class, id);
	}
}
