package br.com.cast.apifilmes.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.apifilmes.client.FilmeClient;
import br.com.cast.apifilmes.dto.FilmeDTO;
import br.com.cast.apifilmes.dto.FilmeDataDTO;
import br.com.cast.apifilmes.dto.FilmeDetalhesDTO;
import br.com.cast.apifilmes.entidade.Filme;
import br.com.cast.apifilmes.entidade.FilmeDetalhes;
import br.com.cast.apifilmes.repository.FilmeRepository;

@Service
public class FilmeBusiness {

	@Autowired
	FilmeClient client;
	
	@Autowired
	FilmeRepository repository;
	
	@Transactional
	public List<FilmeDataDTO> buscarTudo() {
		List<Filme> filmes = repository.buscarFilme();
		
		List<FilmeDataDTO> dtos = filmes.stream().map(filme -> fromFilme(filme)).collect(Collectors.toList());
	
		return dtos;
	}
	
	public FilmeDataDTO fromFilme(Filme f) {
		FilmeDataDTO filme = new FilmeDataDTO();
		filme.setAno(f.getAno());
		filme.setPoster(f.getPoster());
		filme.setTitulo(f.getTitulo());
		filme.setId(f.getId());
		filme.setTipo(f.getTipo());
		
		return filme;
	}
	
	@Transactional
	public List<FilmeDataDTO> buscarPorTituloAPI(String titulo){
		
		FilmeDTO dto = client.getFilmeNaNuvem(titulo);

		repository.buscarTitulo(titulo);
		
		for(FilmeDataDTO f : dto.getSearch()) {
			Filme filme = new Filme();
			filme.setAno(f.getAno());
			filme.setPoster(f.getPoster());
			filme.setTitulo(f.getTitulo());
			filme.setId(f.getId());
			filme.setTipo(f.getTipo());
			
			repository.inserirFilme(filme);
		}
		return dto.getSearch();
	}
	
	@Transactional
	public List<FilmeDataDTO> buscarPorTitulo(String titulo){
		
		List<Filme> filmes = repository.buscarTitulo(titulo);
		List<FilmeDataDTO> listaDTO = new ArrayList<>();
		
				for(Filme f : filmes) {
					FilmeDataDTO dto = new FilmeDataDTO();
					dto.setAno(f.getAno());
					dto.setPoster(f.getPoster());
					dto.setTitulo(f.getTitulo());
					dto.setId(f.getId());
					dto.setTipo(f.getTipo());
					
					if(f.getDetalhes() != null) {
						FilmeDetalhesDTO detalhes = new FilmeDetalhesDTO();
						detalhes.setDiretor(f.getDetalhes().getDiretor());
						detalhes.setGenero(f.getDetalhes().getGenero());
						detalhes.setImdb(f.getDetalhes().getImdb());
						detalhes.setRealizado(f.getDetalhes().getRealizado());
						detalhes.setResumo(f.getDetalhes().getResumo());
						detalhes.setTempo(f.getDetalhes().getTempo());
						dto.setDetalhes(detalhes);
					}
					listaDTO.add(dto);
			}
				return listaDTO;
	}
	
	public List<Filme> DTOparaFilme(FilmeDTO dto) {
		
		List<Filme> listFilme = new ArrayList<>();

		for(FilmeDataDTO f : dto.getSearch()) {
			Filme filme = new Filme();
			filme.setAno(f.getAno());
			filme.setPoster(f.getPoster());
			filme.setTitulo(f.getTitulo());
			filme.setId(f.getId());
			filme.setTipo(f.getTipo());
			listFilme.add(filme);			
		}
		return listFilme;
	}

	@Transactional
	public FilmeDetalhesDTO inserirDetalhes(String id) {
			
		FilmeDetalhes filmeDetalhes = repository.buscarTituloDetalhes(id);
		FilmeDetalhesDTO dto = new FilmeDetalhesDTO();
		
		if(filmeDetalhes == null) {
		dto = client.getFilmeDetalhe(id);
		FilmeDataDTO data = client.getFilmeData(dto.getImdb());
		
		FilmeDetalhes detalhes = new FilmeDetalhes();
		detalhes.setDiretor(dto.getDiretor());
		detalhes.setGenero(dto.getGenero());
		detalhes.setImdb(dto.getImdb());
		detalhes.setRealizado(dto.getRealizado());
		detalhes.setResumo(dto.getResumo());
		detalhes.setTempo(dto.getTempo());
		
		repository.inserirDetalhes(detalhes);
		
		Filme filme = new Filme();
		filme.setId(data.getId());
		filme.setTitulo(data.getTitulo());
		filme.setPoster(data.getPoster());
		filme.setAno(data.getAno());
		filme.setTipo(data.getTipo());
		filme.setDetalhes(detalhes);

		repository.alterar(filme);
		}else {
			dto.setDiretor(filmeDetalhes.getDiretor());
			dto.setGenero(filmeDetalhes.getGenero());
			dto.setImdb(filmeDetalhes.getImdb());
			dto.setRealizado(filmeDetalhes.getRealizado());
			dto.setResumo(filmeDetalhes.getResumo());
			dto.setTempo(filmeDetalhes.getTempo());
		}
		return dto;
	}
}
