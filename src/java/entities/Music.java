package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "MUSIC")
@NamedQueries({
    @NamedQuery(name = "Music.getMusicByTitleAndArtist", query = "SELECT m FROM Music m "
            + "WHERE m.title = :title AND m.artist= :artist"),
    @NamedQuery(name = "Music.getMusicByName", query = "SELECT m FROM Music m WHERE m.title = :title"),
    @NamedQuery(name = "Music.getMusicByArtist", query = "SELECT m FROM Music m WHERE m.artist = :artist"),
    @NamedQuery(name = "Music.getMusicByID", query = "SELECT m FROM Music m WHERE m.id_music = :id_music"),
    @NamedQuery(name = "Music.searchMusicByTitleOrArtist", query = "SELECT m FROM Music m WHERE m.title LIKE :search_str OR m.artist LIKE :search_str ORDER BY m.artist ASC"),
    @NamedQuery(name = "Music.findMusicsPlaylist", query = "SELECT m FROM Music m WHERE m.playlists = :playlist"),
    @NamedQuery(name = "Music.getMusicByUser", query = "SELECT m FROM Music m WHERE m.property = :user")
})

public class Music implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToMany(mappedBy = "musics")
    private List<Playlist> playlists;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MUSIC", nullable = false)
    private Long id_music;
    @NotNull
    @Column(name = "TITLE", nullable = false)
    private String title;
    @NotNull
    @Column(name = "ARTIST", nullable = false)
    private String artist;
    @Column(name = "ALBUM", nullable = false)
    private String album;
    @Column(name = "YEAR_MUSIC", nullable = false)
    private String year;
    @NotNull
    @Column(name = "PATH_MUSIC", nullable = false)
    private String path_music;
    private boolean canEdit;
    @ManyToOne
    private User property;

    public Music() {

    }

    public Music(String title, String artist, String album, String year, String path_music, User user) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.path_music = path_music;
        this.property = user;
        canEdit = false;
    }

    public User getUser() {
        return property;
    }

    public void setUser(User user) {
        this.property = user;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Long getId_music() {
        return id_music;
    }

    public void setId_music(Long id_music) {
        this.id_music = id_music;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPath_music() {
        return path_music;
    }

    public void setPath_music(String path_music) {
        this.path_music = path_music;
    }

    public Long getId() {
        return id_music;
    }

    public void setId(Long id_music) {
        this.id_music = id_music;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_music != null ? id_music.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Music)) {
            return false;
        }
        Music other = (Music) object;
        if ((this.id_music == null && other.id_music != null) || (this.id_music != null && !this.id_music.equals(other.id_music))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Music[ id=" + id_music + " ]";
    }

}
