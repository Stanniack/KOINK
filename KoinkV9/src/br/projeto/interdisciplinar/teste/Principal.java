package br.projeto.interdisciplinar.teste;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.model.Usuario;


public class Principal {

    public static void main(String[] args) {

        BasicDAO<Usuario> dao2 = new BasicDAO<>(Usuario.class);

    }
}
