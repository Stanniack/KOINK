package br.projeto.interdisciplinar.teste;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.model.Lembrete;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.JPAUtil;
import br.projeto.interdisciplinar.util.Utils;

public class JPQLtest {
	public static void main(String[] args) {
		BasicDAO<Lembrete> dao = new BasicDAO<>(Lembrete.class);

		List<Lembrete> lista = dao.listaTodos(1);
		
		for(Lembrete l : lista) {
			System.out.println("Desc: " + l.getDescricao());
		}
	}
}
