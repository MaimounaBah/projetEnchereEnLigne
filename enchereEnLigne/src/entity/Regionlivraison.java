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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 33760
 */
@Entity
@Table(name = "regionlivraison")
@NamedQueries({
    @NamedQuery(name = "Regionlivraison.findAll", query = "SELECT r FROM Regionlivraison r")})
public class Regionlivraison implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idregionlivraison")
    private Integer idregionlivraison;
    @Column(name = "region")
    private String region;
    @OneToMany(mappedBy = "idRegionLiv")
    private Collection<Article> articleCollection;

    public Regionlivraison() {
    }

    public Regionlivraison(Integer idregionlivraison, String region) {
        this.idregionlivraison = idregionlivraison;
        this.region = region;
    }
    
     public Regionlivraison(Integer idregionlivraison) {
        this.idregionlivraison = idregionlivraison;
        
    }


    public Integer getIdregionlivraison() {
        return idregionlivraison;
    }

    public void setIdregionlivraison(Integer idregionlivraison) {
        this.idregionlivraison = idregionlivraison;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregionlivraison != null ? idregionlivraison.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regionlivraison)) {
            return false;
        }
        Regionlivraison other = (Regionlivraison) object;
        if ((this.idregionlivraison == null && other.idregionlivraison != null) || (this.idregionlivraison != null && !this.idregionlivraison.equals(other.idregionlivraison))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Regionlivraison[ idregionlivraison=" + idregionlivraison + " ]";
    }
    
}
