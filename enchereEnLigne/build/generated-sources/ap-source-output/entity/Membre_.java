package entity;

import entity.Achatimmediat;
import entity.Article;
import entity.Avisarticle;
import entity.Avismembre;
import entity.Encherir;
import entity.Prestation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Membre.class)
public class Membre_ { 

    public static volatile CollectionAttribute<Membre, Article> articleCollection;
    public static volatile SingularAttribute<Membre, String> ville;
    public static volatile SingularAttribute<Membre, String> mail;
    public static volatile SingularAttribute<Membre, String> nom;
    public static volatile SingularAttribute<Membre, String> cp;
    public static volatile CollectionAttribute<Membre, Encherir> encherirCollection;
    public static volatile SingularAttribute<Membre, Integer> nbEtoile;
    public static volatile CollectionAttribute<Membre, Avismembre> avismembreCollection1;
    public static volatile SingularAttribute<Membre, String> adressePostal;
    public static volatile CollectionAttribute<Membre, Achatimmediat> achatimmediatCollection;
    public static volatile SingularAttribute<Membre, Date> dateNais;
    public static volatile SingularAttribute<Membre, String> motDePass;
    public static volatile SingularAttribute<Membre, Integer> idmembre;
    public static volatile CollectionAttribute<Membre, Avisarticle> avisarticleCollection;
    public static volatile CollectionAttribute<Membre, Prestation> prestationCollection;
    public static volatile CollectionAttribute<Membre, Avismembre> avismembreCollection;
    public static volatile SingularAttribute<Membre, String> prenom;
    public static volatile SingularAttribute<Membre, String> statut;
    public static volatile SingularAttribute<Membre, String> pays;

}