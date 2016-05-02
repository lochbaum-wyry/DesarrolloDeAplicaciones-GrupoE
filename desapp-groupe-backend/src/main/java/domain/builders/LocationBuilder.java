package domain.builders;

import domain.RoutePoint;

public class LocationBuilder {
    private Double longitude;
    private Double latitude;

    public static LocationBuilder aLocation() {
        return new LocationBuilder();
    }


    public LocationBuilder withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationBuilder withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public RoutePoint build() {
        return new RoutePoint(this.longitude, this.latitude);
    }
}
