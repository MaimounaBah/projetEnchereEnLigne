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
@Table(name = "achatimmediat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Achatimmediat.findAll", query = "SELECT a FROM Achatimmediat a"),
    @NamedQuery(name = "Achatimmediat.findByIdMembre", query = "SELECT a FROM Achatimmediat a WHERE a.achatimmediatPK.idMembre = :idMembre"),
    @NamedQuery(name = "Achatimmediat.findByIdArticle", query = "SELECT a FROM Achatimmediat a WHERE a.achatimmediatPK.idArticle = :idArticle"),
    @NamedQuery(name = "Achatimmediat.findByDateAchat", query = "SELECT a FROM Achatimmediat a WHERE a.dateAchat = :dateAchat")})
public class Achatimmediat implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AchatimmediatPK achatimmediatPK;
    @Column(name = "dateAchat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAchat;
    @JoinColumn(name = "idArticle", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "idMembre", referencedColumnName = "idmembre", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Membre membre;

    public Achatimmediat() {
    }

    public Achatimmediat(AchatimmediatPK achatimmediatPK) {
        this.achatimmediatPK = achatimmediatPK;
    }

    public Achatimmediat(int idMembre, int idArticle) {
        this.achatimmediatPK = new AchatimmediatPK(idMembre, idArticle);
    }

    public AchatimmediatPK getAchatimmediatPK() {
        return achatimmediatPK;
    }

    public void setAchatimmediatPK(AchatimmediatPK achatimmediatPK) {
        this.achatimmediatPK = achatimmediatPK;
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
        hash += (achatimmediatPK != null ? achatimmediatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Achatimmediat)) {
            return false;
        }
        Achatimmediat other = (Achatimmediat) object;
        if ((this.achatimmediatPK == null && other.achatimmediatPK != null) || (this.achatimmediatPK != null && !this.achatimmediatPK.equals(other.achatimmediatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Achatimmediat[ achatimmediatPK=" + achatimmediatPK + " ]";
    }
    
}
