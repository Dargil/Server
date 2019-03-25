/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import Entidades.Categoria;
import Entidades.Empresa;
import Entidades.Evento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jeffe
 */
public class Facade {

    private CalificacionManager calificacion = null;
    private CategoriaManager categoria = null;
    private ClienteManager cliente = null;
    private EmpresaManager empresa = null;
    private EventoManager evento = null;
    private LugarManager lugar = null;
    private EntityManager em;

    public Facade(EntityManager e) {
        em = e;
    }

    public <T> boolean insertarGenerico(T a) {
        try {
            System.out.println("ingreso");
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

    public <T> List<T> listarGenerico(T objet) {
        try {
            String clas = objet.getClass().getSimpleName();

            System.out.println("dvd " + clas);

            String jpql = "SELECT c FROM " + clas + " c";
            em.getTransaction().begin();
            List<T> result = (List<T>) em.createQuery(jpql, objet.getClass()).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> int loginGenerico(String user, String contrasenia, T objet) {
        try {
            String clas = objet.getClass().getName().toString();
            System.out.println("clase " + clas);
            String jpql = "SELECT c FROM " + clas + " c WHERE c.usuario LIKE :USER AND c.contrasenia LIKE :PASS ";

            em.getTransaction().begin();
            int numero = em.createQuery(jpql, objet.getClass()).setParameter("USER", user).setParameter("PASS", contrasenia).getResultList().size();
            em.getTransaction().commit();
            em.close();
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
    
        public <T> int loginEmpresa(String user, String contrasenia) {
        try {
            em.getTransaction().begin();
            System.out.println(".-................................................");
            int numero = em.createQuery("SELECT c FROM Empresa c WHERE c.empresaPK.usuario LIKE :USER AND c.contrasenia LIKE :PASS ", Empresa.class).setParameter("USER", user).setParameter("PASS", contrasenia).getResultList().size();
            System.out.println("numero.............. " + numero);
            // em.getTransaction().commit();
            em.close();
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("pailasajdfbdsghfvbjfd");
            return 0;

        }
    }
     */
    public int loginEmpresa(String user, String contrasenia) {
        try {
            em.getTransaction().begin();
            System.out.println(".-................................................");
            int numero = em.createQuery("SELECT c FROM Empresa c WHERE c.empresaPK.usuario LIKE :USER AND c.contrasenia LIKE :PASS ", Empresa.class).setParameter("USER", user).setParameter("PASS", contrasenia).getResultList().size();
            System.out.println("numero.............. " + numero);
            // em.getTransaction().commit();
            em.close();
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("pailasajdfbdsghfvbjfd");
            return 0;

        }
    }

    public String getNitCliente(String user) {
        cliente = new ClienteManager(em);
        return cliente.getUsuario(user);
    }

    public boolean insertaEvento(Evento e) {
        evento = new EventoManager(em);
        return evento.insertarEvento(e);
    }

    public boolean modificarEvento(Categoria c) {
        categoria = new CategoriaManager(em);
        return categoria.modificarEvento(c);
    }

    public String getNitEmpresa(String user) {
        empresa = new EmpresaManager(em);
        return empresa.getNit(user);
    }

    public CalificacionManager getCalificacionDAO() {
        if (calificacion == null) {
            calificacion = new CalificacionManager(em);
        }
        return calificacion;
    }

    public CategoriaManager getCategoriaDAO() {
        if (categoria == null) {
            categoria = new CategoriaManager(em);
        }
        return categoria;
    }

    public ClienteManager getClienteDAO() {
        if (cliente == null) {
            cliente = new ClienteManager(em);
        }
        return cliente;
    }

    public EmpresaManager getEmpresaDAO() {
        if (empresa == null) {
            empresa = new EmpresaManager(em);
        }
        return empresa;
    }

    public EventoManager getEventoDAO() {
        if (evento == null) {
            evento = new EventoManager(em);
        }
        return evento;
    }

    public LugarManager getLugarDAO() {
        if (lugar == null) {
            lugar = new LugarManager(em);
        }
        return lugar;
    }
}
