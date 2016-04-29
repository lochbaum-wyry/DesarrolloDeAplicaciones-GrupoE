package domain.ride_service;


import domain.*;
import domain.Repositories.RideRepository;
import domain.Repositories.RideRequestRepository;
import domain.exceptions.NoSeatsAvailableException;
import org.hibernate.Query;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RideService
{
//
//    private List<Ride> rides;
//    private List<RideRequest> rideRequests;
//    private List<RideRequest> requestedRides;

    private RideRepository rideRepository ;
    private RideRequestRepository rideRequestRepository;


    public RideService(RideRepository rideRepository)
    {
        this.rideRepository = rideRepository ;
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
        Ride ride = getOrAddRideForRequest(rideRequest);
        ride.takeSeat(rideRequest.getRequester(), rideRequest.getBoardingAt(), rideRequest.getGetOffAt());
        rideRequest.accept();
        rideRepository.save(ride);
    }

    private Ride getOrAddRideForRequest(RideRequest rideRequest)
    {
        Ride ride ;
        Optional<Ride> maybeRide = getRideSuitableForRideRequest(rideRequest);
        if (maybeRide.isPresent())
        {
            ride = maybeRide.get();
        } else {
            ride = createRideForRideRequest(rideRequest);
            addRide(ride);
        }
        return ride;
    }

    private Ride createRideForRideRequest(RideRequest rideRequest)
    {
        Ride ride = Ride.fromRideRequest(this,rideRequest);
        ride.setOilPrice( this.system.getSettings().getOilPrice() );
        return ride;
    }

    private Optional<Ride> getRideSuitableForRideRequest(RideRequest rideRequest)
    {
        rideRepository.getRideSuitableForRideRequest(rideRequest);
    }

    public Boolean suitsRideRequest(RideRequest rideRequest)
    {
        return  this.route == rideRequest.getRoute() &&
                this.date == rideRequest.getDate()
                ;
    }


    public void addRideRequest(RideRequest rideRequest)
    {
        this.rideRequests.add(rideRequest);
    }

    public void addRequestedRide(RideRequest rideRequest)
    {
        this.requestedRides.add(rideRequest);
    }

    public void removeRideRequest(RideRequest rideRequest)
    {
        this.rideRequests.remove(rideRequest);
    }

    public void addRide(Ride ride)
    {
        rides.add(ride);
    }


    public List<Ride> getRidesAsDriver() {
        return this.getRides().stream()
                .filter(ride -> ride.isDriver(this))
                .collect(Collectors.toList());
    }

    public Integer getRidesCount() {
        return this.getRides().size();
    }

    public List<RideRequest> getRideRequests()
    {
        return rideRequests;
    }


}
