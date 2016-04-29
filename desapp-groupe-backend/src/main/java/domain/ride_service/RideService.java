package domain.ride_service;


import domain.*;
import domain.Repositories.RideRepository;
import domain.Repositories.RideRequestRepository;
import domain.exceptions.NoSeatsAvailableException;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Optional;

public class RideService
{
    private SystemSettings systemSettings;
    private RideRepository rideRepository ;
    private RideRequestRepository rideRequestRepository;


    public RideService(RideRepository rideRepository, RideRequestRepository rideRequestRepository)
    {
        this.rideRepository = rideRepository ;
        this.rideRequestRepository = rideRequestRepository;
    }

    public List<Ride> getRidesOfUserAsDriver(User user)
    {
        return null;
    }
    public List<Ride> getRidesOfUserAsPassenger(User user)
    {
        return null;
    }

    public void requestRide(User requester, User driver, DateTime date, Route route, Location boardingAt, Location getOffAt)
    {
        RideRequest rideRequest = new RideRequest(requester, driver, date, route, boardingAt, getOffAt);
        rideRequestRepository.save(rideRequest);
    }

    public void rejectRideRequest(RideRequest rideRequest)
    {
        rideRequest.reject();
        rideRequestRepository.save(rideRequest);
    }

    public void acceptRideRequest(RideRequest rideRequest) throws NoSeatsAvailableException
    {
        Ride ride = getOrCreateRideForRequest(rideRequest);

        ride.takeSeat(rideRequest.getRequester(), rideRequest.getBoardingAt(), rideRequest.getGetOffAt());
        rideRequest.accept();

        rideRepository.save(ride);
    }

    private Ride getOrCreateRideForRequest(RideRequest rideRequest)
    {
        Ride ride ;
        Optional<Ride> maybeRide = getRideOfDriverSuitableForRideRequest(rideRequest);
        if (maybeRide.isPresent())
        {
            ride = maybeRide.get();
        } else {
            ride = createRideForRideRequest(rideRequest);
        }
        return ride;
    }

    private Ride createRideForRideRequest(RideRequest rideRequest)
    {
        Ride ride = Ride.newFromRideRequest(rideRequest);
        ride.setOilPrice( this.systemSettings.getOilPrice() );
        return ride;
    }

    private Optional<Ride> getRideOfDriverSuitableForRideRequest(RideRequest rideRequest)
    {
        return Optional.ofNullable( rideRepository.getRideOfDriverSuitableForRideRequest(rideRequest) );
    }

}
