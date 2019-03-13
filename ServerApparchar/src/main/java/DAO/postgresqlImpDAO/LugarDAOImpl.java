/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.LugarDAO;
import VO.LugarVO;
import java.sql.Connection;
import java.util.ArrayList;

public class LugarDAOImpl implements LugarDAO {

    final String INSERT = "";
    final String UPDATE = "";
    final String DELETE = "";
    final String LISTAR = "";
    final String CONSULTAR = "";

    private Connection conn;

    public LugarDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(LugarVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(LugarVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(LugarVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<LugarVO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LugarVO consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
