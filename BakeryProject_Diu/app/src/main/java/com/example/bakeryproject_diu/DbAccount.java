package com.example.bakeryproject_diu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAccount extends SQLiteOpenHelper {
    public static final String NAME = "database";

    public DbAccount(Context context) {
        super(context, "database", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlUsers = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, phone INTEGER, password VARCHAR);";
        sqLiteDatabase.execSQL(sqlUsers);
    }

    public boolean addUser(String username, String phone, String password){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("phone", phone);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public boolean checkUsername(String username1){
        SQLiteDatabase db = getWritableDatabase();
        String use=username1;
        Log.d("check",String.valueOf(new String[]{username1}));
        String q = "Select * from users where username = " + username1  ;
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public Boolean checkUsernameAndPassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username =? and password =?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sqlUsers = "DROP TABLE IF EXISTS users";
        sqLiteDatabase.execSQL(sqlUsers);

        onCreate(sqLiteDatabase);
    }
}
