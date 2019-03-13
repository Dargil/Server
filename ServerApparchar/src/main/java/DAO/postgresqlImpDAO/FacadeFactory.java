/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CalificacionDAO;
import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.EmpresaDAO;
import DAO.EventoCategoriaDAO;
import DAO.EventoDAO;
import DAO.EventoEmpresaDAO;
import DAO.LugarDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jefferson DArio Marin Giraldo
 */
public class FacadeFactory {

    Connection conn = null;
    Statement stmt = null;
    CalificacionDAO calificacion = null;
    CategoriaDAO categoria = null;
    ClienteDAO cliente = null;
    EmpresaDAO empresa = null;
    EventoCategoriaDAO eventocategoria = null;
    EventoDAO evento = null;
    EventoEmpresaDAO eventoEmpresa = null;
    LugarDAO lugar = null;

    public Connection Conexion() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String Host = "ec2-54-243-228-140.compute-1.amazonaws.com";
        String Database = "d2ibfiosdpju21";
        String User = "hkwgophfphyvpz";
        String Port = "5432";
        String Password = "0228772b951feb2c06272b52998b8cc3cf16d98c5d506c25f6e9ea40430ed6ae";
        String URL = "jdbc:postgresql://" + Host + ":" + Port + "/" + Database + "?sslmode=require";
        try {
            conn = DriverManager.getConnection(URL, User, Password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void CerrarConexion() throws SQLException {
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
            }
        }
    }

    public CalificacionDAO getCalificacionDAO() {
        if (calificacion == null) {
            calificacion = new CalificacionDAOImpl(conn);
        }
        return calificacion;
    }

    public CategoriaDAO getCategoriaDAO() {
        if (categoria == null) {
            categoria = new CategoriaDAOImpl(conn);
        }
        return categoria;
    }

    public ClienteDAO getClienteDAO() {
        if (cliente == null) {
            cliente = new ClienteDAOImpl(conn);
        }
        return cliente;
    }

    public EmpresaDAO getEmpresaDAO() {
        if (empresa == null) {
            empresa = new EmpresaDAOImpl(conn);
        }
        return empresa;
    }

    public EventoCategoriaDAO getEventoCategoriaDAO() {
        if (eventocategoria == null) {
            eventocategoria = new EventoCategoriaDAOImpl(conn);
        }
        return eventocategoria;
    }

    public EventoDAO getEventoDAO() {
        if (evento == null) {
            evento = new EventoDAOImpl(conn);
        }
        return evento;
    }

    public EventoEmpresaDAO getEventoEmpresaDAO() {
        if (eventoEmpresa == null) {
            eventoEmpresa = new EventoEmpresaDAOImpl(conn);
        }
        return eventoEmpresa;
    }

    public LugarDAO getLugarDAO() {
        if (lugar == null) {
            lugar = new LugarDAOImpl(conn);
        }
        return lugar;
    }
}
