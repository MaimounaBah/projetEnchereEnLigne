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
@Table(name = "avisarticle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avisarticle.findAll", query = "SELECT a FROM Avisarticle a"),
    @NamedQuery(name = "Avisarticle.findByIdarticle", query = "SELECT a FROM Avisarticle a WHERE a.avisarticlePK.idarticle = :idarticle"),
    @NamedQuery(name = "Avisarticle.findByIdmembre", query = "SELECT a FROM Avisarticle a WHERE a.avisarticlePK.idmembre = :idmembre"),
    @NamedQuery(name = "Avisarticle.findByAvis", query = "SELECT a FROM Avisarticle a WHERE a.avis = :avis")})
public class Avisarticle implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AvisarticlePK avisarticlePK;
    @Column(name = "avis")
    private String avis;
    @JoinColumn(name = "idarticle", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idmembre", referencedColumnName = "idmembre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;

    public Avisarticle() {
    }

    public Avisarticle(AvisarticlePK avisarticlePK) {
        this.avisarticlePK = avisarticlePK;
    }

    public Avisarticle(int idarticle, int idmembre) {
        this.avisarticlePK = new AvisarticlePK(idarticle, idmembre);
    }

    public AvisarticlePK getAvisarticlePK() {
        return avisarticlePK;
    }

    public void setAvisarticlePK(AvisarticlePK avisarticlePK) {
        this.avisarticlePK = avisarticlePK;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
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
        hash += (avisarticlePK != null ? avisarticlePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avisarticle)) {
            return false;
        }
        Avisarticle other = (Avisarticle) object;
        if ((this.avisarticlePK == null && other.avisarticlePK != null) || (this.avisarticlePK != null && !this.avisarticlePK.equals(other.avisarticlePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Avisarticle[ avisarticlePK=" + avisarticlePK + " ]";
    }
    
}
