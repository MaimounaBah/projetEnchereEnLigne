/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "avismembre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avismembre.findAll", query = "SELECT a FROM Avismembre a"),
    @NamedQuery(name = "Avismembre.findByIdmembreEmis", query = "SELECT a FROM Avismembre a WHERE a.avismembrePK.idmembreEmis = :idmembreEmis"),
    @NamedQuery(name = "Avismembre.findByIdmembreRecu", query = "SELECT a FROM Avismembre a WHERE a.avismembrePK.idmembreRecu = :idmembreRecu"),
    @NamedQuery(name = "Avismembre.findByAvis", query = "SELECT a FROM Avismembre a WHERE a.avis = :avis")})
public class Avismembre implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AvismembrePK avismembrePK;
    @Column(name = "avis")
    private String avis;
    @JoinColumn(name = "idmembreEmis", referencedColumnName = "idmembre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;
    @JoinColumn(name = "idmembreRecu", referencedColumnName = "idmembre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre1;

    public Avismembre() {
    }

    public Avismembre(AvismembrePK avismembrePK) {
        this.avismembrePK = avismembrePK;
    }

    public Avismembre(int idmembreEmis, int idmembreRecu) {
        this.avismembrePK = new AvismembrePK(idmembreEmis, idmembreRecu);
    }

    public AvismembrePK getAvismembrePK() {
        return avismembrePK;
    }

    public void setAvismembrePK(AvismembrePK avismembrePK) {
        this.avismembrePK = avismembrePK;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Membre getMembre1() {
        return membre1;
    }

    public void setMembre1(Membre membre1) {
        this.membre1 = membre1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (avismembrePK != null ? avismembrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avismembre)) {
            return false;
        }
        Avismembre other = (Avismembre) object;
        if ((this.avismembrePK == null && other.avismembrePK != null) || (this.avismembrePK != null && !this.avismembrePK.equals(other.avismembrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Avismembre[ avismembrePK=" + avismembrePK + " ]";
    }
    
}
