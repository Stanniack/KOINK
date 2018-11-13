package br.projeto.interdisciplinar.teste;

import br.projeto.interdisciplinar.dao.UsuarioDAO;

public class UsuarioTESTE {
	public static void main(String[] args) {
		UsuarioDAO dao = new UsuarioDAO();
		
		/*if(dao.buscaPorEmail("fiodor20@outlook.com") == true) {
			System.out.println("E-mail já existe");
		}else {
			System.out.println("E-mail válido para uso");
		}*/
		
		/*if(dao.buscaPorUsuario("MATEUS") == true) {
			System.out.println("Usuário já existe");
		}else {
			System.out.println("Usuário válido para uso");
		}*/
		
		String teste = "";
		
		int r = teste.indexOf("@@");
		
		System.out.println(r);
		
		
	}
}
