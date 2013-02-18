package userInterfaces;


import placeDetails.PlaceResult;
import drawingMap.MapRouteActivity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import experiment.android.easy.MapManager;
import experiment.android.easy.R;

public class PlaceDetails extends ListActivity{
	
	  private  String[] places ;
	  public static  PlaceResult results;
	  private String description;
	  private Bundle bundle;
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    bundle=getIntent().getExtras();
	    results=PlaceSearchInterMediater.result;
	    description=bundle.getString("Description");
	    setList();
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, places));

	  }
	  /*Creating option menu
		 * Currently this method not calling by system
		 */
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			Log.d("Yepppiiiii", "I am here");
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.map_menu, menu);
			return true;
		}
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    // Handle item selection
		    switch (item.getItemId()) {
		        case R.id.Map:
		        	showMapView();
		            return true;
		        case R.id.RouteDetails:
		        	showRoute();
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
		public void showRoute()
		{
			Intent intent=new Intent(this,RouteDetails.class);
			startActivity(intent);
		}
		public void showMapView()
		{
			String start=MapManager.point.getLatitudeE6()/1E6+","+MapManager.point.getLongitudeE6()/1E6;
			String end=results.geometry.location.latitude+","+results.geometry.location.longlitude;
			Intent intent=new Intent(this,MapRouteActivity.class);
			intent.putExtra("start",start );
			intent.putExtra("end",end);
			startActivity(intent);
		}
	  public void setList()
	  {
		  places=new String[]{"Name:","Address:","Rating:","International Phone No:","Formatted Phone No:","Website:","Vincity:","Description:"};
		  if(!(results.name==null))
		  {
		  places[0]=places[0]+results.name;
		  }
		  else
		  {
			  places[0]=places[0]+"Not Available";
		  }
		  
		  if(!(results.formatted_address==null))
		  {
			  places[1]=places[1]+results.formatted_address;
		  }
		  else
		  {
			  places[1]=places[1]+"Not Available";
		  }
		  
		  if(!(results.rating==null))
		  {
			  places[2]=places[2]+results.rating;
		  }
		  else
		  {
			  places[2]=places[2]+"Not Available";
		  }
		  
		  if(!(results.international_phone_number==null))
		  {
			  places[3]=places[3]+results.international_phone_number;
			  
		  }
		  else
		  {
			  places[3]=places[3]+"Not Available";
		  }
		  
		  if(!(results.formatted_phone_number==null))
		  {
			  places[4]=places[4]+results.formatted_phone_number;
			 
		  }
		  else
		  {
			  places[4]=places[4]+"Not Available";
		  }
		  
		  if(!(results.website==null))
		  {
			  places[5]=places[5]+results.website;
			  
		  }
		  else
		  {
			  places[5]=places[5]+"Not Available";
		  }
		  
		  if(!(results.vicinity==null))
		  {
			  places[6]=places[6]+results.vicinity;
		  }
		  else
		  {
			  places[6]=places[6]+"Not Available";
		  } 
		  places[7]=places[7]+description;
	  }
}

