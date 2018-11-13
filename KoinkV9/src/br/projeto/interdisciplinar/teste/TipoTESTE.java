package br.projeto.interdisciplinar.teste;

import java.util.List;

import br.projeto.interdisciplinar.dao.TipoDAO;
import br.projeto.interdisciplinar.model.Tipo;

public class TipoTESTE {

	public static void main(String[] args) {
		
		TipoDAO dao = new TipoDAO();
		
		List<Tipo> lista = dao.listaTodos("RECEITA", 1);
		
		for(Tipo t : lista) {
			System.out.println(t.getTipo());
		}
		
	

	}

}
