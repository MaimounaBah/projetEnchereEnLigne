package entity;

import entity.AchatimmediatPK;
import entity.Article;
import entity.Membre;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-28T22:09:15")
@StaticMetamodel(Achatimmediat.class)
public class Achatimmediat_ { 

    public static volatile SingularAttribute<Achatimmediat, Membre> membre;
    public static volatile SingularAttribute<Achatimmediat, Date> dateAchat;
    public static volatile SingularAttribute<Achatimmediat, AchatimmediatPK> achatimmediatPK;
    public static volatile SingularAttribute<Achatimmediat, Article> article;

}