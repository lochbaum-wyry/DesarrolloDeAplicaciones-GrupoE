package domain.services;


import domain.*;
import domain.repositories.RideRepository;
import domain.repositories.RideRequestRepository;
import domain.exceptions.NoSeatsAvailableException;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


public class RideService extends GenericService<Ride>
{
    private SystemSettings systemSettings;
    private RideRepository rideRepository ;

    public RideRequestRepository getRideRequestRepository() {
        return rideRequestRepository;
    }

    public RideRepository getRideRepository() {
        return rideRepository;
    }

    private RideRequestRepository rideRequestRepository;

    public RideService(){
    }

    public RideService(RideRepository rideRepository, RideRequestRepository rideRequestRepository)
    {
        this.systemSettings = new SystemSettings();
        this.rideRepository = rideRepository ;
        this.rideRequestRepository = rideRequestRepository;
    }

    @Transactional
    public RideRequest requestRide(RideRequest rideRequest)
    {
        rideRequestRepository.saveOrUpdate(rideRequest);
        return rideRequestRepository.findById(rideRequest.getId());
    }



    @Transactional
    public RideRequest requestRide(User requester, User driver, DateTime date, Route route, RoutePoint boardingAt, RoutePoint getOffAt)
    {
        RideRequest rideRequest = new RideRequest(requester, driver, date, route, boardingAt, getOffAt);
        rideRequestRepository.saveOrUpdate(rideRequest);
        return rideRequestRepository.findById(rideRequest.getId());
    }

    @Transactional
    public void rejectRideRequest(RideRequest rideRequest)
    {
        rideRequest.reject();
        rideRequestRepository.save(rideRequest);
    }

    @Transactional
    public Ride acceptRideRequest(RideRequest rideRequest) throws NoSeatsAvailableException
    {
        Ride ride = getOrCreateRideForRequest(rideRequest);

        ride.validateSeatAvailableInSection(rideRequest.getBoardingAt(), rideRequest.getGetOffAt());

        ride.takeSeat(rideRequest.getRequester(), rideRequest.getBoardingAt(), rideRequest.getGetOffAt());
        rideRequest.accept();


        rideRepository.saveOrUpdate(ride);

        return ride;
    }


    @Transactional
    public void cancelRideRequest(int rideRequestId)
    {
        if (rideRequestRepository.exists(rideRequestId))
            rideRequestRepository.deleteById(rideRequestId);
    }

//    public List<Ride> getRidesOfUserAsDriver(User user)
//    {
//        return null;
//    }
//
//    public List<Ride> getRidesOfUserAsPassenger(User user)
//    {
//        return null;
//    }

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

    public Optional<Ride> getRideOfDriverSuitableForRideRequest(RideRequest rideRequest)
    {
        return Optional.ofNullable( rideRepository.getRideOfDriverSuitableForRideRequest(rideRequest) );
    }

    @Transactional
    public List<RideRequest> getPendingRequestsFor(User driver)
    {
        return rideRequestRepository.getPendingRequestsFor(driver);
    }

    @Transactional
    public List<RideRequest> getPendingRequestsBy(User requester)
    {
        return rideRequestRepository.getPendingRequestsBy(requester);
    }

    @Transactional
    public List<Ride> getRateablesRides(int userId) {
        //return rideRepository.getRidesAwaingRates(userId);
        return rideRepository.findAll();
    }

    @Transactional
    public Ride getRateablesFrom(int rideId, int userId) {
        //falta hacer los filtros bien
        return rideRepository.findById(rideId);
    }
}
