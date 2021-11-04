package entity;

import entity.Categorie;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Visstat.class)
public class Visstat_ { 

    public static volatile SingularAttribute<Visstat, Integer> nombreDeVisite;
    public static volatile SingularAttribute<Visstat, Integer> idvisstat;
    public static volatile SingularAttribute<Visstat, Categorie> idCategorie;
    public static volatile SingularAttribute<Visstat, Integer> semaine;
    public static volatile SingularAttribute<Visstat, Integer> annee;

}