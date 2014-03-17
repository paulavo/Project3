package session;

import entities.Music;
import entities.Playlist;
import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Paulo
 */
@Stateless
public class PlaylistFacade extends AbstractFacade<Playlist> {

    @PersistenceContext(unitName = "Project3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaylistFacade() {
        super(Playlist.class);
    }

    public boolean createPlaylist(String name, User user) {
        this.create(new Playlist(user, name));
        return true;
    }

    /**
     * faz pesquisa por um resultado partindo do principi que este e unico.
     *
     * @param name
     * @return
     */
    public Playlist findByNameAndArtist(String name) {
        TypedQuery<Playlist> q = em.createNamedQuery("Playlist.getPlaylistByname", Playlist.class);
        q.setParameter("name", name);
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * faz pesquisa por um resultado partindo do principi que este e unico.
     *
     * @param id_user
     * @return
     */
    public List<Playlist> findByID(long id_user) {
        TypedQuery<Playlist> q = em.createQuery("SELECT m FROM Playlist m WHERE m.property.id_user =" + Long.toString(id_user), Playlist.class);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Playlist> orderByNameAsc(User user) {
        TypedQuery<Playlist> q = em.createNamedQuery("Playlist.orderByNameASC", Playlist.class);
        q.setParameter("user", user);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Playlist> orderByNameDes(User user) {
        TypedQuery<Playlist> q = em.createNamedQuery("Playlist.orderByNameDES", Playlist.class);
        q.setParameter("user", user);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Playlist> orderByDateAsc(User user) {
        TypedQuery<Playlist> q = em.createNamedQuery("Playlist.orderByDateASC", Playlist.class);
        q.setParameter("user", user);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Playlist> orderByDateDes(User user) {
        TypedQuery<Playlist> q = em.createNamedQuery("Playlist.orderByDateASC", Playlist.class);
        q.setParameter("user", user);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Faz pesquise de músicas da Playlist
     *
     * @param playlist
     */
    public List<Music> findByPlaylist(Playlist playlist) {
        TypedQuery<Music> musicsPlaylist = em.createNamedQuery("Music.findMusicsPalylist", Music.class);
        musicsPlaylist.setParameter("playlist", playlist);
        try {
            return musicsPlaylist.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * faz pesquisa por um resultado partindo do principi que este e unico.
     *
     * @param id_user
     * @return
     */
    public List<Playlist> findNotID(int id_user) {
        TypedQuery<Playlist> q = em.createQuery("SELECT m FROM Playlist m WHERE m.property.id_user <>" + Integer.toString(id_user), Playlist.class);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
