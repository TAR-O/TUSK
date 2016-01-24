
package com.taro.tusk.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.taro.tusk.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

// In this case, the fragment displays simple text based on the page
public class Weekly_Weather extends Fragment implements AsyncResponse {

    private String locResult;
    private Menu mymenu;

    ArrayList<HashMap<String, String>> dataList;
    String [] images =  {"clear-day","clear-night","rain","snow","sleet","wind","fog","cloudy","partly-cloudy-day","partly-cloudy-night"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_weekly, container, false);
        // Hashmap for ListView
        dataList = new ArrayList<HashMap<String, String>>();
        SharedPreferences mPrefs = this.getActivity().getSharedPreferences("LocationValues", Context.MODE_PRIVATE);
        webConn asyncTask = new webConn(getActivity(),mPrefs.getString("City", "Toronto"), "weekly");
        asyncTask.delegate = this;

        return rootView;
    }

    public void processFinish(ArrayList output){
        //this you will received result fired from async class of onPostExecute(result) method.
        dataList = output;
        getActivity().runOnUiThread(new Runnable() {
            public void run() {

                HashMap<String, String> map;

                int sizeNum = dataList.size();
                dataList = sortWeekDay(dataList);

                if (sizeNum > 0) {

                    for(int i =0;i<dataList.size();i++){
                        map = dataList.get(i);
                        TextView text0 = (TextView) getView().findViewById(getResources().getIdentifier("text"+i+"0",
                                "id", getActivity().getPackageName()));
                        text0.setText(weekDay(map.get("dateW")));
                        TextView text1 = (TextView) getView().findViewById(getResources().getIdentifier("text"+i+"1",
                                "id", getActivity().getPackageName()));
                        text1.setText(map.get("maxTempW"));
                        TextView text2 = (TextView) getView().findViewById(getResources().getIdentifier("text"+i+"2",
                                "id",  getActivity().getPackageName()));
                        text2.setText(map.get("minTempW"));
                        TextView text3 = (TextView) getView().findViewById(getResources().getIdentifier("text"+i+"3",
                                "id",  getActivity().getPackageName()));
                        text3.setText(map.get("summaryW"));
                    }
                }
            }
        });
    }

    public String weekDay (String date){

        String day = date.substring(8,10);
        String month= date.substring(5, 7);
        String year = date.substring(0, 4);
        String input_date= day + "/" + month + "/" + year;

        try{
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date MyDate = newDateFormat.parse(input_date);
            newDateFormat.applyPattern("EEEE d");
            input_date = newDateFormat.format(MyDate);

        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return input_date;
    }


    private int getNumDate(HashMap<String, String> tempMap){

        String dateReo = tempMap.get("dateW");
        String aDate = dateReo.substring(8,10);
        int newDate = Integer.parseInt(aDate);

        return newDate;
    }

    private ArrayList sortWeekDay(ArrayList jk){
        ArrayList<HashMap<String, String>> a;
        a = jk;
        HashMap<String, String> temp;
        for (int i = 1; i < a.size(); i++) {

            for(int j = i ; j > 0 ; j--){

                if(getNumDate(a.get(j)) < getNumDate(a.get(j-1))){
                    temp = a.get(j);
                    a.set(j,a.get(j-1));
                    a.set(j-1,temp);
                }
            }
        }
        return a;
    }


}