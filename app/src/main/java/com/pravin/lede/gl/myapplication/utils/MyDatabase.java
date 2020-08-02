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

        db.execSQL("create table location (id integer primary key, lat text, lng text)");

    }

    /**
     * called when database changed/upgraded
     *
     * @param db         SQLiteDatabase object provides number of database operations like insert, update, delete
     * @param oldVersion old version of database/table
     * @param newVersion new version of database/table
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS location");
        onCreate(db);
    }

    /**
     * Deletes all location from location table
     */
    public void DeleteLocations() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }

    /**
     * insert location related data in location table
     *
     * @param latitude      latitude value need to insert
     * @param longitude     longitude value need to insert
     * @param date          date value need to insert
     * @return boolean value : status of insert operaration
     */
    public boolean insertLocation(double latitude, double longitude, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        // ContentValues used temporarily to store columns and their respective data
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, String.valueOf(latitude));
        contentValues.put(COL_4, String.valueOf(longitude));

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        //"insert into table_name(id, name, surname, city) values(1, 'pravin', 'lede', 'bhandara')"

        // result returns -1 if operation of insert failed else will return any value.
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * get all data from location table using rawQuery function
     *
     * @return list of locations
     */
    public ArrayList<LatLng> getAllLocation() {
        ArrayList<LatLng> getAllLocation = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                // get value of 2nd columns using cursor.getString(2);
                String latitude = cursor.getString(2);

                // get value of 3nd columns using cursor.getString(3);
                String longitude = cursor.getString(3);

                LatLng latLng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                getAllLocation.add(latLng);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return getAllLocation;
    }

}
