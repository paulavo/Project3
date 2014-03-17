package manager.bean;

import entities.Music;
import entities.Playlist;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import session.PlaylistFacade;

/**
 *
 * @author Carlos
 */
@ManagedBean(name = "ManagerPlayList")
@SessionScoped
public class ManagerPlayList implements Serializable {

    @EJB
    private PlaylistFacade pf;
    private String name;
    @ManagedProperty(value = "#{ManagerUser}")
    private ManagerUser mu;
    private boolean order = true;
    private List<Playlist> playlists;
    private Playlist playlist;
    private List<Music> musicsPlaylist;

    public ManagerPlayList() {

    }

    public List<Playlist> getPlaylists() {
        this.playlists = pf.findByID(mu.getLoggedUser().getId_user());
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public String createPlaylist() {
        if (pf.createPlaylist(name, mu.getLoggedUser()) == true) {
            this.playlists = pf.findByID(mu.getLoggedUser().getId_user());
            return "listMyPlayLists";
        }
        return "Error";
    }

    public String editPlaylist() {
        playlist.setCanEdit(true);
        return null;
    }

    public String savePlayList() {
        //set "canEdit" of all employees to false 
        for (Playlist playlist : playlists) {
            playlist.setCanEdit(false);
            pf.edit(playlist);
        }
        return null;
    }

    public String deletePlaylist() {
        pf.remove(playlist);
        this.playlists.remove(playlist);
        return null;
    }

    public String orderByNameAsc() {
        playlists = pf.orderByNameAsc(mu.getLoggedUser());
        return "listMyPlayLists";
    }

    public String orderByNameDes() {
        playlists = pf.orderByNameDes(mu.getLoggedUser());
        return "listMyPlayLists";
    }

    public String orderByDateAsc() {
        playlists = pf.orderByDateAsc(mu.getLoggedUser());
        return "listMyPlayLists";
    }

    public String orderByDateDes() {
        playlists = pf.orderByDateDes(mu.getLoggedUser());
        return "listMyPlayLists";
    }

    /**
     * Método que devolve lista com músicas das "Minhas" PlayList
     *
     * @return
     */
    public String openMyPlaylist() {
        //Carregar Músicas da PlayList
        this.musicsPlaylist = pf.findByPlaylist(playlist);
        return "openMyPlayList";
    }

    /**
     * Método que devolve lista com músicas da PlayList
     *
     * @return
     */
    public String openPlaylist() {
        //Carregar Músicas da PlayList
        this.musicsPlaylist = pf.findByPlaylist(playlist);
        return "openMyPlayList";
    }

    /**
     * Método que permite adicionar músicas à PlayList
     *
     * @param music
     * @return
     */
    public String addMusicPlaylist(Music music) {
        playlist.getMusics().add(music);
        pf.edit(playlist);
        return "openMyPlayList";
    }

    /**
     * Método que elimina música de playlist
     *
     * @return
     */
    public String deleteMusicPlaylist(Music music) {
        for (int i = 0; i < playlist.getMusics().size(); i++) {
            if (playlist.getMusics().get(i).getId_music() == music.getId_music()) {
                playlist.getMusics().remove(i);
            }
        }
        pf.edit(playlist);
        return null;
    }

    public PlaylistFacade getPf() {
        return pf;
    }

    public void setPf(PlaylistFacade pf) {
        this.pf = pf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManagerUser getMu() {
        return mu;
    }

    public List<Music> getMusicsPlaylist() {
        return musicsPlaylist;
    }

    public void setMusicsPlaylist(List<Music> musicsPlaylist) {
        this.musicsPlaylist = musicsPlaylist;
    }

    public void setMu(ManagerUser mu) {
        this.mu = mu;
        this.playlists = pf.findByID(mu.getLoggedUser().getId_user());
    }

}
