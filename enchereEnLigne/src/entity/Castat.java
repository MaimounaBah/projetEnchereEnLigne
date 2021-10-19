/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "castat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Castat.findAll", query = "SELECT c FROM Castat c"),
    @NamedQuery(name = "Castat.findByIdcastat", query = "SELECT c FROM Castat c WHERE c.idcastat = :idcastat"),
    @NamedQuery(name = "Castat.findBySemaine", query = "SELECT c FROM Castat c WHERE c.semaine = :semaine"),
    @NamedQuery(name = "Castat.findByAnnee", query = "SELECT c FROM Castat c WHERE c.annee = :annee"),
    @NamedQuery(name = "Castat.findByCa", query = "SELECT c FROM Castat c WHERE c.ca = :ca")})
public class Castat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcastat")
    private Integer idcastat;
    @Column(name = "Semaine")
    private Integer semaine;
    @Column(name = "Annee")
    private Integer annee;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CA")
    private BigDecimal ca;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne
    private Categorie idCategorie;

    public Castat() {
    }

    public Castat(Integer idcastat) {
        this.idcastat = idcastat;
    }

    public Integer getIdcastat() {
        return idcastat;
    }

    public void setIdcastat(Integer idcastat) {
        this.idcastat = idcastat;
    }

    public Integer getSemaine() {
        return semaine;
    }

    public void setSemaine(Integer semaine) {
        this.semaine = semaine;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public BigDecimal getCa() {
        return ca;
    }

    public void setCa(BigDecimal ca) {
        this.ca = ca;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcastat != null ? idcastat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Castat)) {
            return false;
        }
        Castat other = (Castat) object;
        if ((this.idcastat == null && other.idcastat != null) || (this.idcastat != null && !this.idcastat.equals(other.idcastat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Castat[ idcastat=" + idcastat + " ]";
    }
    
}
