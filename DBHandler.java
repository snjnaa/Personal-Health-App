package com.example.health_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table waterIntake (date varChar(20),level varChar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String date, String intake) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("level", intake);
        db.insert("waterIntake", null, values);
        db.close();
    }

    public String display() {
        String resData = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from waterIntake", null);
        while (cursor.moveToNext()) {
            String d = cursor.getString(0);
            String wi = cursor.getString(1);
            resData += d+ "  ->  " +wi+ "\n ------------- \n";
        }
        db.close();
        return resData;
    }

    public void delete(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("waterIntake","date=?", new String[] {date});
    }
}
