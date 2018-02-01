package boost.auth;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 20.08.17
 * Time: 21:11
 */

@Named
@SessionScoped
public class PersonBean implements Serializable {
    private String login;
    private String password;
    private boolean authenticated;
    private String initialRequestURI;

    @EJB
    private AuthenticateBean authenticateBean;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getInitialRequestURI() {
        return initialRequestURI;
    }

    public void setInitialRequestURI(String initialRequestURI) {
        this.initialRequestURI = initialRequestURI;
    }

    public void doLogin() {
        authenticated = (authenticateBean.doLogin(login, password) == AuthenticateBean.LoginResult.SUCCESS);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(initialRequestURI);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
