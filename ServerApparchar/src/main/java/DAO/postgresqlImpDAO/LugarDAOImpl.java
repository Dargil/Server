/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.LugarDAO;
import VO.LugarVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LugarDAOImpl implements LugarDAO {

    final String INSERT = "insert into lugar values (?,?,?)";
    final String UPDATE = "update lugar set direccion=?,nombre= ?,descripcion= ? where direccion= ?";
    final String DELETE = "delete from lugar where direccion= ?";
    final String LISTAR = "select * from lugar";
    final String CONSULTAR = "select * from lugar where direccion= ?";

    private Connection conn;

    public LugarDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(LugarVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, a.getDireccion());
            stmt.setString(2, a.getNombre());
            stmt.setString(3, a.getDescripcion());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(LugarVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, a.getDireccion());
            stmt.setString(2, a.getNombre());
            stmt.setString(3, a.getDescripcion());
            stmt.setString(4, a.getDireccion());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(LugarVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setString(1, a.getDireccion());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<LugarVO> listar() {
        ResultSet resp = null;
        try {
            Statement stmt = conn.createStatement();
            resp = stmt.executeQuery(LISTAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<LugarVO> lugares = new ArrayList<>();

        try {
            while (resp.next()) {
                lugares.add(new LugarVO(resp.getString(1), resp.getString(2), resp.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LugarDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lugares;
    }

    @Override
    public LugarVO consultar(String id) {
        PreparedStatement stmt;
        ResultSet resp = null;
        try {
            stmt = conn.prepareStatement(CONSULTAR);
            stmt.setString(1, id);
            resp = stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        LugarVO myLugar = null;

        try {
            while (resp.next()) {
                myLugar = new LugarVO(resp.getString(1), resp.getString(2), resp.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myLugar;
    }

}
