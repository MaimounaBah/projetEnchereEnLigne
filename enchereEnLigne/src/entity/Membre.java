/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 33760
 */
@Entity
@Table(name = "membre")
@NamedQueries({
    @NamedQuery(name = "Membre.findAll", query = "SELECT m FROM Membre m")})
public class Membre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmembre")
    private Integer idmembre;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "dateNais")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNais;
    @Column(name = "mail")
    private String mail;
    @Column(name = "adressePostal")
    private String adressePostal;
    @Column(name = "CP")
    private String cp;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;
    @Column(name = "MotDePass")
    private String motDePass;
    @Column(name = "Statut")
    private String statut;
    @Column(name = "NbEtoile")
    private Integer nbEtoile;
    @JoinTable(name = "choisir", joinColumns = {
        @JoinColumn(name = "idmembre", referencedColumnName = "idmembre")}, inverseJoinColumns = {
        @JoinColumn(name = "idprestation", referencedColumnName = "idPRESTATION")})
    @ManyToMany
    private Collection<Prestation> prestationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Avisarticle> avisarticleCollection;
    @OneToMany(mappedBy = "idvendeur")
    private Collection<Article> articleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Achatimmediat> achatimmediatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Avismembre> avismembreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre1")
    private Collection<Avismembre> avismembreCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
    private Collection<Encherir> encherirCollection;

    public Membre() {
    }

    public Membre(Integer idmembre) {
        this.idmembre = idmembre;
    }

    public Integer getIdmembre() {
        return idmembre;
    }

    public void setIdmembre(Integer idmembre) {
        this.idmembre = idmembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdressePostal() {
        return adressePostal;
    }

    public void setAdressePostal(String adressePostal) {
        this.adressePostal = adressePostal;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getNbEtoile() {
        return nbEtoile;
    }

    public void setNbEtoile(Integer nbEtoile) {
        this.nbEtoile = nbEtoile;
    }

    public Collection<Prestation> getPrestationCollection() {
        return prestationCollection;
    }

    public void setPrestationCollection(Collection<Prestation> prestationCollection) {
        this.prestationCollection = prestationCollection;
    }

    public Collection<Avisarticle> getAvisarticleCollection() {
        return avisarticleCollection;
    }

    public void setAvisarticleCollection(Collection<Avisarticle> avisarticleCollection) {
        this.avisarticleCollection = avisarticleCollection;
    }

    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    public Collection<Achatimmediat> getAchatimmediatCollection() {
        return achatimmediatCollection;
    }

    public void setAchatimmediatCollection(Collection<Achatimmediat> achatimmediatCollection) {
        this.achatimmediatCollection = achatimmediatCollection;
    }

    public Collection<Avismembre> getAvismembreCollection() {
        return avismembreCollection;
    }

    public void setAvismembreCollection(Collection<Avismembre> avismembreCollection) {
        this.avismembreCollection = avismembreCollection;
    }

    public Collection<Avismembre> getAvismembreCollection1() {
        return avismembreCollection1;
    }

    public void setAvismembreCollection1(Collection<Avismembre> avismembreCollection1) {
        this.avismembreCollection1 = avismembreCollection1;
    }

    public Collection<Encherir> getEncherirCollection() {
        return encherirCollection;
    }

    public void setEncherirCollection(Collection<Encherir> encherirCollection) {
        this.encherirCollection = encherirCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmembre != null ? idmembre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membre)) {
            return false;
        }
        Membre other = (Membre) object;
        if ((this.idmembre == null && other.idmembre != null) || (this.idmembre != null && !this.idmembre.equals(other.idmembre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Membre[ idmembre=" + idmembre + " ]";
    }
    
}
