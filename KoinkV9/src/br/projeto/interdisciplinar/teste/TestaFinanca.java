
package br.projeto.interdisciplinar.teste;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.model.Financa;
import br.projeto.interdisciplinar.model.Receita;
import br.projeto.interdisciplinar.model.Tipo;
import br.projeto.interdisciplinar.model.Usuario;
import java.util.Calendar;


public class TestaFinanca {
    public static void main(String[] args) {
        Usuario p = new Usuario();
        p.setId_Usuario(3);
        
        //Tipo t = new Tipo("Teste", TipoFinanca.RECEITA);
        Tipo t = new Tipo();
        t.setId_Tipo(1);
        
        
        //Receita f = new Receita(true, "2018-05-31", 5000.0, "Programas", p, t);
        
        
        
        //BasicDAO<Tipo> daoTipo = new BasicDAO<>(Tipo.class);
        //BasicDAO<Receita> daoFinanca = new BasicDAO<>(Receita.class);
        
        //daoTipo.insere(t);
        //daoFinanca.insere(f);
        
    }
}
