/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.EventoDAO;
import VO.EventoVO;
import java.sql.Connection;
import java.util.ArrayList;

public class EventoDAOImpl implements EventoDAO {

    final String INSERT = "";
    final String UPDATE = "";
    final String DELETE = "";
    final String LISTAR = "";
    final String CONSULTAR = "";

    private Connection conn;

    public EventoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(EventoVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(EventoVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(EventoVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EventoVO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoVO consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
