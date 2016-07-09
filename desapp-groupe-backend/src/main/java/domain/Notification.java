package domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import domain.services.ReducedUserSerializer;
import org.joda.time.DateTime;

public class Notification extends Entity
{
    @JsonSerialize(using= ReducedUserSerializer.class)
    private User receiver ;
    private NotificationType type ;
    private DateTime timestamp ;
    private boolean seen = false  ;

    public Notification() { }

    public Notification(User receiver, NotificationType type)
    {
        timestamp = new DateTime();
        this.receiver = receiver;
        this.type = type ;
    }


    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
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
