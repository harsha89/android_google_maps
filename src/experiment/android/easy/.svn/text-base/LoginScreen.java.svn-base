package experiment.android.easy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class LoginScreen extends Activity {
	/**
	 *  
	 *  main method call 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);// set interface layout as login_screen layout
		final Button button = (Button) findViewById(R.id.initialize);// get button instance
		button.setOnClickListener(new View.OnClickListener() {// action listener to listen button action in login interface
			public void onClick(View v) {
				start();
			}
		});

	}
	/**Creating option menu
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
	 * Navigation to map view from login class
	 */
	public void start() {
		Intent myIntent = new Intent(this, MapManager.class);// creating a intent for start new activity
		startActivity(myIntent);// calling new activity
		this.finish();// terminate current activity
	}

}
