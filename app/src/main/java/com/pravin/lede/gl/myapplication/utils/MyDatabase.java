package com.pravin.lede.gl.myapplication.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Location_db";

    public static final String TABLE_NAME = "location";
    public static final String COL_1 = "id";    // 0
    public static final String COL_2 = "date";  // 1
    public static final String COL_3 = "lat";      //2
    public static final String COL_4 = "lng";   //3


    public MyDatabase(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    /**
     * called when database created
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table location (id integer primary key, date text, lat text, lng text)");

    }

    /**
     * called when database changed/upgraded
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS location");
        onCreate(db);

    }

    public boolean insertLocation(double latitude, double longitude, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, String.valueOf(latitude));
        contentValues.put(COL_4, String.valueOf(longitude));

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if(result==-1){
            return false;
        }else {
            return true;
        }
    }

    public ArrayList<LatLng> getAllLocation(){
        ArrayList<LatLng> getAllLocation = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from "+ TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                LatLng latLng = new LatLng(Double.parseDouble(cursor.getString(2)), Double.parseDouble(cursor.getString(3)));
                getAllLocation.add(latLng);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return getAllLocation;
    }

}
