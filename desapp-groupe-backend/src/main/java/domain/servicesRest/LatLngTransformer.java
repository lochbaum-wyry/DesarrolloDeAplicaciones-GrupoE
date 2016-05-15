package domain.servicesRest;

import domain.LatLng;

/**
 * Created by prospero on 5/14/16.
 */
public class LatLngTransformer extends Transformer<LatLng> {

    LatLng latLng ;
    public LatLngTransformer(String string) throws Exception {
        super(string);
        String[] splittedStr = valueStr.split(",");
        this.latLng = new LatLng(Double.parseDouble(splittedStr[0]),Double.parseDouble(splittedStr[1]));
    }

    @Override
    public LatLng getValue() {
        return latLng;
    }
}
