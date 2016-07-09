package domain.notifications;

import domain.Notification;
import domain.NotificationType;
import domain.Ride;
import domain.User;

abstract public class RideNotification extends Notification {

    private Ride ride;

    public RideNotification(User receiver, Ride ride)
    {
        super();
        this.setReceiver(receiver);
        this.setRide(ride);
    }

    public void setRide(Ride ride)
    {
        this.ride = ride;
    }

    public Ride getRide()
    {
        return ride;
    }

}
