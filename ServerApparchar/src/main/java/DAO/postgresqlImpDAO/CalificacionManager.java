/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CalificacionDAO;
import Entidades.Calificacion;
import java.util.List;
import javax.persistence.EntityManager;

public class CalificacionManager implements CalificacionDAO {

    EntityManager em;

    public CalificacionManager(EntityManager e) {
        em = e;
    }

    @Override
    public boolean insertar(Calificacion a) {
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

    public boolean insertarCal(Calificacion a) {
        try {
            em.getTransaction().begin();

            Calificacion aux = em.find(Calificacion.class, a.getCalificacionPK());
            if (aux != null) {
                if (a.getMultimedia() != null) {
                    aux.setMultimedia(a.getMultimedia());
                }
                if (a.getComentario() != null) {
                    aux.setComentario(a.getComentario());
                }
                if (a.getPorcentaje() != null) {
                    aux.setPorcentaje(a.getPorcentaje());
                }
                em.getTransaction().commit();
                em.close();
            } else {
                em.persist(a);
                em.getTransaction().commit();
                em.close();
                System.out.println("------------------------------PRUEBAAAAAA---------------");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Calificacion a) {
        try {
            em.getTransaction().begin();
            Calificacion aux = em.find(Calificacion.class, a.getCalificacionPK());
            aux.setCalificacionPK(a.getCalificacionPK());
            aux.setCliente(a.getCliente());
            aux.setComentario(a.getComentario());
            aux.setEvento(a.getEvento());
            aux.setFecha(a.getFecha());
            aux.setHora(a.getHora());
            aux.setMultimedia(a.getMultimedia());
            aux.setPorcentaje(a.getPorcentaje());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Calificacion a) {
        try {
            em.getTransaction().begin();
            Calificacion aux = em.find(Calificacion.class, a.getCalificacionPK());
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
    public List<Calificacion> listar() {

        try {
            em.getTransaction().begin();
            List<Calificacion> result = em.createQuery("SELECT c FROM Calificacion c", Calificacion.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Calificacion consultar(String id) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calificacion consultar(String idUser, String idEvento) {
        return null;
    }

    public List<Calificacion> listarbyID(int id) {

        try {
            em.getTransaction().begin();
            List<Calificacion> result = em.createQuery("SELECT c FROM Calificacion c WHERE c.calificacionPK.idevento = :idevento", Calificacion.class).setParameter("idevento", id).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
