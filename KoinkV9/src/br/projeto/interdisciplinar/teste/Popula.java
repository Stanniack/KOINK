package br.projeto.interdisciplinar.teste;

import br.projeto.interdisciplinar.dao.BasicDAO;
import br.projeto.interdisciplinar.model.Usuario;

public class Popula {

    public static void main(String[] args) {

        //Usuario p1 = new Usuario("Mateus", "Vitor", "Staniak", "123", "fiodor20@outlook.com");
        Usuario p2 = new Usuario("Anderson", "Faria", "Ziniak", "456", "aaanderson@gmail.com");
        Usuario p3 = new Usuario("Lucas", "Assis", "CoronelAssis", "789", "grupoassis@gmail.com");
        Usuario p4 = new Usuario("João", "Willian", "Joao23", "101112", "joaowillian@outlook.com");

        BasicDAO<Usuario> dao2 = new BasicDAO<>(Usuario.class);

        //dao2.insere(p1);
        dao2.insere(p2);
        dao2.insere(p3);
        dao2.insere(p4);

    }
}
