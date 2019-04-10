/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.util.Collection;

/**
 *
 * @author jeffe
 */
public class EmpresaM {

    private EmpresaPKM empresaPK;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private String correo;
    private String descripcion;
    private String usuario;
    private String contrasenia;
    private Collection<EventoM> eventoCollection;

    public EmpresaM(EmpresaPKM empresaPK, String nombre, String ubicacion, String telefono, String correo, String descripcion, String usuario, String contrasenia, Collection<EventoM> eventoCollection) {
        this.empresaPK = empresaPK;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.correo = correo;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.eventoCollection = eventoCollection;
    }

    public EmpresaM() {
    }

    public EmpresaPKM getEmpresaPK() {
        return empresaPK;
    }

    public void setEmpresaPK(EmpresaPKM empresaPK) {
        this.empresaPK = empresaPK;
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

    public Collection<EventoM> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<EventoM> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public String toString() {
        return "EmpresaM{" + "empresaPK=" + empresaPK + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", telefono=" + telefono + ", correo=" + correo + ", descripcion=" + descripcion + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", eventoCollection=" + eventoCollection + '}';
    }

}
