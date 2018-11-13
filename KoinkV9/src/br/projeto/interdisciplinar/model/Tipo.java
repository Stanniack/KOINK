package br.projeto.interdisciplinar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Tipo;

    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(nullable = false)
    private String tipo_financa;
    
    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = true)
    private Usuario usuario;
    
    public Tipo(String tipo, String tipo_financa) {
        this.tipo = tipo;
        this.tipo_financa = tipo_financa;
    }
    
    public Tipo(){
        
    }
    

    public Integer getId_Tipo() {
        return id_Tipo;
    }

    public void setId_Tipo(Integer id_Tipo) {
        this.id_Tipo = id_Tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_financa() {
        return tipo_financa;
    }

    public void setTipo_financa(String tipo_financa) {
        this.tipo_financa = tipo_financa;
    }
    
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
    public String toString() {
        return String.format("%s[id_tipo=%d]", getClass().getSimpleName(), getId_Tipo());
    }

	
    
    

}
