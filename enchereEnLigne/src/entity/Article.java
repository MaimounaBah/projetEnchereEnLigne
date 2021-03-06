/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByIdarticle", query = "SELECT a FROM Article a WHERE a.idarticle = :idarticle"),
    @NamedQuery(name = "Article.findByTitreAnnonce", query = "SELECT a FROM Article a WHERE a.titreAnnonce = :titreAnnonce"),
    @NamedQuery(name = "Article.findByDescAnnonce", query = "SELECT a FROM Article a WHERE a.descAnnonce = :descAnnonce"),
    @NamedQuery(name = "Article.findByPrixAchatImme", query = "SELECT a FROM Article a WHERE a.prixAchatImme = :prixAchatImme"),
    @NamedQuery(name = "Article.findByPrixDep", query = "SELECT a FROM Article a WHERE a.prixDep = :prixDep"),
    @NamedQuery(name = "Article.findByPrixReserve", query = "SELECT a FROM Article a WHERE a.prixReserve = :prixReserve"),
    @NamedQuery(name = "Article.findByFraisPort", query = "SELECT a FROM Article a WHERE a.fraisPort = :fraisPort"),
    @NamedQuery(name = "Article.findByDateCloture", query = "SELECT a FROM Article a WHERE a.dateCloture = :dateCloture"),
    @NamedQuery(name = "Article.findByPasEnchere", query = "SELECT a FROM Article a WHERE a.pasEnchere = :pasEnchere"),
    @NamedQuery(name = "Article.findByEtat", query = "SELECT a FROM Article a WHERE a.etat = :etat")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticle")
    private Integer idarticle;
    @Column(name = "titreAnnonce")
    private String titreAnnonce;
    @Column(name = "descAnnonce")
    private String descAnnonce;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prixAchatImme")
    private float prixAchatImme;
    @Column(name = "prixDep")
    private float prixDep;
    @Column(name = "prixReserve")
    private float prixReserve;
    @Column(name = "fraisPort")
    private float fraisPort;
    @Column(name = "dateCloture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCloture;
    @Column(name = "pasEnchere")
    private float pasEnchere;
    @Column(name = "etat")
    private String etat;
    @JoinTable(name = "posseder", joinColumns = {
        @JoinColumn(name = "idarticle", referencedColumnName = "idarticle")}, inverseJoinColumns = {
        @JoinColumn(name = "idimage", referencedColumnName = "idimage")})
    @ManyToMany
    private Collection<Image> imageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<Avisarticle> avisarticleCollection;
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @ManyToOne
    private Categorie idCategorie;
    @JoinColumn(name = "idRegionLiv", referencedColumnName = "idregionlivraison")
    @ManyToOne
    private Regionlivraison idRegionLiv;
    @JoinColumn(name = "idSousCategorie", referencedColumnName = "idCategorie")
    @ManyToOne
    private Categorie idSousCategorie;
    @JoinColumn(name = "idvendeur", referencedColumnName = "idmembre")
    @ManyToOne
    private Membre idvendeur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<Achatimmediat> achatimmCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<Achatimmediat> achatimmediatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private Collection<Encherir> encherirCollection;

    public Article() {
    }

    public Article(Integer idarticle) {
        this.idarticle = idarticle;
    }
    
    public Article(Integer idarticle,String titreAnnonce,float prixDep,float prixReserve, Date dateCloture,float prixAchatImme) {
        this.idarticle = idarticle;
        this.titreAnnonce = titreAnnonce;
        this.prixDep = prixDep;
        this.prixReserve = prixReserve;
        this.dateCloture = dateCloture;
        this.prixAchatImme = prixAchatImme;
    }
     public Article(Integer idarticle,String titreAnnonce,float prixDep,float prixReserve, Date dateCloture,String etat) {
        this.idarticle = idarticle;
        this.titreAnnonce = titreAnnonce;
        this.prixDep = prixDep;
        this.prixReserve = prixReserve;
        this.dateCloture = dateCloture;
        this.etat = etat;
    }
     
      public Article(Integer idarticle,String titreAnnonce,String descAnnonce, float prixAchatImme,Date dateCloture) {
        this.idarticle = idarticle;
        this.titreAnnonce = titreAnnonce;
        this.descAnnonce = descAnnonce;
        this.prixAchatImme = prixAchatImme;
        this.dateCloture = dateCloture;
             
    }
    
    public Article(Integer idarticle,String titreAnnonce,String descAnnonce,float prixDep,float prixReserve, Date dateCloture,float prixAchatImme, float fraisPort,float pasEnchere) {
        this.idarticle = idarticle;
        this.titreAnnonce = titreAnnonce;
        this.descAnnonce = descAnnonce;
        this.prixDep = prixDep;
        this.prixReserve = prixReserve;
        this.dateCloture = dateCloture;
        this.prixAchatImme = prixAchatImme;
        this.pasEnchere = pasEnchere;
    }
    
    public Integer getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public String getTitreAnnonce() {
        return titreAnnonce;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public String getDescAnnonce() {
        return descAnnonce;
    }

    public void setDescAnnonce(String descAnnonce) {
        this.descAnnonce = descAnnonce;
    }

    public float getPrixAchatImme() {
        return prixAchatImme;
    }

    public void setPrixAchatImme(float prixAchatImme) {
        this.prixAchatImme = prixAchatImme;
    }

    public float getPrixDep() {
        return prixDep;
    }

    public void setPrixDep(float prixDep) {
        this.prixDep = prixDep;
    }

    public float getPrixReserve() {
        return prixReserve;
    }

    public void setPrixReserve(float prixReserve) {
        this.prixReserve = prixReserve;
    }

    public float getFraisPort() {
        return fraisPort;
    }

    public void setFraisPort(float fraisPort) {
        this.fraisPort = fraisPort;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public float getPasEnchere() {
        return pasEnchere;
    }

    public void setPasEnchere(float pasEnchere) {
        this.pasEnchere = pasEnchere;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<Avisarticle> getAvisarticleCollection() {
        return avisarticleCollection;
    }

    public void setAvisarticleCollection(Collection<Avisarticle> avisarticleCollection) {
        this.avisarticleCollection = avisarticleCollection;
    }

    public Categorie getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Categorie idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Regionlivraison getIdRegionLiv() {
        return idRegionLiv;
    }

    public void setIdRegionLiv(Regionlivraison idRegionLiv) {
        this.idRegionLiv = idRegionLiv;
    }

    public Categorie getIdSousCategorie() {
        return idSousCategorie;
    }

    public void setIdSousCategorie(Categorie idSousCategorie) {
        this.idSousCategorie = idSousCategorie;
    }

    public Membre getIdvendeur() {
        return idvendeur;
    }

    public void setIdvendeur(Membre idvendeur) {
        this.idvendeur = idvendeur;
    }

    @XmlTransient
    public Collection<Achatimmediat> getAchatimmCollection() {
        return achatimmCollection;
    }

    public void setAchatimmCollection(Collection<Achatimmediat> achatimmCollection) {
        this.achatimmCollection = achatimmCollection;
    }

    @XmlTransient
    public Collection<Achatimmediat> getAchatimmediatCollection() {
        return achatimmediatCollection;
    }

    public void setAchatimmediatCollection(Collection<Achatimmediat> achatimmediatCollection) {
        this.achatimmediatCollection = achatimmediatCollection;
    }

    @XmlTransient
    public Collection<Encherir> getEncherirCollection() {
        return encherirCollection;
    }

    public void setEncherirCollection(Collection<Encherir> encherirCollection) {
        this.encherirCollection = encherirCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticle != null ? idarticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idarticle == null && other.idarticle != null) || (this.idarticle != null && !this.idarticle.equals(other.idarticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Article[ idarticle=" + idarticle + " ]";
    }
    
}
