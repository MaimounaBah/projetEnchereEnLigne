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

/**
 *
 * @author 33760
 */
@Entity
@Table(name = "nostat")
@NamedQueries({
    @NamedQuery(name = "Nostat.findAll", query = "SELECT n FROM Nostat n")})
public class Nostat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnostat")
    private Integer idnostat;
    @Column(name = "Semaine")
    private Integer semaine;
    @Column(name = "Annee")
    private Integer annee;
    @Column(name = "NombreObjet")
    private Integer nombreObjet;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne
    private Categorie idCategorie;

    public Nostat() {
    }

    public Nostat(Integer idnostat) {
        this.idnostat = idnostat;
    }

    public Integer getIdnostat() {
        return idnostat;
    }

    public void setIdnostat(Integer idnostat) {
        this.idnostat = idnostat;
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

    public Integer getNombreObjet() {
        return nombreObjet;
    }

    public void setNombreObjet(Integer nombreObjet) {
        this.nombreObjet = nombreObjet;
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
        hash += (idnostat != null ? idnostat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nostat)) {
            return false;
        }
        Nostat other = (Nostat) object;
        if ((this.idnostat == null && other.idnostat != null) || (this.idnostat != null && !this.idnostat.equals(other.idnostat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Nostat[ idnostat=" + idnostat + " ]";
    }
    
}
