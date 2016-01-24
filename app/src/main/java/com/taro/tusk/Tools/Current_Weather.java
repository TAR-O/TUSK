
package com.taro.tusk.Tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taro.tusk.R;
import java.util.ArrayList;
import java.util.HashMap;

// In this case, the fragment displays simple text based on the page
public class Current_Weather extends Fragment implements AsyncResponse {
    View rootView;
    String [] images =  {"clear-day","clear-night","rain","snow","sleet","wind","fog","cloudy","partly-cloudy-day","partly-cloudy-night"};
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private ImageView i1;
    private Menu mymenu;
    private String locResult;

    ArrayList<HashMap<String, String>> dataList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_current, container, false);

        // Hashmap for ListView
        dataList = new ArrayList<HashMap<String, String>>();

        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("LocationValues", Context.MODE_PRIVATE);
        webConn asyncTask = new webConn(getActivity(),mPrefs.getString("City", "Toronto"), "currently");
        asyncTask.delegate = this;

        return rootView;
    }


    public void processFinish(ArrayList output){
        //this you will received result fired from async class of onPostExecute(result) method.
        dataList = output;
        getActivity().runOnUiThread(new Runnable() {
            public void run() {

                HashMap<String, String> map; //= new HashMap<String, String>();
                t1 = (TextView) getView().findViewById(R.id.feelsLike);
                t2 = (TextView) getView().findViewById(R.id.currTemp);
                t3 = (TextView) getView().findViewById(R.id.summary);
                t4 = (TextView) getView().findViewById(R.id.location);
                i1 = (ImageView) getView().findViewById(R.id.icon);
                int sizeNum = dataList.size();
                String num = Integer.toString(dataList.size());
                //put try catch later

                if (sizeNum > 0) {
                    map = dataList.get(0);
                    t1.setText("Feels like: " + map.get("apparantTemp") + "\u00b0");
                    t2.setText(map.get("temp") + "\u00b0");
                    t4.setText(map.get("city"));
                    t3.setText(map.get("summary"));
                    i1.setImageResource(iconSelect(map.get("icon")));
                }

            }
        });
    }

    private int iconSelect(String s){

        if (s.equals("clear-day")){

            return R.drawable.cleard;

        }else if (s.equals("clear-night")){

            return R.drawable.clearn;

        }else if ( s.equals("rain")){

            return R.drawable.rain;

        }else if ( s.equals("snow")){

            return R.drawable.snow;

        }else if (s.equals("sleet")){

            return R.drawable.sleet;

        }else if (s.equals("wind")){

            return R.drawable.wind;

        }else if ( s.equals("fog")){

            return R.drawable.fog;

        }else if (s.equals("cloudy")){

            return R.drawable.cloudy;

        }else if (s.equals("partly-cloudy-day")){

            return R.drawable.partlycloudyd;

        }else if (s.equals("partly-cloudy-night")){

            return R.drawable.partlycloudyn;

        }else{

            return R.drawable.cloud;

        }

    }





}

