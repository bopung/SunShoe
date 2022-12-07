package com.example.sunshoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

;

public class DbUser {

    private DatabaseHelper db;
    private Context context;
    private SQLiteDatabase database;

    public DbUser(Context context) {
        this.context = context;
    }

    public DbUser open() throws SQLException {
        db = new DatabaseHelper(context);
        database = db.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    //using this method we can add users to user table
    public void addUser(String id, String pass) {

        //create content values to insert
        ContentValues values = new ContentValues();

        values.put(db.USER_ID, id);
        values.put(db.USER_PASS, pass);
        database.insert(db.TABLE_USERS, null, values);
    }

    //Check whether account existed or not
    public boolean cekcek() {

        String sql = "SELECT * FROM " + db.TABLE_USERS + " LIMIT 1";
        Cursor cursor = database.rawQuery(sql, null);
        Log.i("SQLITE", cursor.toString());

        if (cursor != null && cursor.getCount() > 0) {

//            for(String a : cursor.getColumnNames()){
//                Log.i("SQLITE", a);
//            }
//            cursor.moveToFirst();


//            Log.i("SQLITE", "START");
//            Log.i("SQLITE", Integer.toString(cursor.getInt(0)));

            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public void logout() {
        String sql = "DELETE FROM " + db.TABLE_USERS;
        database.execSQL(sql);
    }
}
