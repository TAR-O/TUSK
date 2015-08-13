package com.taro.tusk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class Weather extends ActionBarActivity {

    private TextView text1;
    private TextView text2;


    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser2 jParser = new JSONParser2();

    ArrayList<HashMap<String, String>> dataList;

    // url to get all products list
    private static String url_all_data = "http://tusk.website/get_data.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DATA = "data";
    private static final String TAG_CITY = "city";
    private static final String TAG_TEMP = "temp";
    
    private static final String TAG_APPARANTTEMP = "apparantTemp";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_ICON = "icon";
    private static final String TAG_TIME = "time";
    private static final String TAG_PRESSURE = "pressure";
    private static final String TAG_DEWPOINT = "dewPoint";
    private static final String TAG_HUMIDITY = "humidity";
    private static final String TAG_WINDSPEED = "windSpeed";
    private static final String TAG_WINDBEARING = "windBearing";
    private static final String TAG_PRECIPTYPE = "precipType";
    private static final String TAG_PRECIPPROB = "precipProb";
    private static final String TAG_CLOUDCOVER = "cloudCover";



    // products JSONArray
    JSONArray data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Hashmap for ListView
        dataList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllData().execute();

    }

    /*
    // Response from Edit Product Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // if result code 100
        if (resultCode == 100) {
            // if result code 100 is received
            // means user edited/deleted product
            // reload this screen again
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }
    */

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllData extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Weather.this);
            pDialog.setMessage("Loading data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All products from url
         * */
        protected String doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("city", "Toronto"));

            // getting product details by making HTTP request
            // Note that product details url will use GET request


            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_data, "GET", params);

            // Check your log cat for JSON reponse

           // Log.d("All DATA: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // data found
                    // Getting Array of data
                    data = json.getJSONArray(TAG_DATA);

                    // looping through All data
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String,String>();
                        
                        // adding each child node to HashMap key => value
                        map.put(TAG_CITY, c.optString(TAG_CITY));
                        map.put(TAG_TEMP, c.optString(TAG_TEMP));
                        map.put(TAG_APPARANTTEMP, c.optString(TAG_APPARANTTEMP));
                        map.put(TAG_SUMMARY, c.optString(TAG_SUMMARY));
                        map.put(TAG_ICON, c.optString(TAG_ICON));
                        map.put(TAG_TIME, c.optString(TAG_TIME));
                        map.put(TAG_PRESSURE, c.optString(TAG_PRESSURE));
                        map.put(TAG_DEWPOINT, c.optString(TAG_DEWPOINT));
                        map.put(TAG_HUMIDITY, c.optString(TAG_HUMIDITY));
                        map.put(TAG_WINDSPEED, c.optString(TAG_WINDSPEED));
                        map.put(TAG_WINDBEARING, c.optString(TAG_WINDBEARING));
                        map.put(TAG_PRECIPTYPE, c.optString(TAG_PRECIPTYPE));
                        map.put(TAG_PRECIPPROB, c.optString(TAG_PRECIPPROB));
                        map.put(TAG_CLOUDCOVER, c.optString(TAG_CLOUDCOVER));

                        // adding HashList to ArrayList
                        dataList.add(map);

                    }
                } else {

                    // no data found
                    // Launch Add New product Activity
                    /*
                    Intent i = new Intent(getApplicationContext(),
                            NewProductActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    */
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data to textviews
                     * */
                    HashMap<String, String> map = new HashMap<String,String>();
                    text1 = (TextView) findViewById(R.id.text1);
                    text2 = (TextView) findViewById(R.id.text2);
                    int sizeNum = dataList.size();
                    String num = Integer.toString(dataList.size());
                    text1.setText(num);

                    if (sizeNum > 0){
                        map = dataList.get(0);
                        text1.setText(map.get(TAG_CITY));
                        text2.setText(map.get(TAG_TEMP));
                    }
                }
            });

        }

    }


    public void toDifferentActivity (View v){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
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
}
