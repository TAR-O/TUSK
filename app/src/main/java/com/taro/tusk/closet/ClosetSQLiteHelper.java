package com.taro.tusk.closet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Database for all clothing articles
 *
 * Created by Michelle Xiong on 11/08/2015.
 */
public class ClosetSQLiteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "ClosetClothingArticleDB";
    private static final String TABLE_NAME = "ClosetClothingArticle";
    private static final String ARTICLE_ID = "ID";
    private static final String ARTICLE_NAME = "Article";
    private static final String ARTICLE_TYPE = "ArticleType";
    private static final String IS_ENABLED = "IsEnabled";
    private static final String IS_NOTIFIED = "IsNotified";
    private static final String [] COLUMNS = {ARTICLE_ID,ARTICLE_NAME, ARTICLE_TYPE,IS_ENABLED,IS_NOTIFIED};


    public ClosetSQLiteHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //SQLiteDatabase db = getWritableDatabase(); //delete
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //String CREATE_BOOK_TABLE = "CREATE TABLE books ( " + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "title TEXT, " + "author TEXT )";
        String CREATE_ARTICLE_TABLE = "CREATE TABLE ClosetClothingArticle" + " ( "  +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "Article TEXT, " + "ArticleType TEXT, " + "IsEnabled INTEGER, "+ "IsNotified INTEGER )";
        db.execSQL(CREATE_ARTICLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //CHOOSE STRING??
        this.onCreate(db);
    }

    public void addArticle (ClothingArticle clothingArticle){
        SQLiteDatabase db = this.getWritableDatabase();

       /* int intGetIsEnabled;
        int intGetIsNotified;
        intGetIsEnabled = switchEnabled(clothingArticle.getIsEnabled());
        intGetIsNotified = switchNotified(clothingArticle.getIsNotified());
        if(clothingArticle.getIsEnabled()){
            intGetIsEnabled = 1;
        }else{
            intGetIsEnabled = 0;
        }
        if(clothingArticle.getIsNotified()){
            intGetIsNotified = 1;
        }else{
            intGetIsNotified = 0;
        }*/

        ContentValues values = new ContentValues();
        values.put(ARTICLE_NAME,clothingArticle.getArticle());
        values.put(ARTICLE_TYPE,clothingArticle.getArticleType());
        values.put(IS_ENABLED,clothingArticle.getIsEnabled());
        values.put(IS_NOTIFIED,clothingArticle.getIsNotified());

        //long isInserted = db.insert(TABLE_NAME,null,values);
        db.insert(TABLE_NAME,null,values);
        /*
        if(isInserted == -1){
            return false;}
        else{
            return true;}*/
        db.close();
    }

    public ClothingArticle getClothingArticle (int id){ //id?
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, COLUMNS, ARTICLE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        ClothingArticle clothingArticle = new ClothingArticle
                (Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)));
        return clothingArticle;
    }

    public List<ClothingArticle> getAllClothingArticle(){ //needed?
        List<ClothingArticle> clothingArticleList = new ArrayList<ClothingArticle>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ClothingArticle clothingArticle = new ClothingArticle(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)));

                clothingArticleList.add(clothingArticle);
            }while(cursor.moveToNext());
        }
        return clothingArticleList;
    }
    public List<ClothingArticle> getAllClothingAricleEnabled( int zeroOrOne){ //try and catch statement
        List<ClothingArticle> clothingArticleEnabledList = new ArrayList<ClothingArticle>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + IS_ENABLED + "=" + zeroOrOne;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ClothingArticle clothingArticle = new ClothingArticle(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)));

                clothingArticleEnabledList.add(clothingArticle);
            }while(cursor.moveToNext());
        }
        return clothingArticleEnabledList;
    }
    public List<ClothingArticle> getAllClothingArticleNotified(int zeroOrOne){ //needed?
        List<ClothingArticle> clothingArticleEnabledList = new ArrayList<ClothingArticle>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + IS_NOTIFIED + "=" + zeroOrOne;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ClothingArticle clothingArticle = new ClothingArticle(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)));

                clothingArticleEnabledList.add(clothingArticle);
            }while(cursor.moveToNext());
        }
        return clothingArticleEnabledList;
    }

    public int getClothingArticleCount (){//needed?
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateEnabledNotified(ClothingArticle clothingArticle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IS_ENABLED,clothingArticle.getIsEnabled());
        contentValues.put(IS_NOTIFIED,clothingArticle.getIsNotified());

        return db.update(TABLE_NAME, contentValues, ARTICLE_ID + " = ?",
                new String[]{String.valueOf(clothingArticle.getID())});

    }
/*
    public int updateClothingActicleNotified(ClothingArticle clothingArticle){

    }*/

    //no delete method

}
