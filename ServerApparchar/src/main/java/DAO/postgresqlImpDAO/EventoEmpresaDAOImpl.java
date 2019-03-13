/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.EventoEmpresaDAO;
import VO.EventoEmpresaVO;
import java.sql.Connection;
import java.util.ArrayList;

public class EventoEmpresaDAOImpl implements EventoEmpresaDAO {

    final String INSERT = "";
    final String UPDATE = "";
    final String DELETE = "";
    final String LISTAR = "";
    final String CONSULTAR = "";

    private Connection conn;

    public EventoEmpresaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(EventoEmpresaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(EventoEmpresaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(EventoEmpresaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EventoEmpresaVO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventoEmpresaVO consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
