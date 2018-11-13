package br.projeto.interdisciplinar.teste;

import java.util.Date;
import java.util.List;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.dao.LembreteDAO;
import br.projeto.interdisciplinar.model.Lembrete;

public class RemoveLembrete {

	public static void main(String[] args) {
		
		LembreteDAO dao = new LembreteDAO();
		
		List<Lembrete> lista = dao.verificaDataRemocao(new Date(), 1);
		
		for(Lembrete l : lista) {
			System.out.println(l.getDescricao());
		}

	}

}
