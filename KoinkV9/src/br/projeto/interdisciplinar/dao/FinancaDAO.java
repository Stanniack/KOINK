package br.projeto.interdisciplinar.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.model.Despesa;
import br.projeto.interdisciplinar.model.Financa;
import br.projeto.interdisciplinar.model.Receita;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.JPAUtil;

public class FinancaDAO {

	public List<Despesa> listaTodasDespesas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT d from Despesa d where id_Usuario = :pId";

		TypedQuery<Despesa> query = em.createQuery(jpql, Despesa.class);
		query.setParameter("pId", id_Usuario);

		List<Despesa> lista = query.getResultList();

		em.close();

		return lista;

	}

	public List<Receita> listaTodasReceitas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT r from Receita r where id_Usuario = :pId";

		TypedQuery<Receita> query = em.createQuery(jpql, Receita.class);
		query.setParameter("pId", id_Usuario);

		List<Receita> lista = query.getResultList();

		em.close();

		return lista;

	}

	public Double receitaTotal(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (r.valor) FROM Receita r WHERE id_Usuario = :pId";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}

	public Double receitaTotalMes(int id_Usuario, int data) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (r.valor) FROM Receita r WHERE MONTH(data_criacao) = :pMes AND id_Usuario = :pId";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pMes", data);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}
	
	public Double receitaTotalMes_Ano(int id_Usuario, int data, int ano) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (r.valor) FROM Receita r WHERE MONTH(data_criacao) = :pMes AND YEAR(data_criacao) = :pAno AND "
				+ "id_Usuario = :pId";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pMes", data);
		query.setParameter("pAno", ano);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}
	
	public Double totalReceitaFixa(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (r.valor) FROM Receita r WHERE id_Usuario = :pId AND fixa = 1";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}
	
	public Long qtdReceitasFixas(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT count (*) FROM Receita r WHERE id_Usuario = :pId AND fixa = 1";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);

		Long valor = query.getSingleResult();

		return valor;
	}
	
	
	
	public Long qtdDespesasPendentes(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT count (*) FROM Despesa d WHERE id_Usuario = :pId AND pago = 0";

		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("pId", id_Usuario);

		Long valor = query.getSingleResult();

		return valor;
	}
	
	public Double despesaPendente(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (d.valor) FROM Despesa d WHERE id_Usuario = :pId AND pago = 0";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}

	public Double despesaTotal(int id_Usuario) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (d.valor) FROM Despesa d WHERE id_Usuario = :pId AND pago = 1";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}

	public Double despesaTotalMes(int id_Usuario, int data) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (d.valor) FROM Despesa d WHERE MONTH(data_criacao) = :pMes AND id_Usuario = :pId AND pago = 1";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pMes", data);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}
	
	public Double despesaTotalMes_Ano(int id_Usuario, int data, int ano) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "SELECT sum (d.valor) FROM Despesa d WHERE MONTH(data_criacao) = :pMes AND YEAR(data_criacao) = :pAno AND "
				+ "id_Usuario = :pId AND pago = 1";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pMes", data);
		query.setParameter("pAno", ano);
		query.setParameter("pId", id_Usuario);

		Double valor = query.getSingleResult();

		return valor;
	}

	public Double totalPorTipoReceita(int id_Usuario, int tipo) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "select sum(valor) from Receita r WHERE id_Usuario = :pId AND tipo_id_Tipo = :pTipo";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pId", id_Usuario);
		query.setParameter("pTipo", tipo);

		Double valor = query.getSingleResult();

		return valor;
	}
	
	public Double totalPorTipoDespesa(int id_Usuario, int tipo) {
		EntityManager em = JPAUtil.getInstance();

		String jpql = "select sum(valor) from Despesa d WHERE id_Usuario = :pId AND tipo_id_Tipo = :pTipo";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pId", id_Usuario);
		query.setParameter("pTipo", tipo);

		Double valor = query.getSingleResult();

		return valor;
	}

}
