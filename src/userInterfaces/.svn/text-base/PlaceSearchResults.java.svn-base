package userInterfaces;

import java.util.List;

import places.Result;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import experiment.android.easy.R;

public class PlaceSearchResults extends ListActivity{
	  
	  private List<Result> results;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		results=Intermediater.results;
		String[] list=listToString( );
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, list));
	    ListView lv = getListView();
	    lv.setTextFilterEnabled(true);
	    lv.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	            int position, long id) {
	          // When clicked, show a toast with the TextView text
	          Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_SHORT).show();
	          int locationNo=getReferenceByName(((TextView) view).getText().toString());
	          performPlaceSearch(results.get(locationNo).reference,results.get(locationNo).geometry.location.latitude,results.get(locationNo).geometry.location.longlitude);
	        }
	      });
	}
	public String[] listToString( )
	{
	
		String[] string=new String[results.size()];
		for(int i=0;i<results.size();i++)
		{
			
			string[i]=results.get(i).nameOfPlace;
		}
		return string;
	}
	
	public int getReferenceByName(String name)
	{
		for(int i=0;i<results.size();i++)
		{
			if(results.get(i).nameOfPlace.equalsIgnoreCase(name))
				return i;
		}
		return 0;
	}
	public void performPlaceSearch(String reference,double toLat,double toLon)
	{
		Intent intent=new Intent(this,PlaceSearchInterMediater.class);
	    intent.putExtra("reference", reference);
	    intent.putExtra("toLat", toLat);
	    intent.putExtra("toLon", toLon);
	    Log.d("THiszzz",reference);
		startActivity(intent);
	}
}
