package entity;

import entity.Membre;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Prestation.class)
public class Prestation_ { 

    public static volatile SingularAttribute<Prestation, String> libellePres;
    public static volatile SingularAttribute<Prestation, Float> prixGold;
    public static volatile SingularAttribute<Prestation, Float> prixCat;
    public static volatile SingularAttribute<Prestation, Integer> idPRESTATION;
    public static volatile CollectionAttribute<Prestation, Membre> membreCollection;

}