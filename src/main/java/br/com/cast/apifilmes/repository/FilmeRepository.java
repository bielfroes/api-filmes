package br.com.cast.apifilmes.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.cast.apifilmes.entidade.Filme;
import br.com.cast.apifilmes.entidade.FilmeDetalhes;

@Repository
public class FilmeRepository {
	

	@PersistenceContext
	private EntityManager em;
	
	public void inserirDetalhes(FilmeDetalhes detalhes) {
		em.persist(detalhes);
	}
	
	public void inserirFilme(Filme filme) {
		em.persist(filme);
	}
	
	public void alterar(Filme filme) {
		em.merge(filme);
	}

	public void excluir(Filme filme) {
		em.remove(filme);
	}	
	
	public List<Filme> buscarFilme() {
		Query query = em.createQuery("from " + Filme.class.getName());
		return query.getResultList();
	}
	
	public FilmeDetalhes buscarTituloDetalhes(String id) {
		try {
			Query query = em.createQuery("from " + FilmeDetalhes.class.getName() + " where upper(imdb) = upper(:imdb) ");
			query.setParameter("imdb", id);		
			return (FilmeDetalhes) query.getSingleResult();			
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Filme> buscarTitulo(String titulo) {
			Query query = em.createQuery("from " + Filme.class.getName() + " where upper(titulo) like upper(:titulo) ");
			query.setParameter("titulo", "%" + titulo + "%");		
			return query.getResultList();			
		}
}
