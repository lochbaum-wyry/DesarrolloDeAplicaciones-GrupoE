package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import domain.services.ReducedUserSerializer;
import domain.servicesRest.serialization.JodaDateTimeDeserializer;
import org.joda.time.DateTime;

abstract public class Notification extends Entity
{
//    @JsonSerialize(using= ReducedUserSerializer.class)
    @JsonIgnore
    private User receiver ;

    private String type ;

    @JsonSerialize(using= DateTimeSerializer.class)
    @JsonDeserialize(using= JodaDateTimeDeserializer.class)
    private DateTime timestamp ;
    private boolean seen = false  ;

    public Notification() { }

    public Notification(User receiver, String type)
    {
        timestamp = new DateTime();
        this.receiver = receiver;
        this.type = type ;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
