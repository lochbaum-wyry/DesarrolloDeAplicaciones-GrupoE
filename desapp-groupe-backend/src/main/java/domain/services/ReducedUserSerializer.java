package domain.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domain.User;

import java.io.IOException;

/**
 * Created by prospero on 6/14/16.
 */
public class ReducedUserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("userName", value.getUserName());
        gen.writeStringField("name", value.getUserName());
        gen.writeStringField("lastName", value.getUserName());
        gen.writeStringField("image", value.getImage());
        gen.writeEndObject();
    }
}
