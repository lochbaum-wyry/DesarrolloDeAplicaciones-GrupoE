package domain.notifications;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import domain.Notification;
import domain.NotificationType;
import domain.User;
import domain.services.ReducedUserSerializer;
import org.joda.time.DateTime;


public class InconmingMessageNotification extends Notification
{
    @JsonSerialize(using= ReducedUserSerializer.class)
    private User msgFrom ;
    public InconmingMessageNotification(){}
    public InconmingMessageNotification(User receiver, User msgFrom)
    {
        super(receiver, NotificationType.IncomingMessage.toString());
        this.setMsgFrom(msgFrom);
    }

    public User getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(User msgFrom) {
        this.msgFrom = msgFrom;
    }
}
