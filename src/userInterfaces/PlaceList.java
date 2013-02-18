package userInterfaces;


import java.util.List;

import places.Result;
import experiment.android.easy.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;



public class PlaceList extends ListActivity{
	
	  private static final String[] Places = new String[] {
		    "Hospital", "Hotel", "Retaurant", "Police", "PostOffice",
		    "School", "University", "Advance Search"};
	  public static  List<Result> results;
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, Places));

	    ListView lv = getListView();
	    lv.setTextFilterEnabled(true);
	    lv.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	            int position, long id) {
	          // When clicked, show a toast with the TextView text
	          Toast.makeText(getApplicationContext(), ((TextView) view).getText(),Toast.LENGTH_SHORT).show();
	          if(((TextView) view).getText().toString().equalsIgnoreCase("Advance Search"))
	          {
	        	  callAdvanceSearch();
	          }
	          else
	          {
	      		showSearchList(((TextView) view).getText().toString());
	          }
	        }
	      });
	    
	  }
	  public void showSearchList(String name)//Navigate search list interface
	  {
		    Intent intent=new Intent(this,Intermediater.class);
		    intent.putExtra("locationPlace", name);
		    intent.putExtra("radius", "5000");
			startActivity(intent);
	  }
	  
	  private void callAdvanceSearch()
	  {
		  Intent myIntent = new Intent(this, AdvancedSearch.class);// creating a intent for start new activity
		  startActivity(myIntent);// calling new activity
	  }	
}

