/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.CalificacionDAO;
import VO.CalificacionVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(INSERT);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean modificar(CalificacionVO a) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(UPDATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(CalificacionVO a) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(UPDATE);
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
        /*
                  while (resp.next()) {
            calificaciones.add(new Calificacion(""));
        }
         */
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
        CalificacionVO calificaciones = new CalificacionVO();
        /*
                  while (resp.next()) {
            calificaciones.add(new Calificacion(""));
        }
         */
        return calificaciones;
    }

}
