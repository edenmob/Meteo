package com.example.training.meteo;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements ForecastFragment.Callback{

    private boolean mTwoPane;
    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = Utility.getPreferredLocation(this);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.weather_detail_container) != null){
            mTwoPane = true;
            getSupportFragmentManager().beginTransaction().replace(R.id.weather_detail_container,new DetailFragment()).commit();
        }else{
            mTwoPane = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String location = Utility.getPreferredLocation( this );
        // update the location in our second pane using the fragment manager
        if (location != null && !location.equals(mLocation)) {
            ForecastFragment ff = (ForecastFragment)getFragmentManager().findFragmentById(R.id.fragment_forecast);
            if ( null != ff ) {
                ff.onLocationChanged();
            }
            mLocation = location;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(Uri detailUri) {
        if(mTwoPane){

            DetailFragment fragment = new DetailFragment();
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailFragment.DETAIL_URI, detailUri);
            //arguments.putString(Intent.EXTRA_TEXT,forecastData);
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction().replace(R.id.weather_detail_container,fragment).commit();

        }else{
            Intent intent = new Intent(this,DetailActivity.class);
            intent.setData(detailUri);
            //intent.putExtra(Intent.EXTRA_TEXT,forecastData);
            startActivity(intent);
        }
    }
}
