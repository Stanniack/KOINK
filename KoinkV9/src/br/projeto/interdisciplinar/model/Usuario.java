package br.projeto.interdisciplinar.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Usuario;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String sobrenome;

    @Column(unique = true, nullable = false, length = 50)
    private String usuario;

    private String senha;

    @Column(unique = true, nullable = false, length = 100)
    private String email;
    
    //
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
    List<Tipo> listaDeTipo = new ArrayList<>();
    
    //Um usuário para muitas finanças
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
    List<Financa> listaDeFinanca = new ArrayList<>();
    
    //Um usuário para muitos lembretes
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
    List<Lembrete> listaDeLembrete = new ArrayList<>();
    
    //Um usuário para muitas metas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
    List<Meta> listaDeMeta = new ArrayList<>();

    public Usuario(String nome, String sobrenome, String usuario, String senha, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }
    
    public Usuario() {

    }

    public Integer getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(Integer id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Financa> getListaDeFinanca() {
        return listaDeFinanca;
    }

    public void setListaDeFinanca(List<Financa> listaDeFinanca) {
        this.listaDeFinanca = listaDeFinanca;
    }

	public List<Tipo> getListaDeTipo() {
		return listaDeTipo;
	}

	public void setListaDeTipo(List<Tipo> listaDeTipo) {
		this.listaDeTipo = listaDeTipo;
	}

	public List<Lembrete> getListaDeLembrete() {
		return listaDeLembrete;
	}

	public void setListaDeLembrete(List<Lembrete> listaDeLembrete) {
		this.listaDeLembrete = listaDeLembrete;
	}

	public List<Meta> getListaDeMeta() {
		return listaDeMeta;
	}

	public void setListaDeMeta(List<Meta> listaDeMeta) {
		this.listaDeMeta = listaDeMeta;
	}

   

    

}
