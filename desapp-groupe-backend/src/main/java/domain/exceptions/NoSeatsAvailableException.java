package domain.exceptions;

import domain.RoutePoint;

public class NoSeatsAvailableException extends SubiQueTeLlevoException
{
    private RoutePoint boardingAt;
    private RoutePoint getOffAt;

    public NoSeatsAvailableException(RoutePoint boardingAt, RoutePoint getOffAt)
    {
        super("no_seats_available");
        this.boardingAt = boardingAt ;
        this.getOffAt = getOffAt ;
    }


}
