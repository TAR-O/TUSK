package com.taro.tusk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

/**
 * Created by Tina on 15-08-16.
 */

public class settings  extends Activity implements OnMenuItemClickListener {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.btn_click).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(settings.this, view);
                popupMenu.setOnMenuItemClickListener(settings.this);
                popupMenu.inflate(R.menu.menu_locations);
                popupMenu.show();
            }
        });
    }




    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_Toronto:

                Toast.makeText(this, "Location set as Toronto", Toast.LENGTH_SHORT).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","Toronto");
                setResult(RESULT_OK,returnIntent);
                finish();

                return true;
            case R.id.item_Hamilton:
                Toast.makeText(this, "Location set as Hamilton", Toast.LENGTH_SHORT).show();

                Intent returnIntent2 = new Intent();
                returnIntent2.putExtra("result","Hamilton");
                setResult(RESULT_OK,returnIntent2);
                finish();
                return true;
            case R.id.item_Waterloo:
                Toast.makeText(this, "Location set as Waterloo", Toast.LENGTH_SHORT).show();
                Intent returnIntent3 = new Intent();
                returnIntent3.putExtra("result","Waterloo");
                setResult(RESULT_OK,returnIntent3);
                finish();
                return true;
        }
        return true;
    }

    public void toDifferentActivity (View v){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
