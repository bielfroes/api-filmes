package br.com.cast.apifilmes.entidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="filme", schema="movie")
public class Filme {

	@Id
	private String id;
	
	private String titulo;
	private String ano;
	private String tipo;
	private String poster;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_idmb")
	private FilmeDetalhes detalhes;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public FilmeDetalhes getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(FilmeDetalhes detalhes) {
		this.detalhes = detalhes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
