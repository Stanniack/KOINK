package br.projeto.interdisciplinar.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.dao.UsuarioDAO;
import br.projeto.interdisciplinar.model.Usuario;
import br.projeto.interdisciplinar.util.FMUtil;
import br.projeto.interdisciplinar.util.Utils;

@ManagedBean
@ViewScoped
public class UsuarioController {
	private Usuario usuario = new Usuario();
	private String senhaBd;
	private String senhaAtual;
	private String senhaAtual2;

	public Usuario getUsuario() {
		return usuario;
	}

	public String getSenhaBd() {
		return senhaBd;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaAtual2() {
		return senhaAtual2;
	}

	public void setSenhaAtual2(String senhaAtual2) {
		this.senhaAtual2 = senhaAtual2;
	}

	public void gravar() {
		UsuarioDAO dao = new UsuarioDAO();

		if (senhaBd == null || !senhaBd.equals(usuario.getSenha())) {
			this.usuario.setSenha(Utils.toMD5(this.usuario.getSenha()));

		}

		if (dao.buscaPorEmail(usuario.getEmail()) == true && dao.buscaPorUsuario(usuario.getUsuario()) == true) {
			FMUtil.facesMessageErro("E-mail e usuário já estão em uso");

		} else if (dao.buscaPorEmail(usuario.getEmail()) == true
				&& dao.buscaPorUsuario(usuario.getUsuario()) == false) {
			FMUtil.facesMessageErro("E-mail já está em uso");

		} else if (dao.buscaPorEmail(usuario.getEmail()) == false
				&& dao.buscaPorUsuario(usuario.getUsuario()) == true) {
			FMUtil.facesMessageErro("Usuário já está em uso");

		} else {

			try {
				new BasicDAO<Usuario>(Usuario.class).insere(usuario);
				FMUtil.facesMessageSucesso("Usuário cadastrado com sucesso");

			} catch (RuntimeException e) {

				FMUtil.facesMessageErro("Ops! Algo de errado ao cadastrar usuário, por favor, tente novamente");
			}
		}

		// limpa a tela
		this.usuario = new Usuario();
		this.senhaBd = null;

	}

	public void editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuarioEdit = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
		
		try {
			usuarioEdit.setNome(usuario.getNome());
			usuarioEdit.setSobrenome(usuario.getSobrenome());
			usuarioEdit.setSenha(Utils.toMD5(senhaAtual2));
			new BasicDAO<Usuario>(Usuario.class).atualiza(usuarioEdit);
			
			FMUtil.facesMessageSucesso("Sucesso ao editar os dados do perfil!");

		} catch (RuntimeException e) {

			FMUtil.facesMessageErro(
					"Ops! Algo de errado aconteceu ao editar os dados do perfil, por favor, tente novamente");
		}

	}

	public void testando() {

		FMUtil.facesMessageAviso(usuario.getNome());
	}
}
