package placeDetails;
/*
 * Class for store LocationPlace results which are coming from json output which received from the google apis
 */
import com.google.gson.annotations.SerializedName;
public class LocationPlace {
@SerializedName("lat")
    public double latitude;

    @SerializedName("lng")
    public double longlitude;
}