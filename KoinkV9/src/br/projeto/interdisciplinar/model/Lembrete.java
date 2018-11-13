package br.projeto.interdisciplinar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lembrete {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Lembrete;
	
	@Column(columnDefinition = "tinytext", nullable = false)
	private String descricao;
	
	@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_inicio_aviso;
	
	@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_finalizacao;
	
	@ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = true)
    private Usuario usuario;

	public Lembrete() {
		
	}

	public Integer getId_Lembrete() {
		return id_Lembrete;
	}

	public void setId_Lembrete(Integer id_Lembrete) {
		this.id_Lembrete = id_Lembrete;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_inicio_aviso() {
		return data_inicio_aviso;
	}

	public void setData_inicio_aviso(Date data_inicio_aviso) {
		this.data_inicio_aviso = data_inicio_aviso;
	}

	public Date getData_finalizacao() {
		return data_finalizacao;
	}

	public void setData_finalizacao(Date data_finalizacao) {
		this.data_finalizacao = data_finalizacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
}
