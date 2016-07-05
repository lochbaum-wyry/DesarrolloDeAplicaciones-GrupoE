package domain.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import domain.User;

import java.io.IOException;
import java.util.List;

/**
 * Created by prospero on 7/5/16.
 */
public class ReducedUserListSerializer extends JsonSerializer<List<User>> {
    @Override
    public void serialize(List<User> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
//        ReducedUserSerializer userSerializer = new ReducedUserSerializer();
        gen.writeStartArray();
        for (int i=0 ; i < value.size() ; i ++ )
            serializeUser(gen, value.get(i));
        gen.writeEndArray();
    }

    public void serializeUser(JsonGenerator gen, User value) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("userName", value.getUserName());
        gen.writeStringField("name", value.getUserName());
        gen.writeStringField("lastName", value.getUserName());
        gen.writeStringField("image", value.getImage());
        gen.writeNumberField("points", value.getPoints());
        gen.writeNumberField("goodRateCount", value.getGoodRateCount());
        gen.writeNumberField("badRateCount", value.getBadRateCount());

        gen.writeEndObject();
    }
}
