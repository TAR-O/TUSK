package com.taro.tusk.Tools;

/**
 * Created by Tina on 15-08-26.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.taro.tusk.R;
import java.util.List;

public class setLocation extends Activity {
    /** Called when the activity is first created. */

    private String setProv;
    private String setCoun;
    private String setCity;
    List<String> listOfCoun;
    List<String> listOfProv;
    List<String> listOfCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setlocation);
        Button btn = (Button) findViewById(R.id.btn);
        registerForContextMenu(btn);

        Button btn2 = (Button) findViewById(R.id.btn2);
        registerForContextMenu(btn2);

        Button btn3 = (Button) findViewById(R.id.btn3);
        registerForContextMenu(btn3);

        Button cancelButton = (Button) findViewById(R.id.btn5);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveButton = (Button) findViewById(R.id.btn4);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setCity != null && !setCity.isEmpty()) {
                    SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("LocationValues",MODE_PRIVATE);
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("Country", setCoun);
                    editor.putString("Prov", setProv);
                    editor.putString("City", setCity);
                    editor.commit();
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Missing Values!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.btn:
                menu.setHeaderTitle("Countries");
                listOfCoun = locationGet.getCountry();
                for (int i = 0; i< listOfCoun.size() ;i++){
                    menu.add(0, v.getId(), 0, listOfCoun.get(i));
                }
                break;
            case R.id.btn2:
                menu.setHeaderTitle("Provinces/Territories");
                listOfProv = locationGet.getProvinces(setCoun);
                for (int i = 0; i< listOfProv.size() ;i++){
                    menu.add(0, v.getId(), 0, listOfProv.get(i));
                }
                break;
            case R.id.btn3:
                menu.setHeaderTitle("Cities");
                listOfCity = locationGet.getCity(setProv);
                for (int i = 0; i< listOfCity.size() ;i++){
                    menu.add(0, v.getId(), 0, listOfCity.get(i));
                }
                break;
        }

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        for(int i = 0; i<listOfCoun.size(); i++){
            if (item.getTitle() == listOfCoun.get(i)){
                setCoun = listOfCoun.get(i);
                setText(listOfCoun.get(i),"tcoun");
                return true;
            }
        }
        for(int i = 0; i<listOfProv.size(); i++){
            if (item.getTitle() == listOfProv.get(i)){
                setProv = listOfProv.get(i);
                setText(listOfProv.get(i),"tprov");
                return true;
            }
        }
        for(int i = 0; i<listOfCity.size(); i++){
            if (item.getTitle() == listOfCity.get(i)){
                setCity = listOfCity.get(i);
                setText(listOfCity.get(i),"tcity");
                return true;
            }
        }
        return true;
    }

    public void setText(String value, String idValue){
        TextView txt = (TextView)findViewById(getResources().getIdentifier(idValue, "id", this.getPackageName()));
        txt.setText(value);
    }

}