/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.EmpresaDAO;
import Entidades.Empresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmpresaManager implements EmpresaDAO {

    EntityManager em;

    public EmpresaManager(EntityManager e) {
        em = e;
    }

    @Override
    public boolean insertar(Empresa a) {
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
    public boolean modificar(Empresa a) {
        try {
            em.getTransaction().begin();
            Empresa aux = em.find(Empresa.class, a.getEmpresaPK());
            aux.setContrasenia(a.getContrasenia());
            aux.setCorreo(a.getCorreo());
            aux.setDescripcion(a.getDescripcion());
            aux.setEventoCollection(a.getEventoCollection());
            aux.setNombre(a.getNombre());
            aux.setTelefono(a.getTelefono());
            aux.setUbicacion(a.getUbicacion());
            aux.setEmpresaPK(a.getEmpresaPK());
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(Empresa a) {
        try {
            em.getTransaction().begin();
            Empresa aux = em.find(Empresa.class, a.getEmpresaPK());
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
    public List<Empresa> listar() {

        try {
            em.getTransaction().begin();
            List<Empresa> result = em.createQuery("SELECT c FROM Empresa c", Empresa.class).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Empresa consultar(String id) {
        try {
            em.getTransaction().begin();
            Empresa aux = em.find(Empresa.class, id);
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
            System.out.println(".-................................................");
            int numero = em.createQuery("SELECT c FROM Empresa c WHERE c.empresaPK.usuario LIKE :USER AND c.contrasenia LIKE :PASS ", Empresa.class).setParameter("USER", user).setParameter("PASS", contrasenia).getResultList().size();
            System.out.println("numero.............. " + numero);
            em.getTransaction().commit();
            em.close();
            return numero;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("pailasajdfbdsghfvbjfd");
            return 0;
        }
    }

    @Override
    public String getNit(String user) {
        try {
            em.getTransaction().begin();
            Empresa empresa = em.createQuery("SELECT e FROM Empresa e WHERE e.empresaPK.usuario = :usuario", Empresa.class).setParameter("usuario", user).getSingleResult();
            String nit = empresa.getEmpresaPK().getNitempresa();
            em.getTransaction().commit();
            em.close();
            return nit;
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }
}
