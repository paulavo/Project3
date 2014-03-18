package manager.bean;

import entities.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import session.UserFacade;

@Named("ManagerUser")
@SessionScoped
public class ManagerUser implements Serializable {

    @Inject
    private UserFacade uf;
    private String email;
    private String pass;
    private String pass1;
    private String error;
    private String name;
    private User loggedUser;

    /*
     * Constructor
     **************************************************************************
     */
    public ManagerUser() {

    }

    /*
     *login
     **************************************************************************
     */
    public String login() {
        if (uf.checkLogin(email, pass) == true) {
            //identify the logged user
            loggedUser = uf.findByEmail(email);
            error = "";
            return "loginTrue";
        } else {
            error = "ERRO: Dados Inválidos!";
            return "index";
        }
    }

    /*
     *create new User
     *
     */
    public String register() {
        if (uf.validaEmail(email) && (uf.findByEmail(email) == null) && (pass.equals(pass1)) && uf.validaNome(name) && uf.validaPass(pass)) {//temos de verificar se as password sao iguais para criar novo user
            uf.createNewUser(name, email, pass);
            loggedUser = uf.findByEmail(email);
            error = "";
            return "loginTrue";
        } else {
            error = "ERRO: Utilizador Já Registado!";
            return "registerUser";
        }
    }

    public String changeDataUser() {
        setName(loggedUser.getName());
        setEmail(loggedUser.getEmail());
        if (pass.isEmpty() && uf.validaEmail(email) && uf.validaNome(name)) {
            uf.editUser(loggedUser);
            error = "";
            return "loginTrue";
        } else if (!pass.isEmpty() && uf.validaEmail(email) && (pass.equals(pass1)) && uf.validaNome(name) && uf.validaPass(pass)) {
            loggedUser.setPassword(uf.chave(pass));//faz encriptacao da password
            uf.editUser(loggedUser);
            error = "";
            return "loginTrue";
        } else {
            error = "Wrong password";
            return "editUser";
        }
    }

    public String fechoSessao() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        email = "";
        return "index";
    }

    /**
     * Método que elimina utilizador e todos os seus registos
     *
     * @return
     */
    public String deleteUser() {
        //Remover User;
        uf.remove(loggedUser);
        return "index";
    }

    /*
     * Getters and Setters
     **************************************************************************
     */
    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUf(UserFacade uf) {
        this.uf = uf;
    }

    public UserFacade getUf() {
        return uf;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass1() {
        return pass1;
    }

}
