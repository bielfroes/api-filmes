package br.com.cast.apifilmes.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.apifilmes.business.FilmeBusiness;
import br.com.cast.apifilmes.dto.FilmeDTO;
import br.com.cast.apifilmes.dto.FilmeDataDTO;
import br.com.cast.apifilmes.dto.FilmeDetalhesDTO;

@RestController
@RequestMapping(path="/")
public class FilmeAPI {

	@Autowired
	private FilmeBusiness business;
	
	
	@RequestMapping(path="", method = RequestMethod.GET)
	public List<FilmeDataDTO> buscarFilmes() {
		List<FilmeDataDTO> dtos = business.buscarTudo();
		return dtos;
	}

	@RequestMapping(path="{titulo}", method = RequestMethod.GET)
	public List<FilmeDataDTO> buscarTitulo(@PathVariable String titulo) {
		List<FilmeDataDTO> dto = business.buscarPorTitulo(titulo);
		return dto;
	}

	@RequestMapping(path="detalhes/{id}", method=RequestMethod.GET)
	public FilmeDetalhesDTO inserirFilme(@PathVariable String id) {
		return business.inserirDetalhes(id);
	}
	
	@RequestMapping(path="buscar/{titulo}", method=RequestMethod.GET)
	public List<FilmeDataDTO> buscarTituloAPI(@PathVariable String titulo) {
		List<FilmeDataDTO> dto = business.buscarPorTituloAPI(titulo);
		return dto;
	}
}
