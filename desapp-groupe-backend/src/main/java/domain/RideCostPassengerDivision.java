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

    public Float calculate()
    {
        int totalNumberOfPassengers = ride.getNumberOfPassengers();
        return  ride.getTotalCost() / totalNumberOfPassengers;
    }
}
