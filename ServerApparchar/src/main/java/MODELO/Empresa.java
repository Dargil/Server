/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.util.ArrayList;

/**
 *
 * @author jeffe
 */
public class Empresa {

    public int nitEmpresa;
    public String nombre;
    public String ubicacion;
    public String telefono;
    public String correo;
    public String descripcion;
    public String usuario;
    public String contrasenia;
    public ArrayList<Evento> EventoEmpresa;

    public int getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(int nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ArrayList<Evento> getEventoEmpresa() {
        return EventoEmpresa;
    }

    public void setEventoEmpresa(ArrayList<Evento> EventoEmpresa) {
        this.EventoEmpresa = EventoEmpresa;
    }

    @Override
    public String toString() {
        return "Empresa{" + "nitEmpresa=" + nitEmpresa + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono + ", correo=" + correo + ", descripcion=" + descripcion + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", EventoEmpresa=" + EventoEmpresa + '}';
    }
}
