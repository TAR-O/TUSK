package com.taro.tusk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.taro.tusk.Tools.WeatherFragmentPagerAdapter;
import com.taro.tusk.Tools.setLocation;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Tina on 15-08-29.
 */
public class Weather extends ActionBarActivity {

    private Menu mymenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);
        setContentView(R.layout.activity_weather);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new WeatherFragmentPagerAdapter(getSupportFragmentManager(),
                Weather.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        mymenu = menu;
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


       switch (item.getItemId()) {
            case R.id.action_refresh:
                finish();
                startActivity(getIntent());
                return true;
            case R.id.action_setLocation:
                Intent intent2 = new Intent(this, setLocation.class);
                startActivity(intent2);
                //startActivityForResult(intent2, 1);
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
}