package manager.bean;

import entities.Music;
import entities.Playlist;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import session.PlaylistFacade;

/**
 *
 * @author Carlos
 */
@Named("ManagerPlayList")
@RequestScoped
public class ManagerPlayList implements Serializable {

    @Inject
    private PlaylistFacade pf;
    private String name;
    @Inject
    private ManagerUser mu;
    private List<Playlist> playlists;
    private Playlist playlist;
    private List<Music> musicsPlaylist;

    public ManagerPlayList() {

    }

    public List<Playlist> getPlaylists() {
        if (playlists == null) {
            this.playlists = pf.findByID(mu.getLoggedUser().getId_user());
        }
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
        pf.edit(playlist);
        return null;
    }

    public String savePlayList() {
        //set "canEdit" of all employees to false 
        for (Playlist p : playlists) {
            p.setCanEdit(false);
            pf.edit(p);
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

    public String orderByNumberAsc() {
        playlists = pf.orderByNumberASC(mu.getLoggedUser());
        return "listMyPlayLists";
    }

    /**
     * Método que devolve lista com músicas das "Minhas" PlayList
     *
     * @return
     */
    public String openMyPlaylist() {
        mu.setPlaylist(playlist);
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
        return "openPlayList";
    }

    /**
     * Método que permite adicionar músicas à PlayList
     *
     * @param music
     * @return
     */
    public String addMusicPlaylist(Music music) {
        this.playlist = mu.getPlaylist();
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
