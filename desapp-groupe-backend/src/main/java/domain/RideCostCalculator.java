package domain;


abstract public class RideCostCalculator
{
    protected Ride ride ;

    public RideCostCalculator()
    {
    }

    public RideCostCalculator(Ride ride)
    {
        this.ride = ride;
    }

    public Ride getRide()
    {
        return ride;
    }

    public void setRide(Ride ride)
    {
        this.ride = ride;
    }

    abstract public Float calculate();
}
