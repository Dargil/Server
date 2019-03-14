/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CategoriaDAO;
import VO.CategoriaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAOImpl implements CategoriaDAO {

    final String INSERT = "insert into categoria values (?,?,?)";
    final String DELETE = "delete from categoria where id= ?";
    final String UPDATE = "update categoria set id=?,nombre= ?,icono= ? where id= ?";
    final String LISTAR = "select * from categoria";
    final String CONSULTAR = "select * from categoria where id= ?";

    private Connection conn;

    public CategoriaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(CategoriaVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT);
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setBytes(3, a.getIcono());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modificar(CategoriaVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setBytes(3, a.getIcono());
            stmt.setInt(4, a.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(CategoriaVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, a.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<CategoriaVO> listar() {
        ResultSet resp = null;
        try {
            Statement stmt = conn.createStatement();
            resp = stmt.executeQuery(LISTAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<CategoriaVO> categorias = new ArrayList<>();

        try {
            while (resp.next()) {
                categorias.add(new CategoriaVO(resp.getInt(1), resp.getString(2), resp.getBytes(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    @Override
    public CategoriaVO consultar(String id) {
        PreparedStatement stmt;
        ResultSet resp = null;
        try {
            stmt = conn.prepareStatement(CONSULTAR);
            int c = Integer.parseInt(id);
            stmt.setInt(1, c);
            resp = stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CategoriaVO myCategoria = null;

        try {
            while (resp.next()) {
                myCategoria = new CategoriaVO(resp.getInt(1), resp.getString(2), resp.getBytes(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myCategoria;
    }

}
