package edu.qc.seclass.glm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;
    Cursor cursor;

    public MySQLiteOpenHelper(Context ct){
        super(ct,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table store (type text, item text);");
        db.execSQL("create table myList (name text);");
        db.execSQL("create table info (list text, type text, item text, quantity text, status text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void initialDB(){
        SQLiteDatabase db = this.getWritableDatabase();

        cursor = db.rawQuery("SELECT type FROM store",null);

        if(cursor.moveToFirst() == false){
            ContentValues values = new ContentValues();
            values.put("type", "fruit");
            values.put("item", "apple");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "fruit");
            values.put("item", "banana");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "fruit");
            values.put("item", "orange");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "fruit");
            values.put("item", "peach");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "fruit");
            values.put("item", "pineapple");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "drink");
            values.put("item", "water");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "drink");
            values.put("item", "coffee");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "drink");
            values.put("item", "juice");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "drink");
            values.put("item", "tea");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "drink");
            values.put("item", "milk");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "vegetable");
            values.put("item", "spinach");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "vegetable");
            values.put("item", "carrot");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "vegetable");
            values.put("item", "broccoli");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "vegetable");
            values.put("item", "tomato");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "vegetable");
            values.put("item", "potato");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "meat&seafood");
            values.put("item", "beef");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "meat&seafood");
            values.put("item", "chicken");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "meat&seafood");
            values.put("item", "pork");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "meat&seafood");
            values.put("item", "shrimp");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "meat&seafood");
            values.put("item", "oyster");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "snack");
            values.put("item", "chips");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "snack");
            values.put("item", "jerky");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "snack");
            values.put("item", "nuts");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "snack");
            values.put("item", "candy");
            db.insert("store",null,values);
            values.clear();
            values.put("type", "snack");
            values.put("item", "cheese");
            db.insert("store",null,values);
            values.clear();
        }
    }
}