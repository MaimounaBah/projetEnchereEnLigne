/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "Categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByIdCategorie", query = "SELECT c FROM Categorie c WHERE c.idCategorie = :idCategorie"),
    @NamedQuery(name = "Categorie.findByLibelleCat", query = "SELECT c FROM Categorie c WHERE c.libelleCat = :libelleCat")})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCategorie")
    private Integer idCategorie;
    @Column(name = "libelleCat")
    private String libelleCat;
    @JoinTable(name = "composer", joinColumns = {
        @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")}, inverseJoinColumns = {
        @JoinColumn(name = "idSousCategorie", referencedColumnName = "idCategorie")})
    @ManyToMany
    private Collection<Categorie> categorieCollection;
    @ManyToMany(mappedBy = "categorieCollection")
    private Collection<Categorie> categorieCollection1;
    @JoinTable(name = "composerSous", joinColumns = {
        @JoinColumn(name = "idSousCategorie", referencedColumnName = "idCategorie")}, inverseJoinColumns = {
        @JoinColumn(name = "idSSousCategorie", referencedColumnName = "idCategorie")})
    @ManyToMany
    private Collection<Categorie> categorieCollection2;
    @ManyToMany(mappedBy = "categorieCollection2")
    private Collection<Categorie> categorieCollection3;
    @OneToMany(mappedBy = "idCategorie")
    private Collection<Visstat> visstatCollection;
    @OneToMany(mappedBy = "idCategorie")
    private Collection<Article> articleCollection;
    @OneToMany(mappedBy = "idSousCategorie")
    private Collection<Article> articleCollection1;
    @OneToMany(mappedBy = "idCategorie")
    private Collection<Castat> castatCollection;
    @OneToMany(mappedBy = "idCategorie")
    private Collection<Nostat> nostatCollection;

    public Categorie() {
    }

    public Categorie(Integer idCategorie,String libelleCat) {
        this.idCategorie = idCategorie;
        this.libelleCat = libelleCat;
        
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibelleCat() {
        return libelleCat;
    }

    public void setLibelleCat(String libelleCat) {
        this.libelleCat = libelleCat;
    }

    @XmlTransient
    public Collection<Categorie> getCategorieCollection() {
        return categorieCollection;
    }

    public void setCategorieCollection(Collection<Categorie> categorieCollection) {
        this.categorieCollection = categorieCollection;
    }

    @XmlTransient
    public Collection<Categorie> getCategorieCollection1() {
        return categorieCollection1;
    }

    public void setCategorieCollection1(Collection<Categorie> categorieCollection1) {
        this.categorieCollection1 = categorieCollection1;
    }

    @XmlTransient
    public Collection<Categorie> getCategorieCollection2() {
        return categorieCollection2;
    }

    public void setCategorieCollection2(Collection<Categorie> categorieCollection2) {
        this.categorieCollection2 = categorieCollection2;
    }

    @XmlTransient
    public Collection<Categorie> getCategorieCollection3() {
        return categorieCollection3;
    }

    public void setCategorieCollection3(Collection<Categorie> categorieCollection3) {
        this.categorieCollection3 = categorieCollection3;
    }

    @XmlTransient
    public Collection<Visstat> getVisstatCollection() {
        return visstatCollection;
    }

    public void setVisstatCollection(Collection<Visstat> visstatCollection) {
        this.visstatCollection = visstatCollection;
    }

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    @XmlTransient
    public Collection<Article> getArticleCollection1() {
        return articleCollection1;
    }

    public void setArticleCollection1(Collection<Article> articleCollection1) {
        this.articleCollection1 = articleCollection1;
    }

    @XmlTransient
    public Collection<Castat> getCastatCollection() {
        return castatCollection;
    }

    public void setCastatCollection(Collection<Castat> castatCollection) {
        this.castatCollection = castatCollection;
    }

    @XmlTransient
    public Collection<Nostat> getNostatCollection() {
        return nostatCollection;
    }

    public void setNostatCollection(Collection<Nostat> nostatCollection) {
        this.nostatCollection = nostatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorie != null ? idCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idCategorie == null && other.idCategorie != null) || (this.idCategorie != null && !this.idCategorie.equals(other.idCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Categorie[ idCategorie=" + idCategorie + " ]";
    }
    
}
