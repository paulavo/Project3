package manager.bean;

import entities.Music;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import session.MusicFacade;

/**
 *
 * @author Carlos
 */
@ManagedBean(name = "ManagerMusic")
@RequestScoped
public class ManagerMusic implements Serializable {

    @EJB
    private MusicFacade mf;
    String searchTerm;
    private String title;
    private String artist;
    private String erro;
    private String album;
    private String year;
    private String path;
    private List<Music> result_music;
    @ManagedProperty(value = "#{ManagerUser}")
    private ManagerUser mu;
    private List<Music> musics;
    private Music music;

    public ManagerMusic() {

    }

    public String createMusic() {
        //cria o objecto musica
        if (mf.createMusic(title, artist, album, year, path, mu.getLoggedUser()) == true) {
            this.musics = mf.findByID(mu.getLoggedUser().getId_user());
            return "listMyMusics";
        }
        return "Error";
    }

    public String editMusic() {
        music.setCanEdit(true);
        return null;
    }

    public String saveMusic() {
        //set "canEdit" of all employees to false 
        for (Music music : musics) {
            music.setCanEdit(false);
            mf.edit(music);
        }
        return null;
    }

    public String deleteMusic() {
        mf.remove(music);
        this.musics.remove(music);
        return null;
    }

    public String searchTitleArtist() {
        result_music = mf.searchByNameOrArtist(searchTerm);
        return "listSearchMusics";
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
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

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ManagerUser getMu() {
        return mu;
    }

    public void setMu(ManagerUser mu) {
        this.mu = mu;
        this.musics = mf.findByID(mu.getLoggedUser().getId_user());
    }

    public MusicFacade getMf() {
        return mf;
    }

    public List<Music> getResult_music() {
        return result_music;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

}
