package br.projeto.interdisciplinar.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("projeto");
    
    public static EntityManager getInstance(){
        return emf.createEntityManager();
    }
}
