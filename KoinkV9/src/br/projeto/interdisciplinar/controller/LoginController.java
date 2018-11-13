package br.projeto.interdisciplinar.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.projeto.interdisciplinar.dao.UsuarioDAO;
import br.projeto.interdisciplinar.model.Usuario;


@ManagedBean
@ViewScoped
public class LoginController {
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public String logar(){
		
		usuario = new UsuarioDAO().buscarPorLoginSenha(usuario.getUsuario(), usuario.getSenha());
		
		FacesContext context = FacesContext.getCurrentInstance();
	
		
		if(usuario != null){ // logou
			// inclui usuario na sessao
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			
			return "dashboard?faces-redirect=true";
		}
		else{
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage("Login ou senha incorreto"));
			
			return "login?faces-redirect=true";
		}
	}
	
	public String deslogar(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}
	
	public String obterNome() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		return usuario.getNome() + " " + usuario.getSobrenome();
	}
}
