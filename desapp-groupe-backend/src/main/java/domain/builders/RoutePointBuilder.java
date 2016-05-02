package domain.builders;

import domain.RoutePoint;

public class RoutePointBuilder {
    private Double longitude;
    private Double latitude;

    public static RoutePointBuilder aRoutePoint() {
        return new RoutePointBuilder();
    }


    public RoutePointBuilder withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public RoutePointBuilder withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public RoutePoint build() {
        return new RoutePoint(this.longitude, this.latitude);
    }
}
