package domain.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import domain.Route;
import domain.RoutePoint;

import java.io.IOException;

/**
 * Created by prospero on 6/14/16.
 */
public class ReducedRouteSerializer extends JsonSerializer<Route> {
    @Override
    public void serialize(Route value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeNumberField("distanceInKms", value.getDistanceInKms());
        gen.writeNumberField("fixedCosts", value.getFixedCosts());
            gen.writeArrayFieldStart("routePoints");
            for (RoutePoint rp : value.getRoutePoints())
            {
                gen.writeStartObject();

            }

    }
}
