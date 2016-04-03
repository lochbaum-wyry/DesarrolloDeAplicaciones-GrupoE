package domain;

public class ProductExchange {
    private User user;
    private Product product;

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
}
