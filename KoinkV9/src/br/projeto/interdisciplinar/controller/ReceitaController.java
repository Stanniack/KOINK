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
import br.projeto.interdisciplinar.dao.TipoDAO;
import br.projeto.interdisciplinar.model.Receita;
import br.projeto.interdisciplinar.model.Tipo;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.FMUtil;

@ViewScoped // meus objetos s� ficam vivos enquanto eu estiver na p�gina!
@ManagedBean
public class ReceitaController {
	private Receita receita = new Receita();
	private List<Receita> listaDeReceitas;
	private List<Tipo> listaDeTipos;
	private String converte;

	public void salvar() {
		// pega usu�rio da sess�o
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (receita.isFixa() && receita.getData_fixamento() == null) {
			FMUtil.facesMessageErro("Ops! Ao selecionar receita fixa, � necess�rio uma data de fixamento");

		} else if (!receita.isFixa() && receita.getData_fixamento() != null) {
			FMUtil.facesMessageErro("Ops! n�o � poss�vel ter uma data de fixamento para uma receita que n�o � fixa");

		} else {
			try {

				if (receita.getValor() < 0) {
					receita.setValor((-1) * receita.getValor());
					FMUtil.facesMessageAviso(
							"N�o � poss�vel receitas com n�meros negativos, por isso consertamos o valor para voc�! "
									+ "Caso tenha colocado o valor errado, altere-o no menu de op��es");
				}

				if (this.receita.getId_Financa() == null) {
					receita.setData_criacao(new Date());
					receita.setUsuario(usuario);

					new BasicDAO<Receita>(Receita.class).insere(receita);

					FMUtil.facesMessageSucesso("Receita criada com sucesso!");

				} else {

					new BasicDAO<Receita>(Receita.class).atualiza(receita);

					FMUtil.facesMessageSucesso("Receita atualizada com sucesso!");
				}

				listaTodasReceitas();
				// Limpa campo
				novo();

			} catch (RuntimeException erro) {
				FMUtil.facesMessageErro("N�o foi poss�vel criar a receita.");
				erro.printStackTrace();
			}

		}

	}

	@PostConstruct // funciona como um construtor, toda vez que a p�gina � criada, esse m�todo �
					// invocado
	public void listar() {
		try {
			// tenho que buscar as receitas por id do usu�rio mudar isso aqui!
			listaTodasReceitas();

			// busca por tipo, m�todo n�o est� funfando ent�o vai isto daqui mermo
			listaTodosTipos();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ocorreu um erro ao tentar buscar as receitas.");
			erro.printStackTrace();
		}

	}

	public void excluir(ActionEvent evento) { // ActionEvent serve para capturar dados da view

		try {
			// captura da vari�vel "tipoSelecionado" do arquivo xhtml
			receita = (Receita) evento.getComponent().getAttributes().get("receitaSelecionada");

			// remove
			BasicDAO<Receita> dao = new BasicDAO<>(Receita.class);
			dao.remove(receita.getId_Financa());

			listaTodasReceitas();

			FMUtil.facesMessageSucesso("Sucesso ao remover esta receita");

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ocorreu um erro ao tentar remover esta receita");
		}

	}

	public void editar(ActionEvent evento) {
		// captura da vari�vel "tipoSelecionado" do arquivo xhtml
		receita = (Receita) evento.getComponent().getAttributes().get("receitaSelecionada");

	}

	public void novo() {
		this.receita = new Receita();

	}

	private void listaTodosTipos() {
		// pega usu�rio da sess�o
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		TipoDAO dao = new TipoDAO();
		listaDeTipos = dao.listaTodos("RECEITA", usuario.getId_Usuario());
	}

	private void listaTodasReceitas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		FinancaDAO dao = new FinancaDAO();

		listaDeReceitas = dao.listaTodasReceitas(usuario.getId_Usuario());
	}
	
	public double obtemReceitaTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		FinancaDAO dao = new FinancaDAO();
		
		if(dao.receitaTotal(usuario.getId_Usuario()) != null) {
			return dao.receitaTotal(usuario.getId_Usuario());
		}else {
			return 0;
		}
		
		
	}
	
	public Double obtemSaldoTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		FinancaDAO dao = new FinancaDAO();
		
		if(dao.receitaTotal(usuario.getId_Usuario()) != null && dao.despesaTotal(usuario.getId_Usuario()) != null) {
			return dao.receitaTotal(usuario.getId_Usuario()) - dao.despesaTotal(usuario.getId_Usuario());
			
		}else if(dao.receitaTotal(usuario.getId_Usuario()) != null && dao.despesaTotal(usuario.getId_Usuario()) == null) {
			return dao.receitaTotal(usuario.getId_Usuario());
			
		}else if(dao.receitaTotal(usuario.getId_Usuario()) == null && dao.despesaTotal(usuario.getId_Usuario()) != null) {
			return (-1) * dao.despesaTotal(usuario.getId_Usuario());
		}else {
			return  (double) 0.0;
		}
	}
	
	public Long obtemQtdReceitasFixas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new FinancaDAO().qtdReceitasFixas(usuario.getId_Usuario()) != null) {
			return new FinancaDAO().qtdReceitasFixas(usuario.getId_Usuario());
		}else {
			return (long) 0;
		}
	}
	
	public Double obtemTotalReceitasFixas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new FinancaDAO().totalReceitaFixa(usuario.getId_Usuario()) != null) {
			return new FinancaDAO().totalReceitaFixa(usuario.getId_Usuario());
		}else {
			return 0.0;
		}
	}

	// get e setters
	public Receita getReceita() {
		return receita;
	}

	public List<Receita> getListaDeReceitas() {
		return listaDeReceitas;
	}

	public void setListaDeReceitas(List<Receita> listaDeReceitas) {
		this.listaDeReceitas = listaDeReceitas;
	}

	public List<Tipo> getListaDeTipos() {
		return listaDeTipos;
	}

	public void setListaDeTipos(List<Tipo> listaDeTipos) {
		this.listaDeTipos = listaDeTipos;
	}

	public String getConverte() {
		return converte;
	}

	public void setConverte(String converte) {
		this.converte = converte;
	}

}
