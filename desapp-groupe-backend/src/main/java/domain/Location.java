package domain;

import java.lang.*;

import static java.lang.StrictMath.abs;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Location extends Entity{
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
        return new EqualsBuilder()
                .append(this.getLatitude(), otherLoc.getLatitude())
                .append(this.getLongitude(), otherLoc.getLongitude())
                .isEquals();
    }


    @Override
    public int hashCode()
    {
        return new HashCodeBuilder()
                .append(this.getLatitude())
                .append(this.getLongitude())
                .toHashCode();
    }


    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
