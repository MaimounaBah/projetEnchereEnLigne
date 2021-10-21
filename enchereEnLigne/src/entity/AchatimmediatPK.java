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
public class AchatimmediatPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idMembre")
    private int idMembre;
    @Basic(optional = false)
    @Column(name = "idArticle")
    private int idArticle;

    public AchatimmediatPK() {
    }

    public AchatimmediatPK(int idMembre, int idArticle) {
        this.idMembre = idMembre;
        this.idArticle = idArticle;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMembre;
        hash += (int) idArticle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AchatimmediatPK)) {
            return false;
        }
        AchatimmediatPK other = (AchatimmediatPK) object;
        if (this.idMembre != other.idMembre) {
            return false;
        }
        if (this.idArticle != other.idArticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AchatimmediatPK[ idMembre=" + idMembre + ", idArticle=" + idArticle + " ]";
    }
    
}
