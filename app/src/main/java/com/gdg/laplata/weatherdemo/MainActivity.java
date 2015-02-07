package com.gdg.laplata.weatherdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    TextView textName;
    TextView textTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName = (TextView) findViewById(R.id.textName);
        textTemp = (TextView) findViewById(R.id.textTemp);

    }

    @Override
    public void onResume() {
        super.onResume();
        // Obtain current location and send city / lat,lng / postal code
        new WeatherDataTask().execute("La%20Plata");
    }

    private void updateUI(WeatherData weatherData){
        if(weatherData != null){
            textName.setText(weatherData.name);
            textTemp.setText(weatherData.temp + " C");
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class WeatherDataTask extends AsyncTask<String, Void, WeatherData> {
        protected WeatherData doInBackground(String... value) {
            return WeatherManager.getWeatherData(value[0]);
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(WeatherData weatherData) {
            updateUI(weatherData);
        }
    }
}
