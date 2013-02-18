package userInterfaces;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.android.maps.GeoPoint;

import drawingMap.Road;
import drawingMap.RoadProvider;
import internetAccess.RetrieveSearchResults;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import experiment.android.easy.MapManager;
import experiment.android.easy.R;
import placeDetails.PlaceResult;
public class  PlaceSearchInterMediater extends Activity implements Runnable {
	public static PlaceResult result;
	private ProgressDialog progDailog;
	private Bundle extra;
	private double toLat;
	private double toLon;
	private String reference;
	private GeoPoint point;
	private Road mRoad;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		extra= getIntent().getExtras(); 
		reference=extra.getString("reference");
		toLat=extra.getDouble("toLat");
	    toLon=extra.getDouble("toLon");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intermediator);
		point=MapManager.point;
		startSearch();
	}
	public void startSearch()
	{
		 progDailog = ProgressDialog.show(PlaceSearchInterMediater.this,"", "Please wait...",true);
                 new Thread() {
                 public void run() {
                	RetrieveSearchResults searchResult=new RetrieveSearchResults();
         			result=searchResult.getPlaceDetailt(reference);
         			 String url = RoadProvider.getUrl(point.getLatitudeE6()/1E6, point.getLongitudeE6()/1E6, toLat, toLon); //find the URL
         			 InputStream is = getConnection(url);
         			  mRoad = RoadProvider.getRoute(is);
         			if(result==null)
         			{
         				
         			}
         			else
         			{
         			progDailog.dismiss(); 
         			showList();
         			}
         			}
        }.start();
	}
	  private InputStream getConnection(String url) {
          InputStream is = null;
          try {
                  URLConnection conn = new URL(url).openConnection();
                  is = conn.getInputStream();
          } catch (MalformedURLException e) {
                  e.printStackTrace();
          } catch (IOException e) {
                  e.printStackTrace();
          }
          return is;
  }
	public void showList()
	{
		 Intent intent=new Intent(this,PlaceDetails.class);
		 intent.putExtra("Description", mRoad.mDescription);
		 startActivity(intent);
		 this.finish();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}


