package domain.servicesRest;


import domain.Message;
import domain.Product;
import domain.User;
import domain.exceptions.NotEnoughPointsException;
import domain.exceptions.ProductException;
import domain.exceptions.SingUpException;
import domain.services.ProductsService;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;
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

    @POST
    @Path("{id}/changeProduct")
    @Consumes("application/json")
    @Produces("application/json")
    public Response changeProduct(@PathParam("id") final Integer id,Product product){
        try {
            productsService.userExchangesAProduct(id,product);
        } catch (NotEnoughPointsException e){
            return Response.serverError().build();
        }

        return Response.ok().tag("El producto fue cangeado correctamente").build();
    }
}
