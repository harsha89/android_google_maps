package places;
/*
 * Class for store attribute results which are coming from json output which received from the google apis
 */
import com.google.gson.annotations.SerializedName;

public class Result {
    
    
    public Geometry geometry;
    
    @SerializedName("icon")
    public String iconUrl;
    
    @SerializedName("id")
    public String ID;
    
    @SerializedName("name")
    public String nameOfPlace;
    
    @SerializedName("rating")
    public double rating;

    @SerializedName("reference")
    public String reference;
       
    @SerializedName("types")
    public String[] types;

    @SerializedName("vincity")
    public String vincity;
   
}
