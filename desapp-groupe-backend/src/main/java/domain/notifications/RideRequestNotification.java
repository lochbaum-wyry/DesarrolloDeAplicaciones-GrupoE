package domain.notifications;

import domain.Notification;
import domain.Ride;
import domain.RideRequest;
import domain.User;

/**
 * Created by prospero on 7/8/16.
 */
public class RideRequestNotification extends Notification {

    private RideRequest rideRequest;

    public RideRequestNotification() {}
    public RideRequestNotification(User receiver, RideRequest rideRequest)
    {
        super();
        this.setReceiver(receiver);
        this.setRideRequest(rideRequest);
    }

    public void setRideRequest(RideRequest rideRequest)
    {
        this.rideRequest = rideRequest;
    }

    public RideRequest getRideRequest()
    {
        return rideRequest;
    }

}
