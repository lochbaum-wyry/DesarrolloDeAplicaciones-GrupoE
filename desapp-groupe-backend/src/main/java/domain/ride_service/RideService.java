package domain.ride_service;


import domain.Repositories.RideRepository;
import domain.Repositories.UserRepository;

public class RideService
{
    private RideRepository rideRepository ;

    public RideService(RideRepository rideRepository)
    {
        this.rideRepository = rideRepository ;
    }
}
