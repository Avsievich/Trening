package boost.products;

import boost.products.domain.ProductEntity;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: BoostBrain
 * Date: 02.07.17
 * Time: 20:50
 */

@Named
@SessionScoped
public class ProductsListBean implements Serializable{
    @EJB
    private ProductsManagerBean productsManagerBean;

    @Inject
    @ExampleQualifier
    private ExampleBean exampleBean;

    private Product newProduct = new Product();
    private long idForDelete;
    private Product editingProduct;

    public List<Product> getProducts(){
        return productsManagerBean.readList(0, 100);
    }

    public Product getNewProduct() {
        return newProduct;
    }

    public void createNewProduct(){
        productsManagerBean.create(newProduct);
        newProduct = new Product();
    }

    public long getIdForDelete() {
        return idForDelete;
    }

    public void setIdForDelete(long idForDelete) {
        this.idForDelete = idForDelete;
    }

    public Product getEditingProduct() {
        return editingProduct;
    }

    public void setEditingProduct(Product editingProduct) {
        this.editingProduct = editingProduct;
    }

    public void deleteProduct(){
        productsManagerBean.delete(idForDelete);
    }

    public void saveProduct(){
        productsManagerBean.update(editingProduct);
    }
}
