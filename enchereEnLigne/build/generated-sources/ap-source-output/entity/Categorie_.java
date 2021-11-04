package entity;

import entity.Article;
import entity.Castat;
import entity.Categorie;
import entity.Nostat;
import entity.Visstat;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Categorie.class)
public class Categorie_ { 

    public static volatile CollectionAttribute<Categorie, Article> articleCollection;
    public static volatile SingularAttribute<Categorie, String> libelleCat;
    public static volatile CollectionAttribute<Categorie, Article> articleCollection1;
    public static volatile CollectionAttribute<Categorie, Categorie> categorieCollection1;
    public static volatile CollectionAttribute<Categorie, Castat> castatCollection;
    public static volatile CollectionAttribute<Categorie, Categorie> categorieCollection2;
    public static volatile CollectionAttribute<Categorie, Categorie> categorieCollection3;
    public static volatile SingularAttribute<Categorie, Integer> idCategorie;
    public static volatile CollectionAttribute<Categorie, Nostat> nostatCollection;
    public static volatile CollectionAttribute<Categorie, Visstat> visstatCollection;
    public static volatile CollectionAttribute<Categorie, Categorie> categorieCollection;

}