package br.projeto.interdisciplinar.dao;

import br.projeto.interdisciplinar.model.Receita;
import br.projeto.interdisciplinar.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BasicDAO <Generico> {
    private final Class<Generico> classe;

    public BasicDAO(Class<Generico> classe) {
        this.classe = classe;
    }
    
    public void insere(Generico objeto){
        EntityManager em = JPAUtil.getInstance();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        em.close();
    }
    
    public void atualiza(Generico objeto){
        EntityManager em = JPAUtil.getInstance();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        em.close();
    }
    
    public void remove(Integer id){
        EntityManager em = JPAUtil.getInstance();
        em.getTransaction().begin();
        Generico objeto = em.find(classe, id);
        em.remove(objeto);
        em.getTransaction().commit();
        em.close();
    }
    
    public Generico buscaPorId(int id) {

		EntityManager em = JPAUtil.getInstance();
		Generico obj = em.find(classe, id);
		em.close();
		
		return obj;
	}
    
    public List<Generico> listaTodos() {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT t FROM " + classe.getName() + " t";
		
		TypedQuery<Generico> query = em.createQuery(jpql, classe);
		
		List<Generico> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
    
    public List<Generico> listaTodos(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();
		
		String jpql = "SELECT t FROM " + classe.getName() + " t " + "where id_Usuario = :pId";
		
		TypedQuery<Generico> query = em.createQuery(jpql, classe);
		query.setParameter("pId", id_Usuario);
		
		List<Generico> lista = query.getResultList();
		
		em.close();
		
		return lista;
	}
    
    
   

    
}
