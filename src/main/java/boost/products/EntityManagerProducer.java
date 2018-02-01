package boost.products;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 16.07.17
 * Time: 21:37
 */
public class EntityManagerProducer {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    @Produces
    public EntityManager createEntityManager(){
        return entityManager;
    }

    @Produces
    @ExampleQualifier
    public ExampleBean createExampleBean(){
        return new ExampleBean();
    }
}
