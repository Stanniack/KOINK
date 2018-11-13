package br.projeto.interdisciplinar.teste;

import java.util.Date;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.model.Lembrete;

public class TestaLembrete {

	public static void main(String[] args) {
		Lembrete l = new Lembrete();
		l.setDescricao("funciona, bagaça!");
		l.setData_inicio_aviso(new Date());
		l.setData_finalizacao(new Date());
		
		BasicDAO<Lembrete>dao = new BasicDAO<>(Lembrete.class);
		dao.insere(l);
	}

}
