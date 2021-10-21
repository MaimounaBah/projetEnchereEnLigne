/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "prestation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestation.findAll", query = "SELECT p FROM Prestation p"),
    @NamedQuery(name = "Prestation.findByIdPRESTATION", query = "SELECT p FROM Prestation p WHERE p.idPRESTATION = :idPRESTATION"),
    @NamedQuery(name = "Prestation.findByLibellePres", query = "SELECT p FROM Prestation p WHERE p.libellePres = :libellePres"),
    @NamedQuery(name = "Prestation.findByPrixCat", query = "SELECT p FROM Prestation p WHERE p.prixCat = :prixCat"),
    @NamedQuery(name = "Prestation.findByPrixGold", query = "SELECT p FROM Prestation p WHERE p.prixGold = :prixGold")})
public class Prestation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPRESTATION")
    private Integer idPRESTATION;
    @Column(name = "LibellePres")
    private String libellePres;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PrixCat")
    private float prixCat;
    @Column(name = "PrixGold")
    private float prixGold;
    @ManyToMany(mappedBy = "prestationCollection")
    private Collection<Membre> membreCollection;

    public Prestation() {
    }

    public Prestation(Integer idPRESTATION) {
        this.idPRESTATION = idPRESTATION;
    }
    
    public Prestation(String libellePres, float prixCat) {
        this.idPRESTATION = idPRESTATION;
        this.prixCat = prixCat;
    }

    public Integer getIdPRESTATION() {
        return idPRESTATION;
    }

    public void setIdPRESTATION(Integer idPRESTATION) {
        this.idPRESTATION = idPRESTATION;
    }

    public String getLibellePres() {
        return libellePres;
    }

    public void setLibellePres(String libellePres) {
        this.libellePres = libellePres;
    }

    public float getPrixCat() {
        return prixCat;
    }

    public void setPrixCat(float prixCat) {
        this.prixCat = prixCat;
    }

    public float getPrixGold() {
        return prixGold;
    }

    public void setPrixGold(float prixGold) {
        this.prixGold = prixGold;
    }

    @XmlTransient
    public Collection<Membre> getMembreCollection() {
        return membreCollection;
    }

    public void setMembreCollection(Collection<Membre> membreCollection) {
        this.membreCollection = membreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPRESTATION != null ? idPRESTATION.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestation)) {
            return false;
        }
        Prestation other = (Prestation) object;
        if ((this.idPRESTATION == null && other.idPRESTATION != null) || (this.idPRESTATION != null && !this.idPRESTATION.equals(other.idPRESTATION))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prestation[ idPRESTATION=" + idPRESTATION + " ]";
    }
    
}
