package entity;

import entity.Article;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Regionlivraison.class)
public class Regionlivraison_ { 

    public static volatile SingularAttribute<Regionlivraison, Integer> idregionlivraison;
    public static volatile CollectionAttribute<Regionlivraison, Article> articleCollection;
    public static volatile SingularAttribute<Regionlivraison, String> region;

}