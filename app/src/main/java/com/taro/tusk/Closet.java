package com.taro.tusk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.taro.tusk.closet.type.ArticleType;
import com.taro.tusk.closet.OpenCloset;

public class Closet extends ActionBarActivity {

    ArticleType currentArticle;
    //ArticleType article = OpenCloset.tShirt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_closet, menu);
        return true;
    }

    public void toDifferentActivity (View v){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void changeEnabled (View v){
    ArticleType current = findIDOfArticle(v);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if(current.getIsEnabled()){
            current.setIsEnabled(false);
        }else{
            current.setIsEnabled(true);
        }
        CharSequence text = current + "isEnabled" + Boolean.toString(current.getIsEnabled());
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void changeNotified (View v){
        ArticleType current = findIDOfArticle(v);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        if(current.getIsNotified()){
            current.setIsNotified(false);
        }else{
            current.setIsNotified(true);
        }

        CharSequence text = current + "isNotified" + Boolean.toString(current.getIsNotified());
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void tempMethod (View v){
        ArticleType current = findIDOfArticle(v);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "isNotified" + Boolean.toString(current.getIsNotified())+
                "isEnabled" + Boolean.toString(current.getIsEnabled());
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public ArticleType findIDOfArticle (View v){
        ArticleType findID;
        // is this how to get id of (parent) layout??
        switch (((View) v.getParent()).getId()){
            case R.id.t_shirt: findID = OpenCloset.tShirt;
                break;
            case R.id.skirt: findID = OpenCloset.skirt;
                break;
            case R.id.tank: findID = OpenCloset.tank;
                break;
            case R.id.shorts: findID = OpenCloset.shorts;
                break;
            default: findID = OpenCloset.skirt; //change this to what?
                break;
        }
        return findID;
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
