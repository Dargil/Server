/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.ClienteDAO;
import Entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClienteManager implements ClienteDAO {

    EntityManager em;

    public ClienteManager(EntityManager e) {
        em = e;
    }

    @Override
    public boolean insertar(Cliente a) {
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
    public boolean modificar(Cliente a) {
        try {
            em.getTransaction().begin();
            Cliente aux = em.find(Cliente.class, a.getUsuario());
            aux.setContrasenia(a.getContrasenia());
            aux.setCalificacionCollection(a.getCalificacionCollection());
            aux.setEdad(a.getEdad());
            aux.setNombre(a.getNombre());
            aux.setTelefono(a.getTelefono());
            aux.setUsuario(a.getUsuario());
            aux.setCorreo(a.getCorreo());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Cliente a) {
        try {
            em.getTransaction().begin();
            Cliente aux = em.find(Cliente.class, a.getUsuario());
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
    public List<Cliente> listar() {

        try {
            em.getTransaction().begin();
            List<Cliente> result = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente consultar(String id) {
        try {
            em.getTransaction().begin();
            int prk = Integer.parseInt(id);
            Cliente aux = em.find(Cliente.class, prk);
            em.getTransaction().commit();
            em.close();
            return aux;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int login(String user, String contrasenia) {
        try {
            em.getTransaction().begin();
            int numero = em.createQuery("SELECT c FROM Cliente c WHERE c.usuario LIKE :USER AND c.contrasenia LIKE :PASS ", Cliente.class).setParameter("USER", user).setParameter("PASS", contrasenia).getResultList().size();
            em.getTransaction().commit();
            em.close();
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getUsuario(String user) {
        return "";
    }

}
