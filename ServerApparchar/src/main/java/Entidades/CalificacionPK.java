/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jeffe
 */
@Embeddable
public class CalificacionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usuariocliente")
    private String usuariocliente;
    @Basic(optional = false)
    @Column(name = "idevento")
    private int idevento;

    public CalificacionPK() {
    }

    public CalificacionPK(String usuariocliente, int idevento) {
        this.usuariocliente = usuariocliente;
        this.idevento = idevento;
    }

    public String getUsuariocliente() {
        return usuariocliente;
    }

    public void setUsuariocliente(String usuariocliente) {
        this.usuariocliente = usuariocliente;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuariocliente != null ? usuariocliente.hashCode() : 0);
        hash += (int) idevento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalificacionPK)) {
            return false;
        }
        CalificacionPK other = (CalificacionPK) object;
        if ((this.usuariocliente == null && other.usuariocliente != null) || (this.usuariocliente != null && !this.usuariocliente.equals(other.usuariocliente))) {
            return false;
        }
        if (this.idevento != other.idevento) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CalificacionPK[ usuariocliente=" + usuariocliente + ", idevento=" + idevento + " ]";
    }

}
