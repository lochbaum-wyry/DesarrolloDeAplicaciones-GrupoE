package domain.services;


import domain.*;
import domain.repositories.RideRepository;
import domain.repositories.RideRequestRepository;
import domain.exceptions.NoSeatsAvailableException;
import domain.repositories.UserRepository;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RideService extends GenericService<Ride>
{
    private SystemSettings systemSettings;
    private RideRepository rideRepository ;
    private UserRepository userRepository;

    public RideRequestRepository getRideRequestRepository() {
        return rideRequestRepository;
    }

    public RideRepository getRideRepository() {
        return rideRepository;
    }

    private RideRequestRepository rideRequestRepository;

    public RideService(){
    }

    public RideService(RideRepository rideRepository, RideRequestRepository rideRequestRepository,UserRepository userRepository)
    {
        this.systemSettings = new SystemSettings();
        this.rideRepository = rideRepository ;
        this.rideRequestRepository = rideRequestRepository;
        this.userRepository = userRepository;
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
        rideRequestRepository.update(rideRequest);
    }

    @Transactional
    public Ride acceptRideRequest(RideRequest rideRequest) throws NoSeatsAvailableException
    {
        Ride ride = getOrCreateRideForRequest(rideRequest);

        RoutePoint boardingAt = rideRequest.getBoardingAt();
        RoutePoint getOffAt = rideRequest.getGetOffAt();

        ride.validateSeatAvailableInSection(boardingAt, getOffAt);
        ride.takeSeat(rideRequest.getRequester(), boardingAt, getOffAt);

        rideRequest.accept();
        rideRequestRepository.update(rideRequest);

        rideRepository.saveOrUpdate(ride);
//        rideRequestRepository.delete(rideRequest);

        return ride;
    }


    @Transactional
    public void cancelRideRequest(int rideRequestId)
    {
        if (rideRequestRepository.exists(rideRequestId))
            rideRequestRepository.deleteById(rideRequestId);
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
        User user = userRepository.findById(userId);
        return rideRepository.getRidesAwaingRates(user);
        //return rideRepository.findAll();
    }

    @Transactional
    public Ride getRateablesFrom(int rideId, int userId) {
        //falta hacer los filtros bien
        return rideRepository.findById(rideId);
    }

    @Transactional
    public List<Ride> getFutureRides(int userId) {
        User user = userRepository.findById(userId);
        return rideRepository.getFutureRides(user);
    }

    public void setRideRepository(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public void setRideRequestRepository(RideRequestRepository rideRequestRepository) {
        this.rideRequestRepository = rideRequestRepository;
    }

    public SystemSettings getSystemSettings() {
        return systemSettings;
    }

    public void setSystemSettings(SystemSettings systemSettings) {
        this.systemSettings = systemSettings;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Ride> awaitingRidesAsDriver(int userId) {
        User user = userRepository.findById(userId);
        return rideRepository.awaitingRidesAsDriver(user);
    }

    @Transactional
    public List<Ride> awaitingRidesAsPassenger(int userId) {
        User user = userRepository.findById(userId);
        return rideRepository.awaitingRidesAsPassenger(user);
    }
}
