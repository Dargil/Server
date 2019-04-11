/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.EventoDAO;
import Entidades.Calificacion;
import Entidades.Categoria;
import Entidades.Empresa;
import Entidades.Evento;
import Entidades.EventoPK;
import Entidades.Lugar;
import MODELO.EventoM;
import MODELO.EventoPKM;
import MODELO.LugarM;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EventoManager implements EventoDAO {

    EntityManager em;

    public EventoManager(EntityManager e) {
        em = e;
    }

    @Override
    public boolean insertar(Evento a) {
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

    public boolean insertarEvento(Evento a) {
        try {
            em.getTransaction().begin();
            Evento insertar = new Evento();
            EventoPK primary = new EventoPK();
            ArrayList<Empresa> empresaCollection = new ArrayList<>();
            ArrayList<Categoria> categoriaCollection = new ArrayList<>();
            Lugar direccionc = new Lugar();
            Lugar k = em.find(Lugar.class, a.getDireccion().getDireccion());
            boolean d = false;
            if (k == null) {
                direccionc.setCoordenadaX(a.getDireccion().getCoordenadaX());
                direccionc.setCoordenadaY(a.getDireccion().getCoordenadaY());
                direccionc.setDescripcion(a.getDireccion().getDescripcion());
                direccionc.setDireccion(a.getDireccion().getDireccion());
                direccionc.setNombre(a.getDireccion().getNombre());
                direccionc.setEventoCollection(a.getDireccion().getEventoCollection());
                em.persist(direccionc);
                d = true;
            }
            primary.setFecha(a.getEventoPK().getFecha());
            primary.setHoraFinal(a.getEventoPK().getHoraFinal());
            primary.setHoraInicio(a.getEventoPK().getHoraInicio());
            int v = em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList().size();
            System.out.println("v-------------- " + v);
            primary.setIdevento(v + 1);
            insertar.setEventoPK(primary);
            insertar.setFoto(a.getFoto());
            insertar.setNombre(a.getNombre());
            insertar.setDescripcion(a.getDescripcion());
            if (d) {
                insertar.setDireccion(direccionc);
            } else {
                insertar.setDireccion(k);
            }

            ArrayList<Categoria> aux1 = (ArrayList<Categoria>) a.getCategoriaCollection();
            for (int i = 0; i < aux1.size(); i++) {
                Categoria b = em.find(Categoria.class, aux1.get(i).getId());
                categoriaCollection.add(b);
            }
            ArrayList<Empresa> aux2 = (ArrayList<Empresa>) a.getEmpresaCollection();
            for (int i = 0; i < aux2.size(); i++) {
                Empresa b = em.find(Empresa.class, aux2.get(i).getEmpresaPK());
                empresaCollection.add(b);
            }
            insertar.setCategoriaCollection(categoriaCollection);
            insertar.setEmpresaCollection(empresaCollection);

            em.persist(insertar);
            em.getTransaction().commit();
            for (int i = 0; i < aux1.size(); i++) {
                Categoria b = em.find(Categoria.class, aux1.get(i).getId());
                b.getEventoCollection().add(insertar);
                FacadeFactory.getFacade().modificarEvento(b);
            }
            em.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Evento a) {
        try {
            em.getTransaction().begin();
            Evento aux = em.find(Evento.class, a.getEventoPK());
            aux.setCalificacionCollection(a.getCalificacionCollection());
            aux.setCategoriaCollection(a.getCategoriaCollection());
            aux.setDescripcion(a.getDescripcion());
            aux.setDireccion(a.getDireccion());
            aux.setEmpresaCollection(a.getEmpresaCollection());
            aux.setEventoPK(a.getEventoPK());
            aux.setNombre(a.getNombre());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Evento a) {
        try {
            em.getTransaction().begin();
            Evento aux = em.find(Evento.class, a.getEventoPK());
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
    public List<Evento> listar() {
        try {
            em.getTransaction().begin();
            List<Evento> result = em.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public EventoM obtenerEventoM(EventoPK n) {
        em.getTransaction().begin();
        EventoM c = new EventoM();
        Evento aux = em.find(Evento.class, n);
        // c.setCalificacionCollection(aux.getCalificacionCollection());
        // c.setCategoriaCollection(aux.getCategoriaCollection());
        c.setDescripcion(aux.getDescripcion());
        LugarM hj = new LugarM();
        hj.setDireccion(aux.getDireccion().getDireccion());
        c.setDireccion(hj);
        //  c.setEmpresaCollection(empresaCollection);
        EventoPKM sd = new EventoPKM();
        sd.setFecha(aux.getEventoPK().getFecha());
        sd.setHoraFinal(aux.getEventoPK().getHoraFinal());
        sd.setHoraInicio(aux.getEventoPK().getHoraInicio());
        sd.setIdevento(aux.getEventoPK().getIdevento());
        c.setEventoPK(sd);
        c.setFoto(aux.getFoto());
        c.setNombre(aux.getNombre());

        return c;
    }

    @Override
    public Evento consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
