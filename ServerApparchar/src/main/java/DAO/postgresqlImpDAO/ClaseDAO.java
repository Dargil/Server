/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jeffe
 */
public class ClaseDAO {

    Connection conn = null;
    Statement stmt = null;

    public Connection Conexion() {
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

    public int validarUsuario(String sql) {
        int tipo = 0;
        try {
            if (conn == null) {
                conn = Conexion();
            }
            ResultSet resp = stmt.executeQuery(sql);
            while (resp.next()) {
                tipo = resp.getInt(1);
            }
            return tipo;
        } catch (Exception e) {
            e.printStackTrace();
            return tipo;
        }
    }

    public void insertar(String sql) {
        try {
            if (conn == null) {
                conn = Conexion();
            }
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificar(String sql) {
        try {
            if (conn == null) {
                conn = Conexion();
            }
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String sql) {
        try {
            if (conn == null) {
                conn = Conexion();
            }
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet listar(String sql) {
        ResultSet resp = null;
        try {
            if (conn == null) {
                conn = Conexion();
            }

            resp = stmt.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public ResultSet consultar(String sql) {
        try {
            if (conn == null) {
                conn = Conexion();
            }
            ResultSet resp = stmt.executeQuery(sql);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
