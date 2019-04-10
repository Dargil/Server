/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jeffe
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByNitempresa", query = "SELECT e FROM Empresa e WHERE e.empresaPK.nitempresa = :nitempresa")
    , @NamedQuery(name = "Empresa.findByNombre", query = "SELECT e FROM Empresa e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Empresa.findByUbicacion", query = "SELECT e FROM Empresa e WHERE e.ubicacion = :ubicacion")
    , @NamedQuery(name = "Empresa.findByTelefono", query = "SELECT e FROM Empresa e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empresa.findByCorreo", query = "SELECT e FROM Empresa e WHERE e.correo = :correo")
    , @NamedQuery(name = "Empresa.findByDescripcion", query = "SELECT e FROM Empresa e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Empresa.findByUsuario", query = "SELECT e FROM Empresa e WHERE e.empresaPK.usuario = :usuario")
    , @NamedQuery(name = "Empresa.findByContrasenia", query = "SELECT e FROM Empresa e WHERE e.contrasenia = :contrasenia")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmpresaPK empresaPK;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Size(max = 255)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "correo")
    private String correo;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "contrasenia")
    private String contrasenia;
    @ManyToMany(mappedBy = "empresaCollection")
    private Collection<Evento> eventoCollection;

    public Empresa() {
    }

    public Empresa(EmpresaPK empresaPK) {
        this.empresaPK = empresaPK;
    }

    public Empresa(String nitempresa, String usuario) {
        this.empresaPK = new EmpresaPK(nitempresa, usuario);
    }

    public EmpresaPK getEmpresaPK() {
        return empresaPK;
    }

    public void setEmpresaPK(EmpresaPK empresaPK) {
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empresaPK != null ? empresaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.empresaPK == null && other.empresaPK != null) || (this.empresaPK != null && !this.empresaPK.equals(other.empresaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Empresa[ empresaPK=" + empresaPK + " ]";
    }

}
