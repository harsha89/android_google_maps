package userInterfaces;



import experiment.android.easy.R;
import Validators.IsStringANumber;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdvancedSearch extends Activity {
	private String locationType;
	private String radius;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		final IsStringANumber check=new IsStringANumber();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advanced_search);// set interface layout as login_screen layout
		final Button button = (Button) findViewById(R.id.advanceSearch);// get button instance
		final EditText edittext1 = (EditText) findViewById(R.id.Place);
		final EditText edittext2 = (EditText) findViewById(R.id.radius);
		button.setOnClickListener(new View.OnClickListener() {// action listener to listen button action in login interface
			public void onClick(View v) {
				locationType=edittext1.getText().toString();
				radius=edittext2.getText().toString();
				if(!check.isNumeric(radius))
				{
					Log.d("Yes","Hello");
					Toast.makeText(getBaseContext(), "Radius is not numeric", Toast.LENGTH_SHORT).show();
				}
				else
				{
		        
					showSearchList(locationType,radius);
				}
			}
		});
	}
	 public void showSearchList(String location,String radius)//Navigate search results interface
	  {
		    Intent intent=new Intent(this,Intermediater.class);
		    intent.putExtra("locationPlace", location);
		    intent.putExtra("radius", radius);
			startActivity(intent);
	  }


}