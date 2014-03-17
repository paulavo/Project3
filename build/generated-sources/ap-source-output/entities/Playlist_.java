package entities;

import entities.Music;
import entities.User;
import java.util.GregorianCalendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-17T16:14:13")
@StaticMetamodel(Playlist.class)
public class Playlist_ { 

    public static volatile ListAttribute<Playlist, Music> musics;
    public static volatile SingularAttribute<Playlist, String> name;
    public static volatile SingularAttribute<Playlist, User> property;
    public static volatile SingularAttribute<Playlist, GregorianCalendar> dateCreate;
    public static volatile SingularAttribute<Playlist, Boolean> canEdit;
    public static volatile SingularAttribute<Playlist, Long> id_playlist;

}