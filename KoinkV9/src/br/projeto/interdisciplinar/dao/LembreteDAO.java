package br.projeto.interdisciplinar.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.model.Lembrete;
import br.projeto.interdisciplinar.util.JPAUtil;

public class LembreteDAO {
	
	public List<Lembrete> verificaDataRemocao(Date data, int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT l FROM Lembrete l WHERE data_finalizacao < :pData AND id_Usuario = :pId";
		
		TypedQuery<Lembrete> query = em.createQuery(jpql, Lembrete.class);
		query.setParameter("pId", id_Usuario);
		query.setParameter("pData", data);
		
		List<Lembrete> lista = query.getResultList();
		
		return  lista;
		
	}
	
	public List<Lembrete> verificaDataAviso(Date data, int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT l FROM Lembrete l WHERE data_inicio_aviso >= :pData AND id_Usuario = :pId";
		
		TypedQuery<Lembrete> query = em.createQuery(jpql, Lembrete.class);
		query.setParameter("pId", id_Usuario);
		query.setParameter("pData", data);
		
		List<Lembrete> lista = query.getResultList();
		
		return  lista;
		
	}
	
	public Long lembreteTotal(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();
		
		
		String jpql = "SELECT count(*) FROM Lembrete l WHERE id_Usuario = :pId";
		
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);
		
		Long valor = query.getSingleResult();
		
		return valor;
	}
}
