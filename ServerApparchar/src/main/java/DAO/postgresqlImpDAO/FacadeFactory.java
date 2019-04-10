/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jefferson DArio Marin Giraldo
 */
public class FacadeFactory {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Apparchar");

    public static Facade fachada = null;
    public static EntityManager em;

    public static Facade getFacade() {
        em = emf.createEntityManager();
        fachada = new Facade(em);
        return fachada;
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }

}
