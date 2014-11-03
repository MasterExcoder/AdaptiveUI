package at.hammer.adaptiveui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import at.hammer.adaptiveui.R;
import at.hammer.adaptiveui.SettingsActivity;


public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {
	SharedPreferences sharedPref;
	private String user;
	
	//define xml variables
	TextView userTextView;
	LinearLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(this);
        
        user = sharedPref.getString("users","red");
        
        init();
        setBackground();
        
        userTextView.setText("Logged in as: " + user);
	}
	
	/**
	 * Initializes defined (xml) variables for use in code
	 */
	public void init() {
		userTextView = (TextView) findViewById(R.id.userTextView);
		layout = (LinearLayout) findViewById(R.id.linearLayout1);
	}
	
	/**
	 * Changes the background of the activity user dependent
	 */
	public void setBackground() {
        if(user.equals(getResources().getString(R.string.red))){
            layout.setBackgroundColor(Color.RED);
        }
        if(user.equals(getResources().getString(R.string.green))){
            layout.setBackgroundColor(Color.GREEN);
        }
        if(user.equals(getResources().getString(R.string.blue))){
            layout.setBackgroundColor(Color.BLUE);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if(item.getItemId() == R.id.action_settings){
            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
        }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		user = sharedPref.getString("users", getResources().getString(R.string.red));
	}
}
