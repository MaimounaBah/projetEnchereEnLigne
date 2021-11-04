package entity;

import entity.Article;
import entity.EncherirPK;
import entity.Membre;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Encherir.class)
public class Encherir_ { 

    public static volatile SingularAttribute<Encherir, EncherirPK> encherirPK;
    public static volatile SingularAttribute<Encherir, Integer> ifGagnant;
    public static volatile SingularAttribute<Encherir, BigDecimal> prixPropose;
    public static volatile SingularAttribute<Encherir, Membre> membre;
    public static volatile SingularAttribute<Encherir, Integer> nombrePas;
    public static volatile SingularAttribute<Encherir, Date> dateEncherir;
    public static volatile SingularAttribute<Encherir, Article> article;

}