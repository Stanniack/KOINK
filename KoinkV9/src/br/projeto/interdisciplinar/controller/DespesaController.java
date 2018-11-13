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
import br.projeto.interdisciplinar.model.Despesa;
import br.projeto.interdisciplinar.model.FormaDePagamento;
import br.projeto.interdisciplinar.model.Tipo;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.FMUtil;

@ViewScoped // meus objetos só ficam vivos enquanto eu estiver na página!
@ManagedBean
public class DespesaController {
	private Despesa despesa = new Despesa();
	private List<Despesa> listaDeDespesas;
	private List<Tipo> listaDeTipos;
	private String converte;

	public void salvar() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (despesa.isPago() && despesa.getPagamento() == null) {
			FMUtil.facesMessageErro("Ops! Ao selecionar despesa paga, é necessário uma forma de pagamento");

		} else if (!despesa.isPago() && despesa.getPagamento() != null) {
			FMUtil.facesMessageErro("Ops! não é possível ter uma forma de pagamento para uma despesa que não foi paga");

		} else {
			try {

				if (despesa.getValor() < 0) {
					despesa.setValor((-1) * despesa.getValor());
					FMUtil.facesMessageAviso(
							"Não é possível despesas com números negativos, por isso consertamos o valor para você! "
									+ "Caso tenha colocado o valor errado, altere-o no menu de opções");
				}

				if (this.despesa.getId_Financa() == null) {

					// processo de salvamento
					despesa.setData_criacao(new Date());
					despesa.setUsuario(usuario);

					new BasicDAO<Despesa>(Despesa.class).insere(despesa);

					FMUtil.facesMessageSucesso("Uma nova despesa foi criada com sucesso!");

					// Limpa campo
					this.despesa = new Despesa();

				} else {

					// processo de atualização
					new BasicDAO<Despesa>(Despesa.class).atualiza(despesa);

					FMUtil.facesMessageSucesso("Despesa atualizada com sucesso!");

					// Limpa campo
					this.despesa = new Despesa();
				}

				listaTodasDespesas();

			} catch (RuntimeException erro) {
				FMUtil.facesMessageErro(
						"Algo aconteceu e não foi possível criar sua despesa, por favor, tente novamente");
				erro.printStackTrace();
			}
		}

	} // fim do salva

	@PostConstruct // funciona como um construtor, toda vez que a página é criada, esse método é
					// invocado
	public void listar() {
		try {
			// tenho que buscar as despesas por id do usuário mudar isso aqui!
			listaTodasDespesas();

			// lista por tipo
			listaTodosTipos();
			
			//lista enum formadepagamento
			getFormaDePagamento();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ocorreu um erro ao tentar buscar as despesas :(");
			erro.printStackTrace();
		}

	} // fim do listar

	public void excluir(ActionEvent evento) { // ActionEvent serve para capturar dados da view

		try {
			// captura da variável "despesaSelecionada" do arquivo xhtml
			despesa = (Despesa) evento.getComponent().getAttributes().get("despesaSelecionada");

			// remove
			BasicDAO<Despesa> dao = new BasicDAO<>(Despesa.class);
			dao.remove(despesa.getId_Financa());

			listaTodasDespesas();

			FMUtil.facesMessageSucesso("Sucesso ao remover esta despesa!");

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ops, ocorreu um erro ao tentar remover esta despesa");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		// captura da variável "despesaSelecionada" do arquivo xhtml
		despesa = (Despesa) evento.getComponent().getAttributes().get("despesaSelecionada");

	}

	// limpa objeto
	public void novo() {
		this.despesa = new Despesa();
	}

	private void listaTodosTipos() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		TipoDAO dao = new TipoDAO();
		listaDeTipos = dao.listaTodos("DESPESA", usuario.getId_Usuario());
	}

	private void listaTodasDespesas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		FinancaDAO dao = new FinancaDAO();

		listaDeDespesas = dao.listaTodasDespesas(usuario.getId_Usuario());
	}
	
	public Double obtemDespesaTotal() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		FinancaDAO dao = new FinancaDAO();
		
		if(dao.despesaTotal(usuario.getId_Usuario()) != null) {
			return dao.despesaTotal(usuario.getId_Usuario());
		}else {
			return 0.0;
		}
		
		
	}
	
	public Long obtemQtdDespesasPendentes() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new FinancaDAO().qtdDespesasPendentes(usuario.getId_Usuario()) != null) {
			return new FinancaDAO().qtdDespesasPendentes(usuario.getId_Usuario());
		}else {
			return (long) 0;
		}
	}
	
	public Double obtemDespesasTotalPendentes() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new FinancaDAO().despesaPendente(usuario.getId_Usuario()) != null) {
			return new FinancaDAO().despesaPendente(usuario.getId_Usuario());
		}else {
			return 0.0;
		}
	}
	

	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getListaDeDespesas() {
		return listaDeDespesas;
	}

	public void setListaDeDespesas(List<Despesa> listaDeDespesas) {
		this.listaDeDespesas = listaDeDespesas;
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

	public FormaDePagamento[] getFormaDePagamento() {
		return FormaDePagamento.values();
	}

}
