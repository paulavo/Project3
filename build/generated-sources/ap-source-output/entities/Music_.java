package entities;

import entities.Playlist;
import entities.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-03-17T15:48:21")
@StaticMetamodel(Music.class)
public class Music_ { 

    public static volatile SingularAttribute<Music, String> title;
    public static volatile SingularAttribute<Music, String> path_music;
    public static volatile SingularAttribute<Music, Long> id_music;
    public static volatile SingularAttribute<Music, String> album;
    public static volatile SingularAttribute<Music, String> year;
    public static volatile SingularAttribute<Music, User> property;
    public static volatile ListAttribute<Music, Playlist> playlists;
    public static volatile SingularAttribute<Music, String> artist;
    public static volatile SingularAttribute<Music, Boolean> canEdit;

}