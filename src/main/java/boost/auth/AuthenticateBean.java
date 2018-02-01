package boost.auth;

import boost.auth.domain.*;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 20.08.17
 * Time: 20:43
 */

@Stateless
public class AuthenticateBean {
    public enum LoginResult{
        INCORRECT_LOGIN,
        INCORRECT_PASSWORD,
        SUCCESS
    }

    @Inject
    private EntityManager entityManager;

    public LoginResult doLogin(String login, String password){
        if(StringUtils.isEmpty(login)){
            return LoginResult.INCORRECT_LOGIN;
        }

        if(StringUtils.isEmpty(password)){
            return LoginResult.INCORRECT_PASSWORD;
        }

        PersonEntity personEntity = entityManager.find(PersonEntity.class, login);
        if(personEntity == null){
            return LoginResult.INCORRECT_LOGIN;
        }

        if(!password.equals(personEntity.getPassword())){
            return LoginResult.INCORRECT_PASSWORD;
        }

        return LoginResult.SUCCESS;
    }

    public boolean isGranted(String login, String resource){
        if(StringUtils.isEmpty(login) || StringUtils.isEmpty(resource)){
            return false;
        }

        ResourceEntity resourceEntity = entityManager.find(ResourceEntity.class, resource);
        if(resourceEntity == null){
            return false;
        }

        PersonEntity personEntity = entityManager.find(PersonEntity.class, login);
        if(personEntity == null){
            return false;
        }

        List<PersonRole> personRoles = personEntity.getPersonRoles();
        if(personRoles == null || personRoles.isEmpty()){
            return false;
        }

        for (PersonRole personRole : personRoles){
            Role role = personRole.getRole();
            if(role == null){
                continue;
            }

            List<Right> rights = role.getRights();
            for (Right right : rights){
                if(right == null){
                    continue;
                }

                ResourceEntity rightResource = right.getResourceEntity();
                if (rightResource.getId().equalsIgnoreCase(resource)){
                    return true;
                }
            }
        }

        return false;
    }
}
