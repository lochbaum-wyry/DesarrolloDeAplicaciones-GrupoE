package domain.notifications;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import domain.Notification;
import domain.NotificationType;
import domain.User;
import domain.services.ReducedUserSerializer;

/**
 * Created by prospero on 7/8/16.
 */
public class WallPostNotification extends Notification {

    @JsonSerialize(using= ReducedUserSerializer.class)
    private User msgFrom ;

    public WallPostNotification(User receiver, User msgFrom)
    {
        super(receiver, NotificationType.WallPost);
        this.msgFrom = msgFrom;
    }

    public User getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(User msgFrom) {
        this.msgFrom = msgFrom;
    }
}
