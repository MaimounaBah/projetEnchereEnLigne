package entity;

import entity.Article;
import entity.AvisarticlePK;
import entity.Membre;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Avisarticle.class)
public class Avisarticle_ { 

    public static volatile SingularAttribute<Avisarticle, String> avis;
    public static volatile SingularAttribute<Avisarticle, Membre> membre;
    public static volatile SingularAttribute<Avisarticle, AvisarticlePK> avisarticlePK;
    public static volatile SingularAttribute<Avisarticle, Article> article;

}