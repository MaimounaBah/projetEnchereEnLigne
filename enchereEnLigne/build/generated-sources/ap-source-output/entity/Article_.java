package entity;

import entity.Achatimmediat;
import entity.Avisarticle;
import entity.Categorie;
import entity.Encherir;
import entity.Image;
import entity.Membre;
import entity.Regionlivraison;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile SingularAttribute<Article, Integer> idarticle;
    public static volatile CollectionAttribute<Article, Achatimmediat> achatimmCollection;
    public static volatile SingularAttribute<Article, String> etat;
    public static volatile SingularAttribute<Article, Float> fraisPort;
    public static volatile SingularAttribute<Article, Date> dateCloture;
    public static volatile CollectionAttribute<Article, Encherir> encherirCollection;
    public static volatile SingularAttribute<Article, Categorie> idSousCategorie;
    public static volatile CollectionAttribute<Article, Achatimmediat> achatimmediatCollection;
    public static volatile SingularAttribute<Article, Float> prixAchatImme;
    public static volatile SingularAttribute<Article, Float> pasEnchere;
    public static volatile SingularAttribute<Article, Float> prixReserve;
    public static volatile CollectionAttribute<Article, Image> imageCollection;
    public static volatile SingularAttribute<Article, Regionlivraison> idRegionLiv;
    public static volatile CollectionAttribute<Article, Avisarticle> avisarticleCollection;
    public static volatile SingularAttribute<Article, Categorie> idCategorie;
    public static volatile SingularAttribute<Article, Membre> idvendeur;
    public static volatile SingularAttribute<Article, String> titreAnnonce;
    public static volatile SingularAttribute<Article, String> descAnnonce;
    public static volatile SingularAttribute<Article, Float> prixDep;

}