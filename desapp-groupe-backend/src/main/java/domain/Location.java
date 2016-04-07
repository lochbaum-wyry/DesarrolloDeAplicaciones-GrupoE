package domain;

import java.lang.*;

import static java.lang.StrictMath.abs;

public class Location {

    private Double longitude;
    private Double latitude;

    public Location(Double longitude,Double latitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean isNear(Location location, Float radioCloseness)
    {
        return this.pointIsInCircle(location.getLongitude(),location.getLatitude(), radioCloseness);
    }

    private Boolean pointIsInCircle(Double longitude, Double latitude, Float radioCloseness)
    {
        return (Math.pow(longitude-this.getLongitude(),2) + Math.pow(latitude-this.getLatitude() ,2)) < Math.pow(radioCloseness,2);
    }

    @Override
    public boolean equals(Object other)
    {
        Location otherLoc = (Location) other;
//        return  other instanceof domain.Location &&
//                otherLoc.getLatitude() == latitude &&
//                otherLoc.getLongitude() == longitude;
        return otherLoc.hashCode() == hashCode();
    }


    @Override
    public int hashCode()
    {
        int hash = 111 ;
        hash += latitude.hashCode();
        hash += longitude.hashCode();
        return hash;
    }
}
