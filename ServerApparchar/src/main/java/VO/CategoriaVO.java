/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author jeffe
 */
public class CategoriaVO {

    public int id;
    public String nombre;
    public byte[] icono;

    public CategoriaVO(int id, String nombre, byte[] icono) {
        this.id = id;
        this.nombre = nombre;
        this.icono = icono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return "CategoriaVO{" + "id=" + id + ", nombre=" + nombre + ", icono=" + icono + '}';
    }

}
