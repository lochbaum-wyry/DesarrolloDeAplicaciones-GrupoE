package domain.notifications;

import domain.*;

/**
 * Created by prospero on 7/8/16.
 */
public class RideRequestedNotification extends RideRequestNotification {
    public RideRequestedNotification(User receiver, RideRequest ride)
    {
        super(receiver, ride);
        this.setType(NotificationType.RideRequested);
    }

}
