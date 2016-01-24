package com.taro.tusk;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.taro.tusk.closet.ClosetSQLiteHelper;
import com.taro.tusk.closet.ClothingArticle;

public class Closet extends ActionBarActivity {
    ClosetSQLiteHelper db = new ClosetSQLiteHelper(this);

    /*EditText article;
    EditText articletype;
    ClothingArticle clothingA;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        db.onUpgrade(db.getWritableDatabase(),2,3);

        db.addArticle(new ClothingArticle("t-shirt","top",1,0));
        db.addArticle(new ClothingArticle("tank","top",1,0));
        db.addArticle(new ClothingArticle("shorts","bottom",1,0));
        db.addArticle(new ClothingArticle("skirt","bottom",0,0));

    }

    /*public void addClothingArticle(View v){
        clothingA = new ClothingArticle(article.getText().toString(),
                articletype.getText().toString(),1, 1);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_closet, menu);
        return true;
    }

    public void toDifferentActivity (View v){
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    public void changeEnabled (View v){
        ClothingArticle current = findClothingArticle(v);

        if(current.getIsEnabled()==1){
            current.setIsEnabled(0);
        }else{
            current.setIsEnabled(1);
        }

        db.updateEnabledNotified(current);
    }

    public void changeNotified (View v){
        ClothingArticle current = findClothingArticle(v);

        if(current.getIsNotified()==1){
            current.setIsNotified(0);
        }else{
            current.setIsNotified(1);
        }

        db.updateEnabledNotified(current);
    }

    public ClothingArticle findClothingArticle (View v){

        int idOfCA;

        // is this how to get id of (parent) layout??
        switch (((View) v.getParent()).getId()){
            case R.id.t_shirt: idOfCA = 1;
                break;
            case R.id.skirt: idOfCA = 4;
                break;
            case R.id.tank: idOfCA = 2;
                break;
            case R.id.shorts: idOfCA = 3;
                break;
            /*case R.id.sweater: findID = OpenCloset.sweater;
                break;
            case R.id.raincoat: findID = OpenCloset.raincoat;
                break;
            case R.id.boots: findID = OpenCloset.boots;
                break;
            case R.id.sandals: findID = OpenCloset.sandals;
                break;
            case R.id.sneakers: findID = OpenCloset.sneakers;
                break;
            case R.id.umbrella: findID = OpenCloset.umbrella;
                break;*/
            default: idOfCA = 1; //change this to what?
                break;
        }

        return db.getClothingArticle(idOfCA);
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
