package br.com.cast.apifilmes.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeDTO {

	@JsonProperty("Search")
	private List<FilmeDataDTO> search;

	public List<FilmeDataDTO> getSearch() {
		return search;
	}

	public void setSearch(List<FilmeDataDTO> search) {
		this.search = search;
	}

}
