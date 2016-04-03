package domain.exceptions;

import domain.Location;

/**
 * Created by prospero on 4/3/16.
 */
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
