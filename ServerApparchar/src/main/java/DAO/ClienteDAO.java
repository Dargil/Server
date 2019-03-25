/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Cliente;

/**
 *
 * @author jeffe
 */
public interface ClienteDAO extends DAOWork<Cliente, String> {

    int login(String user, String contrasenia);

    String getUsuario(String user);
}
