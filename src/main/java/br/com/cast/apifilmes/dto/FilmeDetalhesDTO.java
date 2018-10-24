package br.com.cast.apifilmes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeDetalhesDTO {

	@JsonProperty("imdbID")
	private String imdb;

	@JsonProperty("Released")
	private String realizado;

	@JsonProperty("Runtime")
	private String tempo;

	@JsonProperty("Genre")
	private String genero;

	@JsonProperty("Director")
	private String diretor;
	
	@JsonProperty("Plot")
	private String resumo;

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public String getRealizado() {
		return realizado;
	}

	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
}
