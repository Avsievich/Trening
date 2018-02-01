package boost.products;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 02.07.17
 * Time: 20:52
 */
public class Product {
    private long id;
    private String name;
    private long price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
