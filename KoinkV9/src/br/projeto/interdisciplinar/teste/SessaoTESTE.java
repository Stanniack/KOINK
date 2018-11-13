package br.projeto.interdisciplinar.teste;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.projeto.interdisciplinar.dao.UsuarioDAO;
import br.projeto.interdisciplinar.model.Usuario;

public class SessaoTESTE {
	public static void main(String[] args) {
		
		Usuario u = new UsuarioDAO().buscarPorLoginSenha("joana", "123");
		System.out.println("Id: " + u.getId_Usuario());
		
		FacesContext context = FacesContext.getCurrentInstance() ;
		Usuario usuarioLogado = (Usuario) context.getExternalContext() .getSessionMap().get("usuarioLogado");
		Integer id = usuarioLogado.getId_Usuario();
	    
	    System.out.println("Id do usuário logado: " + usuarioLogado.getId_Usuario());
	}
}
