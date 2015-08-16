package com.taro.tusk.Tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import com.taro.tusk.JSONParser2;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Tina on 15-08-14.
 */
//public class webConn extends {
  public  class webConn extends AsyncTask<String, String, String> {

    public AsyncResponse delegate=null;



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

    private Context context;


    // products JSONArray
    JSONArray data = null;


    public webConn (Context cxt){
        // Hashmap for ListView
        dataList = new ArrayList<HashMap<String, String>>();

        context = cxt;
        // Loading products in Background Thread
        this.execute();
    }

    /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
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

             Log.d("All DATA: ", json.toString());

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

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SystemClock.sleep(5000);
            return null;
        }



        /**
         * After completing background task Dismiss the progress dialog
         * **/
       // @Override
        protected void onPostExecute(String result) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            //result = dataList;
            //dataArray(dataList);
            delegate.processFinish(dataList);
        }


    }