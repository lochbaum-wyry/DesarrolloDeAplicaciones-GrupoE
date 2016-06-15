package domain.servicesRest.daos;

import org.joda.time.DateTime;


public class RideRequestDTO {

    protected int requesterId;
    protected int driverId;
    protected int routeId;
    protected DateTime date;
    protected int boardingAtId;
    protected int getOffAtId;
    private int scheduleId;

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public int getBoardingAtId() {
        return boardingAtId;
    }

    public void setBoardingAtId(int boardingAtId) {
        this.boardingAtId = boardingAtId;
    }

    public int getGetOffAtId() {
        return getOffAtId;
    }

    public void setGetOffAtId(int getOffAtId) {
        this.getOffAtId = getOffAtId;
    }

    public void setScheduleId(int scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public int getScheduleId()
    {
        return scheduleId;
    }
}
