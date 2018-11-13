package br.projeto.interdisciplinar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.model.Meta;
import br.projeto.interdisciplinar.util.JPAUtil;

public class MetaDAO {
	
	public List<Meta> listaTodasMetas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT m from Meta m where id_Usuario = :pId";

		TypedQuery<Meta> query = em.createQuery(jpql, Meta.class);
		query.setParameter("pId", id_Usuario);

		List<Meta> lista = query.getResultList();

		em.close();

		return lista;

	}
	
	public Long metaTotal(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT count(*) from Meta m where id_Usuario = :pId";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);

		Long valor = query.getSingleResult();

		em.close();

		return valor;

	}
	
	public Long qtdMetasFinalizadas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT count (*) FROM Meta m WHERE id_Usuario = :pId AND finalizada = 1";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);

		Long valor = query.getSingleResult();

		return valor;
	}
	
	public Long qtdMetasConcluidas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT count (*) FROM Meta m WHERE id_Usuario = :pId AND meta_cumprida = 1";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);

		Long valor = query.getSingleResult();

		return valor;
	}
	
	public Long qtdMetasFracassadas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT count (*) FROM Meta m WHERE id_Usuario = :pId AND meta_cumprida = 0 AND finalizada = 0";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);

		Long valor = query.getSingleResult();

		return valor;
	}

}
