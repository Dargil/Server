/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.CalificacionVO;

/**
 *
 * @author jeffe
 */
public interface CalificacionDAO extends Facade<CalificacionVO, String> {
 CalificacionVO consultar(String idUser,String idEvento);
}
