package br.projeto.interdisciplinar.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.dao.FinancaDAO;
import br.projeto.interdisciplinar.model.Financa;
import br.projeto.interdisciplinar.model.Receita;
import br.projeto.interdisciplinar.util.JPAUtil;

public class JpqlTeste {
	public static void main(String[] args) {
		
		
		FinancaDAO dao = new FinancaDAO();
		
		Double valor = dao.receitaTotal(1);
		
		System.out.println(valor);
		
		

		
	}
}
