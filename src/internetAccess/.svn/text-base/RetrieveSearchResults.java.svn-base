package internetAccess;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.gson.Gson;

import experiment.android.easy.MapManager;

import placeDetails.PlaceResult;
import placeDetails.SearchPlaceResponse;
import places.Result;
import places.SearchResponse;

public class RetrieveSearchResults {
private String radius;
private String placeType;
private GeoPoint geopoint=MapManager.point;
private String reference;
private Gson gson;
/*
 * Overloading methods for get user preferred locationPlace
 */
public List<Result> getResultList(String locationType,String radius)//Call when user prefer to search by locationType and radius
{
	String url;
	setPlaceType(locationType);
	setRadius(radius);
	url="https://maps.googleapis.com/maps/api/place/search/json?location="+geopoint.getLatitudeE6()/1E6+","+geopoint.getLongitudeE6()/1E6+"&radius="+getRadius()+"&name="+getPlaceType()+"&sensor=false&key=AIzaSyAMchGJxNoz80A2LUyYo43pHnITUoze4uE";
	List<Result> temp=getPlacesResults(search(getHttpPostInstance(url)));
	return temp;
}
public PlaceResult getPlaceDetailt(String reference)
{
	String url;
	PlaceResult place;
	setReference(reference);
	url="https://maps.googleapis.com/maps/api/place/details/json?reference="+getReference()+"&sensor=true&key=AIzaSyAMchGJxNoz80A2LUyYo43pHnITUoze4uE";
	place=getPlaceResult(search(getHttpPostInstance(url)));
	return place;
}
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}
private HttpPost getHttpPostInstance(String url)
{
	return new HttpPost(url);
}
/*
 * getSearch results according to the user preference from google apis
 */
public String search(HttpPost post)
{
	String result = "";
	// http post for get json output from google api's
	try {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = post;
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity);
			return result;
		}
	} catch (Exception e) {
		Log.e("log_tag", "Error in http connection " + e.toString());
	}
	
	return null;
}
public List<Result> getPlacesResults(String details)
{
	gson=new Gson();
	List<Result> temp = null;
	SearchResponse response=null;
	response= gson.fromJson(details,SearchResponse.class);//convert json output to java objects
	temp = response.results;
	return temp;
}
public PlaceResult getPlaceResult(String details)
{
	gson=new Gson();
	PlaceResult temp = null;
	SearchPlaceResponse response=null;
	response= gson.fromJson(details,SearchPlaceResponse.class);//convert json output to java objects
	temp = response.result;
	
	return temp;
}
public String getRadius() {
	return radius;
}
public void setRadius(String radius) {
	this.radius = radius;
}
public String getPlaceType() {
	return placeType;
}
public void setPlaceType(String placeType) {
	this.placeType = placeType;
}
}
