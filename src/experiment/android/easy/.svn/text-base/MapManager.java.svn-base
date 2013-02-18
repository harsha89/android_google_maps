package experiment.android.easy;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import userInterfaces.PlaceList;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MapManager extends MapActivity {
	/** Called when the activity is first created. */
	private MapView mapView;
	private MapController mapController;
	public static GeoPoint point;
	private MapItemizedOverlay itemizedoverlay;
	private LocationManager locationManager;
	private List<Overlay> mapOverlays;
	private GPSLocationListener locationListener;
	/**
	 * MapActivity calls onCreate method
	*/
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);//Set view as view define in main.xml file
		point = new GeoPoint(6901547, 79957466);//initialize
		DisPlayMap();//Call function for display map
		GPSService();//Get GPS service
		viewLocation();//Viewing locationPlace by flag
	}
	/**
	 * Navigate to search list interface
	 */
	private void showPlaceSearchList()
	{
		Intent myIntent = new Intent(this, PlaceList.class);// creating a intent for start new activity
		startActivity(myIntent);// calling new activity
	}
	/**
	 * Creating option menu
	 * Currently this method not calling by system
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("Yepppiiiii", "I am here");
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	/**
	 * Call when selecting a item in option menu
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.myLocation:
	        	showAddress();
	            return true;
	        case R.id.Search:
	        	showPlaceSearchList();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	/**
	 * Display the map in android interface
	 */
	@SuppressWarnings("deprecation")
	public void DisPlayMap() {
		
		mapView = (MapView) findViewById(R.id.mapview);//View map
		mapController = mapView.getController();
		mapView.setSatellite(false);//Disabling satellite view
		mapView.setStreetView(true);
		mapView.setBuiltInZoomControls(true);//Enable zooming controls to the map
	}
	/**
	 * Dispaly the current locationPlace
	 */
	public void viewLocation() {
		GeoPoint gpsPoint=point;
		mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.red);
		itemizedoverlay = new MapItemizedOverlay(drawable);
		showAddress();
		OverlayItem overlayitem = new OverlayItem(gpsPoint, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
		mapController.animateTo(gpsPoint);
		mapController.setZoom(15);
		showAddress();
		
	}
	/**
	 * Dispaly the current locationPlace address in android interface
	 */
	public void showAddress()
	{
		String address = "You Are In :" + ConvertPointToLocation(point);
		Toast.makeText(getBaseContext(), address, Toast.LENGTH_SHORT).show();
	}
	/**
	 * Starting GPS Service
	 */
	public void GPSService() {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new GPSLocationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * This function return the user address according user locationPlace
	 */
	public String ConvertPointToLocation(GeoPoint point) {
		String address = "";
		String s = "";
		String country="";
		Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
		
		try {
			List<Address> addresses = geoCoder.getFromLocation(
					point.getLatitudeE6() / 1E6, point.getLongitudeE6() / 1E6,
					1);
			if(addresses.get(0).getSubLocality()!=null)
			{
			s=addresses.get(0).getSubLocality()+",";
			}
			country=addresses.get(0).getCountryName();
			if (addresses.size() > 0) {
				for (int index = 1; index < addresses.get(0)
						.getMaxAddressLineIndex(); index++)
					address += addresses.get(0).getAddressLine(index) + ", ";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s+address+ country;
	}
	/**
	 * Class for access GPS service by communicating android internal services which are already built into the system
	 */
	private class GPSLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				
				point = new GeoPoint((int) (location.getLatitude() *
				1E6), (int) (location.getLongitude() * 1E6));
				OverlayItem overlayitem1 = new OverlayItem(point,"", "");
				itemizedoverlay.addOverlay(overlayitem1);
				mapOverlays.add(itemizedoverlay);
				mapController.animateTo(point);
				mapController.setZoom(15);
				mapView.invalidate();

			}
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}
	}
	
}