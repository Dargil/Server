/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.EmpresaDAO;
import VO.ClienteVO;
import VO.EmpresaVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpresaDAOImpl implements EmpresaDAO {

    final String INSERT = "insert into empresa values (?,?,?,?,?,?,?,?)";
    final String DELETE = "delete from cliente where id= ?";
    final String UPDATE = "update cliente set id=?,nombre= ?,edad= ?,correo= ?,telefono= ?,usuario= ?,contrasenia= ? where nitempresa= ?";
    final String LISTAR = "select * from cliente";
    final String CONSULTAR = "select * from cliente where id= ?";
    final String LOGIN = "select count(*) from empresa where usuario=? and contrasenia=?";
    final String NitEmpresa = "select nitempresa from empresa where usuario=?";
    private Connection conn;

    public EmpresaDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(EmpresaVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT);

            stmt.setString(1, a.getNitEmpresa());
            stmt.setString(2, a.getNombre());
            stmt.setString(3, a.getUbicacion());
            stmt.setString(4, a.getTelefono());
            stmt.setString(5, a.getCorreo());
            stmt.setString(6, a.getDescripcion());
            stmt.setString(7, a.getUsuario());
            stmt.setString(8, a.getContrasenia());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modificar(EmpresaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(EmpresaVO a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EmpresaVO> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpresaVO consultar(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int login(String user, String contrasenia) {
        PreparedStatement stmt;
        ResultSet resp = null;
        try {
            stmt = conn.prepareStatement(LOGIN);
            stmt.setString(1, user);
            stmt.setString(2, contrasenia);
            resp = stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        int dato = -1;

        try {
            while (resp.next()) {
                dato = resp.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dato;
    }

    @Override
    public String getNit(String user) {
        PreparedStatement stmt;
        ResultSet resp = null;
        try {
            stmt = conn.prepareStatement(NitEmpresa);
            stmt.setString(1, user);
            resp = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dato = "";

        try {
            while (resp.next()) {
                dato = resp.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dato;
    }

}
