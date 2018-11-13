package br.projeto.interdisciplinar.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.dao.FinancaDAO;
import br.projeto.interdisciplinar.dao.LembreteDAO;
import br.projeto.interdisciplinar.dao.MetaDAO;
import br.projeto.interdisciplinar.model.Despesa;
import br.projeto.interdisciplinar.model.Meta;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.FMUtil;

@ManagedBean
public class MetaController {
	private Meta meta = new Meta();
	private List<Meta> listaDeMetas;
	public int debugaId = 0;

	public void salvar() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		// && (meta.getData_inicio().after(new Date()) ||
		// meta.getData_inicio().equals(new Date()))
		if (meta.getData_inicio().before(meta.getData_finalizacao()) && meta.getData_inicio().after(new Date())) {
 
			try {

				if (meta.getValor() < 0) {
					meta.setValor((-1) * meta.getValor());
					FMUtil.facesMessageAviso(
							"Não é possível metas com números negativos, por isso consertamos o valor para você! "
									+ "Caso tenha colocado o valor errado, altere-o no menu de opções");
				}

				// fazer as operações necessárias aqui
				double saldo = obtemSaldo();

				// porcentagem conclusão
				meta.setPorcentagem_finalizacao((int) ((saldo * 100) / meta.getValor()));

				if (meta.getPorcentagem_finalizacao() > 100 || meta.getPorcentagem_finalizacao() < 0) {

					if (meta.getPorcentagem_finalizacao() > 100) {
						meta.setPorcentagem_finalizacao(100);
					} else {
						meta.setPorcentagem_finalizacao(0);
					}

				}

				// finalizada?
				if (meta.getData_finalizacao().equals(new Date())) {
					meta.setFinalizada(true);
				} else {
					meta.setFinalizada(false);
				}

				// concluída?
				if (meta.getPorcentagem_finalizacao() >= 100) {
					meta.setMeta_cumprida(true);
				} else {
					meta.setMeta_cumprida(false);
				}

				if (this.meta.getId_Meta() == null) {
					// processo de salvamento
					FMUtil.facesMessageAviso(
							"" + usuario.getId_Usuario() + " " + meta.getId_Meta() + " " + usuario.getId_Usuario());
					meta.setUsuario(usuario);

					// processo de atualização
					new BasicDAO<Meta>(Meta.class).insere(meta);

					FMUtil.facesMessageSucesso("Meta salva com sucesso!");
				} else {
					// processo de atualização
					meta.setId_Meta(debugaId);
					FMUtil.facesMessageAviso(
							"" + usuario.getId_Usuario() + " " + meta.getId_Meta() + " " + usuario.getId_Usuario());
					new BasicDAO<Meta>(Meta.class).atualiza(meta);

					FMUtil.facesMessageSucesso("Meta atualizada com sucesso!");

				}

				// Limpa campo
				this.meta = new Meta();

				listaTodasMetas();

			} catch (RuntimeException erro) {
				FMUtil.facesMessageErro("Algo aconteceu e não foi possível criar sua meta, por favor, tente novamente");
				erro.printStackTrace();
			}

		} else {
			FMUtil.facesMessageAviso("Ops, a data de início maior que a data de finalização. "
					+ "Ou a data de finalização é menor que a data de hoje!");
		}

	}

	public void excluir(ActionEvent evento) {

		try {
			// captura da variável "despesaSelecionada" do arquivo xhtml
			meta = (Meta) evento.getComponent().getAttributes().get("metaSelecionada");

			// remove
			BasicDAO<Meta> dao = new BasicDAO<>(Meta.class);
			dao.remove(meta.getId_Meta());

			listaTodasMetas();

			FMUtil.facesMessageSucesso("Sucesso ao remover a meta!");

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ops, ocorreu um erro ao tentar remover esta meta");
			erro.printStackTrace();
		}

	}

	public void editar(ActionEvent evento) {
		// captura da variável "despesaSelecionada" do arquivo xhtml
		meta = (Meta) evento.getComponent().getAttributes().get("metaSelecionada");

	}

	@PostConstruct
	public void listar() {
		try {
			verificaMetas();
			listaTodasMetas();

		} catch (RuntimeException erro) {
			FMUtil.facesMessageErro("Ocorreu um erro ao tentar buscar as metas :(");
			erro.printStackTrace();
		}
	}

	public void listaTodasMetas() {
		// pega usuário da sessão
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		MetaDAO dao = new MetaDAO();
		listaDeMetas = dao.listaTodasMetas(usuario.getId_Usuario());
	}

	public void novo() {
		meta = new Meta();
	}

	public Long obtemQtdMetas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		MetaDAO dao = new MetaDAO();

		if (dao.metaTotal(usuario.getId_Usuario()) != null) {
			return dao.metaTotal(usuario.getId_Usuario());
		} else {
			return (long) 0;
		}

	}

	public void verificaMetas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		List<Meta> lista = new MetaDAO().listaTodasMetas(usuario.getId_Usuario());

		for (Meta m : lista) {
			
			if (m.isMeta_cumprida() == false || m.getData_finalizacao().before(new Date())) {
				// fazer as operações necessárias aqui
				double saldo = obtemSaldo();

				// porcentagem conclusão
				m.setPorcentagem_finalizacao((int) ((saldo * 100) / m.getValor()));

				if (m.getPorcentagem_finalizacao() > 100 || m.getPorcentagem_finalizacao() < 0) {

					if (m.getPorcentagem_finalizacao() > 100) {
						m.setPorcentagem_finalizacao(100);
					} else {
						m.setPorcentagem_finalizacao(0);
					}

				}

				// finalizada?
				if (m.getData_finalizacao().equals(new Date())) {
					m.setFinalizada(true);
				} else {
					m.setFinalizada(false);
				}

				// concluída?
				if (m.getPorcentagem_finalizacao() >= 100) {
					m.setMeta_cumprida(true);
				} else {
					m.setMeta_cumprida(false);
				}

				new BasicDAO<Meta>(Meta.class).atualiza(m);

			} // fim do for
		} // fim do if

	}

	public double obtemSaldo() {
		double saldo;

		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

		if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) != null
				&& new FinancaDAO().despesaTotal(usuario.getId_Usuario()) != null) {

			saldo = new FinancaDAO().receitaTotal(usuario.getId_Usuario())
					- new FinancaDAO().despesaTotal(usuario.getId_Usuario());

		} else if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) != null
				&& new FinancaDAO().despesaTotal(usuario.getId_Usuario()) == null) {

			saldo = new FinancaDAO().receitaTotal(usuario.getId_Usuario());

		} else if (new FinancaDAO().receitaTotal(usuario.getId_Usuario()) == null
				&& new FinancaDAO().despesaTotal(usuario.getId_Usuario()) != null) {
			saldo = new FinancaDAO().despesaTotal(usuario.getId_Usuario());
		} else {
			saldo = 0;
		}

		return saldo;
	}
	
	public Long obtemMetasDataFinalizada() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new MetaDAO().qtdMetasFinalizadas(usuario.getId_Usuario()) != null) {
			return new MetaDAO().qtdMetasFinalizadas(usuario.getId_Usuario());
		}else {
			return (long) 0;
		}
	}
	
	public Long obtemMetasConcluidas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new MetaDAO().qtdMetasConcluidas(usuario.getId_Usuario()) != null) {
			return new MetaDAO().qtdMetasConcluidas(usuario.getId_Usuario());
		}else {
			return (long) 0;
		}
	}
	
	public Long obtemMetasFracassadas() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		if(new MetaDAO().qtdMetasFracassadas(usuario.getId_Usuario()) != null) {
			return new MetaDAO().qtdMetasFracassadas(usuario.getId_Usuario());
		}else {
			return (long) 0;
		}
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Meta> getListaDeMetas() {
		return listaDeMetas;
	}

	public void setListaDeMetas(List<Meta> listaDeMetas) {
		this.listaDeMetas = listaDeMetas;
	}

}
