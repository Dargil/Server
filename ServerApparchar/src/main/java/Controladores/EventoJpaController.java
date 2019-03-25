/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Controladores.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Lugar;
import Entidades.Empresa;
import java.util.ArrayList;
import java.util.Collection;
import Entidades.Categoria;
import Entidades.Calificacion;
import Entidades.Evento;
import Entidades.EventoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author jeffe
 */
public class EventoJpaController implements Serializable {

    public EventoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Evento evento) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (evento.getEventoPK() == null) {
            evento.setEventoPK(new EventoPK());
        }
        if (evento.getEmpresaCollection() == null) {
            evento.setEmpresaCollection(new ArrayList<Empresa>());
        }
        if (evento.getCategoriaCollection() == null) {
            evento.setCategoriaCollection(new ArrayList<Categoria>());
        }
        if (evento.getCalificacionCollection() == null) {
            evento.setCalificacionCollection(new ArrayList<Calificacion>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Lugar direccion = evento.getDireccion();
            if (direccion != null) {
                direccion = em.getReference(direccion.getClass(), direccion.getDireccion());
                evento.setDireccion(direccion);
            }
            Collection<Empresa> attachedEmpresaCollection = new ArrayList<Empresa>();
            for (Empresa empresaCollectionEmpresaToAttach : evento.getEmpresaCollection()) {
                empresaCollectionEmpresaToAttach = em.getReference(empresaCollectionEmpresaToAttach.getClass(), empresaCollectionEmpresaToAttach.getEmpresaPK());
                attachedEmpresaCollection.add(empresaCollectionEmpresaToAttach);
            }
            evento.setEmpresaCollection(attachedEmpresaCollection);
            Collection<Categoria> attachedCategoriaCollection = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionCategoriaToAttach : evento.getCategoriaCollection()) {
                categoriaCollectionCategoriaToAttach = em.getReference(categoriaCollectionCategoriaToAttach.getClass(), categoriaCollectionCategoriaToAttach.getId());
                attachedCategoriaCollection.add(categoriaCollectionCategoriaToAttach);
            }
            evento.setCategoriaCollection(attachedCategoriaCollection);
            Collection<Calificacion> attachedCalificacionCollection = new ArrayList<Calificacion>();
            for (Calificacion calificacionCollectionCalificacionToAttach : evento.getCalificacionCollection()) {
                calificacionCollectionCalificacionToAttach = em.getReference(calificacionCollectionCalificacionToAttach.getClass(), calificacionCollectionCalificacionToAttach.getCalificacionPK());
                attachedCalificacionCollection.add(calificacionCollectionCalificacionToAttach);
            }
            evento.setCalificacionCollection(attachedCalificacionCollection);
            em.persist(evento);
            if (direccion != null) {
                direccion.getEventoCollection().add(evento);
                direccion = em.merge(direccion);
            }
            for (Empresa empresaCollectionEmpresa : evento.getEmpresaCollection()) {
                empresaCollectionEmpresa.getEventoCollection().add(evento);
                empresaCollectionEmpresa = em.merge(empresaCollectionEmpresa);
            }
            for (Categoria categoriaCollectionCategoria : evento.getCategoriaCollection()) {
                categoriaCollectionCategoria.getEventoCollection().add(evento);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            for (Calificacion calificacionCollectionCalificacion : evento.getCalificacionCollection()) {
                Evento oldEventoOfCalificacionCollectionCalificacion = calificacionCollectionCalificacion.getEvento();
                calificacionCollectionCalificacion.setEvento(evento);
                calificacionCollectionCalificacion = em.merge(calificacionCollectionCalificacion);
                if (oldEventoOfCalificacionCollectionCalificacion != null) {
                    oldEventoOfCalificacionCollectionCalificacion.getCalificacionCollection().remove(calificacionCollectionCalificacion);
                    oldEventoOfCalificacionCollectionCalificacion = em.merge(oldEventoOfCalificacionCollectionCalificacion);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEvento(evento.getEventoPK()) != null) {
                throw new PreexistingEntityException("Evento " + evento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Evento evento) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evento persistentEvento = em.find(Evento.class, evento.getEventoPK());
            Lugar direccionOld = persistentEvento.getDireccion();
            Lugar direccionNew = evento.getDireccion();
            Collection<Empresa> empresaCollectionOld = persistentEvento.getEmpresaCollection();
            Collection<Empresa> empresaCollectionNew = evento.getEmpresaCollection();
            Collection<Categoria> categoriaCollectionOld = persistentEvento.getCategoriaCollection();
            Collection<Categoria> categoriaCollectionNew = evento.getCategoriaCollection();
            Collection<Calificacion> calificacionCollectionOld = persistentEvento.getCalificacionCollection();
            Collection<Calificacion> calificacionCollectionNew = evento.getCalificacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Calificacion calificacionCollectionOldCalificacion : calificacionCollectionOld) {
                if (!calificacionCollectionNew.contains(calificacionCollectionOldCalificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Calificacion " + calificacionCollectionOldCalificacion + " since its evento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (direccionNew != null) {
                direccionNew = em.getReference(direccionNew.getClass(), direccionNew.getDireccion());
                evento.setDireccion(direccionNew);
            }
            Collection<Empresa> attachedEmpresaCollectionNew = new ArrayList<Empresa>();
            for (Empresa empresaCollectionNewEmpresaToAttach : empresaCollectionNew) {
                empresaCollectionNewEmpresaToAttach = em.getReference(empresaCollectionNewEmpresaToAttach.getClass(), empresaCollectionNewEmpresaToAttach.getEmpresaPK());
                attachedEmpresaCollectionNew.add(empresaCollectionNewEmpresaToAttach);
            }
            empresaCollectionNew = attachedEmpresaCollectionNew;
            evento.setEmpresaCollection(empresaCollectionNew);
            Collection<Categoria> attachedCategoriaCollectionNew = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionNewCategoriaToAttach : categoriaCollectionNew) {
                categoriaCollectionNewCategoriaToAttach = em.getReference(categoriaCollectionNewCategoriaToAttach.getClass(), categoriaCollectionNewCategoriaToAttach.getId());
                attachedCategoriaCollectionNew.add(categoriaCollectionNewCategoriaToAttach);
            }
            categoriaCollectionNew = attachedCategoriaCollectionNew;
            evento.setCategoriaCollection(categoriaCollectionNew);
            Collection<Calificacion> attachedCalificacionCollectionNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionCollectionNewCalificacionToAttach : calificacionCollectionNew) {
                calificacionCollectionNewCalificacionToAttach = em.getReference(calificacionCollectionNewCalificacionToAttach.getClass(), calificacionCollectionNewCalificacionToAttach.getCalificacionPK());
                attachedCalificacionCollectionNew.add(calificacionCollectionNewCalificacionToAttach);
            }
            calificacionCollectionNew = attachedCalificacionCollectionNew;
            evento.setCalificacionCollection(calificacionCollectionNew);
            evento = em.merge(evento);
            if (direccionOld != null && !direccionOld.equals(direccionNew)) {
                direccionOld.getEventoCollection().remove(evento);
                direccionOld = em.merge(direccionOld);
            }
            if (direccionNew != null && !direccionNew.equals(direccionOld)) {
                direccionNew.getEventoCollection().add(evento);
                direccionNew = em.merge(direccionNew);
            }
            for (Empresa empresaCollectionOldEmpresa : empresaCollectionOld) {
                if (!empresaCollectionNew.contains(empresaCollectionOldEmpresa)) {
                    empresaCollectionOldEmpresa.getEventoCollection().remove(evento);
                    empresaCollectionOldEmpresa = em.merge(empresaCollectionOldEmpresa);
                }
            }
            for (Empresa empresaCollectionNewEmpresa : empresaCollectionNew) {
                if (!empresaCollectionOld.contains(empresaCollectionNewEmpresa)) {
                    empresaCollectionNewEmpresa.getEventoCollection().add(evento);
                    empresaCollectionNewEmpresa = em.merge(empresaCollectionNewEmpresa);
                }
            }
            for (Categoria categoriaCollectionOldCategoria : categoriaCollectionOld) {
                if (!categoriaCollectionNew.contains(categoriaCollectionOldCategoria)) {
                    categoriaCollectionOldCategoria.getEventoCollection().remove(evento);
                    categoriaCollectionOldCategoria = em.merge(categoriaCollectionOldCategoria);
                }
            }
            for (Categoria categoriaCollectionNewCategoria : categoriaCollectionNew) {
                if (!categoriaCollectionOld.contains(categoriaCollectionNewCategoria)) {
                    categoriaCollectionNewCategoria.getEventoCollection().add(evento);
                    categoriaCollectionNewCategoria = em.merge(categoriaCollectionNewCategoria);
                }
            }
            for (Calificacion calificacionCollectionNewCalificacion : calificacionCollectionNew) {
                if (!calificacionCollectionOld.contains(calificacionCollectionNewCalificacion)) {
                    Evento oldEventoOfCalificacionCollectionNewCalificacion = calificacionCollectionNewCalificacion.getEvento();
                    calificacionCollectionNewCalificacion.setEvento(evento);
                    calificacionCollectionNewCalificacion = em.merge(calificacionCollectionNewCalificacion);
                    if (oldEventoOfCalificacionCollectionNewCalificacion != null && !oldEventoOfCalificacionCollectionNewCalificacion.equals(evento)) {
                        oldEventoOfCalificacionCollectionNewCalificacion.getCalificacionCollection().remove(calificacionCollectionNewCalificacion);
                        oldEventoOfCalificacionCollectionNewCalificacion = em.merge(oldEventoOfCalificacionCollectionNewCalificacion);
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
                EventoPK id = evento.getEventoPK();
                if (findEvento(id) == null) {
                    throw new NonexistentEntityException("The evento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EventoPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Evento evento;
            try {
                evento = em.getReference(Evento.class, id);
                evento.getEventoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The evento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Calificacion> calificacionCollectionOrphanCheck = evento.getCalificacionCollection();
            for (Calificacion calificacionCollectionOrphanCheckCalificacion : calificacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Evento (" + evento + ") cannot be destroyed since the Calificacion " + calificacionCollectionOrphanCheckCalificacion + " in its calificacionCollection field has a non-nullable evento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Lugar direccion = evento.getDireccion();
            if (direccion != null) {
                direccion.getEventoCollection().remove(evento);
                direccion = em.merge(direccion);
            }
            Collection<Empresa> empresaCollection = evento.getEmpresaCollection();
            for (Empresa empresaCollectionEmpresa : empresaCollection) {
                empresaCollectionEmpresa.getEventoCollection().remove(evento);
                empresaCollectionEmpresa = em.merge(empresaCollectionEmpresa);
            }
            Collection<Categoria> categoriaCollection = evento.getCategoriaCollection();
            for (Categoria categoriaCollectionCategoria : categoriaCollection) {
                categoriaCollectionCategoria.getEventoCollection().remove(evento);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            em.remove(evento);
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

    public List<Evento> findEventoEntities() {
        return findEventoEntities(true, -1, -1);
    }

    public List<Evento> findEventoEntities(int maxResults, int firstResult) {
        return findEventoEntities(false, maxResults, firstResult);
    }

    private List<Evento> findEventoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Evento.class));
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

    public Evento findEvento(EventoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Evento.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Evento> rt = cq.from(Evento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
