/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.postgresqlImpDAO;

import DAO.ClienteDAO;
import VO.ClienteVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAOImpl implements ClienteDAO {

    final String INSERT = "insert into cliente values (?,?,?,?,?,?,?)";
    final String DELETE = "delete from cliente where id= ?";
    final String UPDATE = "update cliente set id=?,nombre= ?,edad= ?,correo= ?,telefono= ?,usuario= ?,contrasenia= ? where id= ?";
    final String LISTAR = "select * from cliente";
    final String CONSULTAR = "select * from cliente where id= ?";

    private Connection conn;

    public ClienteDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertar(ClienteVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT);

            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setInt(3, a.getEdad());
            stmt.setString(4, a.getCorreo());
            stmt.setString(5, a.getTelefono());
            stmt.setString(6, a.getUsuario());
            stmt.setString(7, a.getContrasenia());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean modificar(ClienteVO a) {

        try {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, a.getId());
            stmt.setString(2, a.getNombre());
            stmt.setInt(3, a.getEdad());
            stmt.setString(4, a.getCorreo());
            stmt.setString(5, a.getTelefono());
            stmt.setString(6, a.getUsuario());
            stmt.setString(7, a.getContrasenia());
            stmt.setInt(8, a.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminar(ClienteVO a) {
        try {
            PreparedStatement stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, a.getId());
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<ClienteVO> listar() {
        ResultSet resp = null;
        try {
            Statement stmt = conn.createStatement();
            resp = stmt.executeQuery(LISTAR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<ClienteVO> clientes = new ArrayList<>();

        try {
            while (resp.next()) {
                clientes.add(new ClienteVO(resp.getInt(1), resp.getString(2), resp.getInt(3), resp.getString(4), resp.getString(5), resp.getString(6), resp.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    @Override
    public ClienteVO consultar(String id) {
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
        ClienteVO myCliente = null;

        try {
            while (resp.next()) {
                myCliente = new ClienteVO(resp.getInt(1), resp.getString(2), resp.getInt(3), resp.getString(4), resp.getString(5), resp.getString(6), resp.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myCliente;
    }

}
