package domain;

public class ProductExchange extends Entity{
    private User user;
    private Product product;

    public ProductExchange(){
    }

    public ProductExchange(User user,Product product){
        this.user = user;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
