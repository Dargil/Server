/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CalificacionDAO;
import VO.CalificacionVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalificacionDAOImpl implements CalificacionDAO {

    final String INSERT = "insert into calificacion values (?,?,?,?,?,?,?,?,?,?)";
    final String DELETE = "delete from calificacion where iduser= ? and idevento= ?";
    final String UPDATE = "update calificacion set porcentaje=?,comentario= ?,multimedia= ?,hora= ?,iduser= ?,idevento= ?,fecha= ?,horai= ?,horaf= ?,fechae= ? where iduser= ? and idevento= ?";
    final String LISTAR = "select * from calificacion";
    final String CONSULTAR = "select * from calificacion where iduser= ? and idevento= ?";

    private Connection conn;

    public CalificacionDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(CalificacionVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT);
            stmt.setDouble(1, a.getPorcentaje());
            stmt.setString(2, a.getComentario());
            stmt.setBytes(3, a.getMultimedia());
            stmt.setString(4, a.getHora());
            stmt.setInt(5, a.getIdUser());
            stmt.setInt(6, a.getIdEvento());
            stmt.setString(7, a.getFecha());
            stmt.setString(8, a.getHoraI());
            stmt.setString(9, a.getHoraF());
            stmt.setString(10, a.getFechaE());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modificar(CalificacionVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setDouble(1, a.getPorcentaje());
            stmt.setString(2, a.getComentario());
            stmt.setBytes(3, a.getMultimedia());
            stmt.setString(4, a.getHora());
            stmt.setInt(5, a.getIdUser());
            stmt.setInt(6, a.getIdEvento());
            stmt.setString(7, a.getFecha());
            stmt.setString(8, a.getHoraI());
            stmt.setString(9, a.getHoraF());
            stmt.setString(10, a.getFechaE());
            stmt.setInt(11, a.getIdUser());
            stmt.setInt(12, a.getIdEvento());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(CalificacionVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, a.getIdUser());
            stmt.setInt(2, a.getIdEvento());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<CalificacionVO> listar() {
        ResultSet resp = null;
        try {
            Statement stmt = conn.createStatement();
            resp = stmt.executeQuery(LISTAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<CalificacionVO> calificaciones = new ArrayList<>();

        try {
            while (resp.next()) {
                calificaciones.add(new CalificacionVO(resp.getDouble(1), resp.getString(2), resp.getBytes(3), resp.getString(4), resp.getInt(5), resp.getInt(6), resp.getString(7), resp.getString(8), resp.getString(9), resp.getString(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calificaciones;
    }

    @Override
    public CalificacionVO consultar(String id) {
        ResultSet resp = null;
        try {
            Statement stmt = conn.createStatement();
            resp = stmt.executeQuery(LISTAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //CalificacionVO calificaciones = new CalificacionVO();
        /*
                  while (resp.next()) {
            calificaciones.add(new Calificacion(""));
        }
         */
        return null;
    }

    @Override
    public CalificacionVO consultar(String idUser, String idEvento) {
        PreparedStatement stmt;
        ResultSet resp = null;
        try {
            stmt = conn.prepareStatement(CONSULTAR);
            int c = Integer.parseInt(idUser);
            int h = Integer.parseInt(idEvento);
            stmt.setInt(1, c);
            stmt.setInt(2, h);
            resp = stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        CalificacionVO myCalificacion = null;

        try {
            while (resp.next()) {
                myCalificacion = new CalificacionVO(resp.getDouble(1), resp.getString(2), resp.getBytes(3), resp.getString(4), resp.getInt(5), resp.getInt(6), resp.getString(7), resp.getString(8), resp.getString(9), resp.getString(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myCalificacion;
    }

}
