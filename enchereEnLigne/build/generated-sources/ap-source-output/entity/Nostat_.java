package entity;

import entity.Categorie;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Nostat.class)
public class Nostat_ { 

    public static volatile SingularAttribute<Nostat, Integer> idnostat;
    public static volatile SingularAttribute<Nostat, Integer> nombreObjet;
    public static volatile SingularAttribute<Nostat, Categorie> idCategorie;
    public static volatile SingularAttribute<Nostat, Integer> semaine;
    public static volatile SingularAttribute<Nostat, Integer> annee;

}