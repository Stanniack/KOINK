package br.projeto.interdisciplinar.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.projeto.interdisciplinar.model.Usuario;


public class Autorizador implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Obtém contexto da aplicação 
		FacesContext context = event.getFacesContext();
		// Obtém o nome da página que está sendo chamada
	    String nomePagina = context.getViewRoot().getViewId();

	    // System.out.println(nomePagina);

	    // se for a página de login, o usuário pode acessar 
	    if ("/login.xhtml".equals(nomePagina) || "/cadastro.xhtml".equals(nomePagina)) {
	        return;
	    }
	    
	    

	    // Obtém usuário da sessão
	    Usuario usuarioLogado = 
	    	(Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

	    // se há usuário logado, ele pode acessar as páginas
	    if(usuarioLogado != null) {
	    	
	    	// remover se não funcionar
	    	if(nomePagina.equals("/dashboard.xhtml")){
	    		return;
	    	}
	    	
	        return;
	    }

	    // se não há, o usuário é redirecionado para o login
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/login?faces-redirect=true");
	    
	    context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; // o autorizador será executado na fase restore_view
	}

}
