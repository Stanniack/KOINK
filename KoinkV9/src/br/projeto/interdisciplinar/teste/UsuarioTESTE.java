package br.projeto.interdisciplinar.teste;

import br.projeto.interdisciplinar.dao.UsuarioDAO;

public class UsuarioTESTE {
	public static void main(String[] args) {
		UsuarioDAO dao = new UsuarioDAO();
		
		/*if(dao.buscaPorEmail("fiodor20@outlook.com") == true) {
			System.out.println("E-mail j� existe");
		}else {
			System.out.println("E-mail v�lido para uso");
		}*/
		
		/*if(dao.buscaPorUsuario("MATEUS") == true) {
			System.out.println("Usu�rio j� existe");
		}else {
			System.out.println("Usu�rio v�lido para uso");
		}*/
		
		String teste = "";
		
		int r = teste.indexOf("@@");
		
		System.out.println(r);
		
		
	}
}
