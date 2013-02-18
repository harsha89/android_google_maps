package userInterfaces;
import experiment.android.easy.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;



public class RouteDetailsDisplay extends ListActivity{
	
	  private String[] route;
	  private  Bundle bundle;
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    bundle=getIntent().getExtras();
	    route=bundle.getStringArray("route");
	    setListAdapter(new ArrayAdapter<String>(this, R.layout.list_view, route));
	  } 
}

