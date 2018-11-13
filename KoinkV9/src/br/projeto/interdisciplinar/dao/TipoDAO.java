package br.projeto.interdisciplinar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.model.Tipo;
import br.projeto.interdisciplinar.util.JPAUtil;

public class TipoDAO {
	
	public List<Tipo> listaTodos(String tipo_financa) {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT t FROM Tipo t WHERE tipo_financa = :pTipo_financa";
		
		TypedQuery<Tipo> query = em.createQuery(jpql, Tipo.class);
		query.setParameter("pTipo_financa", tipo_financa);
		
		List<Tipo> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	
	public List<Tipo> listaTodos(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT t FROM Tipo t WHERE id_Usuario = :pId";
		
		TypedQuery<Tipo> query = em.createQuery(jpql, Tipo.class);
		query.setParameter("pId", id_Usuario);
		
		List<Tipo> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
	
	public List<Tipo> listaTodos(String tipo_financa, int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT t FROM Tipo t WHERE id_Usuario = :pId AND tipo_financa = :pTipo";
		
		TypedQuery<Tipo> query = em.createQuery(jpql, Tipo.class);
		query.setParameter("pId", id_Usuario);
		query.setParameter("pTipo", tipo_financa);
		
		List<Tipo> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}

}
