package domain.gaming_service.product_service;

import domain.User;

public class ProductExchange {
    private User user;
    private Product product;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
