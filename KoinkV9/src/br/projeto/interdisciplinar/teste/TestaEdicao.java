
package br.projeto.interdisciplinar.teste;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.model.Financa;
import br.projeto.interdisciplinar.model.Tipo;
import br.projeto.interdisciplinar.model.Usuario;

public class TestaEdicao {
    public static void main(String[] args) {
        Usuario p = new Usuario();
        p.setId_Usuario(1);
        
        Tipo t = new Tipo();
        t.setId_Tipo(1);
        
        BasicDAO<Financa> dao = new BasicDAO<>(Financa.class);
        Financa find = dao.buscaPorId(1);
        //find.alteraValorFinanca(2200);
       
        
        //BasicDAO<Tipo> daoTipo = new BasicDAO<>(Tipo.class);
        BasicDAO<Financa> daoFinanca = new BasicDAO<>(Financa.class);
        
        //daoTipo.insere(t);
        daoFinanca.atualiza(find);
    }
}
