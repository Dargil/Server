/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Evento;
import Entidades.Lugar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jeffe
 */
public class LugarJpaController implements Serializable {

    public LugarJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lugar lugar) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (lugar.getEventoCollection() == null) {
            lugar.setEventoCollection(new ArrayList<Evento>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Evento> attachedEventoCollection = new ArrayList<Evento>();
            for (Evento eventoCollectionEventoToAttach : lugar.getEventoCollection()) {
                eventoCollectionEventoToAttach = em.getReference(eventoCollectionEventoToAttach.getClass(), eventoCollectionEventoToAttach.getEventoPK());
                attachedEventoCollection.add(eventoCollectionEventoToAttach);
            }
            lugar.setEventoCollection(attachedEventoCollection);
            em.persist(lugar);
            for (Evento eventoCollectionEvento : lugar.getEventoCollection()) {
                Lugar oldDireccionOfEventoCollectionEvento = eventoCollectionEvento.getDireccion();
                eventoCollectionEvento.setDireccion(lugar);
                eventoCollectionEvento = em.merge(eventoCollectionEvento);
                if (oldDireccionOfEventoCollectionEvento != null) {
                    oldDireccionOfEventoCollectionEvento.getEventoCollection().remove(eventoCollectionEvento);
                    oldDireccionOfEventoCollectionEvento = em.merge(oldDireccionOfEventoCollectionEvento);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findLugar(lugar.getDireccion()) != null) {
                throw new PreexistingEntityException("Lugar " + lugar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lugar lugar) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lugar persistentLugar = em.find(Lugar.class, lugar.getDireccion());
            Collection<Evento> eventoCollectionOld = persistentLugar.getEventoCollection();
            Collection<Evento> eventoCollectionNew = lugar.getEventoCollection();
            Collection<Evento> attachedEventoCollectionNew = new ArrayList<Evento>();
            for (Evento eventoCollectionNewEventoToAttach : eventoCollectionNew) {
                eventoCollectionNewEventoToAttach = em.getReference(eventoCollectionNewEventoToAttach.getClass(), eventoCollectionNewEventoToAttach.getEventoPK());
                attachedEventoCollectionNew.add(eventoCollectionNewEventoToAttach);
            }
            eventoCollectionNew = attachedEventoCollectionNew;
            lugar.setEventoCollection(eventoCollectionNew);
            lugar = em.merge(lugar);
            for (Evento eventoCollectionOldEvento : eventoCollectionOld) {
                if (!eventoCollectionNew.contains(eventoCollectionOldEvento)) {
                    eventoCollectionOldEvento.setDireccion(null);
                    eventoCollectionOldEvento = em.merge(eventoCollectionOldEvento);
                }
            }
            for (Evento eventoCollectionNewEvento : eventoCollectionNew) {
                if (!eventoCollectionOld.contains(eventoCollectionNewEvento)) {
                    Lugar oldDireccionOfEventoCollectionNewEvento = eventoCollectionNewEvento.getDireccion();
                    eventoCollectionNewEvento.setDireccion(lugar);
                    eventoCollectionNewEvento = em.merge(eventoCollectionNewEvento);
                    if (oldDireccionOfEventoCollectionNewEvento != null && !oldDireccionOfEventoCollectionNewEvento.equals(lugar)) {
                        oldDireccionOfEventoCollectionNewEvento.getEventoCollection().remove(eventoCollectionNewEvento);
                        oldDireccionOfEventoCollectionNewEvento = em.merge(oldDireccionOfEventoCollectionNewEvento);
                    }
                }
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
                String id = lugar.getDireccion();
                if (findLugar(id) == null) {
                    throw new NonexistentEntityException("The lugar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lugar lugar;
            try {
                lugar = em.getReference(Lugar.class, id);
                lugar.getDireccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lugar with id " + id + " no longer exists.", enfe);
            }
            Collection<Evento> eventoCollection = lugar.getEventoCollection();
            for (Evento eventoCollectionEvento : eventoCollection) {
                eventoCollectionEvento.setDireccion(null);
                eventoCollectionEvento = em.merge(eventoCollectionEvento);
            }
            em.remove(lugar);
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

    public List<Lugar> findLugarEntities() {
        return findLugarEntities(true, -1, -1);
    }

    public List<Lugar> findLugarEntities(int maxResults, int firstResult) {
        return findLugarEntities(false, maxResults, firstResult);
    }

    private List<Lugar> findLugarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lugar.class));
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

    public Lugar findLugar(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lugar.class, id);
        } finally {
            em.close();
        }
    }

    public int getLugarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lugar> rt = cq.from(Lugar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
