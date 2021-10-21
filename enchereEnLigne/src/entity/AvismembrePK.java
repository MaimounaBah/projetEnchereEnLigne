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
public class AvismembrePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idmembreEmis")
    private int idmembreEmis;
    @Basic(optional = false)
    @Column(name = "idmembreRecu")
    private int idmembreRecu;

    public AvismembrePK() {
    }

    public AvismembrePK(int idmembreEmis, int idmembreRecu) {
        this.idmembreEmis = idmembreEmis;
        this.idmembreRecu = idmembreRecu;
    }

    public int getIdmembreEmis() {
        return idmembreEmis;
    }

    public void setIdmembreEmis(int idmembreEmis) {
        this.idmembreEmis = idmembreEmis;
    }

    public int getIdmembreRecu() {
        return idmembreRecu;
    }

    public void setIdmembreRecu(int idmembreRecu) {
        this.idmembreRecu = idmembreRecu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idmembreEmis;
        hash += (int) idmembreRecu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvismembrePK)) {
            return false;
        }
        AvismembrePK other = (AvismembrePK) object;
        if (this.idmembreEmis != other.idmembreEmis) {
            return false;
        }
        if (this.idmembreRecu != other.idmembreRecu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AvismembrePK[ idmembreEmis=" + idmembreEmis + ", idmembreRecu=" + idmembreRecu + " ]";
    }
    
}
