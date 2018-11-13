package br.projeto.interdisciplinar.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.dao.FinancaDAO;
import br.projeto.interdisciplinar.dao.LembreteDAO;
import br.projeto.interdisciplinar.model.Lembrete;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.FMUtil;

@ManagedBean
public class LembreteController {
	private Lembrete lembrete = new Lembrete();
	private List<Lembrete> listaDeLembretes;
	public int flag = 0;

	public void salvar() {

		if (lembrete.getData_inicio_aviso().before(lembrete.getData_finalizacao())) {

			try {

				// pega usuário da sessão
				FacesContext context = FacesContext.getCurrentInstance();
				Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

				if (this.lembrete.getId_Lembrete() == null) {
					BasicDAO<Lembrete> dao = new BasicDAO<>(Lembrete.class);
					lembrete.setUsuario(usuario);
					dao.insere(lembrete);
					FMUtil.facesMessageSucesso("Lembrete salvo :)");

				} else {
					BasicDAO<Lembrete> dao = new BasicDAO<>(Lembrete.class);
					dao.atualiza(lembrete);
					FMUtil.facesMessageSucesso("Lembrete atualizado :)");
				}

				novo();
				listaLembretes();

			} catch (RuntimeException erro) {
				FMUtil.facesMessageErro("Ops! Não foi possível salvar o lembrete :(");
			}

		} else {
			FMUtil.facesMessageErro("Ops, início do aviso maior que a data de finalização");
		}

	}

	@PostConstruct // funciona como um construtor, toda vez que a página é criada, esse método é
	public void listar() {

		try {

			verificaData_Avisos();
			verificaLembretes();
			listaLembretes();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Erro ao listar os lembretes.");
		}

	}

	public void excluir(ActionEvent evento) { // ActionEvent serve para capturar dados da view

		try {
			// captura da variável "tipoSelecionado" do arquivo xhtml
			lembrete = (Lembrete) evento.getComponent().getAttributes().get("lembreteSelecionado");

			// remove o objeto
			BasicDAO<Lembrete> dao = new BasicDAO<>(Lembrete.class);
			dao.remove(lembrete.getId_Lembrete());

			// chama a lista novamente para atualizar
			listaLembretes();

			// Mensagem de sucesso e mostra o nome do objeto
			FMUtil.facesMessageSucesso("Sucesso ao remover este lembrete!");

			// Limpa campo
			this.lembrete = new Lembrete();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ocorreu um erro ao tentar remover este lembrete :(");
		}

	}

	public void editar(ActionEvent evento) {
		// captura da variável "tipoSelecionado" do arquivo xhtml
		lembrete = (Lembrete) evento.getComponent().getAttributes().get("lembreteSelecionado");

	}

	public void novo() {
		lembrete = new Lembrete();
	}

	// listar por id do usuário!
	public void listaLembretes() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		BasicDAO<Lembrete> dao = new BasicDAO<>(Lembrete.class);
		listaDeLembretes = dao.listaTodos(usuario.getId_Usuario());
	}

	public Long obtemQtdLembretes() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		LembreteDAO dao = new LembreteDAO();

		if (dao.lembreteTotal(usuario.getId_Usuario()) != null) {
			return dao.lembreteTotal(usuario.getId_Usuario());
		} else {
			return (long) 0;
		}

	}

	public void verificaLembretes() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		LembreteDAO dao = new LembreteDAO();

		List<Lembrete> lista = dao.verificaDataRemocao(new Date(), usuario.getId_Usuario());

		for (Lembrete l : lista) {
			FMUtil.facesMessageAviso(
					"O lembrete " + "''" + l.getDescricao() + "''" + " expirou no dia " + l.getData_finalizacao());

		}
	}

	public void verificaData_Avisos() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		LembreteDAO dao = new LembreteDAO();

		List<Lembrete> lista = dao.verificaDataAviso(new Date(), usuario.getId_Usuario());

		for (Lembrete l : lista) {
			FMUtil.facesMessageAviso("Aviso lembretes: " + l.getDescricao());

		}
	}

	public Lembrete getLembrete() {
		return lembrete;
	}

	public void setLembrete(Lembrete lembrete) {
		this.lembrete = lembrete;
	}

	public List<Lembrete> getListaDeLembretes() {
		return listaDeLembretes;
	}

	public void setListaDeLembretes(List<Lembrete> listaDeLembretes) {
		this.listaDeLembretes = listaDeLembretes;
	}

}
