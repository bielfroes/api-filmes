package br.com.cast.apifilmes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeDataDTO {

	@JsonProperty("imdbID")
	private String id;

	@JsonProperty("Title")
	private String titulo;

	@JsonProperty("Year")
	private String ano;

	@JsonProperty("Type")
	private String tipo;

	@JsonProperty("Poster")
	private String poster;

	private FilmeDetalhesDTO detalhes;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public FilmeDetalhesDTO getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(FilmeDetalhesDTO detalhes) {
		this.detalhes = detalhes;
	}

}
