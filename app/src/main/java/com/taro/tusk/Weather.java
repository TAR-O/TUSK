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
    private static final String TAG_NAME = "name";

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

        // Get listview
        //ListView lv = getListView();

        // on seleting single product
        // launching Edit Product Screen
        /*
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String pid = ((TextView) view.findViewById(R.id.city)).getText()
                        .toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        EditProductActivity.class);
                // sending pid to next activity
                in.putExtra(TAG_CITY, pid);

                // starting new activity and expecting some response back
                startActivityForResult(in, 100);
            }
        });
        */

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
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_data, "GET", params);

            // Check your log cat for JSON reponse

            Log.d("All DATA: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
                    data = json.getJSONArray(TAG_DATA);

                    // looping through All Products
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject c = data.getJSONObject(i);

                        // Storing each json item in variable
                        String city = c.getString(TAG_CITY);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String,String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_CITY, city);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        dataList.add(map);

                    }
                } else {

                    // no products found
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
                     * Updating parsed JSON data into ListView
                     * */

                    /*ListAdapter adapter = new SimpleAdapter(
                            Weather.this, dataList,
                            R.layout.list_item, new String[]{TAG_CITY,
                            TAG_NAME},
                            new int[]{R.id.city, R.id.name});
                    // updating listview
                    setListAdapter(adapter);*/


                    HashMap<String, String> map = new HashMap<String,String>();
                    text1 = (TextView) findViewById(R.id.text1);
                    int sizeNum = dataList.size();
                    String num = Integer.toString(dataList.size());
                    text1.setText(num);

                    if (sizeNum > 0){
                        map = dataList.get(1);
                        text1.setText(map.get("name"));
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
