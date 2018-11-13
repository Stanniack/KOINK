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
public class Meta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Meta;

	@Column(length = 45)
	private String nome;
	
	@Column(columnDefinition = "tinytext", nullable = false)
    private String descricao;
	
	@Column(nullable = false)
    private Double valor;
	
	private int porcentagem_finalizacao;
	
	@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_inicio;
	
	@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_finalizacao;
	
	private boolean finalizada;
	
	private boolean meta_cumprida;
	
	@ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = true)
    private Usuario usuario;
	
	public Meta() {
		
	}

	public Integer getId_Meta() {
		return id_Meta;
	}

	public void setId_Meta(Integer id_Meta) {
		this.id_Meta = id_Meta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int getPorcentagem_finalizacao() {
		return porcentagem_finalizacao;
	}

	public void setPorcentagem_finalizacao(int porcentagem_finalizacao) {
		this.porcentagem_finalizacao = porcentagem_finalizacao;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_finalizacao() {
		return data_finalizacao;
	}

	public void setData_finalizacao(Date data_finalizacao) {
		this.data_finalizacao = data_finalizacao;
	}

	public boolean isFinalizada() {
		return finalizada;
	}

	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}

	public boolean isMeta_cumprida() {
		return meta_cumprida;
	}

	public void setMeta_cumprida(boolean meta_cumprida) {
		this.meta_cumprida = meta_cumprida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
}
