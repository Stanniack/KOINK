package br.projeto.interdisciplinar.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import br.projeto.interdisciplinar.util.DATAUtil;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_Financa")
public class Receita extends Financa {

    @Column(nullable = true)
    private boolean fixa;

    @Temporal(TemporalType.DATE)
    private Date data_fixamento;

    public Receita() {

    }

    public Receita(boolean fixa, Date data, Double valor, String descricao, Usuario usuario, Tipo tipo) {
        super(valor, descricao, usuario, tipo);
        this.fixa = fixa;
        this.data_fixamento = data;

    }

    public boolean isFixa() {
        return fixa;
    }

    public void setFixa(boolean fixa) {
        this.fixa = fixa;
    }

    public Date getData_fixamento() {
        return data_fixamento;
    }

    public void setData_fixamento(Date data_fixamento) {
        this.data_fixamento = data_fixamento;
    }

}
