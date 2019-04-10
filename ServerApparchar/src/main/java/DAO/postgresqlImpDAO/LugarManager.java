/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.LugarDAO;
import Entidades.Lugar;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Persistence;

public class LugarManager implements LugarDAO {

    EntityManager em;

    public LugarManager(EntityManager e) {
        em = e;
    }

    @Override
    public boolean insertar(Lugar a) {
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
    public boolean modificar(Lugar a) {
        try {
            em.getTransaction().begin();
            Lugar aux = em.find(Lugar.class, a.getDireccion());
            aux.setDescripcion(a.getDescripcion());
            aux.setDireccion(a.getDireccion());
            aux.setEventoCollection(a.getEventoCollection());
            aux.setNombre(a.getNombre());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Lugar a) {
        try {
            em.getTransaction().begin();
            Lugar aux = em.find(Lugar.class, a.getDireccion());
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
    public List<Lugar> listar() {

        try {
            em.getTransaction().begin();
            List<Lugar> result = em.createQuery("SELECT l FROM Lugar l", Lugar.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Lugar consultar(String id) {
        try {
            em.getTransaction().begin();
            Lugar aux = em.find(Lugar.class, id);
            em.getTransaction().commit();
            em.close();
            return aux;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
