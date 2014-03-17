package entities;

import entities.Music;
import entities.Playlist;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-17T16:14:13")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile ListAttribute<User, Music> musics;
    public static volatile SingularAttribute<User, Long> id_user;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, Playlist> playlist;
    public static volatile SingularAttribute<User, String> password;

}