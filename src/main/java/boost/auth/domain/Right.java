package boost.auth.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 20.08.17
 * Time: 20:10
 */

@Entity
@Table(name = "resource_rights")
public class Right {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ResourceEntity resourceEntity;

    @ManyToOne
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    public void setResourceEntity(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
