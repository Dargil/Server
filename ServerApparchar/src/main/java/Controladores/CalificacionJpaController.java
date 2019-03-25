/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Controladores.exceptions.RollbackFailureException;
import Entidades.Calificacion;
import Entidades.CalificacionPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Cliente;
import Entidades.Evento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jeffe
 */
public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificacion calificacion) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (calificacion.getCalificacionPK() == null) {
            calificacion.setCalificacionPK(new CalificacionPK());
        }
        calificacion.getCalificacionPK().setUsuariocliente(calificacion.getCliente().getUsuario());
        calificacion.getCalificacionPK().setIdevento(calificacion.getEvento().getEventoPK().getIdevento());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cliente cliente = calificacion.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getUsuario());
                calificacion.setCliente(cliente);
            }
            Evento evento = calificacion.getEvento();
            if (evento != null) {
                evento = em.getReference(evento.getClass(), evento.getEventoPK());
                calificacion.setEvento(evento);
            }
            em.persist(calificacion);
            if (cliente != null) {
                cliente.getCalificacionCollection().add(calificacion);
                cliente = em.merge(cliente);
            }
            if (evento != null) {
                evento.getCalificacionCollection().add(calificacion);
                evento = em.merge(evento);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCalificacion(calificacion.getCalificacionPK()) != null) {
                throw new PreexistingEntityException("Calificacion " + calificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        calificacion.getCalificacionPK().setUsuariocliente(calificacion.getCliente().getUsuario());
        calificacion.getCalificacionPK().setIdevento(calificacion.getEvento().getEventoPK().getIdevento());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getCalificacionPK());
            Cliente clienteOld = persistentCalificacion.getCliente();
            Cliente clienteNew = calificacion.getCliente();
            Evento eventoOld = persistentCalificacion.getEvento();
            Evento eventoNew = calificacion.getEvento();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getUsuario());
                calificacion.setCliente(clienteNew);
            }
            if (eventoNew != null) {
                eventoNew = em.getReference(eventoNew.getClass(), eventoNew.getEventoPK());
                calificacion.setEvento(eventoNew);
            }
            calificacion = em.merge(calificacion);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getCalificacionCollection().remove(calificacion);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getCalificacionCollection().add(calificacion);
                clienteNew = em.merge(clienteNew);
            }
            if (eventoOld != null && !eventoOld.equals(eventoNew)) {
                eventoOld.getCalificacionCollection().remove(calificacion);
                eventoOld = em.merge(eventoOld);
            }
            if (eventoNew != null && !eventoNew.equals(eventoOld)) {
                eventoNew.getCalificacionCollection().add(calificacion);
                eventoNew = em.merge(eventoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CalificacionPK id = calificacion.getCalificacionPK();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CalificacionPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getCalificacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            Cliente cliente = calificacion.getCliente();
            if (cliente != null) {
                cliente.getCalificacionCollection().remove(calificacion);
                cliente = em.merge(cliente);
            }
            Evento evento = calificacion.getEvento();
            if (evento != null) {
                evento.getCalificacionCollection().remove(calificacion);
                evento = em.merge(evento);
            }
            em.remove(calificacion);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Calificacion findCalificacion(CalificacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificacion> rt = cq.from(Calificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
