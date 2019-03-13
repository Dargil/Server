/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.EventoCategoriaDAO;
import java.sql.Connection;
import java.util.ArrayList;

public class EventoCategoriaDAOImpl implements EventoCategoriaDAO {

    final String INSERT = "";
    final String UPDATE = "";
    final String DELETE = "";
    final String LISTAR = "";
    final String CONSULTAR = "";

    private Connection conn;

    public EventoCategoriaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(EventoCategoriaDAO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(EventoCategoriaDAO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(EventoCategoriaDAO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EventoCategoriaDAO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoCategoriaDAO consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
