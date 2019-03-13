/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CategoriaDAO;
import VO.CategoriaVO;
import java.sql.Connection;
import java.util.ArrayList;

public class CategoriaDAOImpl implements CategoriaDAO {

    final String INSERT = "";
    final String UPDATE = "";
    final String DELETE = "";
    final String LISTAR = "";
    final String CONSULTAR = "";

    private Connection conn;

    public CategoriaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(CategoriaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(CategoriaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(CategoriaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CategoriaVO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CategoriaVO consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
