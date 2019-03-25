/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidades.Empresa;

/**
 *
 * @author jeffe
 */
public interface EmpresaDAO extends DAOWork<Empresa, String> {

    int login(String user, String contrasenia);

    String getNit(String user);
}
