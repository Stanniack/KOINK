package br.projeto.interdisciplinar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Financa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Financa;

    @Column(nullable = false)
    private Double valor;

    @Column(columnDefinition = "tinytext", nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_criacao;

    // muitas finanças para um usuário
    //@Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = true)
    private Usuario usuario;

    //@Column(nullable = false)
    @OneToOne // colocar notnull
    private Tipo tipo;

    public Financa(Double valor, String descricao, Usuario usuario, Tipo tipo) {
        this.valor = valor;
        this.descricao = descricao;
        this.data_criacao = new Date();
        this.usuario = usuario;
        this.tipo = tipo;

    }

    public Financa() {
       
    }
    

    public Integer getId_Financa() {
        return id_Financa;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
