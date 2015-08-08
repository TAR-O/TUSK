package com.taro.tusk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toDifferentActivity (View v){
        Intent newScreen;
        switch (v.getId()) {
            case R.id.camera_activity: newScreen =
                    new Intent(getApplicationContext(),Camera.class);
                break;
            case R.id.closet_activity: newScreen =
                    new Intent(getApplicationContext(),Closet.class);
                break;
            case R.id.weather_activity: newScreen =
                    new Intent(getApplicationContext(),Weather.class);
                break;
            default: newScreen = new Intent(getApplicationContext(),MainActivity.class);
                break;
        }
        startActivity(newScreen);
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
}
