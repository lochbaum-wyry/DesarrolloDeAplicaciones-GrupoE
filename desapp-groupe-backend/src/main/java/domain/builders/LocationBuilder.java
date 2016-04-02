package domain.builders;

import domain.Location;

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

    public Location build() {
        return new Location(this.longitude, this.latitude);
    }
}
