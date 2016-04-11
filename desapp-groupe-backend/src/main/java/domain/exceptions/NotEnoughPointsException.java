package domain.exceptions;

import domain.gaming_service.product_service.Product;
import domain.User;

public class NotEnoughPointsException extends Exception {
    private User user;
    private Product product;

    public NotEnoughPointsException(User user,Product product){
        this.user = user;
        this.product = product;
    }
}
