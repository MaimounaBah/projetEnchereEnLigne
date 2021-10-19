/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
@Table(name = "visstat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visstat.findAll", query = "SELECT v FROM Visstat v"),
    @NamedQuery(name = "Visstat.findByIdvisstat", query = "SELECT v FROM Visstat v WHERE v.idvisstat = :idvisstat"),
    @NamedQuery(name = "Visstat.findBySemaine", query = "SELECT v FROM Visstat v WHERE v.semaine = :semaine"),
    @NamedQuery(name = "Visstat.findByAnnee", query = "SELECT v FROM Visstat v WHERE v.annee = :annee"),
    @NamedQuery(name = "Visstat.findByNombreDeVisite", query = "SELECT v FROM Visstat v WHERE v.nombreDeVisite = :nombreDeVisite")})
public class Visstat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvisstat")
    private Integer idvisstat;
    @Column(name = "Semaine")
    private Integer semaine;
    @Column(name = "Annee")
    private Integer annee;
    @Column(name = "nombreDeVisite")
    private Integer nombreDeVisite;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne
    private Categorie idCategorie;

    public Visstat() {
    }

    public Visstat(Integer idvisstat) {
        this.idvisstat = idvisstat;
    }

    public Integer getIdvisstat() {
        return idvisstat;
    }

    public void setIdvisstat(Integer idvisstat) {
        this.idvisstat = idvisstat;
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

    public Integer getNombreDeVisite() {
        return nombreDeVisite;
    }

    public void setNombreDeVisite(Integer nombreDeVisite) {
        this.nombreDeVisite = nombreDeVisite;
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
        hash += (idvisstat != null ? idvisstat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visstat)) {
            return false;
        }
        Visstat other = (Visstat) object;
        if ((this.idvisstat == null && other.idvisstat != null) || (this.idvisstat != null && !this.idvisstat.equals(other.idvisstat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Visstat[ idvisstat=" + idvisstat + " ]";
    }
    
}
