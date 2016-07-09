package domain.notifications;

import domain.Notification;
import domain.NotificationType;
import domain.Ride;
import domain.User;


public class RideAcceptedNotification extends RideNotification {

    public RideAcceptedNotification(User receiver, Ride ride)
    {
        super(receiver, ride);
        this.setType(NotificationType.RideAccepted);
    }

}
