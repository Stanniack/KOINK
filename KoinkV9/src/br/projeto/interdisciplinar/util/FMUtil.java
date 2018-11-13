package br.projeto.interdisciplinar.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FMUtil {
	
	public static void facesMessageSucesso() {
		// Cria a mensagem
		String msg = "Sucesso ao realizar esta operação";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);

		// Manda a mensagem
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	public static void facesMessageSucesso(String msg) {
		// Cria a mensagem
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);

		// Manda a mensagem
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	public static void facesMessageErro() {
		// Cria a mensagem
		String msg = "Erro inesperado no sistema";
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);

		// Manda a mensagem
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	public static void facesMessageErro(String msg) {
		// Cria a mensagem
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);

		// Manda a mensagem
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	
	public static void facesMessageAviso(String msg) {
		// Cria a mensagem
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);

		// Manda a mensagem
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	

}
