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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 33760
 */
@Entity
@Table(name = "administrateur")
@NamedQueries({
    @NamedQuery(name = "Administrateur.findAll", query = "SELECT a FROM Administrateur a")})
public class Administrateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idadministrateur")
    private Integer idadministrateur;
    @Column(name = "login")
    private String login;
    @Column(name = "motDePasse")
    private String motDePasse;
    @Column(name = "role")
    private String role;

    public Administrateur() {
    }

    public Administrateur(Integer idadministrateur) {
        this.idadministrateur = idadministrateur;
    }

    public Integer getIdadministrateur() {
        return idadministrateur;
    }

    public void setIdadministrateur(Integer idadministrateur) {
        this.idadministrateur = idadministrateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadministrateur != null ? idadministrateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrateur)) {
            return false;
        }
        Administrateur other = (Administrateur) object;
        if ((this.idadministrateur == null && other.idadministrateur != null) || (this.idadministrateur != null && !this.idadministrateur.equals(other.idadministrateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Administrateur[ idadministrateur=" + idadministrateur + " ]";
    }
    
}
