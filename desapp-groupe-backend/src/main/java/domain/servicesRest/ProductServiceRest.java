package domain.servicesRest;


import domain.Product;
import domain.exceptions.ProductException;
import domain.exceptions.SingUpException;
import domain.services.ProductsService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("product")
@Service
public class ProductServiceRest {
    ProductsService productsService;

    public ProductServiceRest(){}

    public ProductServiceRest(ProductsService productsService) {
        this.productsService = productsService;
    }

    @POST
    @Path("createProduct")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createProduct(Product product){

        try {
            productsService.createProduct(product.getName(), product.getStock(), product.getCost());
        } catch (ProductException e){
            return Response.serverError().build();
        }

        return Response.ok().tag("El producto fue creado").build();
    }

    @GET
    @Path("products")
    @Produces("application/json")
    public List<Product> products(){
        return productsService.products();
    }
}
