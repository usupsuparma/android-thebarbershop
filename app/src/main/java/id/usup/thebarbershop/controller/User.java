package id.usup.thebarbershop.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import id.usup.thebarbershop.model.DbHelperUser;
import id.usup.thebarbershop.model.UserContract;

/**
 * Created by Acer on 12/14/2017.
 */

public class User {
    private static final String TAG="User: ";
    private String name;
    private String gender;
    private String email;
    private String noHp;
    private String password;
    Context context;
    DbHelperUser mDbHelper = new DbHelperUser();

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", noHp='" + noHp + '\'' +
                ", password='" + password + '\'' +
                ", context=" + context +
                ", mDbHelper=" + mDbHelper +
                '}';
    }

    public User(String name, String gender, String noHp, String email, String password) {

        this.name = name;
        this.gender = gender;
        this.email = email;
        this.noHp = noHp;
        this.password = password;
        Log.d(TAG,"Success send data");
    }

    public boolean insertDatabase(){

        String name     = this.name;
        String gender   = this.gender;
        String noHP     = this.noHp;
        String email    = this.email;
        String password = this.password;

        boolean result = mDbHelper.insertData(name,gender,noHP,email,password);

        if (result){
            return true;
        }else {
            return false;
        }


    }



}
