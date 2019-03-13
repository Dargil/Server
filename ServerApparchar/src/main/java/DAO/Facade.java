/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author jeffe
 */
public interface Facade<T, K> {

    boolean insertar(T a);

    boolean modificar(T a);

    boolean eliminar(T a);

    ArrayList<T> listar();

    T consultar(K id);
}
