package br.projeto.interdisciplinar.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.JPAUtil;
import br.projeto.interdisciplinar.util.Utils;

public class UsuarioDAO {
	
	public Usuario buscarPorLoginSenha(String login, String senha) {
		Usuario usuario;
		
		String jpql = "SELECT u FROM Usuario u WHERE usuario = :pLogin AND senha = :pSenha";
				
		EntityManager em = JPAUtil.getInstance();
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", Utils.toMD5(senha));
		
		try {
			usuario = query.getSingleResult();
	    } catch (NoResultException ex) {
	        usuario = null;
	    }
		
		em.close();
		
		return usuario;
	}
	
	
	public boolean buscaPorEmail(String email) {
		String jpql = "SELECT email FROM Usuario u WHERE email = :pEmail";
		
		EntityManager em = JPAUtil.getInstance();
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("pEmail", email);
		
		try {
			query.getSingleResult();
			return true;
		}catch(RuntimeException erro) {
			return false;
		}

	}
	
	public boolean buscaPorUsuario(String email) {
		String jpql = "SELECT usuario FROM Usuario u WHERE usuario = :pUsuario";
		
		EntityManager em = JPAUtil.getInstance();
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("pUsuario", email);
		
		try {
			query.getSingleResult();
			return true;
		}catch(RuntimeException erro) {
			return false;
		}

	}
	

}
