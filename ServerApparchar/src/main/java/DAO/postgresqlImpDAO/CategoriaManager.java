/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CategoriaDAO;
import Entidades.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoriaManager implements CategoriaDAO {

    EntityManager em;

    public CategoriaManager(EntityManager e) {

        em = e;
    }

    @Override
    public boolean insertar(Categoria a) {
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Categoria a) {
        try {
            em.getTransaction().begin();
            Categoria aux = em.find(Categoria.class, a.getId());
            aux.setId(a.getId());
            aux.setEventoCollection(a.getEventoCollection());
            aux.setIcono(a.getIcono());
            aux.setNombre(a.getNombre());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificarEvento(Categoria a) {
        try {
            em.getTransaction().begin();
            Categoria aux = em.find(Categoria.class, a.getId());
            aux.setEventoCollection(a.getEventoCollection());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Categoria a) {
        try {
            em.getTransaction().begin();
            Categoria aux = em.find(Categoria.class, a.getId());
            em.remove(aux);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Categoria> listar() {
        try {
            em.getTransaction().begin();
            List<Categoria> result = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categoria consultar(String id) {
        try {
            em.getTransaction().begin();
            int prk = Integer.parseInt(id);
            Categoria aux = em.find(Categoria.class, prk);
            em.getTransaction().commit();
            em.close();
            return aux;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
