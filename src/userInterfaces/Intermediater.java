package userInterfaces;

import internetAccess.RetrieveSearchResults;

import java.util.List;

import experiment.android.easy.R;

import places.Result;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;



public class Intermediater extends Activity implements Runnable {
	public static List<Result> results;
	private ProgressDialog progDailog;
	private Bundle extra;
	private String searchLocation;
	private String radius;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		extra= getIntent().getExtras(); 
		searchLocation=extra.getString("locationPlace");
		radius=extra.getString("radius");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intermediator);
		startSearch();
		
	}
	public void startSearch()
	{
		 progDailog = ProgressDialog.show(Intermediater.this,"", "Please wait...",true);
                 new Thread() {
                 public void run() {
                	RetrieveSearchResults searchResult=new RetrieveSearchResults();
         			results=searchResult.getResultList(searchLocation,radius);
         			if(results.isEmpty())
         			{
         				//progDailog.dismiss();
         				//Toast.makeText(getApplicationContext(),"No results to display",Toast.LENGTH_SHORT).show();		
         			}
         			else
         			{
         			showList();
         			}
         			Log.d("this called","Call");
         			progDailog.dismiss(); 
         			}
                 
        }.start();
	}
	public void showList()
	{
		 Intent intent=new Intent(this,PlaceSearchResults.class);
		 startActivity(intent);
		 this.finish();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
