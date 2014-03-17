package session;

import entities.Music;
import entities.Playlist;
import entities.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "Project3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    /**
     * procura um utilizador por email
     *
     * @param email
     * @return
     */
    public User findByEmail(String email) {
        TypedQuery<User> q = em.createNamedQuery("User.getUserByEmail", User.class);
        q.setParameter("email", email);
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String encriptPassword(String value) {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            return null;
        }
        return new String(m.digest(value.getBytes()));
    }

    /**
     * cria um novo utilizador
     *
     * @param name
     * @param email
     * @param password
     * @return
     */
    public boolean createNewUser(String name, String email, String password) {
        if (findByEmail(email) == null) {
            String password_enc = encriptPassword(password);
            this.create(new User(name, email, password_enc));
            return true;
        }
        return false;
    }

    public String chave(String pass) {
        return encriptPassword(pass);
    }

    /**
     *
     * @param email
     * @param pass
     * @return
     */
    public boolean checkLogin(String email, String pass) {
        User u = findByEmail(email);
        return u != null && u.getPassword().equals(encriptPassword(pass));
    }

    public String editUser(User logged) {
        edit(logged);
        return "Data change.";
    }

    public boolean validaNome(String str) {
        return !str.isEmpty();
    }

    public boolean validaPass(String str) {
        /*if (str.isEmpty()) {
         return false;
         }
         return str.matches("((?=.*\\d)[A-Za-z].{3,})");*/
        return true;
    }

    /**
     * valida se o email já está a ser utilizado e valida o email
     *
     * @param str
     * @return
     */
    public boolean validaEmail(String str) {
        if (!str.isEmpty()) {
            if (str.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\."
                    + "[a-z0-9-]+)*(\\.[a-z]{2,4})$")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Faz pesquise de músicas do user
     *
     * @param playlist
     */
    public List<Music> findMusicsToDelete(User user) {
        TypedQuery<Music> musicsDelete = em.createNamedQuery("Music.getMusicByUser", Music.class);
        musicsDelete.setParameter("user", user);
        try {
            return musicsDelete.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Faz pesquise de playlists do user
     *
     * @param playlist
     */
    public List<Playlist> findPlaylistsToDelete(User user) {
        TypedQuery<Playlist> playListDelete = em.createNamedQuery("Playlist.getMusicByUser", Playlist.class);
        playListDelete.setParameter("user", user);
        try {
            return playListDelete.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
