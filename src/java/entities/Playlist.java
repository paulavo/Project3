package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PLAYLIST")
@NamedQueries({
    @NamedQuery(name = "Playlist.orderByNameASC", query = "SELECT p FROM Playlist p WHERE p.property = :user ORDER BY p.name ASC"),
    @NamedQuery(name = "Playlist.orderByNameDES", query = "SELECT p FROM Playlist p WHERE p.property = :user ORDER BY p.name DESC"),
    @NamedQuery(name = "Playlist.orderByDateASC", query = "SELECT p FROM Playlist p WHERE p.property = :user ORDER BY p.dateCreate ASC"),
    @NamedQuery(name = "Playlist.orderByDateDES", query = "SELECT p FROM Playlist p WHERE p.property = :user ORDER BY p.dateCreate DESC"),
    @NamedQuery(name = "Playlist.getMusicByUser", query = "SELECT m FROM Playlist m WHERE m.property = :user"),
    @NamedQuery(name = "Playlist.orderByNumberASC", query = "SELECT COUNT (p.musics) FROM Playlist p WHERE p.property = :user GROUP BY p.musics ORDER BY ASC")
})

public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    private User property;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PLAYLIST", nullable = false)
    private Long id_playlist;
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;
    @NotNull
    @Column(name = "CREATION_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateCreate;
    @ManyToMany
    private List<Music> musics;
    private boolean canEdit;

    public Playlist() {
    }

    public Playlist(User user, String name) {
        this.property = user;
        this.name = name;
        this.dateCreate = new GregorianCalendar();
        this.musics = new ArrayList();
        canEdit = false;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public User getProperty() {
        return property;
    }

    public void setProperty(User property) {
        this.property = property;
    }

    public Long getId_playlist() {
        return id_playlist;
    }

    public void setId_playlist(Long id_playlist) {
        this.id_playlist = id_playlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GregorianCalendar getDateCreate() {
        return dateCreate;
    }

    public String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(dateCreate.getTime());
        return date;
    }

    public void setDateCreate(GregorianCalendar dateCreate) {
        this.dateCreate = dateCreate;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_playlist != null ? id_playlist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) object;
        if ((this.id_playlist == null && other.id_playlist != null) || (this.id_playlist != null && !this.id_playlist.equals(other.id_playlist))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Playlist[ id=" + id_playlist + " ]";
    }

}
