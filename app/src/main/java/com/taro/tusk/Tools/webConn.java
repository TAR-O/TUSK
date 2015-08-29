package com.taro.tusk.Tools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Tina on 15-08-14.
 */

  public  class webConn extends AsyncTask<String, String, String> {

    public AsyncResponse delegate=null;

    // Progress Dialog
    private ProgressDialog pDialog;

    // Creating JSON Parser object
    JSONParser2 jParser = new JSONParser2();

    ArrayList<HashMap<String, String>> dataList;

    String[] weeklyList = new String[]{"city", "province", "menu_country", "tempW", "summaryW", "iconW", "dateW", "pressureW", "dewPointW", "humidityW", "windSpeedW", "windBearingW", "precipTypeW", "precipPropW", "cloudCoverW", "maxTempW", "minTempW", "sunRise", "sunSet", "day"};
    String[] currentList = new String[]{"city","temp","apparantTemp","summary","icon","time","pressure","dewPoint","humidity","windSpeed","windBearing","precipType","precipProb", "cloudCover"};
    int numOfData;

    // url to get all products list
    private static String url_all_data = "http://tusk.website/get_data.php";
    private static String url_weekly_data = "http://tusk.website/get_data_weekly.php";
    private static String url_temp;
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_DATA = "data";
    private static final String TAG_CITY = "city";
    private static final String TAG_TEMP = "temp";



    private Context context;
    private String locRec;
    private String dataType;

    // products JSONArray
    JSONArray data = null;
    JSONArray rows = null;


    public webConn (Activity cxt, String loc, String type){
        // Hashmap for ListView
        dataList = new ArrayList<HashMap<String, String>>();
        locRec = loc;
        context = cxt;
        dataType = type;
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

            if(locRec == null){
                locRec = "Toronto";
                params.add(new BasicNameValuePair("city", locRec));
            }else {
                params.add(new BasicNameValuePair("city", locRec));
            }

            // getting product details by making HTTP request
            // Note that data details url will use GET request
            // getting JSON string from URL

            String[] tempList = new String[14];

            if (dataType.equals("currently")){

                url_temp = url_all_data;
                tempList = currentList;
                numOfData = 1;
            }
            else{
                url_temp = url_weekly_data;
                tempList = weeklyList;
                numOfData = 8;
            }
                JSONObject json = jParser.makeHttpRequest(url_temp, "GET", params);


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
                    for (int i = 0; i < numOfData; i++) {

                        JSONObject c = data.getJSONObject(i);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value

                        for( int x=0; x<tempList.length;x++){
                            map.put(tempList[x], c.optString(tempList[x]));
                        }

                        // adding HashList to ArrayList
                        dataList.add(map);


                    }
                } else {
                    // no data found
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            SystemClock.sleep(2000);
            return null;
        }



        /**
         * After completing background task Dismiss the progress dialog
         * **/
       // @Override
        protected void onPostExecute(String result) {

            // dismiss the dialog after getting all products
            pDialog.dismiss();
            delegate.processFinish(dataList);
        }


    }