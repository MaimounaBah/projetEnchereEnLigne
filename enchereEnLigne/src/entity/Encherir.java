/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "encherir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encherir.findAll", query = "SELECT e FROM Encherir e"),
    @NamedQuery(name = "Encherir.findByIdmembre", query = "SELECT e FROM Encherir e WHERE e.encherirPK.idmembre = :idmembre"),
    @NamedQuery(name = "Encherir.findByIdarticle", query = "SELECT e FROM Encherir e WHERE e.encherirPK.idarticle = :idarticle"),
    @NamedQuery(name = "Encherir.findByNombrePas", query = "SELECT e FROM Encherir e WHERE e.nombrePas = :nombrePas"),
    @NamedQuery(name = "Encherir.findByIfGagnant", query = "SELECT e FROM Encherir e WHERE e.ifGagnant = :ifGagnant"),
    @NamedQuery(name = "Encherir.findByPrixPropose", query = "SELECT e FROM Encherir e WHERE e.prixPropose = :prixPropose")})
public class Encherir implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EncherirPK encherirPK;
    @Column(name = "nombrePas")
    private Integer nombrePas;
    @Column(name = "ifGagnant")
    private Integer ifGagnant;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prixPropose")
    private BigDecimal prixPropose;
    @JoinColumn(name = "idarticle", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idmembre", referencedColumnName = "idmembre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;

    public Encherir() {
    }

    public Encherir(EncherirPK encherirPK) {
        this.encherirPK = encherirPK;
    }

    public Encherir(int idmembre, int idarticle) {
        this.encherirPK = new EncherirPK(idmembre, idarticle);
    }

    public EncherirPK getEncherirPK() {
        return encherirPK;
    }

    public void setEncherirPK(EncherirPK encherirPK) {
        this.encherirPK = encherirPK;
    }

    public Integer getNombrePas() {
        return nombrePas;
    }

    public void setNombrePas(Integer nombrePas) {
        this.nombrePas = nombrePas;
    }

    public Integer getIfGagnant() {
        return ifGagnant;
    }

    public void setIfGagnant(Integer ifGagnant) {
        this.ifGagnant = ifGagnant;
    }

    public BigDecimal getPrixPropose() {
        return prixPropose;
    }

    public void setPrixPropose(BigDecimal prixPropose) {
        this.prixPropose = prixPropose;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (encherirPK != null ? encherirPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encherir)) {
            return false;
        }
        Encherir other = (Encherir) object;
        if ((this.encherirPK == null && other.encherirPK != null) || (this.encherirPK != null && !this.encherirPK.equals(other.encherirPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Encherir[ encherirPK=" + encherirPK + " ]";
    }
    
}
