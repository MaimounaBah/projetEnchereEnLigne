/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Steven
 */
@Entity
@Table(name = "achatimm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Achatimm.findAll", query = "SELECT a FROM Achatimm a"),
    @NamedQuery(name = "Achatimm.findByIdmembre", query = "SELECT a FROM Achatimm a WHERE a.achatimmPK.idmembre = :idmembre"),
    @NamedQuery(name = "Achatimm.findByIdarticle", query = "SELECT a FROM Achatimm a WHERE a.achatimmPK.idarticle = :idarticle"),
    @NamedQuery(name = "Achatimm.findByDateAchat", query = "SELECT a FROM Achatimm a WHERE a.dateAchat = :dateAchat")})
public class Achatimm implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AchatimmPK achatimmPK;
    @Column(name = "dateAchat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAchat;
    @JoinColumn(name = "idarticle", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idmembre", referencedColumnName = "idmembre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;

    public Achatimm() {
    }

    public Achatimm(AchatimmPK achatimmPK) {
        this.achatimmPK = achatimmPK;
    }

    public Achatimm(int idmembre, int idarticle) {
        this.achatimmPK = new AchatimmPK(idmembre, idarticle);
    }

    public AchatimmPK getAchatimmPK() {
        return achatimmPK;
    }

    public void setAchatimmPK(AchatimmPK achatimmPK) {
        this.achatimmPK = achatimmPK;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
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
        hash += (achatimmPK != null ? achatimmPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Achatimm)) {
            return false;
        }
        Achatimm other = (Achatimm) object;
        if ((this.achatimmPK == null && other.achatimmPK != null) || (this.achatimmPK != null && !this.achatimmPK.equals(other.achatimmPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Achatimm[ achatimmPK=" + achatimmPK + " ]";
    }
    
}
