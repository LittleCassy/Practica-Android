package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.data.ProfileManager;

public class DDBBUser extends dbHelper {
    private Context context;

    public DDBBUser(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public long insertUser(String username, String pass, String email){
        long id = -1;
        dbHelper myDBHelp = new dbHelper(context);
        SQLiteDatabase DB = myDBHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nombre", username);
        values.put("password", pass);
        values.put("correo_electronico", email);
        values.put("puntos", 0);

        id = DB.insert(TABLE_CONTACTOS, null, values);

        return id;
    }

    public User getUser (String email, String pass){
        dbHelper myDBHelp = new dbHelper(context);
        SQLiteDatabase DB = myDBHelp.getWritableDatabase();
        try{
            Cursor myCursor = DB.rawQuery("SELECT nombre, correo_electronico, puntos FROM " + TABLE_CONTACTOS + " WHERE correo_electronico = " +  "'" + email + "'" +" AND password = " + "'" +pass +  "'", null);
            myCursor.moveToFirst();

            if(myCursor.getCount()>0){
                User u = new User(myCursor.getString(0), myCursor.getString(1), myCursor.getInt(2));
                myCursor.close();
                return u;
            }else{
                myCursor.close();
                return new User("","", 0);
            }
        }catch (Exception e){
            System.out.println("Error");
            return new User("","", 0);
        }
    }

    public boolean updatePoints(int newPoints, Context ct){
        try{
            dbHelper myDBHelp = new dbHelper(ct);
            SQLiteDatabase DB = myDBHelp.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("puntos", newPoints);
            DB.update(TABLE_CONTACTOS, values, "correo_electronico = ?", new String[] {ProfileManager.getEmail()});
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void deleteRow(){
        dbHelper myDBHelp = new dbHelper(context);
        SQLiteDatabase DB = myDBHelp.getWritableDatabase();

        DB.delete(TABLE_CONTACTOS, "correo_electronico = ?", new String[] {ProfileManager.getEmail()});
    }
}
