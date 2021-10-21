/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 33760
 */
@Embeddable
public class EncherirPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idmembre")
    private int idmembre;
    @Basic(optional = false)
    @Column(name = "idarticle")
    private int idarticle;

    public EncherirPK() {
    }

    public EncherirPK(int idmembre, int idarticle) {
        this.idmembre = idmembre;
        this.idarticle = idarticle;
    }

    public int getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(int idmembre) {
        this.idmembre = idmembre;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmembre;
        hash += (int) idarticle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncherirPK)) {
            return false;
        }
        EncherirPK other = (EncherirPK) object;
        if (this.idmembre != other.idmembre) {
            return false;
        }
        if (this.idarticle != other.idarticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EncherirPK[ idmembre=" + idmembre + ", idarticle=" + idarticle + " ]";
    }
    
}
