package br.projeto.interdisciplinar.teste;

import java.util.ArrayList;
import java.util.List;

import br.projeto.interdisciplinar.dao.FinancaDAO;
import br.projeto.interdisciplinar.model.Receita;

public class GraficosTeste {

	public static void main(String[] args) {
		
		List<Receita> lista = new FinancaDAO().listaTodasReceitas(1);
		List<String> listaString = new ArrayList<>();
		
		for(Receita r : lista) {
			if(!listaString.contains(r.getTipo().getTipo())) {
				listaString.add(r.getTipo().getTipo());
				
				System.out.println("Categoria: " + r.getTipo().getTipo() + " Soma total: " + 
				new FinancaDAO().totalPorTipoReceita(1, r.getTipo().getId_Tipo()));
				
			}
			
		}
		
		
	} 

}
