package domain.exceptions;

import domain.Location;

public class NoSeatsAvailableException extends SubiQueTeLlevoException
{
    private Location boardingAt;
    private Location getOffAt;

    public NoSeatsAvailableException(Location boardingAt, Location getOffAt)
    {
        this.boardingAt = boardingAt ;
        this.getOffAt = getOffAt ;
    }
}
