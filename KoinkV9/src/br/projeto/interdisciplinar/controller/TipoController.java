package br.projeto.interdisciplinar.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.exception.ConstraintViolationException;
import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.dao.TipoDAO;
import br.projeto.interdisciplinar.model.Tipo;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.FMUtil;

@ViewScoped // meus objetos só ficam vivos enquanto eu estiver na página!
@ManagedBean
public class TipoController {
	private Tipo tipoNovo = new Tipo();
	private List<Tipo> listaDeTipos;

	public void salvar() {

		try {

			// pega usuário da sessão
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

			if (this.tipoNovo.getId_Tipo() == null) {
				tipoNovo.setUsuario(usuario);
				new BasicDAO<Tipo>(Tipo.class).insere(tipoNovo);
				FMUtil.facesMessageSucesso("Categoria salva com sucesso!");

			} else {
				new BasicDAO<Tipo>(Tipo.class).atualiza(tipoNovo);
				FMUtil.facesMessageSucesso("Categoria atualizada com sucesso!");
			}

			// Limpa campo
			this.tipoNovo = new Tipo();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro();
			erro.printStackTrace();
		}

		listaTodosTipos();

	}

	@PostConstruct // funciona como um construtor, toda vez que a página é criada, esse método é
	public void listar() {
		try {
			listaTodosTipos();

		} catch (ConstraintViolationException erro) {
			FMUtil.facesMessageErro("Erro ao listar as categorias.");
		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro();
		}

	}

	public void excluir(ActionEvent evento) { // ActionEvent serve para capturar dados da view

		try {
			// captura da variável "tipoSelecionado" do arquivo xhtml
			tipoNovo = (Tipo) evento.getComponent().getAttributes().get("tipoSelecionado");

			// remove o objeto
			BasicDAO<Tipo> dao = new BasicDAO<>(Tipo.class);
			dao.remove(tipoNovo.getId_Tipo());

			// chama a lista novamente para atualizar
			listaTodosTipos();

			// Mensagem de sucesso e mostra o nome do objeto
			FMUtil.facesMessageSucesso("Sucesso ao remover este item: " + this.getTipoNovo().getTipo());

			// Limpa campo
			this.tipoNovo = new Tipo();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Não é possível remover esta categoria se ela está relacionanda a uma finança.");
		}

	}

	public void editar(ActionEvent evento) {
		// captura da variável "tipoSelecionado" do arquivo xhtml
		tipoNovo = (Tipo) evento.getComponent().getAttributes().get("tipoSelecionado");
	}

	// limpa objeto
	public void novo() {
		this.tipoNovo = new Tipo();
	}

	private void listaTodosTipos() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		TipoDAO dao = new TipoDAO();
		listaDeTipos = dao.listaTodos(usuario.getId_Usuario());
	}

	// getters e setters
	public Tipo getTipoNovo() {
		return tipoNovo;
	}

	public void setTipoNovo(Tipo tipo) {
		this.tipoNovo = tipo;
	}

	public List<Tipo> getListaDeTipos() {
		return listaDeTipos;
	}

	public void setListaDeTipos(List<Tipo> listaDeTipos) {
		this.listaDeTipos = listaDeTipos;
	}

}
