package domain.notifications;

import domain.*;


public class RideRejectedNotification extends RideRequestNotification
{
    public RideRejectedNotification(){
    }
    public RideRejectedNotification(User receiver, RideRequest rideRequest)
    {
        super(receiver, rideRequest);
        this.setType(NotificationType.RideRejected.toString());
    }
}
