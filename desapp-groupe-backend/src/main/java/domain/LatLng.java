package domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LatLng
{
    private final Double longitude;
    private final Double latitude;

    public LatLng(Double latitude, Double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!obj.getClass().isAssignableFrom(getClass()))
            return false;

        LatLng otherPost = (LatLng) obj;

        return new EqualsBuilder()
                .append(this.getLongitude(), otherPost.getLongitude())
                .append(this.getLatitude(), otherPost.getLatitude())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.getLatitude())
                .append(this.getLongitude())
                .toHashCode();
    }

}
