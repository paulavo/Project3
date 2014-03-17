package session;

import entities.Music;
import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Carlos
 */
@Stateless
public class MusicFacade extends AbstractFacade<Music> {

    @PersistenceContext(unitName = "Project3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

//    private ManagerUser mu_logged;
    public MusicFacade() {
        super(Music.class);
    }

    public boolean createMusic(String title, String artist, String album, String year, String path_music, User user) {
        this.create(new Music(title, artist, album, year, path_music, user));
        return true;
    }

    //faz pesquisa por um resultado partindo do principi que este e unico.
    public Music findByNameAndArtist(String title, String artist) {
        TypedQuery<Music> q = em.createNamedQuery("Music.getMusicByTitleAndArtist", Music.class);
        q.setParameter("title", title);
        q.setParameter("artist", artist);
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Music> searchByNameOrArtist(String word) {
        TypedQuery<Music> q = em.createNamedQuery("Music.searchMusicByTitleOrArtist", Music.class);
        q.setParameter("search_str", "%" + word + "%");
//        q.setParameter("search_str", word);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //faz pesquisa por um resultado partindo do principi que este e unico.
    public List<Music> findByID(long id_user) {
        TypedQuery<Music> q = em.createQuery("SELECT m FROM Music m WHERE m.property.id_user =" + Long.toString(id_user), Music.class);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //faz pesquisa por um resultado partindo do principi que este e unico.
    public List<Music> findNotID(int id_user) {
        TypedQuery<Music> q = em.createQuery("SELECT m FROM Music m WHERE m.property.id_user <>" + Integer.toString(id_user), Music.class);
        try {
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
