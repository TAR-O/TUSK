package com.taro.tusk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;

import android.widget.TextView;

import com.taro.tusk.Tools.AsyncResponse;
import com.taro.tusk.Tools.webConn;


public class Weather extends ActionBarActivity implements AsyncResponse{

    private TextView text1;
    private TextView text2;
    private Menu mymenu;
    private String locResult;

    ArrayList<HashMap<String, String>> dataList;
    //webConn asyncTask =new webConn(Weather.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Hashmap for ListView
        dataList = new ArrayList<HashMap<String, String>>();

        webConn asyncTask = new webConn(Weather.this, locResult);
        asyncTask.delegate = this;

    }



    public void processFinish(ArrayList output){
        //this you will received result fired from async class of onPostExecute(result) method.
        dataList = output;
        runOnUiThread(new Runnable() {
            public void run() {

                HashMap<String, String> map = new HashMap<String, String>();
                text1 = (TextView) findViewById(R.id.text1);
                text2 = (TextView) findViewById(R.id.text2);
                int sizeNum = dataList.size();
                String num = Integer.toString(dataList.size());
                text1.setText(num);

                if (sizeNum > 0) {
                    map = dataList.get(0);
                    text1.setText("City: " + map.get("city"));
                    text2.setText("Temperature: " + map.get("temp"));
                }

            }
        });
    }


    public void toDifferentActivity (View v){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);

        mymenu = menu;
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
         //   return true;
        //}

        switch (item.getItemId()) {
            case R.id.action_refresh:
                finish();
                startActivity(getIntent());
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, settings.class);
               // startActivity(intent);

                startActivityForResult(intent, 1);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                locResult=data.getStringExtra("result");
                webConn asyncTask = new webConn(Weather.this, locResult);
                asyncTask.delegate = this;

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }


}
