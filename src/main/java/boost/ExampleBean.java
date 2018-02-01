package boost;

import javax.annotation.*;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 12.06.17
 * Time: 20:45
 */

@Named(value = "firstBean")
@SessionScoped
public class ExampleBean implements Serializable{
    private String name;

    @PostConstruct
    private void initialize(){
        name = "Hello";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void someAction(){
        name = "";
    }
}
