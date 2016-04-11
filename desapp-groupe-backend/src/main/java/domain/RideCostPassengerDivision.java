package domain;


public class RideCostPassengerDivision extends RideCostCalculator
{

    public RideCostPassengerDivision()
    {
    }

    public RideCostPassengerDivision(Ride ride)
    {
        super(ride);
    }

    public Float calculateCostForPassenger(User passenger)
    {
        int totalNumberOfPassengers = ride.getNumberOfPassengers() + 1;
        return  ride.getTotalCost() / totalNumberOfPassengers;
    }
}
